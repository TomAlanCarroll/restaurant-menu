package com.example.db;

import com.example.model.Menu;
import com.example.model.MenuItem;
import org.springframework.data.cassandra.core.CassandraOperations;

import java.math.BigDecimal;
import java.util.UUID;

public class CassandraTestData {
    public static final String[][] MENUS = {
            {"Breakfast Menu", "menu1", "Orlando, FL"},
            {"Dinner Menu", "menu2", "Orlando, FL"},
            {"Dinner Menu", "menu2", "London, England"}
    };

    public static final Object[][] MENU_ITEMS = {
            {"Drinks", "menu1", "Coffee", "Freshly brewed coffee.", "USA", new BigDecimal(2)},
            {"Drinks", "menu1", "Orange Juice", "Freshly squeezed orange juice.", "USA", new BigDecimal(5)},
            {"Entrees", "menu1", "Eggs Benedict", "Perfectly cooked.", "USA", new BigDecimal(9)},
            {"Entrees", "menu1", "Prosciutto Frittata", "Imported prosciutto baked into a delicious frittata.",
                    "USA", new BigDecimal(10)},
            {"Drinks", "menu2", "House Wine", "Red or white.", "USA", new BigDecimal(5)},
            {"Entrees", "menu2", "Lasagna", "Made from scratch lasagna with a balanced meat sauce.",
                    "USA", new BigDecimal(12)},
            {"Entrees", "menu2", "Chicken Parmesan", "Pan fried chicken breast served with spaghetti.",
                    "USA", new BigDecimal(14)},
            {"Desserts", "menu2", "Tiramisu", "Delicious, subtle, and made fresh daily.",
                    "USA", new BigDecimal(5)},
            {"Drinks", "menu2", "House Wine", "Red or white.", "GBR", new BigDecimal(5)},
            {"Entrees", "menu2", "Lasagna", "Made from scratch lasagna with a balanced meat sauce.",
                    "GBR", new BigDecimal(14)},
            {"Entrees", "menu2", "Chicken Parmesan", "Pan fried chicken breast served with spaghetti.",
                    "GBR", new BigDecimal(16)},
            {"Desserts", "menu2", "Tiramisu", "Delicious, subtle, and made fresh daily.",
                    "GBR", new BigDecimal(6)},

    };

    public static void Load() {
        CassandraOperations cassandraOps = Cassandra.getInstance().getCassandraOperations();

        // Wipe out any previous data for the sake of testing
        cassandraOps.truncate("menu");

        for (int i = 0; i < MENUS.length; i++) {
            int j = 0;
            cassandraOps.insert(new Menu(UUID.randomUUID(), MENUS[i][j++], MENUS[i][j++], MENUS[i][j]));
        }

        for (int i = 0; i < MENU_ITEMS.length; i++) {
            int j = 0;
            cassandraOps.insert(new MenuItem(UUID.randomUUID(), (String)MENU_ITEMS[i][j++], (String)MENU_ITEMS[i][j++],
                    (String)MENU_ITEMS[i][j++],  (String)MENU_ITEMS[i][j++], (String)MENU_ITEMS[i][j++],
                    (BigDecimal)MENU_ITEMS[i][j]));
        }
    }
}
