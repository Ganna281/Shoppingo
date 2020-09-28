package com.example.maps;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MyViewHolder> {
    List<Category> categories;
    Context context;

    public MenuRecyclerViewAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        Category category = categories.get(position);
        myViewHolder.btn.setBackgroundResource(category.imagedrawable);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       Button btn;


        public MyViewHolder(View itemView) {
            super(itemView);
           btn = itemView.findViewById(R.id.category);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != -1) {
                        Category category = categories.get(getAdapterPosition());
                        String []values = new String[] {"itemID","itemname", "itembrand", "itemprice", "itemimage", "itemmaterial", "itemcolor", "itemmodel","numberOfPices"};
                        ArrayList<Item> items = new ArrayList<>();
                        Shoppingo db = new Shoppingo(context);
                        Cursor cursor = db.getAll(category.id,values);
                        while (!cursor.isAfterLast())
                        {
                            items.add(new Item(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8),category.id));
                            cursor.moveToNext();
                        }
                        Intent intent = new Intent (context,ViewProducts.class);
                        intent.putExtra("MyClass", (Serializable) items);
                        intent.putExtra("category", (Serializable) category.name);

                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
