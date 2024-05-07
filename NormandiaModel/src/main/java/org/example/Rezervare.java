package org.example;

import java.io.Serializable;
import java.util.Objects;

public class Rezervare extends Entity<Long>  implements Serializable {
   // private int idClient;
    //Client client;
    User user;
    Long id;
    Cursa cursa;
    //private int idCursa;
    private int locuri;

    /*public Rezervare(int idClient,int idCursa,int Locuri)
    {
        this.idClient=idClient;
        this.idCursa=idCursa;
        this.Locuri=Locuri;
    }*/
    public Rezervare(User user, Cursa cursa, int locuri)
    {
        this.locuri=locuri;
        //this.client=client;
        this.user=user;
        this.cursa=cursa;
    }


    public int getLocuri() {
        return locuri;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setLocuri(int locuri) {
        locuri = locuri;
    }

    public Cursa getCursa() {
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rezervare rezervare = (Rezervare) o;
        return locuri == rezervare.locuri && Objects.equals(user, rezervare.user) && Objects.equals(cursa, rezervare.cursa);
    }

    public User getClient() {
        return user;
    }

    public void setClient(User client) {
        this.user = client;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "client=" + user +
                ", cursa=" + cursa +
                ", Locuri=" + locuri +
                '}';
    }
}
