package com.dao;

import com.entity.Bar;
import com.entity.FkBarproveedor;
import com.entity.Productos;
import com.entity.Proveedores;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BarProveedorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(FkBarproveedor fkBarproveedor) {
        entityManager.persist(fkBarproveedor);
        return;
    }

    public void delete(FkBarproveedor fkBarproveedor) {
        if (entityManager.contains(fkBarproveedor)) {
            entityManager.remove(fkBarproveedor);
        } else {
            entityManager.remove(entityManager.merge(fkBarproveedor));
        }
    }

    public void update(FkBarproveedor fkBarproveedor) {
        entityManager.merge(fkBarproveedor);
    }

    @SuppressWarnings("unchecked")
    public List<FkBarproveedor> getAll() {
        return entityManager.createQuery("select bp from FkBarproveedor bp").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<FkBarproveedor> getByProveedor(int id) {
        return entityManager.createQuery("select bp from FkBarproveedor bp where bp.fkProveedor = "+id).getResultList();
    }


}
