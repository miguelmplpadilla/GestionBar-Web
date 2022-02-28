package com.dao;

import com.entity.Bar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BarDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Bar bar) {
        entityManager.persist(bar);
    }

    public void delete(Bar bar) {
        if (entityManager.contains(bar)) {
            entityManager.remove(bar);
        } else {
            entityManager.remove(entityManager.merge(bar));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Bar> getAll() {
        return entityManager.createQuery("select b from Bar b").getResultList();
    }

    public Bar getById(int id) {
        return entityManager.find(Bar.class, id);
    }

    public Bar update(Bar bar) {
        return entityManager.merge(bar);
    }

    @SuppressWarnings("unchecked")
    public List<Bar> getByUserName(String user) {
        return entityManager.createQuery("select b from Bar b where b.nombre = '"+user+"'").getResultList();
    }

}
