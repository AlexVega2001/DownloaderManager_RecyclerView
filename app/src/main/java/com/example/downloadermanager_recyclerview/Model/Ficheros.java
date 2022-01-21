package com.example.downloadermanager_recyclerview.Model;

public class Ficheros {
    private int id;
    private String description, tamanho, url;

    public Ficheros() {

    }

    public Ficheros(int Id ,String Description, String Tamanho, String Url) {
        this.id = Id;
        this.description = Description;
        this.tamanho = Tamanho;
        this.url = Url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
