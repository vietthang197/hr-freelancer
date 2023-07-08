package com.thanglv.hrfreelancer;

import com.thanglv.hrfreelancer.entity.CorpIndustry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@ApplicationScoped
public class App {

    private final Logger logger = LogManager.getLogger();

    public App() {

    }

    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    @PostConstruct
    public void initIndex() {
        logger.info("Init Hibernate search massIndexer");
        SearchSession searchSession = Search.session( entityManager );

        MassIndexer indexer = searchSession.massIndexer(CorpIndustry.class)
                .threadsToLoadObjects( 7 );

        try {
            indexer.startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
