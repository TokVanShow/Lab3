package ResponsibilityChain;

import java.io.File;
import java.util.List;
import reactors.ReactorType;

public interface FileHandler {

    void setNextHandler(FileHandler handler);

    List<ReactorType> importFromFile(File file);
}
