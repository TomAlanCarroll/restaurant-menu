package com.example.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;

import static org.junit.Assert.*;

public class MenuItemTest {
    NumberFormat defaultCurrencyStyle = DecimalFormat.getCurrencyInstance();
    BigDecimal[] prices = {
            new BigDecimal("0.000"),
            new BigDecimal("14.95"),
            new BigDecimal("6.09"),
            new BigDecimal("005000000.95")
    };

    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;
    MenuItem menuItem4;
    MenuItem nullMenuItem;
    MenuItem emptyMenuItem;

    @Before
    public void testMenuItemConstructor() {
        menuItem1 = new MenuItem(UUID.randomUUID(), "Entree", "Italian Restaurant Menu", "Chicken Parmesan",
                "Tasty homemade chicken parmesan.", "USA", prices[1]);
        menuItem2 = new MenuItem(UUID.randomUUID(), "Drink", "Italian Restaurant Menu", "House Cabernet",
                "Our signature House Cabernet.", "USA", prices[2]);
        menuItem3 = new MenuItem(UUID.randomUUID(), "Dessert", "Italian Restaurant Menu", "Tiramisu",
                "Delicious Tiramisu made fresh daily.", "USA", prices[3]);
        menuItem4 = new MenuItem(UUID.randomUUID(), "Drink", "Italian Restaurant Menu", "House Cabernet",
                "Our signature House Cabernet.", "ITA", prices[2]);
        nullMenuItem = new MenuItem(UUID.randomUUID(), null, null, null, null, null, prices[0]);
        emptyMenuItem = new MenuItem(UUID.randomUUID(), "", "", "", "", "", prices[0]);
    }

    @Test
    public void testGetPrice() {
        assertEquals("Price was not formatted correctly!", "$14.95", menuItem1.getFormattedPrice());
        assertEquals("Price was not formatted correctly!", "$6.09", menuItem2.getFormattedPrice());
        assertEquals("Price was not formatted correctly!", "$5,000,000.95", menuItem3.getFormattedPrice());
        assertEquals("Price was not formatted correctly!", "€ 6,09", menuItem4.getFormattedPrice());
        assertEquals("Price was not formatted correctly!", defaultCurrencyStyle.format(0), nullMenuItem.getFormattedPrice());
        assertEquals("Price was not formatted correctly!", defaultCurrencyStyle.format(0), emptyMenuItem.getFormattedPrice());
    }
}
