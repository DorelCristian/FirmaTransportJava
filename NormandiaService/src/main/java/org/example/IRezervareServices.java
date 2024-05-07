package org.example;

import java.util.List;

public interface IRezervareServices {
    User connect(String username, String password, IRezervareObserver client);

    Rezervare findRezervare(User user, Cursa cursa, Integer locuri);

    void logout(String username);


       List<User> getAllUsers();
       List<Rezervare> getAllRezervari();
       List<Cursa> getAllCurse();

       List<Seat>getAllSeats(Rezervare rezervare);

    void registerRezervare(User client, Cursa cursa, int locuri);

    void addSeat(Rezervare rezervare, Integer seat);


}
