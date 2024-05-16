package service;

import dao.*;
import entity.*;

import java.util.*;

public class Aggregator {

    private final CompanyDAO companyDAO;
    private final CountryDAO countryDAO;
    private final RegionDAO regionDAO;
    private final UnitDAO unitDAO;
    private final FuelConsumptionCalculator fuelConsumptionCalculator;

    public Aggregator(CompanyDAO companyDAO, CountryDAO countryDAO, RegionDAO regionDAO, UnitDAO unitDAO, FuelConsumptionCalculator fuelConsumptionCalculator) {
        this.companyDAO = companyDAO;
        this.countryDAO = countryDAO;
        this.regionDAO = regionDAO;
        this.unitDAO = unitDAO;
        this.fuelConsumptionCalculator = fuelConsumptionCalculator;
    }

    public Map<Company, Double> getCompanyConsumption() {
        List<Company> companies = companyDAO.getAllCompanies();
        Map<Company, Double> result = new HashMap<>();

        for (Company company : companies) {
            List<Unit> units = unitDAO.getUnitsByOwner(company);
            double totalConsumption = units.stream().mapToDouble(fuelConsumptionCalculator::calculateTotalConsumption).sum();
            result.put(company, totalConsumption);
        }

        return result;
    }

    public Map<Company, Double> getOperatorConsumption() {
        List<Company> operators = companyDAO.getAllOperators();
        Map<Company, Double> result = new HashMap<>();

        for (Company operator : operators) {
            List<Unit> units = unitDAO.getUnitsByOperator(operator);
            double totalConsumption = units.stream().mapToDouble(fuelConsumptionCalculator::calculateTotalConsumption).sum();
            result.put(operator, totalConsumption);
        }

        return result;
    }

    public Map<Country, Double> getCountryConsumption() {
        List<Country> countries = countryDAO.getAllCountries();
        Map<Country, Double> result = new HashMap<>();

        for (Country country : countries) {
            List<Unit> units = unitDAO.getUnitsByCountry(country);
            double totalConsumption = units.stream().mapToDouble(fuelConsumptionCalculator::calculateTotalConsumption).sum();
            result.put(country, totalConsumption);
        }

        return result;
    }

    public Map<Region, Double> getRegionConsumption() {
        List<Region> regions = regionDAO.getAllRegions();
        Map<Region, Double> result = new HashMap<>();

        for (Region region : regions) {
            List<Unit> units = unitDAO.getUnitsByRegion(region);
            double totalConsumption = units.stream().mapToDouble(fuelConsumptionCalculator::calculateTotalConsumption).sum();
            result.put(region, totalConsumption);
        }

        return result;
    }
}
