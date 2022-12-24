package com.example.coursework.database.firebase;

import android.content.Context;

import com.example.coursework.database.logics.MedicineLogic;
import com.example.coursework.database.logics.ReceiptLogic;
import com.example.coursework.database.models.MedicineModel;
import com.example.coursework.database.models.ReceiptModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ReceiptFirebaseLogic {
    private final String TABLE = "receipt";

    private DatabaseReference database;

    public ReceiptFirebaseLogic(){
        database = FirebaseDatabase.getInstance().getReference(TABLE);
    }

    public void syncReceipt(Context context) {
        ReceiptLogic logic = new ReceiptLogic(context);

        logic.open();
        List<ReceiptModel> models = logic.getFullList();
        logic.close();

        database.removeValue();

        for (ReceiptModel model: models) {
            database.push().setValue(model);
        }
    }
}
