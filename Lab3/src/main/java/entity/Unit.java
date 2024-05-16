package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_reactor")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "class")
    private String unitClass;

    @Column(name = "model")
    private String model;

    @Column(name = "status")
    private String status;

    @Column(name = "thermal_capacity")
    private Long thermalCapacity;

    @Temporal(TemporalType.DATE)
    @Column(name = "first_grid_connection_date")
    private Date firstGridConnectionDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_shutdown")
    private Date dateShutdown;

    @ManyToOne
    @JoinColumn(name = "ID_owner")
    private Company owner;

    @ManyToOne
    @JoinColumn(name = "ID_operator")
    private Company operator;

    @ManyToOne
    @JoinColumn(name = "ID_country")
    private Country country;

    @Transient
    private double burnup;

    @Transient
    private double firstLoad;

    public Unit() {
    }

    public Unit(Long id, String name, String unitClass, String model, String status, Long thermalCapacity, Date firstGridConnectionDate, Date dateShutdown, Company owner, Company operator, Country country) {
        this.id = id;
        this.name = name;
        this.unitClass = unitClass;
        this.model = model;
        this.status = status;
        this.thermalCapacity = thermalCapacity;
        this.firstGridConnectionDate = firstGridConnectionDate;
        this.dateShutdown = dateShutdown;
        this.owner = owner;
        this.operator = operator;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitClass() {
        return unitClass;
    }

    public void setUnitClass(String unitClass) {
        this.unitClass = unitClass;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getThermalCapacity() {
        return thermalCapacity;
    }

    public void setThermalCapacity(Long thermalCapacity) {
        this.thermalCapacity = thermalCapacity;
    }

    public Date getFirstGridConnectionDate() {
        return firstGridConnectionDate;
    }

    public void setFirstGridConnectionDate(Date firstGridConnectionDate) {
        this.firstGridConnectionDate = firstGridConnectionDate;
    }

    public Date getDateShutdown() {
        return dateShutdown;
    }

    public void setDateShutdown(Date dateShutdown) {
        this.dateShutdown = dateShutdown;
    }

    public Company getOwner() {
        return owner;
    }

    public void setOwner(Company owner) {
        this.owner = owner;
    }

    public Company getOperator() {
        return operator;
    }

    public void setOperator(Company operator) {
        this.operator = operator;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public double getBurnup() {
        return burnup;
    }

    public void setBurnup(double burnup) {
        this.burnup = burnup;
    }

    public double getFirstLoad() {
        return firstLoad;
    }

    public void setFirstLoad(double firstLoad) {
        this.firstLoad = firstLoad;
    }

}
