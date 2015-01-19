package com.example.model;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

@Table
public class MenuItem {

    @PrimaryKeyColumn(name = "id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID id;
    @PrimaryKeyColumn(name = "type", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String type;
    @PrimaryKeyColumn(name = "menuId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String menuId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String locale;
    @Column
    private BigDecimal price;

    public MenuItem(UUID id, String type, String menuId, String name, String description, String locale, BigDecimal price) {
        this.id = id;
        this.type = type;
        this.menuId = menuId;
        this.name = name;
        this.description = description;
        this.locale = locale;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocale() {
        return locale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getFormattedPrice() {
        Locale currentLocale = null;
        NumberFormat currencyStyle = null;

        try {
            currentLocale = getLocaleObject();
            currencyStyle = DecimalFormat.getCurrencyInstance(currentLocale);
        } catch (Exception e) {
            // Unsupported locale
            currencyStyle = DecimalFormat.getCurrencyInstance();
        }

        return currencyStyle.format(price.doubleValue());
    }

    public Locale getLocaleObject() {
        if (this.locale != null && this.locale.trim().length() > 0) {
            Locale[] locales = Locale.getAvailableLocales();
            for (Locale locale : locales) {
                try {
                    if (locale.getISO3Country().equals(this.locale)) {
                        return locale;
                    }
                } catch (Exception e) {
                    // These aren't the locales you're looking for
                }
            }
        }

        return null;
    }
}
