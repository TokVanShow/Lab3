package reactors;

public class ReactorType {

    private String name;
    private String classType;
    private double burnup;
    private double kpd;
    private double enrichment;
    private int thermalCapacity;
    private double electricalCapacity;
    private int lifeTime;
    private double firstLoad;

    private String source;

    public ReactorType() {
        
    }

    public ReactorType(String name, String classType, double burnup, double kpd, double enrichment, int thermalCapacity, double electricalCapacity, int lifeTime, double firstLoad) {
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }


    public double getBurnup() {
        return burnup;
    }

    public void setBurnup(double burnup) {
        this.burnup = burnup;
    }


    public double getKpd() {
        return kpd;
    }

    public void setKpd(double kpd) {
        this.kpd = kpd;
    }


    public double getEnrichment() {
        return enrichment;
    }

    public void setEnrichment(double enrichment) {
        this.enrichment = enrichment;
    }


    public int getThermalCapacity() {
        return thermalCapacity;
    }

    public void setThermalCapacity(int thermalCapacity) {
        this.thermalCapacity = thermalCapacity;
    }


    public double getElectricalCapacity() {
        return electricalCapacity;
    }

    public void setElectricalCapacity(double electricalCapacity) {
        this.electricalCapacity = electricalCapacity;
    }


    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }


    public double getFirstLoad() {
        return firstLoad;
    }

    public void setFirstLoad(double firstLoad) {
        this.firstLoad = firstLoad;
    }
}
