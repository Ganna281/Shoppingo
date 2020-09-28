package com.example.maps;

import java.util.ArrayList;

public class User {
    int ID;
    String name,email,password,birthday;
    ArrayList<CartItem> ShoppingCart;
    static int TotalPrice = 0;
    public User()
    {
        ShoppingCart = new ArrayList<>();
    }
    public void setData(int ID,String name,String email,String password,String birthday)
    {
        this.ID = ID;
        this.email = email;
        this.name = name;
        this.password = password;
    }

}
