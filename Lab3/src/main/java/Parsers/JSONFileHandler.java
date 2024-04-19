package Parsers;

import ResponsibilityChain.FileHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import reactors.ReactorType;

public class JSONFileHandler implements FileHandler {

    private FileHandler nextHandler;
    
    @Override
    public List<ReactorType> importFromFile(File file) {
    List<ReactorType> reactorTypes = new ArrayList<>();

    if (file.getName().endsWith(".json")) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(file);

            JsonNode reactorTypeNode = rootNode.path("ReactorType");
            Iterator<Map.Entry<String, JsonNode>> fields = reactorTypeNode.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String reactorName = entry.getKey();
                JsonNode reactorData = entry.getValue();

                String classType = reactorData.get("class").asText();
                double burnup = reactorData.get("burnup").asDouble();
                double kpd = reactorData.get("kpd").asDouble();
                double enrichment = reactorData.get("enrichment").asDouble();
                int thermalCapacity = reactorData.get("thermal_capacity").asInt();
                int electricalCapacity = reactorData.get("electrical_capacity").asInt();
                int lifeTime = reactorData.get("life_time").asInt();
                double firstLoad = reactorData.get("first_load").asDouble();

                ReactorType reactor = new ReactorType(reactorName, classType, burnup, kpd, enrichment, thermalCapacity, electricalCapacity, lifeTime, firstLoad);
                reactor.setSource(file.getName());
                reactorTypes.add(reactor);
            }
                
            } catch (IOException e) {
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