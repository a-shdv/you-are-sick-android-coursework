package com.example.coursework.database.logics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursework.database.DatabaseHelper;
import com.example.coursework.database.models.ReceiptMedicinesModel;
import com.example.coursework.database.models.ReceiptModel;

import java.util.ArrayList;
import java.util.List;

public class ReceiptMedicinesLogic {
    Context context;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "receipt_medicines";
    final String COLUMN_ID = "id";
    final String COLUMN_RECEIPT_ID = "receipt_id";
    final String COLUMN_MEDICINE_ID = "medicine_id";
    final String COLUMN_COUNT = "count";

    public ReceiptMedicinesLogic(Context context) {
        this.context = context;
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public ReceiptMedicinesLogic open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<ReceiptMedicinesModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<ReceiptMedicinesModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            ReceiptMedicinesModel obj = new ReceiptMedicinesModel();

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setReceiptId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_RECEIPT_ID)));
            obj.setMedicineId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_MEDICINE_ID)));
            obj.setCount(cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNT)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public List<ReceiptMedicinesModel> getFilteredList(int receiptId) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_RECEIPT_ID + " = " + receiptId, null);
        List<ReceiptMedicinesModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            ReceiptMedicinesModel obj = new ReceiptMedicinesModel();

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setReceiptId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_RECEIPT_ID)));
            obj.setMedicineId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_MEDICINE_ID)));
            obj.setCount(cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNT)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public ReceiptMedicinesModel getElement(int id) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + id, null);

        ReceiptMedicinesModel obj = new ReceiptMedicinesModel();

        if (!cursor.moveToFirst()) {
            return null;
        }

        obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
        obj.setReceiptId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_RECEIPT_ID)));
        obj.setMedicineId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_MEDICINE_ID)));
        obj.setCount(cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNT)));

        return obj;
    }

    public void insert(ReceiptMedicinesModel model) {
        ReceiptLogic receiptLogic = new ReceiptLogic(context);
        List<ReceiptModel> receipts = receiptLogic.getFullList();
        ContentValues content = new ContentValues();
        if (model.getReceiptId() == 0) {
            content.put(COLUMN_RECEIPT_ID,receipts.get(receipts.size()-1).getId());
        } else {
            content.put(COLUMN_RECEIPT_ID,model.getReceiptId());
        }
        content.put(COLUMN_MEDICINE_ID,model.getMedicineId());
        content.put(COLUMN_COUNT,model.getCount());
        db.insert(TABLE,null,content);
    }

    public void update(ReceiptMedicinesModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_RECEIPT_ID,model.getReceiptId());
        content.put(COLUMN_MEDICINE_ID,model.getMedicineId());
        content.put(COLUMN_COUNT,model.getCount());
        String where = COLUMN_ID + " = " + model.getId();
        db.update(TABLE,content,where,null);
    }

    public void delete(int id) {
        String where = COLUMN_ID+" = "+id;
        db.delete(TABLE,where,null);
    }

    public void deleteByReceiptId(int receiptId) {
        String where = COLUMN_RECEIPT_ID+" = "+receiptId;
        db.delete(TABLE,where,null);
    }
}
