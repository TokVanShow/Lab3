package service;

import dao.KIUMDAO;
import entity.KIUM;
import entity.Unit;
import reactors.Storage;

import java.util.*;
import java.util.stream.Collectors;

public class FuelConsumptionCalculator {

    private final KIUMDAO kiumDAO;
    private final Storage storage; // добавляем storage

    private final Map<Long, Map<Long, Double>> kiumCache = new HashMap<>(); // Кэш для значений KIUM

    public FuelConsumptionCalculator(KIUMDAO kiumDAO, Storage storage) {
        this.kiumDAO = kiumDAO;
        this.storage = storage;
        prefetchKiumValues(); // Предварительная загрузка значений KIUM
    }

    private void prefetchKiumValues() {
        List<KIUM> allKIUMs = kiumDAO.getAllKIUMs();
        for (KIUM kium : allKIUMs) {
            kiumCache
                .computeIfAbsent(kium.getReactor().getId(), id -> new HashMap<>())
                .put(kium.getYear(), kium.getKiumValue());
        }
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
        return kiumCache.getOrDefault(unit.getId(), Collections.emptyMap()).getOrDefault(year, 85.0);
    }

    private int getYear(java.util.Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        return cal.get(java.util.Calendar.YEAR);
    }
}
