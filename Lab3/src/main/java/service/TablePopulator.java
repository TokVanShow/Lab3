package service;

import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.Map;
import entity.Company;
import entity.Country;
import entity.Region;

public class TablePopulator {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");

    public void populateTableByCompany(DefaultTableModel tableModel, Map<Company, Map<Integer, Double>> companyData) {
        clearTable(tableModel);
        tableModel.addColumn("Владельцы");
        tableModel.addColumn("Объем ежегодного потребления, т.");
        tableModel.addColumn("Год");
        for (Map.Entry<Company, Map<Integer, Double>> entry : companyData.entrySet()) {
            Company company = entry.getKey();
            for (Map.Entry<Integer, Double> yearEntry : entry.getValue().entrySet()) {
                tableModel.addRow(new Object[]{company.getCompanyName(), formatValue(yearEntry.getValue()), yearEntry.getKey()});
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
            for (Map.Entry<Integer, Double> yearEntry : entry.getValue().entrySet()) {
                tableModel.addRow(new Object[]{country.getCountryName(), formatValue(yearEntry.getValue()), yearEntry.getKey()});
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
            for (Map.Entry<Integer, Double> yearEntry : entry.getValue().entrySet()) {
                tableModel.addRow(new Object[]{region.getRegionName(), formatValue(yearEntry.getValue()), yearEntry.getKey()});
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
            for (Map.Entry<Integer, Double> yearEntry : entry.getValue().entrySet()) {
                tableModel.addRow(new Object[]{operator.getCompanyName(), formatValue(yearEntry.getValue()), yearEntry.getKey()});
            }
        }
    }

    private void clearTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
    }

    private String formatValue(Double value) {
        return DECIMAL_FORMAT.format(value);
    }

    // Custom non-editable table model
    public static class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel() {
            super();
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // All cells are non-editable
        }
    }
}
