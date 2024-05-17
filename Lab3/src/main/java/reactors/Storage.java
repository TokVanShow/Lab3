package reactors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private final Map<String, ReactorType> storageMap;
    private Map<Long, Double> burnupValues = new HashMap<>();
    private Map<Long, Double> firstLoadValues = new HashMap<>();

    public Storage() {
        this.storageMap = new HashMap<>();
    }

    public void addReactorTypes(String fileType, List<ReactorType> reactorTypes) {
        for (ReactorType reactorType : reactorTypes) {
            reactorType.setClassType(reactorType.getClassType().trim());
            storageMap.put(reactorType.getClassType(), reactorType);
            System.out.println("Storage: " + storageMap.toString());
        }
    }

    public ReactorType getReactorType(String classType) {
        classType = classType.trim();

        ReactorType reactorType = storageMap.get(classType);
        if (reactorType != null) {
            System.out.println("Retrieved reactor type for class type: " + classType);
        } else {
            System.out.println("No reactor type found for class type: " + classType);
        }
        return reactorType;
    }

    public Map<String, ReactorType> getAllReactorTypes() {
        return new HashMap<>(storageMap);
    }

    // Сохранение временных значений
    public void saveTemporaryValues(Long unitId, double burnup, double firstLoad) {
        burnupValues.put(unitId, burnup);
        firstLoadValues.put(unitId, firstLoad);
    }

    // Получение временных значений
    public double getTemporaryBurnup(Long unitId) {
        return burnupValues.getOrDefault(unitId, 0.0);
    }

    public double getTemporaryFirstLoad(Long unitId) {
        return firstLoadValues.getOrDefault(unitId, 0.0);
    }
}
