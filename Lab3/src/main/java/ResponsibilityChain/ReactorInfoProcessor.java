package ResponsibilityChain;

import Gui.GUI_Form;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import java.util.Map;
import parsers.JSONFileHandler;
import parsers.XMLFileHandler;
import parsers.YAMLFileHandler;
import reactors.ReactorType;
import reactors.Storage;

public class ReactorInfoProcessor {

    private final FileHandler importerChain;
    private final GUI_Form gui;
    private final Storage storage;

    public ReactorInfoProcessor(GUI_Form gui, Storage storage) {
        FileHandler jsonImporter = new JSONFileHandler();
        FileHandler xmlImporter = new XMLFileHandler();
        FileHandler yamlImporter = new YAMLFileHandler();
        jsonImporter.setNextHandler(xmlImporter);
        xmlImporter.setNextHandler(yamlImporter);
        this.importerChain = jsonImporter;
        this.gui = gui;
        this.storage = storage;
    }

    public void importReactorInfoFromFile(File file) {
        List<ReactorType> reactorTypes = importerChain.importFromFile(file);
        if (reactorTypes != null && !reactorTypes.isEmpty()) {
            String fileType = getFileExtension(file);
            storage.addReactorTypes(fileType, reactorTypes);
            System.out.println("Added reactor types for file type: " + fileType);
            gui.displayReactorInfoInTree(storage.getAllReactorTypes().values());
        } else {
            System.out.println("No reactor information found in the file.");
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');
        return lastIndexOfDot == -1 ? "" : fileName.substring(lastIndexOfDot + 1);
    }

    public DefaultMutableTreeNode toTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Reactor Info");
        return rootNode;
    }
}
