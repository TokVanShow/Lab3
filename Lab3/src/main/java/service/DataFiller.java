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
            List<ReactorType> reactorTypes = storage.getReactorTypes(reactorClassType);
            for (ReactorType type : reactorTypes) {
                if (checkReactorType(unit, type)) {
                    unit.setBurnup(type.getBurnup());
                    unit.setFirstLoad(type.getFirstLoad());
                    break;
                }
            }
        }
    }

    private String mapClassType(String classType) {
        switch (classType) {
            case "LWGR":
                return "RBMK";
            case "GCR":
                return "MAGNOX";
            case "FBR":
                return "BN";
            default:
                return classType;
        }
    }

    private boolean checkReactorType(Unit unit, ReactorType reactorType) {
        switch (unit.getUnitClass()) {
            case "PHWR":
                return reactorType.getClassType().equals("PHWR");
            case "PWR":
                return reactorType.getClassType().equals("PWR");
            case "BWR":
                return reactorType.getClassType().equals("BWR");
            default:
                return false;
        }
    }
}
