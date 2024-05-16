package entity;

import javax.persistence.*;

@Entity
@Table(name = "KIUM")
public class KIUM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_KIUM")
    private Long id;

    @Column(name = "kium_value")
    private Double kiumValue;

    @Column(name = "year")
    private Long year;

    @ManyToOne
    @JoinColumn(name = "ID_reactor")
    private Unit reactor;

    public KIUM() {
    }

    public KIUM(Long id, Double kiumValue, Long year, Unit reactor) {
        this.id = id;
        this.kiumValue = kiumValue;
        this.year = year;
        this.reactor = reactor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getKiumValue() {
        return kiumValue;
    }

    public void setKiumValue(Double kiumValue) {
        this.kiumValue = kiumValue;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Unit getReactor() {
        return reactor;
    }

    public void setReactor(Unit reactor) {
        this.reactor = reactor;
    }
}
