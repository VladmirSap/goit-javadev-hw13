package org.example.service;


import org.example.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientCrudService {

    private static SessionFactory factory;

    static {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
    }

    public void save(Client client) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }
    }

    public Client findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.getTransaction().commit();
            return client;
        }
    }

    public List<Client> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).getResultList();
            session.getTransaction().commit();
            return clients;
        }
    }

    public void delete(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.delete(client);
            }
            session.getTransaction().commit();
        }
    }
}
