package com.example;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.example.db.Cassandra;
import com.example.model.Menu;
import com.example.model.MenuItem;
import org.springframework.data.cassandra.core.CassandraOperations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * RESTful service for menus (exposed at "menu" path)
 */
@Path("menu")
public class MenuRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Set<MenuItem>> getAll() {
        CassandraOperations cassandraOps = Cassandra.getInstance().getCassandraOperations();

        List<Menu> menus = cassandraOps.select(QueryBuilder.select().from("menu"), Menu.class);

        List<MenuItem> menuItems = cassandraOps.select(QueryBuilder.select().from("menuitem"), MenuItem.class);

        Map<String, Set<MenuItem>> toReturn = new HashMap<String, Set<MenuItem>>();

        for (Menu menu : menus) {
            if (!toReturn.containsKey(menu.getMenuId())) {
                toReturn.put(menu.getMenuId(), new HashSet<MenuItem>());
            }
        }

        for (MenuItem menuItem : menuItems) {
            if (toReturn.containsKey(menuItem.getMenuId())) {
                toReturn.get(menuItem.getMenuId()).add(menuItem);
            }
        }

        return toReturn;
    }
}
