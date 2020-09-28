package com.example.maps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.widget.Toast;

public class Shoppingo extends SQLiteOpenHelper {
    private static String dataBaseName = "shoppingo";

    private  static final String Table_Name = "Cart";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Image = "product_image";
    private static final String COLUMN_Name = "product_name";
    private static final String COLUMN_Price = "product_price";
    private static final String COLUMN_userID = "userID";

    private Context context;

    public Shoppingo(Context context) {
        super(context, dataBaseName, null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user (userID integer primary key autoincrement, name text not null, email text not null, password text not null, birthday text not null )");
        sqLiteDatabase.execSQL("create table category (categoryID integer primary key autoincrement, categoryname text not null, categoryimage integer not null)");
        sqLiteDatabase.execSQL("create table items (itemID integer primary key autoincrement, itemname text not null, itembrand text not null, itemprice integer not null," +
                " itemimage integer not null, itemmaterial text, itemcolor text, itemmodel text, numberOfPices integer, categoryID integer, FOREIGN KEY (categoryID) REFERENCES category (categoryID) )");
        sqLiteDatabase.execSQL("create table Cart (_id integer primary key autoincrement, product_image integer, product_name text, product_price integer,quantity integer, userID integer, FOREIGN KEY (userID) REFERENCES user (userID))");

        sqLiteDatabase.execSQL("INSERT INTO category (categoryname,categoryimage) VALUES ('Accessories', 2131099752)");
        sqLiteDatabase.execSQL("INSERT INTO category (categoryname,categoryimage) VALUES ('Bag',2131099744)");
        sqLiteDatabase.execSQL("INSERT INTO category (categoryname,categoryimage) VALUES ('clothes',2131099767)");
        sqLiteDatabase.execSQL("INSERT INTO category (categoryname,categoryimage) VALUES ('makeup',2131099810)");

        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Gold Watch','Rolex',1500, 2131099736,'Stainless steel','Gold','B17999',1,1)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Gold Necklace','lazurdy',1000, 2131099737,'Gold','Gold','MJ875',2,1)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Gold Necklace','lazurdy',1500, 2131099738,'Gold','Gold','GJ750',2,1)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Gold Ring','lazurdy',500, 2131099739,'Gold','Gold','FAJ86',3,1)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Block Watch','Accessorize',50, 2131099740,'Stainless steel','Black','YAW55',1,1)");

        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Hand bag','Louis Vuitton',180, 2131099745,'Leather','Light brown','ADE432',3,2)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Laptop sleeve','HP',20, 2131099746,'Leather','Pink','LP88',5,2)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('BackPack','Bershka',50, 2131099747,'Leather','Brown','90R00',5,2)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Hand bag','LC Wakiki',50, 2131099748,'Lether','Gray','BBGhh2',3,2)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('BackPack','zara',250, 2131099749,'Leather','Black','ERT88',1,2)");

        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Wide Pants','zara',50, 2131099768,null,'Yellow','B345N',5,3)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Striped Shirt','Bershka',30, 2131099770,null,'Pink','GH771',2,3)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Floral Pants','Bershka',60, 2131099771,null,'Black','XL556',1,3)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Shirt','Zara',20, 2131099772,null,'Black','T785',2,3)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Floral Jeans','Jenny',80, 2131099773,null,'Blue Jeans','W334g',3,3)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Floral Dresss','Jenny',90, 2131099774,null,'Black','P8532',3,3)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Jumper','Zara',50, 2131099775,null,'Orange','QS24',1,3)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Jacket','Zara',250, 2131099776,null,'off white','Jk9945',2,3)");

        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Lipgloss','Maybellin',120, 2131099804,null,'Number: 20','B345N',5,4)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Concelear','Fentyy Beauty',30, 2131099805,null,'Number: 2','GH771',2,4)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('EyeLinear','SHEIN',60, 2131099806,null,'Matt Waterproof','XL556',1,4)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Mascara','Loreal Paris',50, 2131099807,null,'Waterproof','T785',2,4)");
        sqLiteDatabase.execSQL("INSERT INTO items (itemname, itembrand, itemprice, itemimage, itemmaterial, itemcolor, itemmodel,numberOfPices, categoryID ) VALUES ('Lipstick','MAC',80, 2131099808,null,'Red','W334g',3,4)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
         sqLiteDatabase.execSQL("drop table if exists user");
        sqLiteDatabase.execSQL("drop table if exists category");
        sqLiteDatabase.execSQL("drop table if exists items");
        sqLiteDatabase.execSQL("drop table if exists Cart");
        onCreate(sqLiteDatabase);


    }

    public void AddUser(String name,String email,String password,String birthday)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("birthday",birthday);
        db.insert("user",null,contentValues);
        ProfileActivity.user.setData(getID(email),name,email,password,birthday);
        db.close();
    }

    public int getID (String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select userID from user where email=?",new String[]{email});
        cursor.moveToFirst();
        return cursor.getInt(0);

    }

    public Boolean checkEmail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        if (cursor.getCount()>0) // Email Exists in the DataBase
            return false; // cannot register
        return true; // can Register

    }

    public Boolean checkAccountInfo(String email,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            ProfileActivity.user.setData(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            return true;
        }
        return false;
    }


    public Cursor getAll(int id,String [] values) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    //    Cursor cursor = sqLiteDatabase.rawQuery("Select * from items where categoryID=1", null);
        Cursor cursor = sqLiteDatabase.query("items", values, "categoryID" + "='" + id + "'", null,null,null,null);
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        return cursor;
    }


    public Cursor getAll(String value,String [] values) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        //    Cursor cursor = sqLiteDatabase.rawQuery("Select * from items where categoryID=1", null);
        Cursor cursor = sqLiteDatabase.query("items", values, "itembrand" + "='" + value + "'", null,null,null,null);
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        return cursor;
    }


    public Cursor getAll(String value) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from items where itembrand=?", new String []{value});
       // Cursor cursor = sqLiteDatabase.query("items", values, "itembrand" + "='" + value + "'", null,null,null,null);
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        return cursor;
    }


    public Cursor getCategories()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from category ",null);
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        return cursor;

    }
    public int getCategoryName(int id)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("items", new String [] {"categoryname"}, "categoryID" + "='" + id + "'", null,null,null,null);
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public void AddCart(String name, int price, int image,int ID,int quantity){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_Image, image);
        cv.put(COLUMN_Name,  name);
        cv.put(COLUMN_Price,  price);
        cv.put(COLUMN_userID,ID);
        cv.put("quantity",quantity);

        Long result = sqLiteDatabase.insert(Table_Name,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor readCartData(String [] values,int id){
       // String query = "SELECT * FROM " + Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Cart", values, "userID" + "='" + id + "'", null,null,null,null);
      //  Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0)
            cursor.moveToFirst();

        return cursor;
    }
    public void UpdateQuantity(String name,int Quantity,String tableName,String columnName,String conditionColumn)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // String sql = "UPDATE "+ tableName +" SET " +columnName+ " = '"+Quantity+"' WHERE "+"itemname"+ " = "+name;

        ContentValues cv = new ContentValues();
        cv.put(columnName, Quantity);
        db.update(tableName, cv, conditionColumn + "= ?", new String[] {name});
    }
    public int getQuantity(String name)
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select numberOfPices from items where itemname=?",new String[]{name},null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public void DeleteCart(String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //long deleted =  sqLiteDatabase.delete(Table_Name, "id=", new String[]{rowID});
        int deleted = sqLiteDatabase.delete(Table_Name,"product_name=?",new String[]{name});
        if(deleted == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
