
package ResponsibilityChain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import reactors.ReactorType;

public abstract class AbstractFileHandler implements FileHandler {
    protected FileHandler nextHandler;

    @Override
    public void setNextHandler(FileHandler handler) {
        this.nextHandler = handler;
    }

    protected List<ReactorType> passToNext(File file) {
        if (nextHandler != null) {
            return nextHandler.importFromFile(file);
        }
        return new ArrayList<>();
    }
}