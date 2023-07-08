package com.thanglv.hrfreelancer.dao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@ApplicationScoped
public class JobDao {
    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    @PostConstruct
    public void test() {
        System.out.println("MyEntity Manager: " + entityManager);
    }
}
