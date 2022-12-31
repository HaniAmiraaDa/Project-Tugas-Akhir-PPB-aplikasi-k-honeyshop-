package com.example.kpopstore;

import android.content.Context;
import android.database.Cursor;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class CartCouter {
    Context context;

    public CartCouter(Context context){
        this.context = context;
    }

    public int cartCount(){
        int count=0;
        EasyDB easyDB = EasyDB.init(context, "ITEM_DB")// "TEST" is the name of the DATABASE
                .setTableName("ITEM_DB")
                .addColumn(new Column("item_id",new String[]{"text","unique"})) // Contrains like "text", "unique", "not null" are not case sensitive
                .addColumn(new Column("item_name",new String[] {"text", "not null"}))
                .addColumn(new Column("item_price",new String[] {"text", "not null"}))
                .addColumn(new Column("item_image",new String[] {"text", "not null"}))
                .doneTableColumn();
        Cursor res=easyDB.getAllData();
        while (res.moveToNext()){
            count++;
        }
return count;
    }

}
