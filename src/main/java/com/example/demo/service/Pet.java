package com.example.demo.service;

public class Pet {

    private String id;
    private String name;
    private String type;
    private String age;
    private String price;

    public Pet(String id, String name, String type, String age, String price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.price = price;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

}
