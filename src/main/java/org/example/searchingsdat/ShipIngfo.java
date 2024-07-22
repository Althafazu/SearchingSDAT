package org.example.searchingsdat;

import java.util.Date;

public class ShipIngfo {
//    ID,Ship,Country,Type,Produksi,Fate

    private int id;
    private String name;
    private String country;
    private String type;
    private String  produksi;
    private String fate;

    public ShipIngfo(int id, String name, String country, String type, String produksi, String fate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.type = type;
        this.produksi = produksi;
        this.fate = fate;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduksi() {
        return produksi;
    }

    public void setProduksi(String produksi) {
        this.produksi = produksi;
    }

    public String getFate() {
        return fate;
    }

    public void setFate(String fate) {
        this.fate = fate;
    }
}
