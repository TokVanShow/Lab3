package service;

import javax.swing.table.DefaultTableModel;
import java.util.Map;
import entity.Company;
import entity.Country;
import entity.Region;

public class TablePopulator {

    public void populateTableByCompany(DefaultTableModel tableModel, Map<Company, Double> companyData) {
        clearTable(tableModel);
        for (Map.Entry<Company, Double> entry : companyData.entrySet()) {
            Company company = entry.getKey();
            Double consumption = entry.getValue();
            tableModel.addRow(new Object[]{company.getCompanyName(), consumption});
        }
    }

    public void populateTableByCountry(DefaultTableModel tableModel, Map<Country, Double> countryData) {
        clearTable(tableModel);
        for (Map.Entry<Country, Double> entry : countryData.entrySet()) {
            Country country = entry.getKey();
            Double consumption = entry.getValue();
            tableModel.addRow(new Object[]{country.getCountryName(), consumption});
        }
    }

    public void populateTableByRegion(DefaultTableModel tableModel, Map<Region, Double> regionData) {
        clearTable(tableModel);
        for (Map.Entry<Region, Double> entry : regionData.entrySet()) {
            Region region = entry.getKey();
            Double consumption = entry.getValue();
            tableModel.addRow(new Object[]{region.getRegionName(), consumption});
        }
    }

    public void populateTableByOperator(DefaultTableModel tableModel, Map<Company, Double> operatorData) {
        clearTable(tableModel);
        for (Map.Entry<Company, Double> entry : operatorData.entrySet()) {
            Company operator = entry.getKey();
            Double consumption = entry.getValue();
            tableModel.addRow(new Object[]{operator.getCompanyName(), consumption});
        }
    }

    private void clearTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
    }
}
