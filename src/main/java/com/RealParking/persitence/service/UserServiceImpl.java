package com.RealParking.persitence.service;

import com.RealParking.persitence.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

public class UserServiceImpl implements UserService{

    private EntityManagerFactory emf;
    private EntityManager em;

    public UserServiceImpl() {
        emf = Persistence.createEntityManagerFactory("RealParkingPersistence");
        em = emf.createEntityManager();
    }

    @Override
    public List<User> findAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public List<User> findAllUserByUser(String filter) {
        String jpql = "SELECT u FROM User u WHERE u.username LIKE '%" + filter.toUpperCase() + "%'";
        return em.createQuery(jpql, User.class).getResultList();
    }

    @Override
    public User findUserById(User user) {
        return em.find(User.class,user.getIdUser());
    }

    @Override
    public User findUserByUser(User user) {
        //Chamo encontré una solución, pero lo malo es que lanza una excepción si no encuentra nada
        Query query = em.createQuery("select u from User u where u.username = ?1", User.class);
        query.setParameter(1, user.getUsername());
        return (User) query.getSingleResult();
    }

    @Override
    public void insertUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(User user) {
        em.getTransaction().begin();
        em.remove(em.merge(user));
        em.getTransaction().commit();
    }
}
