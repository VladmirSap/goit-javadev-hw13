package org.example;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;

import java.util.List;

public class TestApp {
    public static void main(String[] args) {

        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();
        TicketCrudService ticketService = new TicketCrudService();


        Planet earth = new Planet();
        earth.setId("EARTH");
        earth.setName("Earth");
        planetService.save(earth);

        Planet mars = new Planet();
        mars.setId("MARS");
        mars.setName("Mars");
        planetService.save(mars);

        Planet venus = new Planet();
        venus.setId("VEN");
        venus.setName("Venus");
        planetService.save(venus);


        Client client1 = new Client();
        client1.setName("John Doe");
        clientService.save(client1);

        Client client2 = new Client();
        client2.setName("Jane Smith");
        clientService.save(client2);

        Client client3 = new Client();
        client3.setName("Alex Johnson");
        clientService.save(client3);


        Client retrievedClient1 = clientService.findById(client1.getId());
        Client retrievedClient2 = clientService.findById(client2.getId());
        Client retrievedClient3 = clientService.findById(client3.getId());

        if (retrievedClient1 == null || retrievedClient2 == null || retrievedClient3 == null) {
            System.out.println("One or more clients were not saved correctly. Please check.");
            return;
        }


        Ticket ticket1 = new Ticket();
        ticket1.setClient(retrievedClient1);
        ticket1.setFromPlanet(earth);
        ticket1.setToPlanet(mars);
        ticketService.save(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setClient(retrievedClient2);
        ticket2.setFromPlanet(mars);
        ticket2.setToPlanet(venus);
        ticketService.save(ticket2);

        Ticket ticket3 = new Ticket();
        ticket3.setClient(retrievedClient3);
        ticket3.setFromPlanet(venus);
        ticket3.setToPlanet(earth);
        ticketService.save(ticket3);


        System.out.println("All Clients:");
        List<Client> clients = clientService.findAll();
        clients.forEach(client -> {
            System.out.println("Client ID: " + client.getId() + ", Client Name: " + client.getName());
        });


        System.out.println("\nAll Planets:");
        List<Planet> planets = planetService.findAll();
        planets.forEach(planet -> {
            System.out.println("Planet ID: " + planet.getId() + ", Planet Name: " + planet.getName());
        });


        System.out.println("\nAll Tickets:");
        List<Ticket> tickets = ticketService.findAll();
        tickets.forEach(ticket -> {
            System.out.println("Ticket ID: " + ticket.getId() + " | " +
                    "Client: " + ticket.getClient().getName() + " | " +
                    "From: " + ticket.getFromPlanet().getName() + " | " +
                    "To: " + ticket.getToPlanet().getName());
        });
    }
}
