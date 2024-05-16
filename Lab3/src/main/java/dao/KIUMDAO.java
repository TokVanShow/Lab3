package dao;

import entity.KIUM;
import entity.Unit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class KIUMDAO {

    private final EntityManager entityManager;
    private final int batchSize;

    public KIUMDAO(EntityManager entityManager, int batchSize) {
        this.entityManager = entityManager;
        this.batchSize = batchSize;
    }

    public void saveKIUM(KIUM kium) {
        entityManager.merge(kium);
    }

    public List<KIUM> getAllKIUMs() {
        TypedQuery<KIUM> query = entityManager.createQuery("SELECT k FROM KIUM k", KIUM.class);
        return query.getResultList();
    }

    public List<KIUM> getKiumByReactorAndYear(Unit reactor, Long year) {
        TypedQuery<KIUM> query = entityManager.createQuery("SELECT k FROM KIUM k WHERE k.reactor = :reactor AND k.year = :year", KIUM.class);
        query.setParameter("reactor", reactor);
        query.setParameter("year", year);
        return query.getResultList();
    }

    public void saveEntitiesInBatch(List<KIUM> entities) {
        for (int i = 0; i < entities.size(); i++) {
            saveKIUM(entities.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
