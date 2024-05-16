package service;

import dao.*;
import entity.*;
import java.util.List;

public class DataExporter {
    private final CompanyDAO companyDAO;
    private final RegionDAO regionDAO;
    private final CountryDAO countryDAO;
    private final UnitDAO unitDAO;
    private final KIUMDAO kiumDAO;

    public DataExporter(CompanyDAO companyDAO, RegionDAO regionDAO, CountryDAO countryDAO, UnitDAO unitDAO, KIUMDAO kiumDAO) {
        this.companyDAO = companyDAO;
        this.regionDAO = regionDAO;
        this.countryDAO = countryDAO;
        this.unitDAO = unitDAO;
        this.kiumDAO = kiumDAO;
    }

    public List<Company> exportCompanies() {
        return companyDAO.getAllCompanies();
    }

    public List<Region> exportRegions() {
        return regionDAO.getAllRegions();
    }

    public List<Country> exportCountries() {
        return countryDAO.getAllCountries();
    }

    public List<Unit> exportUnits() {
        return unitDAO.getAllUnits();
    }

    public List<KIUM> exportKIUMs() {
        return kiumDAO.getAllKIUMs();
    }
}
