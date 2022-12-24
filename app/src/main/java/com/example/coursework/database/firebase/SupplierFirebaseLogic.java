package com.example.coursework.database.firebase;

import android.content.Context;

import com.example.coursework.database.logics.SupplierLogic;
import com.example.coursework.database.models.SupplierModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SupplierFirebaseLogic {
    private final String TABLE = "supplier";

    private DatabaseReference database;

    public SupplierFirebaseLogic(){
        database = FirebaseDatabase.getInstance().getReference(TABLE);
    }

    public void syncSuppliers(Context context) {
        SupplierLogic logic = new SupplierLogic(context);

        logic.open();
        List<SupplierModel> models = logic.getFullList();
        logic.close();

        database.removeValue();

        for (SupplierModel model: models) {
            database.push().setValue(model);
        }
    }
}
