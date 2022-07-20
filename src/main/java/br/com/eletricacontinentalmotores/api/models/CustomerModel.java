package br.com.eletricacontinentalmotores.api.models;

public class CustomerModel {

    private String name;

    public CustomerModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
