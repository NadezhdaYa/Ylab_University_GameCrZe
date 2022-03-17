import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLSimulationGameParser{
    private static final XMLInputFactory INPUT_FACTORY = XMLInputFactory.newInstance();
    private File file;

  public XMLSimulationGameParser(File file) {
        Checker.checkFile(file);
        this.file = file;
    }

    public SimulationGame read() throws IOException {
        Player player;
        List<Player> players = new ArrayList<>();
        List<Step> steps = new ArrayList<>();
        try {
           XMLEventReader reader = INPUT_FACTORY.createXMLEventReader(new FileInputStream(file.getAbsolutePath()), "windows-1251");
            while (reader.hasNext()) {
                  XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        // получаем Player
                        case "Player":
                            player = getPlayer(startElement);
                            players.add(player);
                            break;
                        // получаем Step
                        case "Step":
                            String numAtt = startElement
                                    .getAttributeByName(new QName("num"))
                                    .getValue();
                            String playerIdAtt = startElement
                                    .getAttributeByName(new QName("playerId"))
                                    .getValue();
                            xmlEvent = reader.nextEvent();
                            String move = xmlEvent.asCharacters().toString();

                            //Добавляем победителя в конец списка игроков
                        case "GameResult":
                            xmlEvent = reader.nextEvent();
                            if (xmlEvent.isStartElement()) {
                                startElement = xmlEvent.asStartElement();
                                player = getPlayer(startElement);
                                if (player.getName() != null)
                                    players.add(player);
                            }
                            break;
                    }
                } else if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                }
            }
            reader.close();
        } catch (XMLStreamException exc) {
            exc.printStackTrace();
        }

          return new SimulationGame(players, steps);
    }

    private Player getPlayer(StartElement startElement) {
        Player player = new Simulation();
        Attribute idAtt = startElement.getAttributeByName(new QName("id"));
        Attribute nameAtt = startElement.getAttributeByName(new QName("name"));
        Attribute symbolAtt = startElement.getAttributeByName(new QName("symbol"));
        if (idAtt != null) {
            player.setId(Integer.parseInt(idAtt.getValue()));
        }
        if (nameAtt != null)
            player.setName(nameAtt.getValue());
        if (symbolAtt != null) {
            if (symbolAtt.getValue().matches("[XxХх]"))
                player.setSymbol(Symbol.X);
            else if (symbolAtt.getValue().matches("[OoОо0]"))
                player.setSymbol(Symbol.O);
        }
        return player;
    }
}
