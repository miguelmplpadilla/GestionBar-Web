package com.dao;

import com.entity.Bar;
import com.entity.Productos;
import com.entity.Proveedores;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProveedoresDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Proveedores proveedor) {
        entityManager.persist(proveedor);
        return;
    }

    public void delete(Proveedores proveedor) {
        if (entityManager.contains(proveedor)) {
            entityManager.remove(proveedor);
        } else {
            entityManager.remove(entityManager.merge(proveedor));
        }
    }

    public void update(Proveedores proveedores) {
        entityManager.merge(proveedores);
    }

    @SuppressWarnings("unchecked")
    public List<Proveedores> getAll() {
        return entityManager.createQuery("select p from Proveedores p").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Proveedores> getById(int id) {
        return entityManager.createQuery("select p from Proveedores p where p.id = "+id).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Proveedores> getByBar(int bar) {
        return entityManager.createQuery("select p from Proveedores p, FkBarproveedor bp where p.id = bp.fkProveedor and bp.fkBar = "+bar).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Productos> getProductoByProveedor(int proveedor, int idBar) {
        return entityManager.createQuery("select p from Productos p, FkProductosproveedores pp, Proveedores pro where p.id = pp.fkProducto and pp.fkProveedor = pro.id and pro.id = '"+proveedor+"' and pp.fkBar = "+idBar).getResultList();
    }


}
