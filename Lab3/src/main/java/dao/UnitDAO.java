package dao;

import entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UnitDAO {

    private final EntityManager entityManager;
    private final int batchSize;

    public UnitDAO(EntityManager entityManager, int batchSize) {
        this.entityManager = entityManager;
        this.batchSize = batchSize;
    }

    public void saveUnit(Unit unit) {
        entityManager.merge(unit);
    }

    public List<Unit> getAllUnits() {
        TypedQuery<Unit> query = entityManager.createQuery("SELECT u FROM Unit u", Unit.class);
        return query.getResultList();
    }

    public List<Unit> getUnitsByOwner(Company owner) {
        TypedQuery<Unit> query = entityManager.createQuery("SELECT u FROM Unit u WHERE u.owner = :owner", Unit.class);
        query.setParameter("owner", owner);
        return query.getResultList();
    }

    public List<Unit> getUnitsByOperator(Company operator) {
        TypedQuery<Unit> query = entityManager.createQuery("SELECT u FROM Unit u WHERE u.operator = :operator", Unit.class);
        query.setParameter("operator", operator);
        return query.getResultList();
    }

    public List<Unit> getUnitsByCountry(Country country) {
        TypedQuery<Unit> query = entityManager.createQuery("SELECT u FROM Unit u WHERE u.country = :country", Unit.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

//    public List<Unit> getUnitsByRegion(Region region) {
//        TypedQuery<Unit> query = entityManager.createQuery("SELECT u FROM Unit u WHERE u.country.region = :region", Unit.class);
//        query.setParameter("region", region);
//        return query.getResultList();
//    }

    public List<Unit> getUnitsByRegion(Region region) {
        TypedQuery<Unit> query = entityManager.createQuery("SELECT u FROM Unit u JOIN FETCH u.country c WHERE c.region = :region", Unit.class);
        query.setParameter("region", region);
        return query.getResultList();
    }

    public void saveEntitiesInBatch(List<Unit> entities) {
        for (int i = 0; i < entities.size(); i++) {
            saveUnit(entities.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
