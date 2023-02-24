import java.util.Map;
import java.util.stream.Collectors;

class ShowInMap<K,V> {
    Map<K,V> map;

    public ShowInMap(Map<K, V> map) {this.map = map;}

    void ShowInMap() {
        System.out.println(map.entrySet().stream()
                .map(entry -> "{" + entry.getKey() + ", " + entry.getValue() + "}").collect(Collectors.toList()));
    }
}
