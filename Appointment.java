package com.domebarbershop;

import java.io.Serializable;

public class Appointment implements Serializable {
    private String customerName;
    private String barberName;
    private String service;
    private String date;
    private String time;
    private String paymentMethod;
    private String detail;

    public Appointment(String customerName,String barberName,String service,String date,String time,String paymentMethod,String detail){
        this.customerName = customerName;
        this.barberName = barberName;
        this.service = service;
        this.date = date;
        this.time = time;
        this.paymentMethod = paymentMethod;
        this.detail = detail;
    }

    public String getCustomerName(){ return customerName; }
    public String getBarberName(){ return barberName; }
    public String getService(){ return service; }
    public String getDate(){ return date; }
    public String getTime(){ return time; }
    public String getPaymentMethod(){ return paymentMethod; }
    public String getDetail(){ return detail; }

    public void setService(String service){ this.service = service; }
    public void setDate(String date){ this.date = date; }
    public void setTime(String time){ this.time = time; }
    public void setPaymentMethod(String paymentMethod){ this.paymentMethod = paymentMethod; }
    public void setDetail(String detail){ this.detail = detail; }
}
