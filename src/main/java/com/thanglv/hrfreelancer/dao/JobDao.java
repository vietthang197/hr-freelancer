package com.thanglv.hrfreelancer.dao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.engine.cfg.spi.ConfigurationPropertyChecker;
@ApplicationScoped
public class JobDao {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @PostConstruct
    public void test() {
        System.out.println("MyEntity Manager: " + entityManager);
    }
}
