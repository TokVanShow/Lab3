package Parsers;

import ResponsibilityChain.FileHandler;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import reactors.ReactorType;
import reactors.ReactorTypeList;

public class XMLFileHandler implements FileHandler {

    private FileHandler nextHandler;

    @Override
    public List<ReactorType> importFromFile(File file) {
        List<ReactorType> reactorTypes = new ArrayList<>();

        if (file.getName().endsWith(".xml")) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(ReactorTypeList.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ReactorTypeList reactorTypeList = (ReactorTypeList) jaxbUnmarshaller.unmarshal(file);
                reactorTypes = reactorTypeList.getMethods();
            } catch (JAXBException e) {
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
