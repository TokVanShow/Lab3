package service;

import dao.KIUMDAO;
import entity.KIUM;
import entity.Unit;

import java.util.Optional;

public class FuelConsumptionCalculator {

    private final KIUMDAO kiumDAO;

    public FuelConsumptionCalculator(KIUMDAO kiumDAO) {
        this.kiumDAO = kiumDAO;
    }

    public double calculateAnnualConsumption(Unit unit, int year) {
        double burnup = unit.getBurnup();
        double thermalCapacity = unit.getThermalCapacity();
        double kiumValue = fetchKiumValue(unit, year);

        return (thermalCapacity / burnup) * kiumValue / 100;
    }

    public double calculateTotalConsumption(Unit unit) {
        System.out.println("\u001B[43mReactor: " + unit.getName() + "\u001B[0m");
        int startYear = Math.max(getYear(unit.getFirstGridConnectionDate()), 2014);
        int endYear = Math.min(getYear(Optional.ofNullable(unit.getDateShutdown()).orElse(new java.util.Date())), 2024);

        double totalConsumption = 0.0;

        for (int year = startYear; year <= endYear; year++) {
            if (year == getYear(unit.getFirstGridConnectionDate())) {
                totalConsumption += unit.getFirstLoad();
            } else {
                totalConsumption += calculateAnnualConsumption(unit, year);
            }
        }

        return totalConsumption;
    }

    private double fetchKiumValue(Unit unit, long year) {
        KIUM kium = kiumDAO.getKiumByReactorAndYear(unit, year).stream().findFirst().orElse(null);
        return kium != null ? kium.getKiumValue() : 85;
    }

    private int getYear(java.util.Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        return cal.get(java.util.Calendar.YEAR);
    }
}
