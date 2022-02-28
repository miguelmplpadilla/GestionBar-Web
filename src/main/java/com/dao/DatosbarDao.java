package com.dao;


import com.entity.Comparativaprecio;
import com.entity.Datosbar;
import com.entity.Productos;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DatosbarDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Datosbar datosbar) {
        if (datosbar.getCorreoElectronico() == null) {
            datosbar.setCorreoElectronico("correo@correo.com");
        }
        if (datosbar.getFotoPerfil() == null) {
            datosbar.setFotoPerfil("https://www.compuserviciosbcs.com/images/items/no-img.jpg");
        }
        entityManager.persist(datosbar);
        return;
    }

    public void delete(Datosbar datosbar) {
        if (entityManager.contains(datosbar)) {
            entityManager.remove(datosbar);
        } else {
            entityManager.remove(entityManager.merge(datosbar));
        }
    }

    public void update(Datosbar datosbar) {
        entityManager.merge(datosbar);
    }

    @SuppressWarnings("unchecked")
    public List<Datosbar> getDatosByBarId(int id) {
        return entityManager.createQuery("select db from Datosbar db where db.fkBar = "+id).getResultList();
    }

}
