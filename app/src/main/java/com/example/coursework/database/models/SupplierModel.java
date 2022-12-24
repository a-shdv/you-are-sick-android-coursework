package com.example.coursework.database.models;

public class SupplierModel {
    private int id;
    private String name;
    private long contractExecution;
    private int userid;

    public SupplierModel(String name, long contractExecution, int userid){
        this.name = name;
        this.contractExecution = contractExecution;
        this.userid = userid;
    }

    public SupplierModel(){

    }

    public int getId() {
        return id;
    }

/*    public int getById(int id) {
        if (id == this.id) {
            return SupplierModel.class;
        }
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContractExecution() {
        return contractExecution;
    }

    public void setContractExecution(long contractExecution) {
        this.contractExecution = contractExecution;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
