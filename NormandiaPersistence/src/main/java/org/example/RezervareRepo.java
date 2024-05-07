package org.example;

//import domain.Rezervare;
import org.example.IRepository;

public interface RezervareRepo extends IRepository<Integer, Rezervare> {
    Rezervare findByName(String name);
    void add(Rezervare elem);
    void update(Integer integer, Rezervare elem);
}
