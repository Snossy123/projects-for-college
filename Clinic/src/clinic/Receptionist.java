
package clinic;

import com.mysql.jdbc.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Receptionist extends Person implements User{ 
     
            

   // connect with database
    public Connection fun () throws SQLException
    {
        connection makeconn = new connection();
            
        Connection conn = makeconn.conn();
        
        return conn;
    }
    //frames are used in receptionist page 
    JFrame frame = new JFrame("Receptionest");
    JFrame frame2 = new JFrame("Add Patient");
    JFrame frame3 = new JFrame("view patient");
    JFrame frame4 = new JFrame("view Prescription");
    JFrame frame5 = new JFrame("patient Inline");

   //valiables local 
    private String user_name, user_password;
    private int ReceptionId;
    private String price = "";
    /* constractor  this constructor to make a new receptionist */
    public Receptionist(String person_name, String person_mobile, String person_address, String user_name, String user_password) throws SQLException {
    super( person_name, person_address,person_mobile);
    this.user_name = user_name;
    this.user_password = user_password;
    }
    
    //this constractor user to make frame to receptionist  
    public Receptionist(String user_name, String user_password) throws SQLException {
    
        this.user_name = user_name;
        this.user_password = user_password;

//Gui of home page of receptionist
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(400, 120);
    frame.setResizable(false);
    frame.setLayout(null);
    //img background
    ImageIcon iconback = new ImageIcon("ig.jpg");
    JLabel imgback = new JLabel(iconback);
    imgback.setBounds(0, -40, 500, 540);
        //profile image
            JLabel profile = new JLabel(""+user_name);//create label and set text with username
            profile.setBounds(150, 30, 170, 150);//set bound of label
            profile.setFont(new Font("Arial", Font.BOLD, 20));
            ImageIcon icon = new ImageIcon("reception.png");//image of profile
            profile.setHorizontalTextPosition(JLabel.CENTER);//position of words accordding image left or right or center
            profile.setVerticalTextPosition(JLabel.BOTTOM);//position of words accordding image top or bottom
            profile.setIconTextGap(20);//gap bettween image and text 

            Image img = icon.getImage();//convert ImageIcon to image to deal with her scale
            Image ImgScale = img.getScaledInstance(profile.getWidth(), profile.getHeight()-50, img.SCALE_SMOOTH);//set image scale 
            ImageIcon Imageicon = new ImageIcon(ImgScale);//return to original state ImageIco
            profile.setIcon(Imageicon);//set lable (profile) imgIcon of it
        //buttons  are used in frame Receptionist
            // button change password
                JButton b1 = new JButton("Change Password");
                b1.setBounds(50, 400, 200, 30);
                b1.setFont(new Font("Arial", Font.BOLD, 15));
            // button Logout 
                JButton b2 = new JButton("Logout");
                b2.setFont(new Font("Arial", Font.BOLD, 15));
                b2.setBounds(350, 400, 100, 30);
            // button Add patient
                JButton b3 = new JButton("Add Patient");
                b3.setFont(new Font("Arial", Font.BOLD, 15));
                b3.setBounds(150, 200, 200, 30);
            // button submit in frame2 (Add patient) 
                JButton b4 = new JButton("Submit");
                b4.setFont(new Font("Arial", Font.BOLD, 15));
            // button back in frame2 (Add patient) 
                JButton b5 = new JButton("Back");
                b5.setFont(new Font("Arial", Font.BOLD, 15));
            // button View patient
                JButton b6 = new JButton("View patient");
                b6.setFont(new Font("Arial", Font.BOLD, 15));
                b6.setBounds(150, 250, 200, 30);
            // button patient Inline 
                JButton b7 = new JButton("patient Inline");
                b7.setFont(new Font("Arial", Font.BOLD, 15));
                b7.setBounds(150, 300, 200, 30);
            // button View Prescription
                JButton b8 = new JButton("View Prescription");
                b8.setFont(new Font("Arial", Font.BOLD, 15));
                b8.setBounds(150, 350, 200, 30);
            // button patient inline
                JButton b444 = new JButton("View");
                b444.setFont(new Font("Arial", Font.BOLD, 15));
                b444.setBounds(50, 420, 150, 30);
            //button view Prescription
                JButton b4444 = new JButton("View");
                b4444.setFont(new Font("Arial", Font.BOLD, 15));
                b4444.setBounds(50, 420, 150, 30);
            //button Submit in frame view patient
                JButton b44 = new JButton("View");
                b44.setFont(new Font("Arial", Font.BOLD, 15));
                b44.setBounds(50, 420, 150, 30);
        //Functions of each buttons are used in frame Receptionist
            
            //change password of receptionist
                b1.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String username = JOptionPane.showInputDialog(frame, "Enter username");
                        String oldpass = JOptionPane.showInputDialog(frame, "Enter Old Password");
                        String newpass = JOptionPane.showInputDialog(frame, "Enter new Password");
                        changepassword(username, oldpass, newpass);
                    }
                });
       
            // Logout 
                b2.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        logout();
                    }
                });
            
            //Add Patient Frame components
                //Labels  are used in frame add patient 
                    //Enter Patient Name
                    JLabel l2 = new JLabel("Enter Patient Name");
                    l2.setBounds(50, 50, 200, 30);
                    l2.setFont(new Font("Arial", Font.BOLD, 16));
                    //Enter Patient Address
                    JLabel l3 = new JLabel("Enter Patient Address");
                    l3.setBounds(50, 100, 200, 30);
                    l3.setFont(new Font("Arial", Font.BOLD, 16));
                    //Enter Patient mobile
                    JLabel l4 = new JLabel("Enter Patient mobile");
                    l4.setBounds(50, 150, 200, 30);
                    l4.setFont(new Font("Arial", Font.BOLD, 16));
                    //enter Date
                    JLabel l6 = new JLabel("Date");
                    l6.setBounds(50, 200, 200, 30);
                    l6.setFont(new Font("Arial", Font.BOLD, 16));
                    // enter Doctor ID of patient 
                    JLabel l7 = new JLabel("Doctor Name");
                    l7.setBounds(50, 250, 200, 30);
                    l7.setFont(new Font("Arial", Font.BOLD, 16));


                //Buttons  are used in frame add patient 
                    //Enter Patient Name
                    JTextField t2 = new JTextField();
                    t2.setBounds(300, 50, 160, 30);
                    t2.setFont(new Font("Arial", Font.BOLD, 16));
                     //Enter Patient Address
                    JTextField t3 = new JTextField();
                    t3.setBounds(300, 100, 160, 30);
                    t3.setFont(new Font("Arial", Font.BOLD, 16));
                     //Enter Patient mobile
                    JTextField t4 = new JTextField();
                    t4.setBounds(300, 150, 160, 30);
                    t4.setFont(new Font("Arial", Font.BOLD, 16));
                    //enter Date
                    JTextField t6 = new JTextField();
                    t6.setBounds(300, 200, 160, 30);
                    t6.setFont(new Font("Arial", Font.BOLD, 16));
                    // enter Doctor ID of patient 
                     //create combobox 
                    JComboBox<String> comboBox = new JComboBox<>(new Vector<>(doctors_names()));
                     comboBox.setBounds(300, 250, 160, 30);
            //Gui of frame of add patient             
                    frame2.setSize(500, 500);
                    frame2.setLocation(400, 120);
                    frame2.setResizable(false);
                    frame2.setLayout(null);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add patient action
            b3.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                //Functions of each buttons are used in frame Add patient 
                    //add components to frame add patient 
                            frame2.add(b4);frame2.add(b5);frame2.add(l7);frame2.add(comboBox);
                            frame2.add(l2);frame2.add(l3);frame2.add(l4); frame2.add(l6);
                            frame2.add(t2);frame2.add(t3);frame2.add(t4); frame2.add(t6);
                        //Show frame of add patient    
                            frame2.setVisible(true);frame2.add(imgback);
                        //hide frame of receptionist
                            frame.setVisible(false);
                        //to close programe properly    
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
                //btn submit action 
                        b4.setBounds(50, 420, 150, 30);
                        b4.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                //create new patient 
                                Patient pat = new Patient();
                                 if(t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals("")||t6.getText().equals("") ){
                                  JOptionPane.showMessageDialog(frame2,"Please Enter valid format......","Failed", JOptionPane.ERROR_MESSAGE);

                                 } 
                                 else{
                                    //patient details
                                    pat.setPerson_name(t2.getText());
                                    pat.setPerson_address(t3.getText());
                                    pat.setPerson_mobile(t4.getText());
                                    //token id equal 0 initial but it update after add patient to patient table 
                                   //then to waiting list and the number patient from waiting list will add to token id 
                                    pat.setToken_id("0");
                                    //in date section may fromate of date is wrong  
                                    try{  
                                            pat.setDate(Date.valueOf(t6.getText()));
                                            
                                                int select = retrive_doc_id(comboBox.getSelectedItem().toString());
                                                pat.setDoctor_id(select);
                                                    //insert patient in table patient in database
                                                 insert_pat(pat);
                                                 //insert patient in table patient in database
                                                 insert_tokenline(retrive_patient_id() ,  pat.getDoctor_id());
                                                 update_tokenId (retrive_token_id(),retrive_patient_id());
                                                //set the values of textfields
                                                 t2.setText("");t3.setText("");t4.setText(""); t6.setText(""); 
                                            
                                    
                                    }
                                    catch(RuntimeException ex){
                                          JOptionPane.showMessageDialog(frame2,"Please Enter valid dateformat year-month-day......","Failed", JOptionPane.ERROR_MESSAGE);
                                    }
                                    
                            }
                            }
                        });
                //btn back section
                    b5.setBounds(300, 400, 150, 30);
                    b5.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                             //set the values of textfields
                            t2.setText("");t3.setText("");t4.setText("");t6.setText("");
                            //hide frame of add patient
                            frame2.setVisible(false);
                            //to close programe properly
                            //Show frame of receptionist
                            frame.setVisible(true);frame.add(imgback);
                        }
                    });

            //Add Patient Frame components
                //Labels view patient
                //Enter patient Id
                JLabel labelsearch = new JLabel("Enter patient Id");
                labelsearch.setBounds(50, 50, 130, 30);
                labelsearch.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient Id
                JLabel l11 = new JLabel("Patient Id"); 
                l11.setBounds(50, 100, 130, 30);
                l11.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient Name
                JLabel l22 = new JLabel("Patient Name");
                l22.setBounds(50, 150, 200, 30);
                l22.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient Address
                JLabel l33 = new JLabel("Patient Address");
                l33.setBounds(50, 200, 200, 30);
                l33.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient mobile
                JLabel l44 = new JLabel("Patient mobile");
                l44.setBounds(50, 250, 200, 30);
                l44.setFont(new Font("Arial", Font.BOLD, 16));
                //token States
                JLabel l55 = new JLabel("token States");
                l55.setBounds(50, 300, 200, 30);
                l55.setFont(new Font("Arial", Font.BOLD, 16));
                //Date
                JLabel l66 = new JLabel("Date");
                l66.setBounds(50, 350, 200, 30);
                l66.setFont(new Font("Arial", Font.BOLD, 16));

                //TextField view patient
                //Enter patient Id
                JTextField textsearch = new JTextField();
                textsearch.setBounds(300, 50, 160, 30);
                textsearch.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient Id
                JTextField t11 = new JTextField();
                t11.setBounds(300, 100, 160, 30);
                t11.setFont(new Font("Arial", Font.BOLD, 16));
                t11.setEditable(false);
                 //Patient Name
                JTextField t22 = new JTextField();
                t22.setBounds(300, 150, 160, 30);
                t22.setFont(new Font("Arial", Font.BOLD, 16));
                t22.setEditable(false);
               //Patient Address
                JTextField t33 = new JTextField();
                t33.setBounds(300, 200, 160, 30);
                t33.setFont(new Font("Arial", Font.BOLD, 16));
                t33.setEditable(false);
                //Patient mobile
                JTextField t44 = new JTextField();
                t44.setBounds(300, 250, 160, 30);
                t44.setFont(new Font("Arial", Font.BOLD, 16));
                t44.setEditable(false);
               //token States
                JTextField t55 = new JTextField();
                t55.setBounds(300, 300, 160, 30);
                t55.setFont(new Font("Arial", Font.BOLD, 16));
                t55.setEditable(false);
                //Date  
                JTextField t66 = new JTextField();
                t66.setBounds(300, 350, 160, 30);
                t66.setFont(new Font("Arial", Font.BOLD, 16));
                t66.setEditable(false);
     
            //Gui of frame of view patient  
                frame3.setSize(500, 500);
                frame3.setLocation(400, 120);
                frame3.setResizable(false);
                frame3.setLayout(null);
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //btn view patient action
                    b6.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                       
                        // components to view patient frame
                        frame3.add(l11);frame3.add(l22);frame3.add(l33);frame3.add(l44);frame3.add(l55);frame3.add(l66);
                        frame3.add(t11);frame3.add(t22);frame3.add(t33);frame3.add(t44);frame3.add(t55);frame3.add(t66);
                        frame3.add(labelsearch);frame3.add(textsearch);
                        frame3.add(b44);frame3.add(b5);
                        frame.setVisible(false);
                        frame3.setVisible(true);frame3.add(imgback);
                    }
                });
                        //btn submit
                        b44.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                if(textsearch.getText().equals("")){
                           JOptionPane.showMessageDialog(frame3, "Please enter Patient ID", "Failed", JOptionPane.ERROR_MESSAGE);
                           }
                           else{ 
                              int x = Integer.parseInt(textsearch.getText());
                              //date of patient from datebase
                              Patient pat = retrive_data_patient(x);
                              //Show data of patient 
                              t11.setText(""+pat.getPatient_id());
                              t22.setText(""+pat.getPerson_name());
                              t44.setText(""+pat.getPerson_mobile());
                              t33.setText(""+pat.getPerson_address());
                              //check if patient was examin or not 
                              if(get_token_id(x)==null){
                                  t55.setText("this patient was finished");
                              }
                              else{
                              t55.setText(""+get_token_id(x));
                              }
                              t66.setText(""+pat.getDate());
                              JOptionPane.showMessageDialog(frame3, "Successfully", "Successful", JOptionPane.PLAIN_MESSAGE);
                              textsearch.setText("");
                                }
                               } 
                        });

                        //btn back
                        b5.setBounds(300, 400, 150, 30);
                        b5.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                t11.setText(null);t22.setText(null);t33.setText(null);t44.setText(null);t55.setText(null);t66.setText(null);
                                textsearch.setText(null);
                                frame3.setVisible(false);frame.add(imgback);
                                frame.setVisible(true);
                                
                            }
                        });
            //inline Patient Frame components
                JLabel Label_pat_id= new JLabel("Please enter patient ID");
                JTextField TextField_pat_id = new JTextField(10);
                Label_pat_id.setFont(new Font("Arial",Font.BOLD,15));
                TextField_pat_id.setFont(new Font("Arial",Font.BOLD,15));

                Label_pat_id.setBounds(50, 50, 130, 30);
                TextField_pat_id.setBounds(250, 50, 130, 30);
                //databse waiting list data
                JTable TPat = new JTable();
                TPat.setBounds(30, 130,450 , 200);
                TPat.setModel(inline());
                JScrollPane pane = new JScrollPane(TPat);
                pane.setBounds(30, 130,450 , 200);
                pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                pane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                pane.setVisible(true);
                //after search
                JTable TPat2 = new JTable();
                TPat2.setBounds(30, 130,450 , 200);
                //add table to Scrollpane
                JScrollPane pane2 = new JScrollPane(TPat2);
                pane2.setBounds(30, 130,450 , 200);
                pane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                pane2.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                pane2.setVisible(false);
            //Gui of frame of view inline  
                frame5.setSize(500, 500);
                frame5.setLocation(400, 120);
                frame5.setResizable(false);
                frame5.setLayout(null);
                frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //inline action
                b7.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    
                    
                      //frame5
                    frame5.add(Label_pat_id);frame5.add(TextField_pat_id);
                    frame5.add(pane); frame5.add(pane2);
                    frame5.add(b444);frame5.add(b5);
                    frame.setVisible(false);
                    frame5.setVisible(true);frame5.add(imgback);
                }
            }); 
                    //btn submit
                    b444.setText("View");
                    b444.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            // if textfield empty that mean he want all patients
                        if(TextField_pat_id.getText().equals("")){
                         try {  //update table with date from table waiting list
                                TPat2.setModel(inline()); 
                                pane.setVisible(true);
                                pane2.setVisible(false);
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(frame5, ex);
                            }   //show public table and hide private table until user enter id 
                               
                        }
                        else{// he want special patient
                            int pat_id = Integer.parseInt(TextField_pat_id.getText());
                            try {//update table with date from table waiting list
                                TPat2.setModel(inline_doc(pat_id));
                                pane.setVisible(false);
                                pane2.setVisible(true);
                            } catch (SQLException ex) {
                                Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                          }
                        }
                    });

                    //btn back
                    b5.setBounds(300, 400, 150, 30);
                    b5.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            TextField_pat_id.setText("");
                            frame5.setVisible(false);
                            frame.setVisible(true);frame.add(imgback);
                        }
                    });
            //view Prescription
            // Prescription Patient Frame components\
                //buttons
                 //add combobox to medcine information
                 //create combobox 
                JComboBox<String> comboBox_medicine = new JComboBox<>(new Vector<>(medicine_info()));
                 comboBox_medicine.setBounds(50, 300, 200,30);
                 JButton btn2 = new JButton("Enter Resources charges");
                btn2.setBounds(260, 300, 200,30);
                JButton btn3 = new JButton("Enter Bed cost");
                btn3.setBounds(50, 350, 200,30);
                JTextField textdisharge4 = new JTextField("Discharge Patient");
                textdisharge4.setEditable(false);
                textdisharge4.setBounds( 350, 350, 100,30);
                JButton bcalc = new JButton("Total");
                bcalc.setBounds(260, 350, 90,30);
                //labels
                JLabel lp1 = new JLabel("Date Prescription");
                lp1.setBounds(50, 100, 200, 30);
                lp1.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel lp2 = new JLabel("Describe medicine");
                 lp2.setBounds(50, 150, 200, 30);
                lp2.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel lp3 = new JLabel("bed needed");
                lp3.setBounds(50, 200, 200, 30);
                lp3.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel lp4 = new JLabel("Number of residency days");
                lp4.setBounds(50, 250, 200, 30);
                lp4.setFont(new Font("Arial", Font.BOLD, 16));
                lp4.setBounds(50, 250, 200, 30);
                lp4.setFont(new Font("Arial", Font.BOLD, 16));
                //TextField
                JTextField tp1 = new JTextField();
                tp1.setBounds(300, 100, 160, 30);
                tp1.setFont(new Font("Arial", Font.BOLD, 16));
                tp1.setEditable(false);
                JTextField tp2 = new JTextField();
                tp2.setBounds(300, 150, 160, 30);
                tp2.setFont(new Font("Arial", Font.BOLD, 16));
                tp2.setEditable(false);
                JTextField tp3 = new JTextField();
                tp3.setBounds(300, 200, 160, 30);
                tp3.setFont(new Font("Arial", Font.BOLD, 16));
                tp3.setEditable(false);
                JTextField tp4 = new JTextField();
                tp4.setBounds(300, 250, 160, 30);
                tp4.setFont(new Font("Arial",Font.BOLD, 16));
                tp4.setEditable(false);
            //prescreption action buttons    
                Patient p = new Patient();
                //Enter medicine Price
               //update to medicine price 
                    
                  // String price = new String("");
                    
                     
              comboBox_medicine.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    String medicine = comboBox_medicine.getSelectedItem().toString();
                    int x = medicine.length();
                    
                            price += medicine.substring(medicine.indexOf(",")+1, x-1);
                    updateMedicine(medicine);
                    p.calcMedicine_price(Float.valueOf(price));
                    price="";
                }});
                
            //Enter Resources charges
                btn2.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    String Resources_charges = JOptionPane.showInputDialog(frame4, "Enter Resources charges");
                    //update to Resources_charges
                    p.calcResources_charges(Float.valueOf(Resources_charges));
                }
                });
            //Enter Bed Cost
                btn3.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    String bed_cost = JOptionPane.showInputDialog(frame4, "Enter Bed Cost");
                      //update to bed_cost
                    p.calcBed_cost(Float.valueOf(bed_cost));
                }
                });
            
            
            //Calculate total cost
                bcalc.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                     try{
                         // calculate total cost
                      float total_cost =  p.getMedicine_price() + p.getResources_charges() + p.getBed_cost();
                      //make a new bill
                      Bill bill = new Bill(Integer.parseInt(textsearch.getText()), getDocId(Integer.parseInt(textsearch.getText())), tp2.getText(),Date.valueOf(tp1.getText()),  p.getResources_charges() , p.getMedicine_price(), p.getBed_cost(), total_cost);
                      //add bill to database
                      Insert_new_Bill(bill);
                      //Show Total cost
                      textdisharge4.setText(""+total_cost);
                       
                   
                     }
                   catch(NumberFormatException es){
                             JOptionPane.showMessageDialog(frame4 , "please enter value to Resources_charges and Bed_cost or set there 0 ..." , "Error Message " ,JOptionPane.ERROR_MESSAGE); 
                        }  
                     
                  }
              });
        //gui view Prescription
            frame4.setSize(500, 500);
            frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame4.setLocation(400, 120);
            frame4.setResizable(false);
            frame4.setLayout(null);
        //view Prescription action 
                b8.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                   
                    //add componets to Prescription
                    frame4.add(labelsearch);frame4.add(textsearch);
                    frame4.add(lp1);frame4.add(lp2);frame4.add(lp3);frame4.add(lp4);
                    frame4.add(tp1);frame4.add(tp2);frame4.add(tp3);frame4.add(tp4);
                    frame4.add(b4444);frame4.add(b5);frame4.add(textdisharge4);frame4.add(bcalc);
                    frame4.add(btn2);frame4.add(btn3);frame4.add(comboBox_medicine);
                    frame4.setVisible(true);
                    frame.setVisible(false);frame4.add(imgback);
                }
            });
                    //btn submit
                    b4444.setText("View");
                    b4444.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){  
                           try{
                           prescription pres =  viewPrescription2(textsearch.getText());
                           tp4.setText(""+pres.getNum_residency_days());
                           tp3.setText(""+pres.getBed_needed());
                           tp2.setText(""+pres.getDescribe_medicine());
                           tp1.setText(""+pres.getDate_pres());
                           }
                           catch(SQLException epx){
                                 JOptionPane.showMessageDialog(frame4 , "It's Not Exist,please Enter valid id..." , "Error Message " ,JOptionPane.ERROR_MESSAGE); 
          
                           }
                           catch(NullPointerException ee){
                                 JOptionPane.showMessageDialog(frame4 , "It's Not Exist,please Enter valid id..." , "Error Message " ,JOptionPane.ERROR_MESSAGE); 
          
                           }
                        }
                    });

                    //btn back
                    b5.setBounds(300, 420, 150, 30);
                    b5.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            textsearch.setText("");
                            frame4.setVisible(false);
                            frame.setVisible(true);frame.add(imgback);
                        }
                    });
    //add components to Receptionist frame            
    frame.add(profile);frame.add(b1);frame.add(b2);frame.add(b3);frame.add(b6);
    frame.setVisible(true);frame.add(b7);frame.add(b8);frame.add(imgback);
}
    
   

