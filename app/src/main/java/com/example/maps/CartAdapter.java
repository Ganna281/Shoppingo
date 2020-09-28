package com.example.maps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolde> {
    ArrayList<CartItem> cartItems;
    Context context;


    public CartAdapter (Context context, ArrayList<CartItem>cartItems)
    {
        this.context = context;
        this.cartItems = cartItems;
        Toast.makeText(context, String.valueOf(cartItems.size()), Toast.LENGTH_SHORT).show();
    }
    @Override
    public MyViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
        return new CartAdapter.MyViewHolde(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolde holder, final int position) {
        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
        final CartItem cartItem = cartItems.get(position);
        holder.image.setImageResource(cartItem.image);
        holder.name.setText(cartItem.name);
        holder.price.setText(String.valueOf(cartItem.price));

        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ConfirmDeletion(position, cartItem);
                return true;
            }
        });

        holder.btn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                //update number of products

                Shoppingo db = new Shoppingo(context);
                int AvilablePices = db.getQuantity(cartItem.name);
                holder.btn.setNumber(String.valueOf(cartItem.quantity));

                if (newValue>oldValue)
                {
                    if (AvilablePices>0) {
                        cartItem.quantity++;
                        db.UpdateQuantity(cartItem.name,AvilablePices-1,"items","numberOfPices","itemname");
                        db.UpdateQuantity(cartItem.name,cartItem.quantity,"Cart","quantity","product_name");
                    }
                    else
                    {

                        Toast.makeText(context,"Sold Out!!",Toast.LENGTH_SHORT).show();
                        holder.btn.setNumber(String.valueOf(oldValue));


                    }
                }
                else
                {
                    cartItem.quantity--;
                    db.UpdateQuantity(cartItem.name,AvilablePices+1,"items","numberOfPices","itemname");
                    db.UpdateQuantity(cartItem.name,cartItem.quantity,"Cart","quantity","product_name");
                }

            }
        });
    }

    private void removeItem(CartItem cartItem) {
        int positionTobeRemoved = cartItems.indexOf(cartItem);
        cartItems.remove(positionTobeRemoved);
        notifyItemRemoved(positionTobeRemoved);
    }
    void ConfirmDeletion(final int position, final CartItem cartItem){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Deleta a Cart");
        builder.setMessage("Are you sure you want to delete this cart?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Shoppingo DB = new Shoppingo(context);
                int AvilablePices = DB.getQuantity(cartItem.name);
                DB.UpdateQuantity(cartItem.name,cartItem.quantity+AvilablePices,"items","numberOfPices","itemname");
                DB.DeleteCart(cartItem.name);
                User.TotalPrice -= cartItem.price;
                if(User.TotalPrice < 0)
                    User.TotalPrice = 0;
                removeItem(cartItem);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class MyViewHolde extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView price;
        ElegantNumberButton btn;
        public MyViewHolde(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            btn = (ElegantNumberButton)itemView.findViewById(R.id.amount);

            btn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                    //update number of products

                }
            });

        }
    }
}