package org.example.service;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TicketCrudService {

    private static SessionFactory factory;

    static {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }

    public void save(Ticket ticket) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(ticket);
            session.getTransaction().commit();
        }
    }

    public Ticket findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            session.getTransaction().commit();
            return ticket;
        }
    }

    public List<Ticket> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).getResultList();
            session.getTransaction().commit();
            return tickets;
        }
    }

    public void delete(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            }
            session.getTransaction().commit();
        }
    }
}
