package com.dao;

import com.entity.Comparativaprecio;
import com.entity.FkProductosproveedores;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ComparativaPrecioDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Comparativaprecio comparativaprecio) {
        entityManager.persist(comparativaprecio);
        return;
    }

    public void delete(Comparativaprecio comparativaprecio) {
        if (entityManager.contains(comparativaprecio)) {
            entityManager.remove(comparativaprecio);
        } else {
            entityManager.remove(entityManager.merge(comparativaprecio));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Comparativaprecio> getByProductId(int id, int idBar) {
        return entityManager.createQuery("select c from Comparativaprecio c where c.fkProducto = "+id+" and c.fkBar = "+idBar).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Comparativaprecio> getByProveedorIdAndProductoId(int proveedorId, int productoId, int idBar) {
        return entityManager.createQuery("select c from Comparativaprecio c where c.fkProducto = "+productoId+" and c.fkProveedor = "+proveedorId+" and c.fkBar = "+idBar).getResultList();
    }
}
