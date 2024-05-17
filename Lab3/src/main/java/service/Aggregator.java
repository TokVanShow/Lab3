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
    private DataFiller dataFiller;
    private boolean dataFilled = false; // Флаг, указывающий, что данные уже заполнены

    public Aggregator(CompanyDAO companyDAO, CountryDAO countryDAO, RegionDAO regionDAO, UnitDAO unitDAO, FuelConsumptionCalculator fuelConsumptionCalculator) {
        this.companyDAO = companyDAO;
        this.countryDAO = countryDAO;
        this.regionDAO = regionDAO;
        this.unitDAO = unitDAO;
        this.fuelConsumptionCalculator = fuelConsumptionCalculator;
    }

    public void setDataFiller(DataFiller dataFiller) {
        this.dataFiller = dataFiller;
    }

    private void ensureDataFilled() {
        if (!dataFilled && this.dataFiller != null) {
            this.dataFiller.fillMissingBurnupValues();
            dataFilled = true; // Устанавливаем флаг после заполнения данных
        }
    }

    public Map<Company, Map<Integer, Double>> getCompanyConsumption() {
        ensureDataFilled(); // Заполняем данные перед выполнением агрегации

        List<Company> companies = companyDAO.getAllCompanies();
        Map<Company, Map<Integer, Double>> result = new HashMap<>();

        for (Company company : companies) {
            List<Unit> units = unitDAO.getUnitsByOwner(company);
            ensureDataFilled();
            Map<Integer, Double> consumptionByYear = new HashMap<>();
            for (Unit unit : units) {
                Map<Integer, Double> unitConsumption = fuelConsumptionCalculator.calculateAnnualConsumptions(unit);
                unitConsumption.forEach((year, consumption) -> consumptionByYear.merge(year, consumption, Double::sum));
            }
            result.put(company, consumptionByYear);
        }

        return result;
    }

    public Map<Company, Map<Integer, Double>> getOperatorConsumption() {
        ensureDataFilled(); // Заполняем данные перед выполнением агрегации

        List<Company> operators = companyDAO.getAllOperators();
        Map<Company, Map<Integer, Double>> result = new HashMap<>();

        for (Company operator : operators) {
            List<Unit> units = unitDAO.getUnitsByOperator(operator);
            Map<Integer, Double> consumptionByYear = new HashMap<>();
            for (Unit unit : units) {
                Map<Integer, Double> unitConsumption = fuelConsumptionCalculator.calculateAnnualConsumptions(unit);
                unitConsumption.forEach((year, consumption) -> consumptionByYear.merge(year, consumption, Double::sum));
            }
            result.put(operator, consumptionByYear);
        }

        return result;
    }

    public Map<Country, Map<Integer, Double>> getCountryConsumption() {
        ensureDataFilled(); // Заполняем данные перед выполнением агрегации

        List<Country> countries = countryDAO.getAllCountries();
        Map<Country, Map<Integer, Double>> result = new HashMap<>();

        for (Country country : countries) {
            List<Unit> units = unitDAO.getUnitsByCountry(country);
            Map<Integer, Double> consumptionByYear = new HashMap<>();
            for (Unit unit : units) {
                Map<Integer, Double> unitConsumption = fuelConsumptionCalculator.calculateAnnualConsumptions(unit);
                unitConsumption.forEach((year, consumption) -> consumptionByYear.merge(year, consumption, Double::sum));
            }
            result.put(country, consumptionByYear);
        }

        return result;
    }

    public Map<Region, Map<Integer, Double>> getRegionConsumption() {
        ensureDataFilled(); // Заполняем данные перед выполнением агрегации

        List<Region> regions = regionDAO.getAllRegions();
        Map<Region, Map<Integer, Double>> result = new HashMap<>();

        for (Region region : regions) {
            List<Unit> units = unitDAO.getUnitsByRegion(region);
            Map<Integer, Double> consumptionByYear = new HashMap<>();
            for (Unit unit : units) {
                Map<Integer, Double> unitConsumption = fuelConsumptionCalculator.calculateAnnualConsumptions(unit);
                unitConsumption.forEach((year, consumption) -> consumptionByYear.merge(year, consumption, Double::sum));
            }
            result.put(region, consumptionByYear);
        }

        return result;
    }
}