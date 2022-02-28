package com.dao;

import com.entity.Categorias;
import com.entity.Codigostrazabilidad;
import com.entity.Productos;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CodigosTrazabilidadDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Codigostrazabilidad codigostrazabilidad) {
        entityManager.persist(codigostrazabilidad);
        return;
    }

    public void delete(Codigostrazabilidad codigostrazabilidad) {
        if (entityManager.contains(codigostrazabilidad)) {
            entityManager.remove(codigostrazabilidad);
        } else {
            entityManager.remove(entityManager.merge(codigostrazabilidad));
        }
    }

    public void update(Codigostrazabilidad codigostrazabilidad) {
        entityManager.merge(codigostrazabilidad);
    }

    @SuppressWarnings("unchecked")
    public List<Codigostrazabilidad> getAll() {
        return entityManager.createQuery("select ct from Codigostrazabilidad ct").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Codigostrazabilidad> getByProductoIdBar(int id, int idBar) {
        return entityManager.createQuery("select ct from Codigostrazabilidad ct where ct.fkProducto = "+id+" and ct.fkBar = "+idBar).getResultList();
    }

}
