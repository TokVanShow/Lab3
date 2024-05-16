package dao;

import entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CompanyDAO {

    private final EntityManager entityManager;
    private final int batchSize;

    public CompanyDAO(EntityManager entityManager, int batchSize) {
        this.entityManager = entityManager;
        this.batchSize = batchSize;
    }

    public void saveCompany(Company company) {
        entityManager.merge(company);
    }

    public List<Company> getAllCompanies() {
        TypedQuery<Company> query = entityManager.createQuery("SELECT c FROM Company c", Company.class);
        return query.getResultList();
    }

    public List<Company> getAllOperators() {
        TypedQuery<Company> query = entityManager.createQuery("SELECT DISTINCT u.operator FROM Unit u", Company.class);
        return query.getResultList();
    }

    public List<Company> getAllOwners() {
        TypedQuery<Company> query = entityManager.createQuery("SELECT DISTINCT u.owner FROM Unit u", Company.class);
        return query.getResultList();
    }

    public void saveEntitiesInBatch(List<Company> entities) {
        for (int i = 0; i < entities.size(); i++) {
            saveCompany(entities.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