//setter and getter functions
    //User_name
    public void setReceptionId(int ReceptionId) {
        this.ReceptionId = ReceptionId;
    }
    public void setUser_name(String user_name) {
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
    public int getReceptionId()  {
        return ReceptionId;
       }
    
//another functions
    //functions calculate Total cost
    //price of medicine & Resources_charges
    public void Medicine_price(Patient patient, float medicine) {
         patient.setMedicine_price(medicine);
     }
    public void Resources_charges(Patient patient, float Resources_charges){
      patient.setResources_charges(Resources_charges);
  }

    
    
//functions insert in datebase
    private void insert_pat(Patient p1){
          if( p1.getName().equals("") || p1.getPerson_address().equals("")|| p1.getPerson_mobile().equals("") || p1.getDate().equals("")){
                JOptionPane.showMessageDialog(frame2, "Not Update", "Erorr", JOptionPane.ERROR_MESSAGE);

           }else{

               insert_new_patient(p1);
               JOptionPane.showMessageDialog(frame2, "Successfully Update Token Id:" + retrive_patient_id(), "Successful", JOptionPane.INFORMATION_MESSAGE);

           }
       }
    private void insert_new_patient(Patient patient){
        try{
        String insert = "INSERT INTO patient(id , name , mobile , address  , token_id , date, doctor_id) VALUES (NULL,?,?,?,'NULL',?,?)"; 
        PreparedStatement preparedStmt = fun().prepareStatement(insert);
       // create the java statement
       // execute the query,

           preparedStmt.setString(1,patient.getPerson_name());
           preparedStmt.setString(2,patient.getPerson_mobile());
           preparedStmt.setString(3,patient.getPerson_address());
           preparedStmt.setDate(4,patient. getDate()); 
           preparedStmt.setInt(5, patient.getDoctor_id());
           preparedStmt.execute();
                fun().close();
        }
       catch(SQLException e){
          JOptionPane.showMessageDialog(null, e);
       }

       }
    private void insert_tokenline( int patient_id ,int Doctor_id){
        try{
        String insert = "INSERT INTO waiting_list (number_patient,doctor_id,patient_id) VALUES (NULL, ?, ?)"; 
        PreparedStatement preparedStmt = fun().prepareStatement(insert);
       // create the java statement
       // execute the query,

           preparedStmt.setInt(1,Doctor_id);
           preparedStmt.setInt(2,patient_id);
           preparedStmt.execute();
                fun().close();
        }
       catch(SQLException e){
          JOptionPane.showMessageDialog(null, e);
       }

    }
    private void Insert_new_Bill(Bill bill){
         try{
        String insert = "INSERT INTO bill (BILL_ID,PATIENT_ID,DOCTOR_ID,MEDICINE,DATE,Resources_Charges,medicine_price,Bed_Cost,Total_Charge) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        PreparedStatement preparedStmt = fun().prepareStatement(insert);
       // create the java statement
       // execute the query,

           preparedStmt.setInt(1,bill.getPatientId());
           preparedStmt.setInt(2,bill.getDoctorId());
           preparedStmt.setString(3,bill.getMedicine());
           preparedStmt.setDate(4,bill.getDate());
           preparedStmt.setFloat(5,bill.getResources_Charge());
           preparedStmt.setFloat(6,bill.getMedicine_price());
           preparedStmt.setFloat(7,bill.getBed_Cost());
           preparedStmt.setFloat(8,bill.getTotal());

           preparedStmt.execute();
                fun().close();
        }
       catch(SQLException e){
          JOptionPane.showMessageDialog(null, e);
       }

    }
    private void  update_tokenId (String token_id,int patient_id){
     
        try{
         
        String update = "UPDATE patient SET token_id = ? WHERE id = ?"; 
     // create the java statement
     PreparedStatement pst = fun().prepareStatement(update);
     pst.setString(1,String.valueOf(token_id));
     pst.setInt(2, patient_id);
     pst.executeUpdate();
     fun().close();
     }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
    
    }
    private void  updateMedicine(String medicine){
        String namemed = medicine.substring(0 , medicine.indexOf(","));
        try{
         
        String update = "UPDATE medicine SET number_pieces = number_pieces - 1  WHERE name = ?"; 
     // create the java statement
     PreparedStatement pst = fun().prepareStatement(update);
  //   pst.setString(1,);
     pst.setString(1, namemed);
     pst.executeUpdate();
     fun().close();
     }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
    
    }







// functions retrive date from datebase
    private Patient retrive_data_patient( int id_patient){
         Patient pat = null; 
        try{
            String search = "SELECT * FROM patient WHERE id = '"+id_patient+"'"; 
            // create the java statement
        Statement pst= fun().createStatement();
        // execute the query,
            ResultSet rs = pst.executeQuery(search); 
             //data to be displayed in the table
           if (rs.next()) 
           {      
                  
                  pat = new Patient( rs.getString("name"), rs.getString("mobile"), rs.getString("address"), rs.getInt("doctor_id")  , rs.getString("token_id") ,rs.getDate("date"));
                  pat.setPatient_id(rs.getInt("id"));
           }
           else { JOptionPane.showMessageDialog(frame3 , "please Enter valid id..." , "Error Message " ,JOptionPane.ERROR_MESSAGE);}
           fun().close();

        }
        catch(SQLException e){
          JOptionPane.showMessageDialog(null , e);
        }
        return pat;
        }
    public prescription viewPrescription2(String patientID)throws  SQLException{
            prescription pres = null;
            int patid = Integer.parseInt(patientID);
             
            String search = "SELECT * FROM prescription WHERE patient_id = '"+patid+"'"; 
            // create the java statement
             Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
             //data to be displayed in the table
           while (rs.next()) 
           {               
                  //p1.setBed_cost(rs.getFloat("bed_cost"));
                 pres = new prescription(rs.getString("describe_medicine"), rs.getDate("date"), rs.getInt("Bed_Needed") , rs.getInt("number_residency_days")); 

           }
            
           fun().close();

          
            return pres;
        }
    private int retrive_patient_id(){
       int  pat_id = 0;
        try{
            String search = "SELECT * FROM patient"; 
            // create the java statement
            Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
           while (rs.next()) 
           {               
                  pat_id = rs.getInt("id");
           }
            fun().close();
      }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
        }
       return pat_id;
    }
    private String retrive_token_id(){
      String  token_id = null;
       try{
           String search = "SELECT * FROM waiting_list"; 
           // create the java statement
           Statement pst= fun().createStatement();
           // execute the query,
           ResultSet rs = pst.executeQuery(search); 
          while (rs.next()) 
          {               
                 token_id = rs.getString("number_patient");
          }
           fun().close();
     }
       catch(SQLException e){
          JOptionPane.showMessageDialog(null, e);
       }
      return token_id;
   }
    private String get_token_id(int pat_id){
         String  token_id = null;
        try{
            String search = "SELECT number_patient FROM waiting_list WHERE patient_id =  '"+pat_id+"'"; 
               // create the java statement
            Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
           if(rs.next()) 
           {               
                  token_id = rs.getString("number_patient");
           }
        else { JOptionPane.showMessageDialog(frame3 , "this patient was finished..." , "Information Message " ,JOptionPane.INFORMATION_MESSAGE);}
         fun().close();
      }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
        }
       return token_id;

    }
    private int getDocId(int pat_id){
         int  doc_id = 0;
        try{
            String search = "SELECT doctor_id FROM patient WHERE id =  '"+pat_id+"'"; 
               // create the java statement
            Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
           while(rs.next()) 
           {               
                  doc_id = rs.getInt("doctor_id");
           }            
           fun().close();
      }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
        }
       return doc_id;

    }
    public DefaultTableModel inline() throws SQLException {

            DefaultTableModel dm = new DefaultTableModel();
            String[] arr = {"number_patient","doctor_id","patient_id"};


            for (int i = 0; i < arr.length; i++) {
                dm.addColumn(arr[i]);
            }

            String qSql = "SELECT * FROM waiting_list";
            Statement stm = fun().createStatement();
            ResultSet rs = stm.executeQuery(qSql);
           while(rs.next())
            {
                   Object [] row = {rs.getInt("number_patient"), rs.getInt("doctor_id"), rs.getInt("patient_id") };
                    dm.addRow(row);
            }        
             return  dm; 
        }   
    public DefaultTableModel  inline_doc(int pat_id)throws SQLException{
            DefaultTableModel dm = new DefaultTableModel();
            String[] arr = {"number_patient","doctor_id","patient_id"};

            for (int i = 0; i < arr.length; i++) {
                dm.addColumn(arr[i]);
            }

            String qSql = "SELECT * FROM waiting_list WHERE patient_id = '"+pat_id+"'";
            Statement stm = fun().createStatement();
            ResultSet rs = stm.executeQuery(qSql);
           while(rs.next())
            {
                   Object [] row ={rs.getInt("number_patient"), rs.getInt("doctor_id"), rs.getInt("patient_id") };
                   dm.addRow(row);
            }
         return  dm; 
     }
    private boolean check_doc_id(int id){
       boolean  flag = false;
        try{
            String search = "SELECT * FROM doctor WHERE id = '"+id+"'"; 
            // create the java statement
            Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
           while (rs.next()) 
           {               
                  flag = true ;
           }
            fun().close();
      }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
        }
       return flag;
    }
    private ArrayList<String>  doctors_names(){
       ArrayList<String> doctors = new ArrayList<String>();
        try{
            String search = "SELECT * FROM doctor"; 
            // create the java statement
            Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
           
           while (rs.next()) 
           {               
                  doctors.add( rs.getString("name"));
           }
            fun().close();
      }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
        }
       return doctors;
    }
    private int retrive_doc_id(String name_doc){
       int  doc_id = 0;
        try{
            String search = "SELECT * FROM doctor WHERE name = '"+name_doc+"'"; 
            // create the java statement
            Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
           while (rs.next()) 
           {               
                  doc_id = rs.getInt("id");
           }
            fun().close();
      }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
        }
       return doc_id;
    }
    private ArrayList<String>  medicine_info(){
       ArrayList<String> doctors = new ArrayList<String>();
        try{
            String search = "SELECT * FROM medicine"; 
            // create the java statement
            Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
           
           while (rs.next()) 
           {               
                  doctors.add( rs.getString("name")+","+rs.getFloat("cost"));
           }
            fun().close();
      }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
        }
       return doctors;
    }
    
    
    
