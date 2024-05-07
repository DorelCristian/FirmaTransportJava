package org.example;


//import domain.Client;

import java.util.List;
/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in repository
 */
public interface IRepository<ID,E  > {


   /* Optional<E> findOne(ID id);


 //   Optional<E>findUser(String firstName);

    Iterable<E> findAll();


    Optional<E> save(E entity);



    Optional<E> delete(Long id);


    Optional<E> update(Long id);*/
    void save(E elem);
    void update(ID id);

 void update();

 void delete(ID id);
    List<E> findAll();
    E findOne(ID id);

    Object findOne(Client client);
}