package com.kxwl.kxpro.entity;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by SEELE on 2017/3/2.
 */
public class GoodsEntity {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;//the name of the goods
    @DatabaseField
    private String barCode;// the barcode of the goods
    @DatabaseField
    private String specification;//the sepecification of the goods,just like the volume 340ml
    @DatabaseField
    private String brand;//the brand which the goods belong to,just like the cola belong to BaiShi
    @DatabaseField
    private String origin;//the origin of the goods,for example China
    @DatabaseField(foreign = true,columnName = "classification_id")
    private ClassificationEntity classification;//the classification of the goods which just called UNSPSC --The Universal Standard Products and Services Classification

    public GoodsEntity() {
    }

    public GoodsEntity(int id, String name, String barCode, String specification, String brand, String origin, ClassificationEntity classification) {
        this.id = id;
        this.name = name;
        this.barCode = barCode;
        this.specification = specification;
        this.brand = brand;
        this.origin = origin;
        this.classification = classification;
    }

    public ClassificationEntity getClassification() {
        return classification;
    }

    public void setClassification(ClassificationEntity classification) {
        this.classification = classification;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", barCode='" + barCode + '\'' +
                ", specification='" + specification + '\'' +
                ", brand='" + brand + '\'' +
                ", origin='" + origin + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}

