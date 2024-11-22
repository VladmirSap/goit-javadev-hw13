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
        // Створюємо SessionFactory для роботи з Hibernate
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }

    /**
     * Зберігає квиток, перевіряючи наявність клієнта та планет.
     * @param ticket квиток для збереження.
     * @throws Exception якщо клієнт або планети не існують.
     */
    public void save(Ticket ticket) throws Exception {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            // Перевірка, чи існує клієнт
            if (ticket.getClient() == null || session.get(Client.class, ticket.getClient().getId()) == null) {
                throw new Exception("Client does not exist");
            }

            // Перевірка, чи існує початкова планета
            if (ticket.getFromPlanet() == null || session.get(Planet.class, ticket.getFromPlanet().getId()) == null) {
                throw new Exception("Start planet does not exist");
            }

            // Перевірка, чи існує кінцева планета
            if (ticket.getToPlanet() == null || session.get(Planet.class, ticket.getToPlanet().getId()) == null) {
                throw new Exception("End planet does not exist");
            }

            // Збереження квитка в базі
            session.save(ticket);
            session.getTransaction().commit();
        } catch (Exception e) {
            // Якщо виникає помилка, відкатуємо транзакцію
            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e; // повторно кидаємо виключення для подальшої обробки
        }
    }

    /**
     * Повертає квиток за його ID.
     * @param id ідентифікатор квитка.
     * @return знайдений квиток.
     */
    public Ticket findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            session.getTransaction().commit();
            return ticket;
        } catch (Exception e) {
            // Відкатуємо транзакцію в разі помилки
            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
        }
    }

    /**
     * Повертає всі квитки.
     * @return список усіх квитків.
     */
    public List<Ticket> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).getResultList();
            session.getTransaction().commit();
            return tickets;
        } catch (Exception e) {
            // Відкатуємо транзакцію в разі помилки
            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
        }
    }

    /**
     * Видаляє квиток за його ID.
     * @param id ідентифікатор квитка.
     */
    public void delete(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            // Відкатуємо транзакцію в разі помилки
            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
        }
    }

    /**
     * Оновлює квиток.
     * @param ticket квиток з оновленими даними.
     * @throws Exception якщо клієнт або планети не існують.
     */
    public void update(Ticket ticket) throws Exception {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            // Перевірка, чи існує клієнт
            if (ticket.getClient() == null || session.get(Client.class, ticket.getClient().getId()) == null) {
                throw new Exception("Client does not exist");
            }

            // Перевірка, чи існує початкова планета
            if (ticket.getFromPlanet() == null || session.get(Planet.class, ticket.getFromPlanet().getId()) == null) {
                throw new Exception("Start planet does not exist");
            }

            // Перевірка, чи існує кінцева планета
            if (ticket.getToPlanet() == null || session.get(Planet.class, ticket.getToPlanet().getId()) == null) {
                throw new Exception("End planet does not exist");
            }

            // Оновлення квитка в базі
            session.update(ticket);
            session.getTransaction().commit();
        } catch (Exception e) {
            // Відкатуємо транзакцію в разі помилки
            if (factory.getCurrentSession() != null && factory.getCurrentSession().getTransaction().isActive()) {
                factory.getCurrentSession().getTransaction().rollback();
            }
            throw e;
        }
    }
}
