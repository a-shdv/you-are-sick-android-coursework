package com.example.coursework.database.models;

import com.google.firebase.database.Exclude;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class ReceiptModel {
    private int id;
    private long receiving_date;
    private long discharge_date;
    private int supplierId;
    private String supplierName;
    private List<ReceiptMedicinesModel> receiptMedicines = new LinkedList<>();

    public ReceiptModel(){

    }

    public ReceiptModel(long receiving_date, long discharge_date, int supplierId, String supplierName, List<ReceiptMedicinesModel> receiptMedicines){
        this.receiving_date = receiving_date;
        this.discharge_date = discharge_date;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.receiptMedicines = receiptMedicines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getReceiving_date() {
        return receiving_date;
    }

    public void setReceiving_date(long receiving_date) {
        this.receiving_date = receiving_date;
    }

    public long getDischarge_date() {
        return discharge_date;
    }

    public void setDischarge_date(long discharge_date) {
        this.discharge_date = discharge_date;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<ReceiptMedicinesModel> getReceiptMedicines() {
        return receiptMedicines;
    }

    public void setReceiptMedicines(List<ReceiptMedicinesModel> receiptMedicines) {
        this.receiptMedicines = receiptMedicines;
    }
}
