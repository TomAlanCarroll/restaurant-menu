package com.example.db;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.model.Menu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.cassandra.core.CassandraOperations;

import java.util.UUID;

public enum Cassandra {
    INSTANCE;

    private Cluster cluster;
    private Session session;
    private CassandraOperations cassandraOps;

    Cassandra()
    {
        // Get the config
        ApplicationContext context = new AnnotationConfigApplicationContext(CassandraConfig.class);

        // Connect to Cassandra
        try {
            cluster = (Cluster) context.getBean("cluster");

            session = (Session) context.getBean("session");

            cassandraOps = (CassandraOperations) context.getBean("cassandraOperationsTemplate");

            cassandraOps.insert(new Menu(UUID.randomUUID(), "Dinner Menu", "menu1", "Orlando, FL"));

//            Select s = QueryBuilder.select().from("menu");
//            s.where(QueryBuilder.eq("menuId", "menu1"));
//
//            cassandraOps.truncate("menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Static getter
    public static Cassandra getInstance()
    {
        return INSTANCE;
    }

    public Session getSession()
    {
        return session;
    }

    public CassandraOperations getCassandraOperations()
    {
        return cassandraOps;
    }
}
