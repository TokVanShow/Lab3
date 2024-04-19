package reactors;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlRootElement(name = "ReactorType") // Change the root element name
public class ReactorTypeList {
    private List<ReactorType> methods;

    public ReactorTypeList() {
        // Default constructor
    }

    @XmlElement(name = "function")
    public List<ReactorType> getMethods() {
        return methods;
    }

    public void setMethods(List<ReactorType> methods) {
        this.methods = methods;
    }
}
