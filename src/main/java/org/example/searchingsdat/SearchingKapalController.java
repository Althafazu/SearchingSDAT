package org.example.searchingsdat;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchingKapalController {
    private static class Ship {
        private String name;
        private String country;
        private String type;
        private int year;
        private String fate;

        public Ship(String name, String country, String type, int year, String fate) {
            this.name = name;
            this.country = country;
            this.type = type;
            this.year = year;
            this.fate = fate;
        }

        public boolean matches(String keyword) {
            return name.contains(keyword)
                    || country.contains(keyword)
                    || type.contains(keyword)
                    || String.valueOf(year).contains(keyword)
                    || fate.contains(keyword);
        }
    }
}
