package com.kxwl.kxpro.entity;

/**the description of the goods
 * Created by answer on 2017-02-20.
 */
public class Goods {
    private String name;//the name of the goods
    private String barCode;// the barcode of the goods
    private String specification;//the sepecification of the goods,just like the volume 340ml
    private String brand;//the brand which the goods belong to,just like the cola belong to BaiShi
    private String origin;//the origin of the goods,for example China
    private String classification;//the classification of the goods which just called UNSPSC --The Universal Standard Products and Services Classification

    public Goods() {
        this.name=this.barCode=this.specification=this.brand=this.origin=this.classification="";
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
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
