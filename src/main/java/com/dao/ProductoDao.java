package com.dao;

import com.entity.Bar;
import com.entity.Categorias;
import com.entity.Productos;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductoDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Productos producto) {
        entityManager.persist(producto);
        return;
    }

    public void delete(Productos producto) {
        if (entityManager.contains(producto)) {
            entityManager.remove(producto);
        } else {
            entityManager.remove(entityManager.merge(producto));
        }
    }

    public void update(Productos producto) {
        entityManager.merge(producto);
    }

    @SuppressWarnings("unchecked")
    public List<Productos> getAll() {
        return entityManager.createQuery("select p from Productos p").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Categorias> getIdByNombre(String name) {
        return entityManager.createQuery("select c from Categorias c where c.nombre = '"+name+"'").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Productos> getById(int id) {
        return entityManager.createQuery("select p from Productos p where p.id = "+id).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Productos> getByProveedor(int id) {
        return entityManager.createQuery("select p from Productos p, FkProductosproveedores pp where p.id = pp.fkProducto and pp.fkProveedor = "+id).getResultList();
    }
}
