package com.RealParking.persitence.service;

import com.RealParking.persitence.entity.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

public class RoleServiceImpl implements RoleService{

    private EntityManagerFactory emf;
    private EntityManager em;

    public RoleServiceImpl() {
        emf = Persistence.createEntityManagerFactory("RealParkingPersistence");
        em = emf.createEntityManager();
    }

    @Override
    public List<Role> findAllRoles() {
        return em.createNamedQuery("Role.findAll").getResultList();
    }

    @Override
    public Role findRoleById(Role role) {
        return em.find(Role.class,role.getIdRol());
    }

    @Override
    public List<Role> findRoleByRole(String role) {
        String jpql = "SELECT r FROM Role r WHERE r.description LIKE '%" + role.toUpperCase() + "%'";
        return em.createQuery(jpql, Role.class).getResultList();
    }

    @Override
    public void insertRole(Role role) {
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
    }

    @Override
    public void updateRole(Role role) {
        em.getTransaction().begin();
        em.merge(role);
        em.getTransaction().commit();
    }

    @Override
    public void deleteRole(Role role) {
        em.getTransaction().begin();
        em.remove(em.merge(role));
        em.getTransaction().commit();
    }
}
