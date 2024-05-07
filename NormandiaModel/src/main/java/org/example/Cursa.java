package org.example;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Cursa extends Entity<Long>  implements Serializable {

    private Long id;
    private String destinatie;
    //private Date date;
    private Date date;
    private Time ora;
    //private int locuriDisponibile;

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public Cursa(String destinatie, Date date, Time ora)
    {
        this.destinatie=destinatie;
        this.date=date;
        this.ora=ora;
        //this.locuriDisponibile=locuriDisponibile;
    }

    public Time getOra() {
        return ora;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setOra(Time ora) {
        this.ora = ora;
    }

    public Date getDate() {
        return (Date) date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false;
        Cursa cursa = (Cursa) o;
        return Objects.equals(destinatie, cursa.destinatie) && Objects.equals(date, cursa.date) && Objects.equals(ora, cursa.ora);
    }
    public boolean equals1(Cursa o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       // if (!super.equals(o)) return false;
        Cursa cursa =  o;
        return Objects.equals(destinatie, cursa.destinatie) && Objects.equals(date, cursa.date) && Objects.equals(ora, cursa.ora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), destinatie, date, ora);
    }

    @Override
    public String toString() {

        return "Cursa{" +
                "destinatie='" + destinatie + '\'' +
                ", date=" + date +
                ", ora=" + ora +
                '}';
    }
}
