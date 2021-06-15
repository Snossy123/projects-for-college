/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.sql.Date;

/**
 *
 * @author aa
 */
public class Bill {
    private int PatientId , DoctorId;
    private String Medicine;
    private Date date;
    private float Medicine_price , Resources_Charge , Bed_Cost , Total;

    public Bill(int PatientId, int DoctorId, String Medicine, Date date, float Medicine_price, float Resources_Charge, float Bed_Cost, float Total) {
        this.PatientId = PatientId;
        this.DoctorId = DoctorId;
        this.Medicine = Medicine;
        this.date = date;
        this.Medicine_price = Medicine_price;
        this.Resources_Charge = Resources_Charge;
        this.Bed_Cost = Bed_Cost;
        this.Total = Total;
    }

    public int getPatientId() {
        return PatientId;
    }

    public int getDoctorId() {
        return DoctorId;
    }

    public String getMedicine() {
        return Medicine;
    }

    public Date getDate() {
        return date;
    }

    public float getMedicine_price() {
        return Medicine_price;
    }

    public float getResources_Charge() {
        return Resources_Charge;
    }

    public float getBed_Cost() {
        return Bed_Cost;
    }

    public float getTotal() {
        return Total;
    }
    
}
