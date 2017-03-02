package com.kxwl.kxpro.entity;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by SEELE on 2017/3/2.
 */
public class ClassificationEntity {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    public ClassificationEntity() {
    }

    public ClassificationEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
