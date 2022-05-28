/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.sql.Date;

public class Patient extends Person{
    private int patient_id;
    private Date date;
    private String token_id;
    private int doctor_id;
    private Boolean need_bed;
    private String disease;
    private String describe_medicine;
    private float medicine_price=0;
    private float Bed_cost=0;
    private float resources_charges=0;
    private float Cost;
    
            /* constractor*/
        public Patient( String person_name, String person_mobile, String person_address , int doctor_id , String token_id  ,Date  date ) {
        super( person_name, person_address,person_mobile);
        this.token_id=token_id;
        this.doctor_id=doctor_id;
        this.date = date;
     
    }

    public Patient() {
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

     
    public void setDate(Date date)throws RuntimeException{
        this.date = date;
    }
      public void setToken_id(String token_id) {
        this.token_id = token_id;
    }
    
    public void setBed_cost(float Bed_cost) {
        this.Bed_cost = Bed_cost;
    }
    public void calcBed_cost(float Bed_cost) {
        this.Bed_cost += Bed_cost;
    }
    public void setNeed_bed(Boolean need_bed) {
        this.need_bed = need_bed;
    }

    public void set_disease(String disease) {
        this.disease = disease;
    }

    public void setDescribe_medicine(String describe_medicine) {
        this.describe_medicine = describe_medicine;
    }
    public void setResources_charges(float resources_charges) {
        this.resources_charges = resources_charges;
    }
    public void calcResources_charges(float resources_charges) {
        this.resources_charges += resources_charges;
    }
    public void setMedicine_price(float medicine_price) {
        this.medicine_price = medicine_price;
    }
    public void calcMedicine_price(float medicine_price) {
        this.medicine_price += medicine_price;
    }
    
    public void setCost(float Cost) {
        this.Cost = Cost;
    }

    public Date getDate() {
        return date;
    }
     
    public String get_token_id() {
        return token_id;
    }

    public float getBed_cost() {
        return Bed_cost;
    }
    
    public Boolean getNeed_bed() {
        return need_bed;
    }

    public float getResources_charges() {
        return resources_charges;
    }
    
     public String get_Disease() {
        return disease;
    }
     

   public String getDescribe_medicine() {
        return describe_medicine;
    }
   
   public float getMedicine_price() {
        return medicine_price;
    }
   
   public float getCost() {
        return Cost;
    }
    
    public void reset_token_id(){
        this.setToken_id("0");
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

     
  
   }
    