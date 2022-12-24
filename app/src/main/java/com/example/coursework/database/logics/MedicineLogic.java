package com.example.coursework.database.logics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursework.database.DatabaseHelper;
import com.example.coursework.database.models.MedicineModel;

import java.util.ArrayList;
import java.util.List;

public class MedicineLogic {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "medicine";
    final String COLUMN_ID = "id";
    final String COLUMN_NAME = "name";
    final String COLUMN_TYPE = "type";
    final String COLUMN_PRICE = "price_per_package";

    public MedicineLogic(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public MedicineLogic open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<MedicineModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<MedicineModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            MedicineModel obj = new MedicineModel();

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
            obj.setType(cursor.getString((int) cursor.getColumnIndex(COLUMN_TYPE)));
            obj.setPrice_per_package(cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public MedicineModel getElement(int id) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + id, null);
        MedicineModel obj = new MedicineModel();
        if (!cursor.moveToFirst()) {
            return null;
        }

        obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
        obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
        obj.setType(cursor.getString((int) cursor.getColumnIndex(COLUMN_TYPE)));
        obj.setPrice_per_package(cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE)));

        return obj;
    }

    public void insert(MedicineModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME,model.getName());
        content.put(COLUMN_TYPE,model.getType());
        content.put(COLUMN_PRICE,model.getPrice_per_package());
        db.insert(TABLE,null,content);
    }

    public void update(MedicineModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_NAME,model.getName());
        content.put(COLUMN_TYPE,model.getType());
        content.put(COLUMN_PRICE,model.getPrice_per_package());
        String where = COLUMN_ID + " = " + model.getId();
        db.update(TABLE,content,where,null);
    }

    public void delete(int id) {
        String where = COLUMN_ID+" = "+id;
        db.delete(TABLE,where,null);
    }
}
