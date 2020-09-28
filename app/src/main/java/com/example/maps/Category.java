package com.example.maps;
import java.io.Serializable;

public class Category implements Serializable {
    String name;
    int id;
    int imagedrawable;

   public Category(int id,String name,int imagedrawable)
   {
       this.id = id;
       this.name =name;
       this.imagedrawable = imagedrawable;
   }
}
