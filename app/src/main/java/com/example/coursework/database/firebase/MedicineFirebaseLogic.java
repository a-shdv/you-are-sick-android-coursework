package com.example.coursework.database.firebase;

import android.content.Context;

import com.example.coursework.database.logics.MedicineLogic;
import com.example.coursework.database.models.MedicineModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MedicineFirebaseLogic {
    private final String TABLE = "medicine";

    private DatabaseReference database;

    public MedicineFirebaseLogic(){
        database = FirebaseDatabase.getInstance().getReference(TABLE);
    }

    public void syncMedicines(Context context) {
        MedicineLogic logic = new MedicineLogic(context);

        logic.open();
        List<MedicineModel> models = logic.getFullList();
        logic.close();

        database.removeValue();

        for (MedicineModel model: models) {
            database.push().setValue(model);
        }
    }

}
