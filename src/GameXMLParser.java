import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GameXMLParser {

    private static final XMLOutputFactory OUTPUT_FACTORY = XMLOutputFactory.newInstance();

    private static final String END = "\n";

    private static final String TAB = "\t";

    private static final String DIRECTORY = "src/";

    private static final String FORMAT_FILE = ".xml";

    public GameXMLParser() {
    }

    public void write(List<Player> players, Player winPlayer, List<Step> steps) {
        try {
            File file = createFile(players);
            XMLStreamWriter writer = OUTPUT_FACTORY.createXMLStreamWriter(new FileOutputStream(file), "windows-1251");
            //Заголовок документа
            writer.writeStartDocument("windows-1251", "1.0");
            writer.writeDTD(END);

            //Открываем заголовок GamePlay
            writer.writeStartElement("GamePlay");
            writer.writeDTD(END);

            //Добавляем пустые теги Player
            for (Player player : players) {
                addPlayer(writer, player);
            }

            //Открываем заголовок Game
            writer.writeDTD(TAB);
            writer.writeStartElement("Game");
            writer.writeDTD(END);

            //Добавляем элементы Step
            for (Step step : steps) {
                writer.writeDTD(TAB);
                addStep(writer, step);
            }

            //Закрываем заголовок Game
            writer.writeDTD(TAB);
            writer.writeEndElement();
            writer.writeDTD(END);

            //Открываем заголовок GameResult
            writer.writeDTD(TAB);
            writer.writeStartElement("GameResult");
            writer.writeDTD(END);

            //Добавляем пустой тег выигравшего Player
            writer.writeDTD(TAB);
            addPlayer(writer, winPlayer);

            //Закрываем заголовок GameResult
            writer.writeDTD(TAB);
            writer.writeEndElement();
            writer.writeDTD(END);

            //Закрываем заголовок GamePlay
            writer.writeEndElement();
            writer.writeDTD(END);
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        }
        catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addPlayer(XMLStreamWriter writer, Player player) throws XMLStreamException {
        writer.writeDTD(TAB);
        writer.writeEmptyElement("Player");
        String id = String.valueOf(player.getId());
        String name = player.getName();
        String symbol = player.getSymbol().name();
        writer.writeAttribute("id", id);
        writer.writeAttribute("name", name);
        writer.writeAttribute("symbol", symbol);
        writer.writeDTD(END);
    }

    private void addStep(XMLStreamWriter writer, Step step) throws XMLStreamException {
        writer.writeDTD(TAB);
        writer.writeStartElement("Step");
        String num = String.valueOf(step.getNum());
        String playerId = String.valueOf(step.getPlayer().getId());
        String move = String.valueOf(step.getRow() * 3 + step.getCol() + 1);
        writer.writeAttribute("num", num);
        writer.writeAttribute("playerId", playerId);
        writer.writeCharacters(move);
        writer.writeEndElement();
        writer.writeDTD(END);
    }

    private File createFile(List<Player> players) throws IOException {
        String fullName = players.get(0).getName() + "and" + players.get(1).getName() + "_";
        FileFilter filter = (pathname) -> pathname.getName().matches(fullName + "\\d*\\.xml");
        var files = new File(DIRECTORY).listFiles(filter);
        File newFile;
        if (files != null && files.length > 0) {
            Comparator<File> comparator = (x, y) -> {
                int numFile1 = Integer.parseInt(x.getName().replace(fullName, "").replace(FORMAT_FILE, ""));
                int numFile2 = Integer.parseInt(y.getName().replace(fullName, "").replace(FORMAT_FILE, ""));
                return Integer.compare(numFile2, numFile1);
            };
            Arrays.sort(files, comparator);
            String maxNumLastGame = files[0].getName().replace(fullName, "").replace(FORMAT_FILE, "");
            int num = Integer.parseInt(maxNumLastGame) + 1;
            newFile = new File(DIRECTORY + fullName + num + ".xml");
        }
        else {
            newFile = new File(DIRECTORY + fullName + "1.xml");
        }
        //newFile.createNewFile();
        return newFile;
    }
}