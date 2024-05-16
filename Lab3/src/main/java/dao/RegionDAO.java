package dao;

import entity.Region;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RegionDAO {

    private final EntityManager entityManager;
    private final int batchSize;

    public RegionDAO(EntityManager entityManager, int batchSize) {
        this.entityManager = entityManager;
        this.batchSize = batchSize;
    }

    public void saveRegion(Region region) {
        entityManager.merge(region);
    }

    public List<Region> getAllRegions() {
        TypedQuery<Region> query = entityManager.createQuery("SELECT r FROM Region r", Region.class);
        return query.getResultList();
    }

    public void saveEntitiesInBatch(List<Region> entities) {
        for (int i = 0; i < entities.size(); i++) {
            saveRegion(entities.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
