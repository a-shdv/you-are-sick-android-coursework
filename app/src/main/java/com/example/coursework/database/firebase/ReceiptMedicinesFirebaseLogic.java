package com.example.coursework.database.firebase;

import android.content.Context;

import com.example.coursework.database.logics.ReceiptMedicinesLogic;
import com.example.coursework.database.models.ReceiptMedicinesModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ReceiptMedicinesFirebaseLogic {
    private final String TABLE = "receiptMedicines";

    private DatabaseReference database;

    public ReceiptMedicinesFirebaseLogic(){
        database = FirebaseDatabase.getInstance().getReference(TABLE);
    }

    public void syncReceiptMedicines(Context context) {
        ReceiptMedicinesLogic logic = new ReceiptMedicinesLogic(context);

        logic.open();
        List<ReceiptMedicinesModel> models = logic.getFullList();
        logic.close();

        database.removeValue();

        for (ReceiptMedicinesModel model: models) {
            database.push().setValue(model);
        }
    }
}
