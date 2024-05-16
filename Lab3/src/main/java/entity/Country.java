package entity;

import javax.persistence.*;

@Entity
@Table(name = "Countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_country")
    private Long id;

    @Column(name = "name_country")
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "ID_region")
    private Region region;

    public Country() {
    }

    public Country(Long id, String countryName, Region region) {
        this.id = id;
        this.countryName = countryName;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
