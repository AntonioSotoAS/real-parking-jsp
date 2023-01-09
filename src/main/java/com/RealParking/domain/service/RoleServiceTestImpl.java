package com.RealParking.domain.service;

import com.RealParking.persitence.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class RoleServiceTestImpl implements RoleService{

    private EntityManagerFactory emf;
    private EntityManager em;

    public RoleServiceTestImpl() {
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
    public void insertRole(Role role) {
        em.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        em.merge(role);
    }

    @Override
    public void deleteRole(Role role) {
        em.remove(em.merge(role));
    }
}
