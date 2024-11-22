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


    public void save(Ticket ticket) throws Exception {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();


            if (ticket.getClient() == null || session.get(Client.class, ticket.getClient().getId()) == null) {
                throw new Exception("Client does not exist");
            }


            if (ticket.getFromPlanet() == null || session.get(Planet.class, ticket.getFromPlanet().getId()) == null) {
                throw new Exception("Start planet does not exist");
            }


            if (ticket.getToPlanet() == null || session.get(Planet.class, ticket.getToPlanet().getId()) == null) {
                throw new Exception("End planet does not exist");
            }


            session.save(ticket);
            session.getTransaction().commit();
        } catch (Exception e) {

            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
        }
    }


    public Ticket findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            session.getTransaction().commit();
            return ticket;
        } catch (Exception e) {

            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
        }
    }


    public List<Ticket> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).getResultList();
            session.getTransaction().commit();
            return tickets;
        } catch (Exception e) {

             if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
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
        } catch (Exception e) {

            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
        }
    }


    public void update(Ticket ticket) throws Exception {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();


            if (ticket.getClient() == null || session.get(Client.class, ticket.getClient().getId()) == null) {
                throw new Exception("Client does not exist");
            }


            if (ticket.getFromPlanet() == null || session.get(Planet.class, ticket.getFromPlanet().getId()) == null) {
                throw new Exception("Start planet does not exist");
            }


            if (ticket.getToPlanet() == null || session.get(Planet.class, ticket.getToPlanet().getId()) == null) {
                throw new Exception("End planet does not exist");
            }


            session.update(ticket);
            session.getTransaction().commit();
        } catch (Exception e) {

            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
        }
    }
}
