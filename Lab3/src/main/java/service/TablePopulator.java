package service;

import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;
import entity.Company;
import entity.Country;
import entity.Region;

public class TablePopulator {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public void populateTableByCompany(DefaultTableModel tableModel, Map<Company, Map<Integer, Double>> companyData) {
        clearTable(tableModel);
        tableModel.addColumn("Владельцы");
        tableModel.addColumn("Объем ежегодного потребления, т.");
        tableModel.addColumn("Год");
        for (Map.Entry<Company, Map<Integer, Double>> entry : companyData.entrySet()) {
            Company company = entry.getKey();
            Map<Integer, Double> sortedYearData = new TreeMap<>(entry.getValue());
            for (Map.Entry<Integer, Double> yearEntry : sortedYearData.entrySet()) {
                String formattedConsumption = DECIMAL_FORMAT.format(yearEntry.getValue());
                tableModel.addRow(new Object[]{company.getCompanyName(), formattedConsumption, yearEntry.getKey()});
            }
        }
    }

    public void populateTableByCountry(DefaultTableModel tableModel, Map<Country, Map<Integer, Double>> countryData) {
        clearTable(tableModel);
        tableModel.addColumn("Страны");
        tableModel.addColumn("Объем ежегодного потребления, т.");
        tableModel.addColumn("Год");
        for (Map.Entry<Country, Map<Integer, Double>> entry : countryData.entrySet()) {
            Country country = entry.getKey();
            Map<Integer, Double> sortedYearData = new TreeMap<>(entry.getValue());
            for (Map.Entry<Integer, Double> yearEntry : sortedYearData.entrySet()) {
                String formattedConsumption = DECIMAL_FORMAT.format(yearEntry.getValue());
                tableModel.addRow(new Object[]{country.getCountryName(), formattedConsumption, yearEntry.getKey()});
            }
        }
    }

    public void populateTableByRegion(DefaultTableModel tableModel, Map<Region, Map<Integer, Double>> regionData) {
        clearTable(tableModel);
        tableModel.addColumn("Регионы");
        tableModel.addColumn("Объем ежегодного потребления, т.");
        tableModel.addColumn("Год");
        for (Map.Entry<Region, Map<Integer, Double>> entry : regionData.entrySet()) {
            Region region = entry.getKey();
            Map<Integer, Double> sortedYearData = new TreeMap<>(entry.getValue());
            for (Map.Entry<Integer, Double> yearEntry : sortedYearData.entrySet()) {
                String formattedConsumption = DECIMAL_FORMAT.format(yearEntry.getValue());
                tableModel.addRow(new Object[]{region.getRegionName(), formattedConsumption, yearEntry.getKey()});
            }
        }
    }

    public void populateTableByOperator(DefaultTableModel tableModel, Map<Company, Map<Integer, Double>> operatorData) {
        clearTable(tableModel);
        tableModel.addColumn("Операторы");
        tableModel.addColumn("Объем ежегодного потребления, т.");
        tableModel.addColumn("Год");
        for (Map.Entry<Company, Map<Integer, Double>> entry : operatorData.entrySet()) {
            Company operator = entry.getKey();
            Map<Integer, Double> sortedYearData = new TreeMap<>(entry.getValue());
            for (Map.Entry<Integer, Double> yearEntry : sortedYearData.entrySet()) {
                String formattedConsumption = DECIMAL_FORMAT.format(yearEntry.getValue());
                tableModel.addRow(new Object[]{operator.getCompanyName(), formattedConsumption, yearEntry.getKey()});
            }
        }
    }

    private void clearTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
    }
}
