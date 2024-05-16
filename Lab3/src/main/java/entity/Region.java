package entity;

import javax.persistence.*;

@Entity
@Table(name = "Regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_region")
    private Long id;

    @Column(name = "name_region")
    private String regionName;

    public Region() {
    }

    public Region(Long id, String regionName) {
        this.id = id;
        this.regionName = regionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
