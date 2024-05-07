package org.example;

//import domain.Client;

public interface ClientRepo extends IRepository<Integer, Client> {
    Client findByName(String name);
    void add(Client elem);
    void update(Integer integer, Client elem);
}
