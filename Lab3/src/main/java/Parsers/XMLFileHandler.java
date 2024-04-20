package parsers;

import ResponsibilityChain.AbstractFileHandler;
import reactors.ReactorType;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import ResponsibilityChain.FileHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.DocumentException;

public class XMLFileHandler extends AbstractFileHandler {

    private FileHandler nextHandler;

    @Override
    public List<ReactorType> importFromFile(File file) {
        List<ReactorType> reactorTypes = new ArrayList<>();

        if (file.getName().endsWith(".xml")) {
            try {
                SAXReader reader = new SAXReader();
                Document document = reader.read(file);
                Element root = document.getRootElement();

                // Получаем элемент <Static>
                Element staticElement = root.element("methods").element("Static");

                // Получаем все элементы <function>
                List<Element> functionElements = staticElement.elements("function");

                for (Element functionElement : functionElements) {
                    // Парсим каждый элемент <function>
                    ReactorType reactorType = new ReactorType();
                    reactorType.setName(functionElement.attributeValue("name"));
                    reactorType.setClassType(functionElement.elementText("class"));
                    reactorType.setBurnup(Double.parseDouble(functionElement.elementText("burnup")));
                    reactorType.setKpd(Double.parseDouble(functionElement.elementText("kpd")));
                    reactorType.setEnrichment(Double.parseDouble(functionElement.elementText("enrichment")));
                    reactorType.setThermalCapacity(Integer.parseInt(functionElement.elementText("thermal_capacity")));
                    reactorType.setElectricalCapacity(Double.parseDouble(functionElement.elementText("electrical_capacity")));
                    reactorType.setLifeTime(Integer.parseInt(functionElement.elementText("life_time")));
                    reactorType.setFirstLoad(Double.parseDouble(functionElement.elementText("first_load")));
                    reactorType.setSource(file.getName());
                    reactorTypes.add(reactorType);
                }
            } catch (NumberFormatException | DocumentException e) {
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
