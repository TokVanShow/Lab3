package reactors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private Map<String, List<ReactorType>> storageMap;

    public Storage() {
        this.storageMap = new HashMap<>();
    }

    public void addReactorTypes(String fileType, List<ReactorType> reactorTypes) {
        storageMap.put(fileType, reactorTypes);
    }

    public List<ReactorType> getReactorTypes(String fileType) {
        return storageMap.get(fileType);
    }
}
