package com.dao;

import com.entity.Categorias;
import com.entity.Productos;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoriasDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Categorias categoria) {
        entityManager.persist(categoria);
        return;
    }

    public void delete(Categorias categoria) {
        if (entityManager.contains(categoria)) {
            entityManager.remove(categoria);
        } else {
            entityManager.remove(entityManager.merge(categoria));
        }
    }

    public void update(Categorias categorias) {
        entityManager.merge(categorias);
    }

    @SuppressWarnings("unchecked")
    public List<Categorias> getById(int id) {
        return entityManager.createQuery("select c from Categorias c where c.id = "+id).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Categorias> getAll() {
        return entityManager.createQuery("select c from Categorias c").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Categorias> findCategoriaById(int id) {
        return entityManager.createQuery("select c.nombre from Categorias c where c.id = "+id).getResultList();
    }
}
