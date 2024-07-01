package org.example;

/*import java.io.Serializable;

public class User extends Entity<Long> implements Serializable {
   // private Long id;
    private String username;
    private String password;
    public User(String username, String password)
    {
        this.username=username;
        this.password=password;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setUsername(String username)
    {
        this.username=username;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return "User{"+
                "username="+'\''+username+'\''+
                ",password="+'\''+password+'\'';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return this.getId().equals(that.getId()) && this.username.equals(that.username) && this.password.equals(that.password);
    }
}*/


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Entity;
import org.example.Entitate;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id"))

})
@Table(name="User")
public class User extends Entitate implements Serializable {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
