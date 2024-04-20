package parsers;

import ResponsibilityChain.AbstractFileHandler;
import ResponsibilityChain.FileHandler;
import org.yaml.snakeyaml.Yaml;
import reactors.ReactorType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YAMLFileHandler extends AbstractFileHandler {

    private FileHandler nextHandler;

    @Override
    public List<ReactorType> importFromFile(File file) {
        List<ReactorType> reactorTypes = new ArrayList<>();

        if (file.getName().endsWith(".yaml") || file.getName().endsWith(".yml")) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                Yaml yaml = new Yaml();
                Map<String, Map<String, List<Map<String, Object>>>> data = yaml.load(inputStream);
                Map<String, List<Map<String, Object>>> reactorType = data.get("ReactorType");
                Map<String, Object> methodsMap = (Map<String, Object>) reactorType.get("methods");
                List<Map<String, Object>> methodList = (List<Map<String, Object>>) methodsMap.get("Static");

                for (Map<String, Object> entry : methodList) {
                    String name = (String) entry.get("name");
                    String classType = (String) entry.get("class");
                    double burnup = ((Number) entry.get("burnup")).doubleValue();
                    double kpd = ((Number) entry.get("kpd")).doubleValue();
                    double enrichment = ((Number) entry.get("enrichment")).doubleValue();
                    int thermalCapacity = ((Number) entry.get("thermal_capacity")).intValue();
                    double electricalCapacity = ((Number) entry.get("electrical_capacity")).doubleValue();
                    int lifeTime = ((Number) entry.get("life_time")).intValue();
                    double firstLoad = ((Number) entry.get("first_load")).doubleValue();

                    ReactorType reactor = new ReactorType(name, classType, burnup, kpd, enrichment, thermalCapacity, electricalCapacity, lifeTime, firstLoad);
                    reactor.setSource(file.getName());
                    reactorTypes.add(reactor);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (nextHandler != null) {
            reactorTypes = nextHandler.importFromFile(file);
        }

        return reactorTypes;
    }

    @Override
    public void setNextHandler(FileHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

