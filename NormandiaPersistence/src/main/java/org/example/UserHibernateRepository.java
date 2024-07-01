package org.example;



import org.example.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UserHibernateRepository implements IRepository{
    public static User findbyId(int idClient) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            User user = session.createQuery("from User where id = :idClient", User.class)
                    .setParameter("idClient", idClient)
                    .uniqueResult();
            return user; // Returnăm utilizatorul găsit sau null dacă nu a fost găsit niciun utilizator
        }
    }

    @Override
    public void save(Object elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(elem));
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public Object findOne(Object o) {
        return null;
    }

    @Override
    public Object findOne(Client client) {
        return null;
    }

   // @Override
    public void save(User user) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(user));
    }




    public User findByUsername(String username) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            User user =  session.createSelectionQuery("from User where username=:username ", User.class)
                    .setParameter("username", username)
                    .getSingleResultOrNull();
          //  return user;
            if (user == null){
                return null;
            }
            return user;
        }
    }


}