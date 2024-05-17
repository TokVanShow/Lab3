package dao;

import entity.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.Query;

public class CountryDAO {

    private final EntityManager entityManager;
    private final int batchSize;

    public CountryDAO(EntityManager entityManager, int batchSize) {
        this.entityManager = entityManager;
        this.batchSize = batchSize;
    }

    public void saveCountry(Country country) {
        entityManager.merge(country);
    }

    public List<Country> getAllCountries() {
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    public void saveEntitiesInBatch(List<Country> entities) {
        for (int i = 0; i < entities.size(); i++) {
            saveCountry(entities.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
