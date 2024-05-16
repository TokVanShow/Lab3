package service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import entity.*;
import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelDataLoader {

    private final EntityManager entityManager;
    private final CompanyDAO companyDAO;
    private final RegionDAO regionDAO;
    private final CountryDAO countryDAO;
    private final UnitDAO unitDAO;
    private final KIUMDAO kiumDAO;
    private final int batchSize;

    public ExcelDataLoader(EntityManager entityManager, int batchSize) {
        this.entityManager = entityManager;
        this.batchSize = batchSize;
        this.companyDAO = new CompanyDAO(entityManager, batchSize);
        this.regionDAO = new RegionDAO(entityManager, batchSize);
        this.countryDAO = new CountryDAO(entityManager, batchSize);
        this.unitDAO = new UnitDAO(entityManager, batchSize);
        this.kiumDAO = new KIUMDAO(entityManager, batchSize);
    }

    public void loadExcelData(File file) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);

            try {
                // Начало транзакции
                transaction.begin();

                // Загрузка данных из листов Excel
                List<Company> companies = readCompanies(workbook.getSheet("companies"));
                companyDAO.saveEntitiesInBatch(companies);

                List<Region> regions = readRegions(workbook.getSheet("regions"));
                regionDAO.saveEntitiesInBatch(regions);

                List<Country> countries = readCountries(workbook.getSheet("countries"));
                countryDAO.saveEntitiesInBatch(countries);

                List<Unit> units = readUnits(workbook.getSheet("reactors"));
                unitDAO.saveEntitiesInBatch(units);

                List<KIUM> kiums = readKIUMs(workbook.getSheet("kium"));
                kiumDAO.saveEntitiesInBatch(kiums);

                // Коммит транзакции
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                workbook.close();
                excelFile.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Company> readCompanies(Sheet sheet) {
        List<Company> companies = new ArrayList<>();
        Iterator<Row> iterator = sheet.iterator();
        // Пропускаем заголовок
        if (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Long id = (long) currentRow.getCell(0).getNumericCellValue();
            String companyName = currentRow.getCell(1).getStringCellValue();
            Company company = new Company(id, companyName);
            companies.add(company);
        }
        return companies;
    }

    private List<Region> readRegions(Sheet sheet) {
        List<Region> regions = new ArrayList<>();
        Iterator<Row> iterator = sheet.iterator();
        // Пропускаем заголовок
        if (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Long id = (long) currentRow.getCell(0).getNumericCellValue();
            String regionName = currentRow.getCell(1).getStringCellValue();
            Region region = new Region(id, regionName);
            regions.add(region);
        }
        return regions;
    }

    private List<Unit> readUnits(Sheet sheet) {
        List<Unit> units = new ArrayList<>();
        Iterator<Row> iterator = sheet.iterator();
        // Пропускаем заголовок
        if (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Long id = (long) currentRow.getCell(0).getNumericCellValue();
            String name = currentRow.getCell(1).getStringCellValue();
            String unitClass = currentRow.getCell(2).getStringCellValue();
            String model = currentRow.getCell(3).getStringCellValue();
            String status = currentRow.getCell(4).getStringCellValue();
            Long thermalCapacity = (long) currentRow.getCell(5).getNumericCellValue();
            Date firstGridConnectionDate = currentRow.getCell(6).getDateCellValue();
            Date dateShutdown = null;
            if (currentRow.getCell(7) != null && currentRow.getCell(7).getCellType() == CellType.NUMERIC) {
                dateShutdown = currentRow.getCell(7).getDateCellValue();
            }
            Long ownerId = (long) currentRow.getCell(8).getNumericCellValue();
            Long operatorId = (long) currentRow.getCell(9).getNumericCellValue();
            Long countryId = (long) currentRow.getCell(10).getNumericCellValue();

            Company owner = entityManager.find(Company.class, ownerId);
            Company operator = entityManager.find(Company.class, operatorId);
            Country country = entityManager.find(Country.class, countryId);

            Unit unit = new Unit(id, name, unitClass, model, status, thermalCapacity,
                    firstGridConnectionDate, dateShutdown, owner, operator, country);
            units.add(unit);
        }
        return units;
    }

    private List<KIUM> readKIUMs(Sheet sheet) {
        List<KIUM> kiums = new ArrayList<>();
        Iterator<Row> iterator = sheet.iterator();
        // Пропускаем заголовок
        if (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Long id = (long) currentRow.getCell(0).getNumericCellValue();
            Cell kiumValueCell = currentRow.getCell(1);

            Double kiumValue = null;

            if (kiumValueCell != null) {
                String valueAsString = kiumValueCell.getStringCellValue();
                try {
                    kiumValue = Double.parseDouble(valueAsString);
                } catch (NumberFormatException e) {
                    e.printStackTrace(); 
                }
            }
            Long year = (long) currentRow.getCell(2).getNumericCellValue();
            Long reactorId = (long) currentRow.getCell(3).getNumericCellValue();

            Unit reactor = entityManager.find(Unit.class, reactorId);

            KIUM kium = new KIUM(id, kiumValue, year, reactor);
            kiums.add(kium);
        }
        return kiums;
    }

    private List<Country> readCountries(Sheet sheet) {
        List<Country> countries = new ArrayList<>();
        Iterator<Row> iterator = sheet.iterator();
        // Пропускаем заголовок
        if (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Long id = (long) currentRow.getCell(0).getNumericCellValue();
            String countryName = currentRow.getCell(1).getStringCellValue();
            Long regionId = (long) currentRow.getCell(2).getNumericCellValue();

            Region region = entityManager.find(Region.class, regionId);

            Country country = new Country(id, countryName, region);
            countries.add(country);
        }
        return countries;
    }
}
