package com.polyblack.company.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("response")
    @Expose
    private List<Employee> response = null;

    public List<Employee> getEmployees() {
        return response;
    }

    public void setEmployees(List<Employee> employees) {
        this.response = employees;
    }
}

