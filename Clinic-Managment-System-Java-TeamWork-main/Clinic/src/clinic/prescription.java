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
public class prescription {
    private String describe_medicine ;
    private String disease ;
    private int num_residency_days ;
    private Date date_pres ;
    private int bed_needed ;
    private int patient_id ;

    public prescription(int patient_id , Date date , String describe_medicine, int bed_needed, int num_residency_days , String disease ) {
        this.describe_medicine = describe_medicine;
        this.disease = disease;
        this.num_residency_days = num_residency_days;
        this.bed_needed = bed_needed;
        this.patient_id= patient_id;
        this.date_pres=date;
    }
   
    public prescription(String describe_medicine, Date date_pres, int bed_needed , int number_residency_days) {
        this.describe_medicine = describe_medicine;
        this.date_pres = date_pres;
        this.bed_needed = bed_needed;
        this.num_residency_days=number_residency_days;
    }

    public String getDescribe_medicine() {
        return describe_medicine;
    }

    public void setDescribe_medicine(String describe_medicine) {
        this.describe_medicine = describe_medicine;
    }

    public Date getDate_pres() {
        return date_pres;
    }

    public void setDate_pres(Date date_pres) {
        this.date_pres = date_pres;
    }

    public int getBed_needed() {
        return bed_needed;
    }

    public void setBed_needed(int bed_needed) {
        this.bed_needed = bed_needed;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getNum_residency_days() {
        return num_residency_days;
    }

    public void setNum_residency_days(int num_residency_days) {
        this.num_residency_days = num_residency_days;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
}
