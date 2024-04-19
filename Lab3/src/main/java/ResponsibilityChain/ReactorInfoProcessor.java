package ResponsibilityChain;

import Gui.GUI_Form;
import Parsers.JSONFileHandler;
import Parsers.XMLFileHandler;
import Parsers.YAMLFileHandler;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import reactors.ReactorType;

public class ReactorInfoProcessor {

    private final FileHandler importerChain;
    private final GUI_Form gui;

    public ReactorInfoProcessor(GUI_Form gui) {
        FileHandler jsonImporter = new JSONFileHandler();
        FileHandler xmlImporter = new XMLFileHandler();
        FileHandler yamlImporter = new YAMLFileHandler();

        jsonImporter.setNextHandler(xmlImporter);
        xmlImporter.setNextHandler(yamlImporter);

        this.importerChain = jsonImporter;
        this.gui = gui;
    }

    public void importReactorInfoFromFile(File file) {
        List<ReactorType> reactorTypes = importerChain.importFromFile(file);
        System.out.println("reactorTypes  " + reactorTypes);
        if (reactorTypes != null && !reactorTypes.isEmpty()) {
            gui.displayReactorInfoInTree(reactorTypes);
        } else {
            System.out.println("No reactor information found in the file.");
        }
    }

    public DefaultMutableTreeNode toTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Reactor Info");
        return rootNode;
    }
}
