package reactors;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "function")
public class ReactorType {

    private String name;
    private String classType;
    private double burnup;
    private double kpd;
    private double enrichment;
    private int thermalCapacity;
    private int electricalCapacity;
    private int lifeTime;
    private double firstLoad;

    private String source; // добавл€ем атрибут дл€ хранени€ информации о источнике данных

    public ReactorType() {
        //  онструктор по умолчанию
    }

    public ReactorType(String name, String classType, double burnup, double kpd, double enrichment, int thermalCapacity, int electricalCapacity, int lifeTime, double firstLoad) {
        this.name = name;
        this.classType = classType;
        this.burnup = burnup;
        this.kpd = kpd;
        this.enrichment = enrichment;
        this.thermalCapacity = thermalCapacity;
        this.electricalCapacity = electricalCapacity;
        this.lifeTime = lifeTime;
        this.firstLoad = firstLoad;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "class")
    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @XmlElement(name = "burnup")
    public double getBurnup() {
        return burnup;
    }

    public void setBurnup(double burnup) {
        this.burnup = burnup;
    }

    @XmlElement(name = "kpd")
    public double getKpd() {
        return kpd;
    }

    public void setKpd(double kpd) {
        this.kpd = kpd;
    }

    @XmlElement(name = "enrichment")
    public double getEnrichment() {
        return enrichment;
    }

    public void setEnrichment(double enrichment) {
        this.enrichment = enrichment;
    }

    @XmlElement(name = "thermal_capacity")
    public int getThermalCapacity() {
        return thermalCapacity;
    }

    public void setThermalCapacity(int thermalCapacity) {
        this.thermalCapacity = thermalCapacity;
    }

    @XmlElement(name = "electrical_capacity")
    public int getElectricalCapacity() {
        return electricalCapacity;
    }

    public void setElectricalCapacity(int electricalCapacity) {
        this.electricalCapacity = electricalCapacity;
    }

    @XmlElement(name = "lifeTime")
    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    @XmlElement(name = "first_load")
    public double getFirstLoad() {
        return firstLoad;
    }

    public void setFirstLoad(double firstLoad) {
        this.firstLoad = firstLoad;
    }
}
