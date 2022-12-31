package com.example.kpopstore;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.EasyConnectStatusCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHolder>{
    Context context;
    List<ModelProduct> modelProducts;
    FloatingActionButton floatingActionButton;

    public AdapterProduct(Context context, List<ModelProduct> modelProducts, FloatingActionButton floatingActionButton) {
        this.context = context;
        this.modelProducts = modelProducts;
        this.floatingActionButton = floatingActionButton;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.produkcontainer,parent,false);
        return new  AdapterProduct.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String name = modelProducts.get(position).getName();
        String price = modelProducts.get(position).getPrice();
        String image = modelProducts.get(position).getImage();

        holder.produk_name.setText(name);
        holder.produk_price.setText(price);
        try {
            Picasso.get().load(image).into(holder.produk_image);

        } catch (Exception e) {
        }
        holder.addcartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(name,price,image);

                new CircleAnimationUtil().attachActivity((Activity)context ).setTargetView(holder.produk_image).setMoveDuration(1000).setDestView(floatingActionButton).setAnimationListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        ((MenuAlbum)context).getCartCount();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }
        });
    }
public int itemid=1;
    private void addToCart(String name, String price, String image) {

        Random random=new Random();
        itemid=random.nextInt(200000);

        EasyDB easyDB = EasyDB.init(context, "ITEM_DB")// "TEST" is the name of the DATABASE
                .setTableName("ITEM_DB")
                .addColumn(new Column("item_id",new String[]{"text","unique"})) // Contrains like "text", "unique", "not null" are not case sensitive
                .addColumn(new Column("item_name",new String[] {"text", "not null"}))
                .addColumn(new Column("item_price",new String[] {"text", "not null"}))
                .addColumn(new Column("item_image",new String[] {"text", "not null"}))
                .doneTableColumn();

        Boolean b=easyDB.addData("item_id",itemid)
                .addData("item_name",name)
                .addData("item_price",price)
                .addData("item_image",image)
                .doneDataAdding();
        Toast.makeText(context, "Added to Cart...", Toast.LENGTH_SHORT).show();


    }

    @Override
    public int getItemCount() {
        return modelProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView produk_image;
        TextView produk_name, produk_price;
        Button addcartButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            produk_image=itemView.findViewById(R.id.produkimage);
            produk_name=itemView.findViewById(R.id.produkname);
            produk_price=itemView.findViewById(R.id.produkprice);
            addcartButton=itemView.findViewById(R.id.cartbutton);
        }
    }

}
