package com.dao;

import com.entity.Categorias;
import com.entity.FkBarproveedor;
import com.entity.FkProductosproveedores;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductoProveedorDao {


    @PersistenceContext
    private EntityManager entityManager;

    public void create(FkProductosproveedores fkProductosproveedores) {
        entityManager.persist(fkProductosproveedores);
        return;
    }

    public void delete(FkProductosproveedores fkProductosproveedores) {
        if (entityManager.contains(fkProductosproveedores)) {
            entityManager.remove(fkProductosproveedores);
        } else {
            entityManager.remove(entityManager.merge(fkProductosproveedores));
        }
    }

    public void update(FkProductosproveedores fkProductosproveedores) {
        entityManager.merge(fkProductosproveedores);
    }

    @SuppressWarnings("unchecked")
    public List<FkProductosproveedores> getByProductId(int id, int idBar) {
        return entityManager.createQuery("select pp from FkProductosproveedores pp where pp.fkProducto = "+id+" and pp.fkBar = "+idBar).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<FkProductosproveedores> getAllByProveedor(int id, int idBar) {
        return entityManager.createQuery("select pp from FkProductosproveedores pp where pp.fkProveedor = "+id+" and pp.fkBar = "+idBar).getResultList();
    }

}
