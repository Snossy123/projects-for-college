/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

/**
 *
 * @author aa
 */
public class WaitingList {
    private int doctor_id ;
    private int patient_id ;
    private int token_id ;

    public WaitingList(int token_id,int doctor_id, int patient_id) {
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.token_id = token_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getToken_id() {
        return token_id;
    }

    public void setToken_id(int token_id) {
        this.token_id = token_id;
    }
    
    
}
