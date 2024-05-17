package service;

import dao.UnitDAO;
import entity.Unit;
import reactors.Storage;
import reactors.ReactorType;

import java.util.List;

public class DataFiller {

    private final UnitDAO unitDAO;
    private final Storage storage;

    public DataFiller(UnitDAO unitDAO, Storage storage) {
        this.unitDAO = unitDAO;
        this.storage = storage;
    }

    public void fillMissingBurnupValues() {
        List<Unit> units = unitDAO.getAllUnits();
        for (Unit unit : units) {
            String reactorClassType = mapClassType(unit.getUnitClass());
            ReactorType reactorType = storage.getReactorType(reactorClassType);
            if (reactorType != null) {
                unit.setBurnup(reactorType.getBurnup());
                unit.setFirstLoad(reactorType.getFirstLoad());
                // Сохраняем временные значения в память
                storage.saveTemporaryValues(unit.getId(), reactorType.getBurnup(), reactorType.getFirstLoad());
            }
        }
    }

    private String mapClassType(String classType) {
        String mappedType = switch (classType.trim()) {
            case "LWGR" -> "RBMK";
            case "GCR" -> "MAGNOX";
            case "FBR" -> "BN";
            default -> classType.trim();
        };
        return mappedType;
    }
}


//    private boolean checkReactorType(Unit unit, ReactorType reactorType) {
//        switch (unit.getUnitClass()) {
//            case "PHWR":
//                return reactorType.getClassType().equals("PHWR");
//            case "PWR":
//                return reactorType.getClassType().equals("PWR");
//            case "BWR":
//                return reactorType.getClassType().equals("BWR");
//            default:
//                return false;
//        }
//    }

