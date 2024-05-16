package entity;

import javax.persistence.*;

@Entity
@Table(name = "Companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_company")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    public Company() {
    }

    public Company(Long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
