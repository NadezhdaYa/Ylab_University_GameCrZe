import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameTXT extends LocalStorage implements Repository<Player> {

    private final File file;

    private final Convert convert;

  public GameTXT() {
       this.file = new File("GameStory.txt");
        this.convert = new Convert(" : ", "\n");
   }

    @Override
    public Player get(String name) {
        Player player = null;
        try {
            String stringFile = readFile(file);
            var map = convert.stringToMap(stringFile);
            String point = map.get(name);
            if (point != null && point.matches("\\d+")) {
                player = new Person(name, Integer.parseInt(point));
            }
            else {
                player = new Person(name);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public void saveAll(List<Player> players) {
        try {
            String stringFile = readFile(file);
            var map = convert.stringToMap(stringFile);
            for (var player: players)
                map.put(player.getName(), String.valueOf(player.getPoint()));
            map = sorted(map);
            String value = convert.mapToString(map);
            writeFile(file, value);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> sorted(Map<String, String> map) {
        Comparator<String> comparator = (x, y) -> {
            Integer ix = Integer.parseInt(map.get(x));
            Integer iy = Integer.parseInt(map.get(y));
            int compare = iy.compareTo(ix);
            if (compare == 0) {
                compare = x.compareTo(y);
            }
            return compare;
        };
        Map<String, String> treeMap = new TreeMap<>(comparator);
        treeMap.putAll(map);
        return treeMap;
    }
}