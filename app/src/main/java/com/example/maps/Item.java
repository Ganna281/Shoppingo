package com.example.maps;

import java.io.Serializable;

public class Item implements Serializable {
    String name, brand, material, model, color;
    int price, imagedrawable, catergoryID,ID,quantity;

    public Item(int ID,String name, String brand, int price, int imagedrawable, String material, String color, String model,int quantity,int catergoryID) {
        this.price = price;
        this.name = name;
        this.imagedrawable = imagedrawable;
        this.brand = brand;
        this.material = material;
        this.model = model;
        this.color = color;
        this.ID = ID;
        this.catergoryID = catergoryID;
        this.quantity = quantity;

    }

  /*  class Clothes extends Item {

        Clothes(String name, String brand, int price, int imagedrawable, String material, String color, String model) {
            super(name, brand, price, imagedrawable, material, color, model);
            this.ID = 3;


        }
    }

    class Accessories extends Item {

        Accessories(String name, String brand, int price, int imagedrawable, String material, String color, String model) {
            super(name, brand, price, imagedrawable, material, color, model);


        }
    }

    class Shoes extends Item {

        Shoes(String name, String brand, int price, int imagedrawable, String material, String color, String model) {
            super(name, brand, price, imagedrawable, material, color, model);

        }
    }

    class Bag extends Item {

        Bag(String name, String brand, int price, int imagedrawable, String material, String color, String model) {
            super(name, brand, price, imagedrawable, material, color, model);

            this.ID = 2;
        }
    }

    class makeup extends Item {

        makeup(String name, String brand, int price, int imagedrawable, String material, String color, String model) {
            super(name, brand, price, imagedrawable, material, color, model);


        }
    }*/
}


