package com.example.coursework.database.logics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursework.database.DatabaseHelper;
import com.example.coursework.database.models.SupplierModel;

import java.util.ArrayList;
import java.util.List;

public class SupplierLogic {
    Context context;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "supplier";
    final String COLUMN_ID = "id";
    final String COLUMN_NAME = "name";
    final String COLUMN_CONTRACT_EXECUTION = "contract_execution";
    final String COLUMN_USERID = "user_id";

    public SupplierLogic(Context context) {
        this.context = context;
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public SupplierLogic open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<SupplierModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<SupplierModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            SupplierModel obj = new SupplierModel();

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
            obj.setContractExecution(cursor.getLong((int) cursor.getColumnIndex(COLUMN_CONTRACT_EXECUTION)));
            obj.setUserid(cursor.getInt((int) cursor.getColumnIndex(COLUMN_USERID)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public List<SupplierModel> getFilteredList(int userId) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_USERID + " = " + userId, null);
        List<SupplierModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            SupplierModel obj = new SupplierModel();

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
            obj.setContractExecution(cursor.getLong((int) cursor.getColumnIndex(COLUMN_CONTRACT_EXECUTION)));
            obj.setUserid(cursor.getInt((int) cursor.getColumnIndex(COLUMN_USERID)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public SupplierModel getElement(int id) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + id, null);
        SupplierModel obj = new SupplierModel();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
        obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
        obj.setContractExecution(cursor.getLong((int) cursor.getColumnIndex(COLUMN_CONTRACT_EXECUTION)));
        obj.setUserid(cursor.getInt((int) cursor.getColumnIndex(COLUMN_USERID)));
        return obj;
    }

    public void insert(SupplierModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME,model.getName());
        content.put(COLUMN_CONTRACT_EXECUTION,model.getContractExecution());
        content.put(COLUMN_USERID,model.getUserid());
        db.insert(TABLE,null,content);
    }

    public void update(SupplierModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_NAME,model.getName());
        content.put(COLUMN_CONTRACT_EXECUTION,model.getContractExecution());
        content.put(COLUMN_USERID,model.getUserid());
        String where = COLUMN_ID + " = " + model.getId();
        db.update(TABLE,content,where,null);
    }

    public void delete(int id) {
        String where = COLUMN_ID+" = "+id;
        db.delete(TABLE,where,null);
        ReceiptLogic receiptLogic = new ReceiptLogic(context);
        receiptLogic.deleteBySupplierId(id);
    }
    public void deleteByUserId(int userId) {
        String where = COLUMN_USERID+" = "+userId;
        db.delete(TABLE,where,null);
    }

}
