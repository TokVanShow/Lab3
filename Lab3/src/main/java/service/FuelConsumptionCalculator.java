package service;

import dao.KIUMDAO;
import entity.KIUM;
import entity.Unit;

import java.util.*;
import reactors.Storage;

public class FuelConsumptionCalculator {

    private final KIUMDAO kiumDAO;
    private final Storage storage; // добавляем storage

    public FuelConsumptionCalculator(KIUMDAO kiumDAO, Storage storage) {
        this.kiumDAO = kiumDAO;
        this.storage = storage;
    }

    public double calculateAnnualConsumption(Unit unit, int year) {
        double burnup = storage.getTemporaryBurnup(unit.getId()); // Извлекаем временное значение
        double thermalCapacity = unit.getThermalCapacity();
        double kiumValue = fetchKiumValue(unit, year);
        System.out.println("burnup = " + burnup);
        System.out.println("FirstLoad = " + storage.getTemporaryFirstLoad(unit.getId()));

        return (thermalCapacity / burnup) * kiumValue / 100;
    }

    public Map<Integer, Double> calculateAnnualConsumptions(Unit unit) {
        Map<Integer, Double> annualConsumptions = new HashMap<>();
        int startYear = Math.max(getYear(unit.getFirstGridConnectionDate()), 2014);
        int endYear = Math.min(getYear(Optional.ofNullable(unit.getDateShutdown()).orElse(new java.util.Date())), 2024);

        for (int year = startYear; year <= endYear; year++) {
            if (year == getYear(unit.getFirstGridConnectionDate())) {
                annualConsumptions.put(year, storage.getTemporaryFirstLoad(unit.getId())); // Извлекаем временное значение
            } else {
                annualConsumptions.put(year, calculateAnnualConsumption(unit, year));
            }
        }
        return annualConsumptions;
    }

    private double fetchKiumValue(Unit unit, long year) {
        KIUM kium = kiumDAO.getKiumByReactorAndYear(unit, year).stream().findFirst().orElse(null);
        System.out.println("KIUM = " + kium);
        return kium != null ? kium.getKiumValue() : 85;
    }

    private int getYear(java.util.Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        return cal.get(java.util.Calendar.YEAR);
    }
}
