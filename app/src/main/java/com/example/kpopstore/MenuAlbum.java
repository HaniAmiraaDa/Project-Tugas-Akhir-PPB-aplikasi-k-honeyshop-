package com.example.kpopstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.security.cert.PolicyNode;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class MenuAlbum extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    List<ModelProduct> modelProducts;
    AdapterProduct adapterProduct;
    TextView cartcounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_album);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView=findViewById(R.id.produkrecycleview);
        floatingActionButton=findViewById(R.id.floatingcart);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuAlbum.this,Cart.class));
            }
        });
        modelProducts=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MenuAlbum.this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);
        cartcounter=findViewById(R.id.cartcounter);
        getCartCount();

        getProducts();
        
    }

    public void getCartCount() {
    CartCouter cartCouter=new CartCouter(MenuAlbum.this);
    int count= cartCouter.cartCount();
    cartcounter.setText(""+count);

    }
    private void getProducts(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("product");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelProducts.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    ModelProduct modelProduct=snapshot.getValue(ModelProduct.class);
                    modelProducts.add(modelProduct);
                    adapterProduct=new AdapterProduct(MenuAlbum.this,modelProducts,floatingActionButton);
                    recyclerView.setAdapter(adapterProduct);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}