//functions user acount
    private void update_pass(String username , String newpass) {
    try{
         
        String update = "UPDATE receptionist SET password = ? WHERE username = ?"; 
     // create the java statement
     PreparedStatement pst = fun().prepareStatement(update);
     pst.setString(1, newpass);
     pst.setString(2, username);
     pst.executeUpdate();
     fun().close();
     }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
    
 }
    @Override
    public void changepassword(String username , String user_old_password, String user_new_password) {
            if(username.isEmpty() || user_old_password.isEmpty() || user_new_password.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Not Update", "Erorr", JOptionPane.ERROR_MESSAGE);
            }else{
                if(username.equals(this.user_name) && user_old_password.equals(this.user_password)){
                    user_password = user_new_password;
                    update_pass( username ,  user_new_password);
                    JOptionPane.showMessageDialog(frame, "Successfully Update", "Successful", JOptionPane.WARNING_MESSAGE);
                }
                else if(!username.equals(this.user_name))
                    JOptionPane.showMessageDialog(frame, "UserName It's Not Correct, Not Update", "Erorr", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "Old Password It's Not Correct, Not Update", "Erorr", JOptionPane.ERROR_MESSAGE);
                }
    }
    //Logout Function
    @Override
    public void logout() {
        //hide frame of Receptionist
       frame.setVisible(false);
       login_page login = new login_page();
    }
}
