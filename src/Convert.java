import java.util.HashMap;
import java.util.Map;

public class Convert {

    private final String separator;
    private final String delimiter;

   public Convert(String separator, String delimiter) {
        this.separator = separator;
        this.delimiter = delimiter;
    }

    public Map<String, String> stringToMap(String string) {
        Map<String, String> map = new HashMap<>();
        var strings = string.split(delimiter);
        for (var str: strings) {
            var kayValue = str.split(separator);
            if (kayValue.length == 2)
                map.put(kayValue[0], kayValue[1]);
        }
        return map;
    }

    public String mapToString(Map<String, ?> map) {
        var entrySet = map.entrySet();
        StringBuilder builder = new StringBuilder();
        for (var it = entrySet.iterator(); it.hasNext();) {
            var entry = it.next();
            builder.append(entry.getKey())
                    .append(separator)
                    .append(entry.getValue());
            if (it.hasNext())
                builder.append(delimiter);
        }
        return builder.toString();
    }

}