package com.crud.singl.eyehealth.entities;

public class Knowledge {

    private int id_know;
    private String type;
    private String name;
    private String detail;
    private String image;

    public Knowledge(int id_know, String type, String name, String detail, String image) {
        this.id_know = id_know;
        this.type = type;
        this.name = name;
        this.detail = detail;
        this.image = image;
    }

    public int getId_know() {
        return id_know;
    }

    public void setId_know(int id_know) {
        this.id_know = id_know;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
