package com.example.coursework.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coursework.R;
import com.example.coursework.database.logics.SupplierLogic;
import com.example.coursework.database.models.SupplierModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SupplierActivity extends AppCompatActivity {

    Button button_create;
    Button button_cancel;
    EditText edit_text_name;
    DatePicker date_picker_contract_execution;
    SupplierLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        int userId = getIntent().getExtras().getInt("userId");
        int id = getIntent().getExtras().getInt("id");

        logic = new SupplierLogic(this);

        button_create = findViewById(R.id.button_create);
        button_cancel = findViewById(R.id.button_cancel);
        edit_text_name = findViewById(R.id.edit_text_name);
        date_picker_contract_execution = findViewById(R.id.date_picker_contract_execution);

        if (id != 0){
            logic.open();
            SupplierModel model = logic.getElement(id);
            logic.close();

            edit_text_name.setText(model.getName());
            Date date = new Date(model.getContractExecution());
            int year = date.getYear() + 1900;
            int month = date.getMonth();
            int day = date.getDate();
            date_picker_contract_execution.init(year, month, day,null );
        }

        button_create.setOnClickListener(
                v -> {
                    Calendar date = new GregorianCalendar();
                    date.set( date_picker_contract_execution.getYear(), date_picker_contract_execution.getMonth(), date_picker_contract_execution.getDayOfMonth());
                    SupplierModel model = new SupplierModel(edit_text_name.getText().toString(),date.getTime().getTime(), userId);

                    logic.open();

                    if(id != 0){
                        model.setId(id);
                        logic.update(model);
                    } else {
                        logic.insert(model);
                    }

                    logic.close();
                    this.finish();
                }
        );

        button_cancel.setOnClickListener(
                v -> this.finish()
        );
    }
}