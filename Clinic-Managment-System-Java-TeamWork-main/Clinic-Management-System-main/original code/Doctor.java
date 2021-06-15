/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Doctor extends Person implements User{
    JFrame frame = new JFrame("Doctor");

     private String user_name, user_password;
   
    /* constractor*/
        public Doctor(int person_id, String person_name, String person_address, String person_mobile, String user_name, String user_password) {
        super(person_id, person_name, person_address,person_mobile);
        this.user_name = user_name;
        this.user_password = user_password;
        //Gui
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400, 120);
        frame.setResizable(false);
        frame.setLayout(null);
        
        JButton b1 = new JButton("Change Password");
        JButton b2 = new JButton("Logout");

        b1.setBounds(50, 400, 150, 30);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String oldpass = JOptionPane.showInputDialog(frame, "Enter Old Password");
                String newpass = JOptionPane.showInputDialog(frame, "Enter new Password");
                changepassword(oldpass, newpass);
            }
        });
        b2.setBounds(350, 400, 100, 30);
        b2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                logout();
            }
        });

        frame.add(b1);frame.add(b2);
        frame.setVisible(true);
    }

     //User_name
    public void setUser_name(String user_name)
    {
          for(int i=0;i<user_name.length();++i)
        {
            if(user_name.charAt(i)=='@' ||user_name.charAt(i)=='#' || user_name.charAt(i)=='$'
                   || user_name.charAt(i)=='%'||user_name.charAt(i)=='&' || user_name.charAt(i)=='*')
                {
                 System.out.println("invalid input for user_name , it can't contine any special character ");
                 break;
                }
             else
                {
                  this.user_name = user_name;
                }
        }
    }

   


    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void Describe_medicine(Patient patient, String medicine){
        patient.setDescribe_medicine(medicine);
        patient.reset_token_id();
    }
    
    public void Write_disease(Patient patient, String disease){
        patient.set_disease(disease);
    }
    
    public void need_bed(Patient patient, Boolean bed){
        patient.setNeed_bed(bed);
    }
    
    public void Number_residency_days(Patient patient, int Number_residency_days){
        patient.setNumber_residency_days(Number_residency_days);
    }
     //veiw patient inline
    public void viewpatient() {
        
    }   
    
    @Override
    public void changepassword(String user_old_password, String user_new_password) {
        if(user_old_password.equals(user_password)){
            user_password = user_new_password;
            JOptionPane.showMessageDialog(frame, "Successfully Update", "Successful", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(frame, "Old Password It's Not Correct", "Erorr", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    @Override
    public void logout() {
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
