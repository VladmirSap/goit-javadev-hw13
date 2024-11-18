package org.example.service;

import org.example.entity.Planet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import org.hibernate.cfg.Configuration;

public class PlanetCrudService {
    private static SessionFactory factory;

    static {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }

    public void save(Planet planet) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(planet);
            session.getTransaction().commit();
        }
    }

    public Planet findById(String id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            session.getTransaction().commit();
            return planet;
        }
    }

    public List<Planet> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Planet> planets = session.createQuery("from Planet", Planet.class).getResultList();
            session.getTransaction().commit();
            return planets;
        }
    }

    public void delete(String id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.delete(planet);
            }
            session.getTransaction().commit();
        }
    }
}
