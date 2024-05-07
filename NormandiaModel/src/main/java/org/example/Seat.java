package org.example;

import java.io.Serializable;
import java.util.Objects;

public class Seat extends Entity<Long> implements Serializable {
   // private int idRezervare;
    Rezervare rezervare;
    private int seatNumber;
    public Seat(Rezervare rezervare, int seatNumber)
    {
        this.rezervare=rezervare;
        this.seatNumber=seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Rezervare getRezervare() {
        return rezervare;
    }
    public void setRezervare(Rezervare rezervare) {
        this.rezervare = rezervare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Seat seat = (Seat) o;
        return seatNumber == seat.seatNumber && Objects.equals(rezervare, seat.rezervare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rezervare, seatNumber);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "rezervare=" + rezervare +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
