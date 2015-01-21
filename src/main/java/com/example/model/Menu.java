package com.example.model;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table("menu")
public class Menu {
    @PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID id;
    @PrimaryKeyColumn(name = "menuId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String menuId;
    @Column("type")
    private String type;
    @Column("location")
    private String location;

    public Menu(UUID id, String type, String menuId, String location) {
        this.id = id;
        this.type = type;
        this.menuId = menuId;
        this.location = location;
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

    public String getLocation() {
        return location;
    }
}
