
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
    /* constractor  this constructor to make a new receptionist */
    public Receptionist(String person_name, String person_mobile, String person_address, String user_name, String user_password) throws SQLException {
    super( person_name, person_address,person_mobile);
    this.user_name = user_name;
    this.user_password = user_password;
    }
    
    //this constractor user to make frame to receptionist  
    public Receptionist(String user_name, String user_password) throws SQLException {
    

//Gui of home page of receptionist
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(400, 120);
    frame.setResizable(false);
    frame.setLayout(null);
        //profile image
            JLabel profile = new JLabel(""+user_name);//create label and set text with username
            profile.setBounds(150, 30, 170, 150);//set bound of label
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
                b1.setForeground(Color.MAGENTA);
                b1.setFont(new Font("Arial", Font.BOLD, 14));
                b1.setBounds(50, 400, 200, 30);
            // button Logout 
                JButton b2 = new JButton("Logout");
                b2.setForeground(Color.MAGENTA);
                b2.setFont(new Font("Arial", Font.BOLD, 14));
                b2.setBounds(350, 400, 100, 30);
            // button Add patient
                JButton b3 = new JButton("Add Patient");
                b3.setForeground(Color.MAGENTA);
                b3.setFont(new Font("Arial", Font.BOLD, 14));
                b3.setBounds(150, 200, 200, 30);
            // button submit in frame2 (Add patient) 
                JButton b4 = new JButton("Submit");
                b4.setForeground(Color.MAGENTA);
                b4.setFont(new Font("Arial", Font.BOLD, 14));
            // button back in frame2 (Add patient) 
                JButton b5 = new JButton("Back");
                b5.setForeground(Color.MAGENTA);
                b5.setFont(new Font("Arial", Font.BOLD, 14));
            // button View patient
                JButton b6 = new JButton("View patient");
                b6.setForeground(Color.MAGENTA);
                b6.setFont(new Font("Arial", Font.BOLD, 14));
                b6.setBounds(150, 250, 200, 30);
            // button patient Inline 
                JButton b7 = new JButton("patient Inline");
                b7.setForeground(Color.MAGENTA);
                b7.setFont(new Font("Arial", Font.BOLD, 14));
                b7.setBounds(150, 300, 200, 30);
            // button View Prescription
                JButton b8 = new JButton("View Prescription");
                b8.setFont(new Font("Arial", Font.BOLD, 14));
                b8.setForeground(Color.MAGENTA);
                b8.setBounds(150, 350, 200, 30);
            // button patient inline
                JButton b444 = new JButton("View");
                b444.setForeground(Color.MAGENTA);
                b444.setFont(new Font("Arial", Font.BOLD, 14));
                b444.setBounds(50, 400, 150, 30);
            //button view Prescription
                JButton b4444 = new JButton("View");
                b4444.setForeground(Color.MAGENTA);
                b4444.setFont(new Font("Arial", Font.BOLD, 14));
                b4444.setBounds(50, 400, 150, 30);
            //button Submit in frame view patient
                JButton b44 = new JButton("View");
                b44.setForeground(Color.MAGENTA);
                b44.setFont(new Font("Arial", Font.BOLD, 14));
                b44.setBounds(50, 400, 150, 30);
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
                    l2.setForeground(Color.magenta);
                    l2.setFont(new Font("Arial", Font.BOLD, 16));
                    //Enter Patient Address
                    JLabel l3 = new JLabel("Enter Patient Address");
                    l3.setBounds(50, 100, 200, 30);
                    l3.setForeground(Color.magenta);
                    l3.setFont(new Font("Arial", Font.BOLD, 16));
                    //Enter Patient mobile
                    JLabel l4 = new JLabel("Enter Patient mobile");
                    l4.setBounds(50, 150, 200, 30);
                    l4.setForeground(Color.magenta);
                    l4.setFont(new Font("Arial", Font.BOLD, 16));
                    //enter Date
                    JLabel l6 = new JLabel("Date");
                    l6.setBounds(50, 200, 200, 30);
                    l6.setForeground(Color.magenta);
                    l6.setFont(new Font("Arial", Font.BOLD, 16));
                    // enter Doctor ID of patient 
                    JLabel l7 = new JLabel("Doctor ID");
                    l7.setBounds(50, 250, 200, 30);
                    l7.setForeground(Color.magenta);
                    l7.setFont(new Font("Arial", Font.BOLD, 16));


                //Buttons  are used in frame add patient 
                    //Enter Patient Name
                    JTextField t2 = new JTextField();
                    t2.setBounds(300, 50, 160, 30);
                    t2.setForeground(Color.magenta);
                    t2.setFont(new Font("Arial", Font.BOLD, 16));
                     //Enter Patient Address
                    JTextField t3 = new JTextField();
                    t3.setBounds(300, 100, 160, 30);
                    t3.setForeground(Color.magenta);
                    t3.setFont(new Font("Arial", Font.BOLD, 16));
                     //Enter Patient mobile
                    JTextField t4 = new JTextField();
                    t4.setBounds(300, 150, 160, 30);
                    t4.setForeground(Color.magenta);
                    t4.setFont(new Font("Arial", Font.BOLD, 16));
                    //enter Date
                    JTextField t6 = new JTextField();
                    t6.setBounds(300, 200, 160, 30);
                    t6.setForeground(Color.magenta);
                    t6.setFont(new Font("Arial", Font.BOLD, 16));
                    // enter Doctor ID of patient 
                    JTextField t7 = new JTextField();
                    t7.setBounds(300, 250, 160, 30);
                    t7.setForeground(Color.magenta);
                    t7.setFont(new Font("Arial", Font.BOLD, 16));
            
            //Gui of frame of add patient             
                    frame2.setSize(500, 500);
                    frame2.setLocation(400, 120);
                    frame2.setResizable(false);
                    frame2.setLayout(null);
            
                // add patient action
                    b3.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                        
                            //add components to frame add patient 
                                frame2.add(b4);frame2.add(b5);frame2.add(t7);frame2.add(l7);
                                frame2.add(l2);frame2.add(l3);frame2.add(l4); frame2.add(l6);
                                frame2.add(t2);frame2.add(t3);frame2.add(t4); frame2.add(t6);
                            //Show frame of add patient    
                                frame2.setVisible(true);
                            //hide frame of receptionist
                                frame.setVisible(false);
                            //to close programe properly    
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });
                        //Functions of each buttons are used in frame Add patient 
                            //btn submit action 
                                b4.setBounds(50, 400, 150, 30);
                                b4.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e){
                                        //create new patient 
                                        Patient pat = new Patient();
                                            //patient details
                                            pat.setPerson_name(t2.getText());
                                            pat.setPerson_address(t3.getText());
                                            pat.setPerson_mobile(t4.getText());
                                            //token id equal 0 initial but it update after add patient to patient table 
                                           //then to waiting list and the number patient from waiting list will add to token id 
                                            pat.setToken_id(0);
                                            
                                           
                                            //in date section may fromate of date is wrong  
                                            try{  
                                                pat.setDate(Date.valueOf(t6.getText()));
                                            pat.setDoctor_id(Integer.parseInt(t7.getText()));
                                            //insert patient in table patient in database
                                             insert_pat(pat);
                                             //insert patient in table patient in database
                                             insert_tokenline(retrive_patient_id() ,  pat.getDoctor_id());
                                             update_tokenId (retrive_token_id(),retrive_patient_id());
                                            //set the values of textfields
                                             t2.setText("");t3.setText("");t4.setText(""); t6.setText("");t7.setText("");
                                            }
                                            catch(RuntimeException ex){
                                                  JOptionPane.showMessageDialog(frame2,"Please Enter valid date or valid format......","Failed", JOptionPane.ERROR_MESSAGE);
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
                                        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        //Show frame of receptionist
                                        frame.setVisible(true);
                                    }
                                });
            //Add Patient Frame components
                //Labels view patient
                //Enter patient Id
                JLabel labelsearch = new JLabel("Enter patient Id");
                labelsearch.setBounds(50, 50, 130, 30);
                labelsearch.setForeground(Color.magenta);
                labelsearch.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient Id
                JLabel l11 = new JLabel("Patient Id"); 
                l11.setBounds(50, 100, 130, 30);
                l11.setForeground(Color.magenta);
                l11.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient Name
                JLabel l22 = new JLabel("Patient Name");
                l22.setBounds(50, 150, 200, 30);
                l22.setForeground(Color.magenta);
                l22.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient Address
                JLabel l33 = new JLabel("Patient Address");
                l33.setBounds(50, 200, 200, 30);
                l33.setForeground(Color.magenta);
                l33.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient mobile
                JLabel l44 = new JLabel("Patient mobile");
                l44.setBounds(50, 250, 200, 30);
                l44.setForeground(Color.magenta);
                l44.setFont(new Font("Arial", Font.BOLD, 16));
                //token States
                JLabel l55 = new JLabel("token States");
                l55.setBounds(50, 300, 200, 30);
                l55.setForeground(Color.magenta);
                l55.setFont(new Font("Arial", Font.BOLD, 16));
                //Date
                JLabel l66 = new JLabel("Date");
                l66.setBounds(50, 350, 200, 30);
                l66.setForeground(Color.magenta);
                l66.setFont(new Font("Arial", Font.BOLD, 16));

                //TextField view patient
                //Enter patient Id
                JTextField textsearch = new JTextField();
                textsearch.setBounds(300, 50, 160, 30);
                textsearch.setForeground(Color.magenta);
                textsearch.setFont(new Font("Arial", Font.BOLD, 16));
                //Patient Id
                JTextField t11 = new JTextField();
                t11.setBounds(300, 100, 160, 30);
                t11.setForeground(Color.magenta);
                t11.setFont(new Font("Arial", Font.BOLD, 16));
                t11.setEditable(false);
                 //Patient Name
                JTextField t22 = new JTextField();
                t22.setBounds(300, 150, 160, 30);
                t22.setForeground(Color.magenta);
                t22.setFont(new Font("Arial", Font.BOLD, 16));
                t22.setEditable(false);
               //Patient Address
                JTextField t33 = new JTextField();
                t33.setBounds(300, 200, 160, 30);
                t33.setForeground(Color.magenta);
                t33.setFont(new Font("Arial", Font.BOLD, 16));
                t33.setEditable(false);
                //Patient mobile
                JTextField t44 = new JTextField();
                t44.setBounds(300, 250, 160, 30);
                t44.setForeground(Color.magenta);
                t44.setFont(new Font("Arial", Font.BOLD, 16));
                t44.setEditable(false);
               //token States
                JTextField t55 = new JTextField();
                t55.setBounds(300, 300, 160, 30);
                t55.setForeground(Color.magenta);
                t55.setFont(new Font("Arial", Font.BOLD, 16));
                t55.setEditable(false);
                //Date  
                JTextField t66 = new JTextField();
                t66.setBounds(300, 350, 160, 30);
                t66.setForeground(Color.magenta);
                t66.setFont(new Font("Arial", Font.BOLD, 16));
                t66.setEditable(false);
     
            //Gui of frame of view patient  
                frame3.setSize(500, 500);
                frame3.setLocation(400, 120);
                frame3.setResizable(false);
                frame3.setLayout(null);
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
                        frame3.setVisible(true);
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
                                    try{
                                        int x = Integer.parseInt(textsearch.getText());
                                        //date of patient from datebase
                                        Patient pat = retrive_data_patient(x);
                                        //Show data of patient 
                                        t11.setText(""+pat.getPatient_id());
                                        t22.setText(""+pat.getPerson_name());
                                        t44.setText(""+pat.getPerson_mobile());
                                        t33.setText(""+pat.getPerson_address());
                                        //check if patient was examin or not 
                                        if(get_token_id(x)==0){
                                            t55.setText("this patient was finished");
                                        }
                                        else{
                                        t55.setText(""+get_token_id(x));
                                        }
                                        t66.setText(""+pat.getDate());
                                        JOptionPane.showMessageDialog(frame3, "Successfully Submit", "Successful", JOptionPane.PLAIN_MESSAGE);
                                        textsearch.setText("");
                                    }
                                    catch(NullPointerException ex){
                               JOptionPane.showMessageDialog(frame3, "Please enter valid patient ID", "Failed", JOptionPane.ERROR_MESSAGE);

                                    }
                                }
                               } 
                        });
                    //btn back
                        b5.setBounds(300, 400, 150, 30);
                        b5.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                textsearch.setText("");
                                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame3.setVisible(false);
                                frame.setVisible(true);
                            }
                        });
            //inline Patient Frame components
                JLabel Label_pat_id= new JLabel("Please enter patient ID");
                JTextField TextField_pat_id = new JTextField(10);
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
            //inline action
                b7.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    
                      //frame5
                    frame5.add(Label_pat_id);frame5.add(TextField_pat_id);
                    frame5.add(pane); frame5.add(pane2);
                    frame5.add(b444);frame5.add(b5);
                    frame.setVisible(false);
                    frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame5.setVisible(true);
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
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(frame5, ex);
                            }   //show public table and hide private table until user enter id 
                                pane.setVisible(true);
                                pane2.setVisible(false);
                        }
                        else{// he want special patient
                            int pat_id = Integer.parseInt(TextField_pat_id.getText());
                            try {//update table with date from table waiting list
                                TPat2.setModel(inline_doc(pat_id));
                            } catch (SQLException ex) {
                                Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                pane.setVisible(false);
                                pane2.setVisible(true);
                        }
                        }
                    });

                    //btn back
                    b5.setBounds(300, 400, 150, 30);
                    b5.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            textsearch.setText("");
                            frame5.setVisible(false);
                            frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setVisible(true);
                        }
                    });

            //view Prescription
            // Prescription Patient Frame components\
                //buttons
                JButton btn1 = new JButton("Enter medicine price");
                btn1.setBounds(50, 300, 200,30);
                btn1.setForeground(Color.MAGENTA);
                JButton btn2 = new JButton("Enter Resources charges");
                btn2.setBounds(260, 300, 200,30);
                btn2.setForeground(Color.MAGENTA);
                JButton btn3 = new JButton("Enter Bed cost");
                btn3.setBounds(50, 350, 200,30);
                btn3.setForeground(Color.MAGENTA);
                JTextField textdisharge4 = new JTextField("Discharge Patient");
                textdisharge4.setEditable(false);
                textdisharge4.setBounds(350, 350, 100,30);
                textdisharge4.setForeground(Color.MAGENTA);
                JButton bcalc = new JButton("Total");
                bcalc.setBounds(260, 350, 90,30);
                bcalc.setForeground(Color.MAGENTA);
                //labels
                JLabel lp1 = new JLabel("Date Prescription");
                lp1.setBounds(50, 100, 200, 30);
                lp1.setForeground(Color.magenta);
                lp1.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel lp2 = new JLabel("Describe medicine");
                 lp2.setBounds(50, 150, 200, 30);
                lp2.setForeground(Color.magenta);
                lp2.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel lp3 = new JLabel("bed needed");
                lp3.setBounds(50, 200, 200, 30);
                lp3.setForeground(Color.magenta);
                lp3.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel lp4 = new JLabel("Number of residency days");
                lp4.setBounds(50, 250, 200, 30);
                lp4.setForeground(Color.magenta);
                lp4.setFont(new Font("Arial", Font.BOLD, 16));
                lp4.setBounds(50, 250, 200, 30);
                lp4.setForeground(Color.magenta);
                lp4.setFont(new Font("Arial", Font.BOLD, 16));
                //TextField
                JTextField tp1 = new JTextField();
                tp1.setBounds(300, 100, 160, 30);
                tp1.setForeground(Color.magenta);
                tp1.setFont(new Font("Arial", Font.BOLD, 16));
                tp1.setEditable(false);
                JTextField tp2 = new JTextField();
                tp2.setBounds(300, 150, 160, 30);
                tp2.setForeground(Color.magenta);
                tp2.setFont(new Font("Arial", Font.BOLD, 16));
                tp2.setEditable(false);
                JTextField tp3 = new JTextField();
                tp3.setBounds(300, 200, 160, 30);
                tp3.setForeground(Color.magenta);
                tp3.setFont(new Font("Arial", Font.BOLD, 16));
                tp3.setEditable(false);
                JTextField tp4 = new JTextField();
                tp4.setBounds(300, 250, 160, 30);
                tp4.setForeground(Color.magenta);
                tp4.setFont(new Font("Arial",Font.BOLD, 16));
                tp4.setEditable(false);
            //prescreption action buttons    
                Patient p = new Patient();
                //Enter medicine Price
                btn1.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    String medicine_price = JOptionPane.showInputDialog(frame4, "Enter medicine Price");
                    //update to medicine price 
                    p.calcMedicine_price(Float.valueOf(medicine_price));
                }
                });
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
                      // calculate total cost
                      float total_cost =  p.getMedicine_price() + p.getResources_charges() + p.getBed_cost();
                      //make a new bill
                      Bill bill = new Bill(Integer.parseInt(textsearch.getText()), getDocId(Integer.parseInt(textsearch.getText())), tp2.getText(),Date.valueOf(tp1.getText()),  p.getResources_charges() , p.getMedicine_price(), p.getBed_cost(), total_cost);
                      //add bill to database
                      Insert_new_Bill(bill);
                      //Show Total cost
                      textdisharge4.setText(""+total_cost);
                      textsearch.setText(""); 
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
                    frame4.add(btn1);frame4.add(btn2);frame4.add(btn3);
                    frame4.setVisible(true);
                    frame.setVisible(false);
                }
            });
                     //btn submit
                    b4444.setText("View");
                    b4444.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){  
                                
                                if(textsearch.getText().equals("")){
                           JOptionPane.showMessageDialog(frame3, "Please enter Patient ID", "Failed", JOptionPane.ERROR_MESSAGE);
                           }
                           else{    
                                    try{
                                prescription pres =  viewPrescription2(textsearch.getText());
                                tp4.setText(""+pres.getNum_residency_days());
                                tp3.setText(""+pres.getBed_needed());
                                tp2.setText(""+pres.getDescribe_medicine());
                                tp1.setText(""+pres.getDate_pres());
                                    }catch(NullPointerException ex){
                               JOptionPane.showMessageDialog(frame3, "Please enter valid patient ID", "Failed", JOptionPane.ERROR_MESSAGE);

                                    }
                        }}
                    });

                    //btn back
                    b5.setBounds(300, 400, 150, 30);
                    b5.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            textsearch.setText("");
                            frame4.setVisible(false);
                            frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setVisible(true);
                        }
                    });
                    
                    
    //add components to Receptionist frame            
    frame.add(profile);frame.add(b1);frame.add(b2);frame.add(b3);frame.add(b6);
    frame.setVisible(true);frame.add(b7);frame.add(b8);
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
               JOptionPane.showMessageDialog(frame2, "Successfully Update", "Successful", JOptionPane.WARNING_MESSAGE);

           }
       }
    private void insert_new_patient(Patient patient){
        try{
        String insert = "INSERT INTO patient(id , name , mobile , address  , token_id , date, doctor_id) VALUES (NULL,?,?,?,NULL,?,?)"; 
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
    private void  update_tokenId (int token_id,int patient_id){
        try{
         
        String update = "UPDATE patient SET token_id = ? WHERE id = ?"; 
     // create the java statement
     PreparedStatement pst = fun().prepareStatement(update);
     pst.setInt(1,token_id);
     pst.setInt(2, patient_id);
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
           while(rs.next()) 
           {      
                  
                  pat = new Patient( rs.getString("name"), rs.getString("mobile"), rs.getString("address"), rs.getInt("doctor_id")  , rs.getInt("token_id") ,rs.getDate("date"));
                  pat.setPatient_id(rs.getInt("id"));
           } 
           fun().close();

        }
        catch(SQLException e){
          JOptionPane.showMessageDialog(null , e);
        }
        return pat;
        }
    public prescription viewPrescription2(String patientID){
            prescription pres = null;
            int patid = Integer.parseInt(patientID);
             try{
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

          }
        catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
        }
            return pres;
        }
    private int retrive_patient_id(){
       int  pat_id = 0;
        try{
            String search = "SELECT * FROM patient "; 
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
    private int retrive_token_id(){
      int  token_id = 0;
       try{
           String search = "SELECT * FROM waiting_list"; 
           // create the java statement
           Statement pst= fun().createStatement();
           // execute the query,
           ResultSet rs = pst.executeQuery(search); 
          while (rs.next()) 
          {               
                 token_id = rs.getInt("number_patient");
          }
           fun().close();
     }
       catch(SQLException e){
          JOptionPane.showMessageDialog(null, e);
       }
      return token_id;
   }
    private int get_token_id(int pat_id){
         int  token_id = 0;
        try{
            String search = "SELECT number_patient FROM waiting_list WHERE patient_id =  '"+pat_id+"'"; 
               // create the java statement
            Statement pst= fun().createStatement();
            // execute the query,
            ResultSet rs = pst.executeQuery(search); 
           if(rs.next()) 
           {               
                  token_id = rs.getInt("number_patient");
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
           if(rs.next()) 
           {               
                  doc_id = rs.getInt("doctor_id");
           }            
           else { JOptionPane.showMessageDialog(frame4 , "please Enter valid id..." , "Error Message " ,JOptionPane.ERROR_MESSAGE);}
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
         
         user_password = user_new_password;
            update_pass( username ,  user_new_password);
            JOptionPane.showMessageDialog(frame, "Successfully Update", "Successful", JOptionPane.INFORMATION_MESSAGE);
    }
    //Logout Function
    @Override
    public void logout() {
        //hide frame of Receptionist
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
