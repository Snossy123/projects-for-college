/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

public class Patient extends Person{
       
    private String date;
    private int token_id;
    private int number_residency_days;
    private float Bed_cost;
    private float resources_charges;
    private Boolean need_bed;
     
    private String disease;
    private String describe_medicine;
    
    private float medicine_price;
    private float Cost;
    
            /* constractor*/
        public Patient(int person_id, String person_name, String person_address, String person_mobile) {
        super(person_id, person_name, person_address,person_mobile);

    }

    public void setDate(String date) {
        this.date = date;
    }
      public void setToken_id(int token_id) {
        this.token_id = token_id;
    }
    public void setNumber_residency_days(int number_residency_days) {
        this.number_residency_days = number_residency_days;
    }

    public void setBed_cost(float Bed_cost) {
        this.Bed_cost = Bed_cost;
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
    public void setMedicine_price(float medicine_price) {
        this.medicine_price = medicine_price;
    }
    
    public void setCost(float Cost) {
        this.Cost = Cost;
    }

    public String getDate() {
        return date;
    }
    public int getNumber_residency_days() {
        return number_residency_days;
    }
    public int get_token_id() {
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
        this.setToken_id(0);
    }
    
   public void viewprescription()
       {
      System.out.println("Date : ");
       System.out.println(this.getDate());
       
       System.out.println("Describe medicine: ");
       System.out.println(this.getDescribe_medicine());
       
       System.out.println("Need bed: ");
       if(this.getNeed_bed()){
           System.out.println("Yes");
       }else{
            System.out.println("No");
       }
       
       System.out.println("Number of residency days: ");
       System.out.println(this.getNumber_residency_days());
       }  
    }
    