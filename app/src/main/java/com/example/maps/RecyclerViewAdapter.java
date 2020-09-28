package com.example.maps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    List<Item> items;
    Context context;

    public RecyclerViewAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        Item item = items.get(position);
        myViewHolder.image.setImageResource(item.imagedrawable);
        myViewHolder.name.setText(item.name);
        myViewHolder.price.setText(String.valueOf(item.price)+"$");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price;
        TextView name;


        public MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != -1) {
                        Item item = items.get(getAdapterPosition());
                        Intent intent = new Intent (context,ProductDetails.class);
                        intent.putExtra("MyClass",item);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
