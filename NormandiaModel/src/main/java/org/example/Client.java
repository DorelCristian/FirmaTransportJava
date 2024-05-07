package org.example;

import java.io.Serializable;
import java.util.Objects;

public class Client extends Entity<Long> implements Serializable {
    private String nume;
    private String password;

    public Client(String nume, String password) {
        this.nume = nume;
        this.password=password;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(nume, client.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nume);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nume='" + nume + '\'' +
                '}';
    }
}
