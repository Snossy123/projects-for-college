package clinic;

import com.mysql.jdbc.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Admin extends Person implements User
{
    // check on data in database 
        // connect with database 
     public Connection fun () throws SQLException
    {
        connection makeconn = new connection();
            
        Connection conn = makeconn.conn();
        
        return conn;
    }
    //frames are used in receptionist page 
    JFrame frame = new JFrame("Admin");
    JFrame frame1=new JFrame("Add Doctor");
    JFrame frame2=new JFrame("Add Receptionist");
    JFrame frame3=new JFrame("View Patient");
    JFrame frame4=new JFrame("View Receptionist");
    JFrame frame5=new JFrame("View Doctor");
    JFrame frame6=new JFrame("Resources Tracking");
    JFrame frame7=new JFrame("Revesion Bills");
    //valiables local 
   private String user_name, user_password;
   
     /* constractor  this constructor to make a new receptionist */
        public Admin( String person_name, String person_address, String person_mobile, String user_name, String user_password) throws SQLException {
        super( person_name, person_address,person_mobile);
        this.user_name = user_name;
        this.user_password = user_password;
        }
        
          //this constractor user to make frame to receptionist  
        public Admin(String user_name, String user_password) throws SQLException {


//Gui of home page of receptionist
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(400, 120);
    frame.setResizable(false);
    frame.setLayout(null);
        
        //profile image
            JLabel profile = new JLabel(""+user_name);//create label and set text with username
            profile.setBounds(150, 30, 170, 150);//set bound of label
            ImageIcon icon = new ImageIcon("Admin.png");//image of profile
            profile.setHorizontalTextPosition(JLabel.CENTER);//position of words accordding image left or right or center
            profile.setVerticalTextPosition(JLabel.BOTTOM);//position of words accordding image top or bottom
            profile.setIconTextGap(20);//gap bettween image and text 

            Image img = icon.getImage();//convert ImageIcon to image to deal with her scale
            Image ImgScale = img.getScaledInstance(profile.getWidth(), profile.getHeight()-50, img.SCALE_SMOOTH);//set image scale 
            ImageIcon Imageicon = new ImageIcon(ImgScale);//return to original state ImageIco
            profile.setIcon(Imageicon);//set lable (profile) imgIcon of it
            
            
        // main buttons
            //Add Doctor
            JButton b1 = new JButton("Add Doctor");
            b1.setBounds(70, 200, 150, 30);
            //Add Receptionist
            JButton b2 = new JButton("Add Receptionist");
            b2.setBounds(270, 200, 150, 30);
            //View Doctor
            JButton b3 = new JButton("View Doctor");
            b3.setBounds(70, 250, 150, 30);
            //View Receptionist
            JButton b4 = new JButton("View Receptionist");
            b4.setBounds(270, 250, 150, 30);
            //View Patient
            JButton b5 = new JButton("View Patient");
            b5.setBounds(70, 300, 150, 30);
            //Change Password
            JButton b6 = new JButton("Change Password");
            b6.setBounds(30, 400, 150, 30);
            //Logout
            JButton b7 = new JButton("Logout");
            b7.setBounds(350, 400, 100, 30);
            //Resources Tracking
            JButton b8 = new JButton("Resources Tracking");
            b8.setBounds(270, 300, 150, 30);
            //Revesion Bills
            JButton b9 = new JButton("Revesion Bills");
            b9.setBounds(170, 350, 150, 30);
        //Functions of each buttons are used in frame Admin    
           
             // button change password action
                b6.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                    String username = JOptionPane.showInputDialog(frame, "Enter username");
                    String oldpass = JOptionPane.showInputDialog(frame, "Enter Old Password");
                    String newpass = JOptionPane.showInputDialog(frame, "Enter new Password");
                     changepassword(username, oldpass, newpass);

                    }
                });
        
            // button logout action
                b7.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        logout();
                    }
                });
        
        
            //GUI add doctor  Frame  
                frame1.setSize(500, 500);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setLocation(400, 120);
                frame1.setResizable(false);
                frame1.setLayout(null);
        
         
            //add doctor label (1,2,3,4)
                JLabel Label1= new JLabel("Please, Enter Doctor Information... ");
                JLabel Label2= new JLabel("Name");
                JLabel Label3= new JLabel("Mobile");
                JLabel Label4= new JLabel("Address");
                Label1.setBounds(150,20,300, 100);
                Label2.setBounds(110, 100,100, 30);
                Label3.setBounds(110, 160,100, 30);
                Label4.setBounds(110, 230,100, 30); 
                
            // doctor(1,2,3,4)textfield
                JTextField TextField2 = new JTextField(10);
                JTextField TextField3 = new JTextField(10);
                JTextField TextField4 = new JTextField(30);
                TextField2.setBounds(190, 100, 150, 30);
                TextField3.setBounds(190, 160, 150, 30);
                TextField4.setBounds(190, 230, 150, 30);
        
            // Buttons add doctor frame
                JButton button_doctor=new JButton("Submit");
                JButton button_doctor1=new JButton("back");
                button_doctor.setBounds(50, 350, 100,50); 
                button_doctor1.setBounds(330, 350, 100,50); 
                
            //Functions of each buttons are used in frame Add doctor    
                //add doctor
                    b1.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                    //show frame of add doctor 
                        frame.setVisible(false);
                        frame1.setVisible(true);
                    
                       }
                    });
                //Action of Buttons add doctor
                    //submit button
                        button_doctor.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                               if(TextField2.getText().equals("")||TextField3.getText().equals("")||TextField4.getText().equals("")){
                               JOptionPane.showMessageDialog(frame1, "Must enter all data", "Failed", JOptionPane.ERROR_MESSAGE);
                               }
                               else{
                                   try {
                                       Add_Doctor( TextField2.getText()  , TextField4.getText()  ,   TextField3.getText() );
                                   } catch (SQLException ex) {
                                       Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                                   }

                                   JOptionPane.showMessageDialog(frame2, "Successfully Added", "Successful", JOptionPane.PLAIN_MESSAGE);
                               TextField2.setText("");TextField3.setText("");TextField4.setText("");

                               }
                            }
                        });
                    //back button
                        button_doctor1.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                              frame.setVisible(true);
                              frame1.setVisible(false);

                            }
                        });
                        
                        
            //add recptionist frame
                frame2.setSize(500, 500);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setLocation(400, 120);
                frame2.setResizable(false);
                frame2.setLayout(null);
        
            //add receptionist label(7,8,9,10)
                JLabel Label7= new JLabel("Please Enter Receptionist Information......");
                JLabel Label8= new JLabel("Name");
                JLabel Label9= new JLabel("Mobile");
                JLabel Label10= new JLabel("Address");
                Label7.setBounds(150,20,300, 100);
                Label8.setBounds(110, 100,100, 30);
                Label9.setBounds(110, 160,100, 30);
                Label10.setBounds(110,230,100, 30); 
                
            //add recptionist (7,8,9,10)textfield
                JTextField TextField8 = new JTextField(10);
                JTextField TextField9 = new JTextField(10);
                JTextField TextField10 = new JTextField(30);
                TextField8.setBounds(200, 100, 150, 30);
                TextField9.setBounds(200, 160, 150, 30);
                TextField10.setBounds(200, 230, 150, 30);
                
            // buttons  add recptionist
                JButton button_receptionist=new JButton("Submit");
                JButton button_receptionist1=new JButton("back");
                button_receptionist.setBounds(50, 350, 100,50);
                button_receptionist1.setBounds(330, 350, 100,50);

                
            // Add receptionist button action 
                b2.addActionListener(new ActionListener(){ 

                  @Override
                  public void actionPerformed(ActionEvent e)
                  {
                    // Show frame Add receptionist
                        frame.setVisible(false);
                        frame2.setVisible(true);  
                    
                  }

                }); 
                
                // action of add receptionist 
                    //submit button
                        button_receptionist.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                            if(TextField8.getText().equals("")||TextField9.getText().equals("")||TextField10.getText().equals("")){
                                JOptionPane.showMessageDialog(frame2, "Must enter all data", "Failed", JOptionPane.ERROR_MESSAGE);
                                }
                            else{
                                try {
                                    Add_Receptionist(TextField8.getText()  , TextField9.getText()  ,  TextField10.getText() );
                                } catch (SQLException ex) {
                                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                                }
                              JOptionPane.showMessageDialog(frame2, "Successfully Added", "Successful", JOptionPane.PLAIN_MESSAGE);
                              TextField8.setText("");TextField9.setText("");TextField10.setText("");
                              }
                            }
                        });
                    //back button
                        button_receptionist1.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                          frame.setVisible(true);
                          frame2.setVisible(false);
                        }
                    });
                        
                        
        
             //GUI add view patient frame
                frame3.setSize(500, 500);
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.setLocation(400, 120);
                frame3.setResizable(false);
                frame3.setLayout(null);
            // add view patient component
                //patient  label 11 and textfield 11
                    JLabel Label11= new JLabel("Patient ID");
                    JTextField TextField11 = new JTextField(10);
                    TextField11.setBounds(180, 40, 150, 30);
                    Label11.setBounds(110, 40,100, 30);
                //data of patient
                    JLabel Label_pat_id= new JLabel("Pat ID");
                    JTextField TextField_pat_id = new JTextField(10);
                    TextField_pat_id.setEditable(false);
                    JLabel Label_pat_name= new JLabel("Pat Name");
                    JTextField TextField_pat_name = new JTextField(10);
                    TextField_pat_name.setEditable(false);
                    JLabel Label_pat_mobile= new JLabel("Pat Mobile");
                    JTextField TextField_pat_mobile = new JTextField(10);
                    TextField_pat_mobile.setEditable(false);
                    JLabel Label_pat_Address= new JLabel("Pat Address");
                    JTextField TextField_pat_Address = new JTextField(10);
                    TextField_pat_Address.setEditable(false);
                    JLabel Label_pat_doc_id= new JLabel("Doctor ID");
                    JTextField TextField_pat_doc_id = new JTextField(10);
                    TextField_pat_doc_id.setEditable(false);
                    JLabel Label_pat_token_id = new JLabel("Token_ID");
                    JTextField TextField_pat_token_id = new JTextField(10);
                    TextField_pat_token_id.setEditable(false);
                    JLabel Label_pat_date = new JLabel("date");
                    JTextField TextField_pat_date = new JTextField(10);
                    TextField_pat_date.setEditable(false);
                //data of patient from database scale
                    TextField_pat_id.setBounds(100,200 ,50, 30);
                    Label_pat_id.setBounds(50, 200,100, 30);
                    TextField_pat_name.setBounds(290,200 , 150, 30);
                    Label_pat_name.setBounds(220, 200,100, 30);
                    TextField_pat_mobile.setBounds(200,240 , 150, 30);
                    Label_pat_mobile.setBounds(50, 240,100, 30);
                    TextField_pat_Address.setBounds(200,280 , 150, 30);
                    Label_pat_Address.setBounds(50, 280,100, 30);
                    TextField_pat_doc_id.setBounds(200,320 ,  50, 30);
                    Label_pat_doc_id.setBounds(50, 320,200, 30);
                    TextField_pat_token_id.setBounds(350,320, 50, 30);
                    Label_pat_token_id.setBounds(270, 320,100, 30);
                    TextField_pat_date.setBounds(200,360, 150, 30);
                    Label_pat_date.setBounds(50, 360,100, 30);
                           
                // Buttons of frame view patient 
                    JButton button_search1=new JButton("Search");
                    JButton button_viewback1=new JButton("back");
                    button_search1.setBounds(50, 100, 90,40);
                    button_viewback1.setBounds(330,100, 90,40); 

                // view patient action
                    b5.addActionListener(new ActionListener()  {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                            
                            //to show frame of view patient 
                                frame.setVisible(false);
                                frame3.setVisible(true);
                            
                    }
                    });
                    // action of buttons of view patient 
                        //search button
                            button_search1.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                if(TextField11.getText().equals("")){
                               JOptionPane.showMessageDialog(frame3, "Please enter Patient ID", "Failed", JOptionPane.ERROR_MESSAGE);
                               }
                               else{ 
                                    try{    
                                        int x = Integer.parseInt(TextField11.getText());
                                        Patient pat = retrive_data_patient(x);
                                         //data of patient from datebase
                                         TextField_pat_id.setText(""+pat.getPatient_id());
                                         TextField_pat_name.setText(""+pat.getPerson_name());
                                         TextField_pat_mobile.setText(""+pat.getPerson_mobile());
                                         TextField_pat_Address.setText(""+pat.getPerson_address());
                                         TextField_pat_doc_id.setText(""+pat.getDoctor_id());
                                         TextField_pat_token_id.setText(""+pat. get_token_id());
                                         TextField_pat_date.setText(""+pat.getDate());                  
                                       JOptionPane.showMessageDialog(frame3, "Successfully Submit", "Successful", JOptionPane.PLAIN_MESSAGE);
                                       TextField11.setText("");}
                                    catch(NullPointerException ex){
                               JOptionPane.showMessageDialog(frame3, "Please enter valid patient ID", "Failed", JOptionPane.ERROR_MESSAGE);

                                    }    
                                }
                                }
                               });
                        // back button
                            button_viewback1.addActionListener(new ActionListener(){
                              @Override
                              public void actionPerformed(ActionEvent e){
                                frame.setVisible(true);
                                frame3.setVisible(false);
                              }
                             }); 

        
            // GUI add view recptionest frame
                frame4.setSize(500, 500);
                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame4.setLocation(400, 120);
                frame4.setResizable(false);
                frame4.setLayout(null);
        
            // view recptiones component
                //recptiones 12 label and textfield 12
                    JLabel Label12= new JLabel("Receptionist ID");
                    JTextField TextField12 = new JTextField(10);
                    TextField12.setBounds(200, 40, 150, 30);
                    Label12.setBounds(110, 40,100, 30);
                //data of receptionist
                    JLabel Label_rece_id= new JLabel("Recep ID");
                    JTextField TextField_rece_id = new JTextField(10);
                    TextField_rece_id.setEditable(false);
                    JLabel Label_rece_name= new JLabel("Recep Name");
                    JTextField TextField_rece_name = new JTextField(10);
                    TextField_rece_name.setEditable(false);
                    JLabel Label_rece_mobile= new JLabel("Recep Mobile");
                    JTextField TextField_rece_mobile = new JTextField(10);
                    TextField_rece_mobile.setEditable(false);
                    JLabel Label_rece_Address= new JLabel("Recep Address");
                    JTextField TextField_rece_Address = new JTextField(10);
                    TextField_rece_Address.setEditable(false);
                    JLabel Label_rece_username= new JLabel("Recep Username");
                    JTextField TextField_rece_username = new JTextField(10);
                    TextField_rece_username.setEditable(false);
                    JLabel Label_rece_password = new JLabel("Recep Password");
                    JTextField TextField_rece_password = new JTextField(10);
                    TextField_rece_password.setEditable(false);
                //data of receptionist from database
                    TextField_rece_id.setBounds(200,200 , 150, 30);
                    Label_rece_id.setBounds(50, 200,100, 30);
                    TextField_rece_name.setBounds(200,240 , 150, 30);
                    Label_rece_name.setBounds(50, 240,100, 30);
                    TextField_rece_mobile.setBounds(200,280 , 150, 30);
                    Label_rece_mobile.setBounds(50, 280,100, 30);
                    TextField_rece_Address.setBounds(200,320 , 150, 30);
                    Label_rece_Address.setBounds(50, 320,100, 30);
                    TextField_rece_username.setBounds(200,360 , 150, 30);
                    Label_rece_username.setBounds(50, 360,100, 30);
                    TextField_rece_password.setBounds(200,400, 150, 30);
                    Label_rece_password.setBounds(50, 400,100, 30);    
                //buttons of view recptiones
                    JButton button_search2=new JButton("Search");
                    JButton button_viewback2=new JButton("back");
                    button_search2.setBounds(50, 100, 90,40);
                    button_viewback2.setBounds(330,100, 90,40); 
                      
            // view Receptionist action
                b4.addActionListener(new ActionListener() {
                   @Override
                      public void actionPerformed(ActionEvent e) {
                     
                    //show frame of view Receptionist    
                        frame.setVisible(false);
                        frame4.setVisible(true); 
                   }
                });
                // add action of buttons of view receptionist
                    //search button
                       button_search2.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                           //to test it
                           //System.out.println(TextField12.getText());
                        if(TextField12.getText().equals("")){
                        JOptionPane.showMessageDialog(frame4, "Please enter Receptionist ID", "Failed", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            
                            try{   
                                   int x = Integer.parseInt(TextField12.getText());
                                   Receptionist Recep = retrive_data_rec( x );
                                   TextField_rece_id.setText(""+Recep.getReceptionId());
                                   TextField_rece_name.setText(""+Recep.getPerson_name());
                                   TextField_rece_mobile.setText(""+Recep.getPerson_mobile());
                                   TextField_rece_Address.setText(""+Recep.getPerson_address());
                                   TextField_rece_username.setText(""+Recep.getUser_name());
                                   TextField_rece_password.setText(""+Recep.getUser_password());
                                    JOptionPane.showMessageDialog(frame4, "Successfully Submit", "Successful", JOptionPane.PLAIN_MESSAGE);
                                      TextField12.setText("");
                                          }
                               
                          catch(NullPointerException ex){
                               JOptionPane.showMessageDialog(frame4, "Please enter valid receptionist ID", "Failed", JOptionPane.ERROR_MESSAGE);

                                    }
                                }
                               }
                        });
                    // back button
                        button_viewback2.addActionListener(new ActionListener(){
                         @Override
                         public void actionPerformed(ActionEvent e){
                           frame.setVisible(true);
                           frame4.setVisible(false);
                            }
                        });
        
       
            // GUI view doctor frame
                frame5.setSize(500, 500);
                frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame5.setLocation(400, 120);
                frame5.setResizable(false);
                frame5.setLayout(null);
        
            //view doctor component
                //doctor 13 label and textfield
                    JLabel Label13= new JLabel("Doctor ID");
                    JTextField TextField13 = new JTextField(10);
                    TextField13.setBounds(200, 40, 150, 30);
                    Label13.setBounds(110, 40,100, 30);
                //data of doctor
                    JLabel Label_doc_id= new JLabel("Doc ID");
                    JTextField TextField_doc_id = new JTextField(10);
                    TextField_doc_id.setEditable(false);
                    JLabel Label_doc_name= new JLabel("Doc Name");
                    JTextField TextField_doc_name = new JTextField(10);
                    TextField_doc_name.setEditable(false);
                    JLabel Label_doc_mobile= new JLabel("Doc Mobile");
                    JTextField TextField_doc_mobile = new JTextField(10);
                    TextField_doc_mobile.setEditable(false);
                    JLabel Label_doc_Address= new JLabel("Doc Address");
                    JTextField TextField_doc_Address = new JTextField(10);
                    TextField_doc_Address.setEditable(false);
                    JLabel Label_doc_username= new JLabel("Doc Username");
                    JTextField TextField_doc_username = new JTextField(10);
                    TextField_doc_username.setEditable(false);
                    JLabel Label_doc_password = new JLabel("Doc Password");
                    JTextField TextField_doc_password = new JTextField(10);
                    TextField_doc_password.setEditable(false);
                //data of doctor from database scale
                    TextField_doc_id.setBounds(200,200 , 150, 30);
                    Label_doc_id.setBounds(50, 200,100, 30);
                    TextField_doc_name.setBounds(200,240 , 150, 30);
                    Label_doc_name.setBounds(50, 240,100, 30);
                    TextField_doc_mobile.setBounds(200,280 , 150, 30);
                    Label_doc_mobile.setBounds(50, 280,100, 30);
                    TextField_doc_Address.setBounds(200,320 , 150, 30);
                    Label_doc_Address.setBounds(50, 320,100, 30);
                    TextField_doc_username.setBounds(200,360 , 150, 30);
                    Label_doc_username.setBounds(50, 360,100, 30);
                    TextField_doc_password.setBounds(200,400, 150, 30);
                    Label_doc_password.setBounds(50, 400,100, 30);
                //buttons view doctor
                    JButton button_search3=new JButton("Search");
                    JButton button_viewback3=new JButton("back");
                    button_search3.setBounds(50, 100, 90,40);
                    button_viewback3.setBounds(330,100, 90,40);
                        
            // button view doctor action 
                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //to show frame of doctor 
                           frame.setVisible(false);
                           frame5.setVisible(true);
                     }
                });    
                //button view doctor action           
                    //search button
                    button_search3.addActionListener(new ActionListener(){
                               public void actionPerformed(ActionEvent e){

                              if(TextField13.getText().equals("")){
                                JOptionPane.showMessageDialog(frame5, "Please enter Doctor ID", "Failed", JOptionPane.ERROR_MESSAGE);
                              }
                              else{

                                       try{
                                                int x = Integer.parseInt(TextField13.getText());
                                            Doctor doc = retrive_data_doc(x);

                                            TextField_doc_id.setText(""+doc.getDoctorId());
                                            TextField_doc_name.setText(""+doc.getPerson_name());
                                            TextField_doc_mobile.setText(""+doc.getPerson_mobile());
                                            TextField_doc_Address.setText(""+doc.getPerson_address());
                                            TextField_doc_username.setText(""+doc.getUser_name());
                                            TextField_doc_password.setText(""+doc.getUser_password());
                                            JOptionPane.showMessageDialog(frame5, "Successfully Submit", "Successful", JOptionPane.PLAIN_MESSAGE);

                                       }
                                       catch(NullPointerException ex){
                                            JOptionPane.showMessageDialog(frame5, "Please enter valid Doctor ID", "Failed", JOptionPane.ERROR_MESSAGE);

                                       }
                                    }

                                   }
                               });
                    //back button
                    button_viewback3.addActionListener(new ActionListener(){
                                @Override
                             public void actionPerformed(ActionEvent e){
                               frame.setVisible(true);
                               frame5.setVisible(false);
                                frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                             }
                            });

                            
                            
            //GUI add Resources Tracking frame 
                frame6.setSize(500, 500);
                frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame6.setLocation(400, 120);
                frame6.setResizable(false);
                frame6.setLayout(null);
       
            //Tracking resources components
                //table
                    JLabel head = new JLabel("Medicine Information.......");
                    head.setBounds(150, 5, 200, 50);
                    JTable MT = new JTable();
                    MT.setBounds(30, 50, 450, 150);
                    JScrollPane pane = new JScrollPane(MT);
                //insert
                    JButton BIN =new JButton("Insert");
                    BIN.setBounds(20, 250, 80, 30);
                    JLabel barcode = new JLabel("BarCode");
                    barcode.setBounds(150, 220, 50, 30);
                    TextField brcdField = new TextField();
                    brcdField.setBounds(150, 250, 70, 30);
                    JLabel name = new JLabel("Name");
                    name.setBounds(220, 220, 50, 30);
                    TextField nameField = new TextField();
                    nameField.setBounds(220, 250, 70, 30);
                    JLabel cost = new JLabel("Cost");
                    cost.setBounds(290, 220, 50, 30);
                    TextField costField = new TextField();
                    costField.setBounds(290, 250, 70, 30);
                    JLabel Num = new JLabel("Number of pieces");
                    Num.setBounds(360, 220, 150, 30);
                    TextField NumField = new TextField();
                    NumField.setBounds(360, 250, 120, 30);
                //update
                    JButton UPD =new JButton("Update");
                    UPD.setBounds(20, 320, 80, 30);
                    JLabel barcode2 = new JLabel("BarCode");
                    barcode2.setBounds(150, 290, 50, 30);
                    TextField brcdField2 = new TextField();
                    brcdField2.setBounds(150, 320, 70, 30);
                    JLabel value = new JLabel("value of number");
                    value.setBounds(290, 290, 150, 30);
                    TextField valueField = new TextField();
                    valueField.setBounds(290, 320, 130, 30);
                //delete 
                    JButton DEL =new JButton("Delete");
                    DEL.setBounds(20, 400, 80, 30);
                    JLabel barcode3 = new JLabel("BarCode");
                    barcode3.setBounds(150, 370, 50, 30);
                    TextField brcdField3 = new TextField();
                    brcdField3.setBounds(150, 400, 70, 30);
                //back button 
                    JButton BackB = new JButton("back");
                    BackB.setBounds(370, 400, 80, 30);
        
       
            //Resources Tracking action       
                 b8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Show frame of  Resources Tracking 
                        frame.setVisible(false);
                        frame6.setVisible(true);
                    //data of table 
                        try {
                            //create table
                             MT.setModel(viewMedicine());
                             pane.setBounds(30, 50, 450, 150);
                             pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
                             pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
                             pane.setVisible(true);
                                      } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                }});
            //Insert button
                        BIN.addActionListener( new ActionListener() {
                             @Override
                             public void actionPerformed(ActionEvent e) {
                                 Medicine medicine = new Medicine(Integer.parseInt(brcdField.getText()), nameField.getText(), Float.valueOf(costField.getText()), Integer.parseInt(NumField.getText()));
                         insert_new_medicine(medicine);  
                         brcdField.setText(""); nameField.setText(""); costField.setText("");  NumField.setText("");
                               try {
                            //to update the date in table is showen
                             MT.setModel(viewMedicine());
                                } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                             }
                         });
                    //Update button 
                        UPD.addActionListener( new ActionListener() {
                             @Override
                             public void actionPerformed(ActionEvent e) {
                                 UPdate_medicine(Integer.parseInt(brcdField2.getText()),Integer.parseInt(valueField.getText()));
                                 brcdField2.setText(""); valueField.setText("");
                                 //to update the date in table is showen
                                 try {
                            //create table
                             MT.setModel(viewMedicine());
                                } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             }
                         });
                    //delete button 
                        DEL.addActionListener( new ActionListener() {
                             @Override
                             public void actionPerformed(ActionEvent e) {
                                 delete_medicine(Integer.parseInt(brcdField3.getText()));
                                 brcdField3.setText("");  
                                 //to update the date in table is showen
                                 try {
                            //create table
                             MT.setModel(viewMedicine());
                                } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                             }
                         });
                    // back button
                        BackB.addActionListener(new ActionListener(){
                         @Override
                         public void actionPerformed(ActionEvent e){
                           frame.setVisible(true);
                           frame6.setVisible(false);
                         }
                     }); 
            //Gui Bill frame
                frame7.setSize(500, 500);
                frame7.setLocation(400, 120);
                frame7.setResizable(false);
                frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame7.setLayout(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //btn view Bills components
                //initial data in table 
                    JLabel labelsearch = new JLabel("Enter bill Id");
                    JTextField textsearch = new JTextField();
                    JTable TPat = new JTable();
                    TPat.setBounds(30, 130,450 , 200);
                    TPat.setModel(viewBills());
                    JScrollPane Tpane = new JScrollPane(TPat);
                    Tpane.setBounds(30, 130,450 , 200);
                    Tpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    Tpane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    Tpane.setVisible(true);
                //after search
                    JTable TPat2 = new JTable();
                    TPat2.setBounds(30, 130,450 , 200);
                    JScrollPane pane2 = new JScrollPane(TPat2);
                    pane2.setBounds(30, 130,450 , 200);
                    pane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    pane2.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    pane2.setVisible(false);
                //labels
                    labelsearch.setBounds(80, 50, 130, 30);
                    labelsearch.setForeground(Color.magenta);
                    labelsearch.setFont(new Font("Arial", Font.BOLD, 16));
                //textfield
                    textsearch.setBounds(260, 50, 160, 30);
                    textsearch.setForeground(Color.magenta);
                    textsearch.setFont(new Font("Arial", Font.BOLD, 16));
                //buttons
                    JButton button_search7=new JButton("Search");
                    JButton button_viewback7=new JButton("back");
                    button_search7.setBounds(50, 100, 90,40);
                    button_viewback7.setBounds(330,100, 90,40); 
                
            //button bill action
                    b9.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                           //show of frame bill 
                                frame7.setVisible(true);
                                frame.setVisible(false);
                               }
                    });
                            //btn submit
                                button_search7.setBounds(50, 400, 150, 30);
                                button_search7.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e){
                                    if(textsearch.getText().equals(""))
                                    {
                                        try {
                                            TPat2.setModel(viewBills());
                                        } catch (SQLException ex) {
                                            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                            Tpane.setVisible(true);
                                            pane2.setVisible(false);
                                    }
                                    else{
                                        int bill_id = Integer.parseInt(textsearch.getText());
                                        try {
                                            TPat2.setModel(view_special_bill(bill_id));
                                        } catch (SQLException ex) {
                                            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                            Tpane.setVisible(false);
                                            pane2.setVisible(true);
                                    }
                                   }
                                });

                            //btn back
                                button_viewback7.setBounds(300, 400, 150, 30);
                                button_viewback7.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e){
                                        textsearch.setText("");
                                        frame7.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });
                       
 
            // add components to frames 
                //doctor
                    frame1.add(TextField2);frame1.add(TextField3);frame1.add(TextField4);
                    frame1.add(Label1);frame1.add(Label2);frame1.add(Label3);frame1.add(Label4);frame1.add(button_doctor);
                    frame1.add(button_doctor1);
                //receptionest  
                    frame2.add(TextField8);frame2.add(TextField9);frame2.add(TextField10);
                    frame2.add(Label7);frame2.add(Label8);frame2.add(Label9);frame2.add(Label10);frame2.add(button_receptionist);
                    frame2.add(button_receptionist1);
                // view patient   
                    frame3.add(TextField11);frame3.add(Label11);frame3.add(button_search1);frame3.add(button_viewback1);
                    frame3.add(Label_pat_id); frame3.add(TextField_pat_id);
                    frame3.add(Label_pat_name); frame3.add(TextField_pat_name);frame3.add(Label_pat_mobile); frame3.add(TextField_pat_mobile);
                    frame3.add(Label_pat_Address); frame3.add(TextField_pat_Address);frame3.add(Label_pat_doc_id); frame3.add(TextField_pat_doc_id);
                    frame3.add(Label_pat_token_id); frame3.add(TextField_pat_token_id); frame3.add(Label_pat_date); frame3.add(TextField_pat_date);
                // view recptionest  
                    frame4.add(TextField12);frame4.add(Label12);frame4.add(button_search2);
                    frame4.add(button_viewback2);frame4.add(Label_rece_id); frame4.add(TextField_rece_id);
                    frame4.add(Label_rece_name); frame4.add(TextField_rece_name);frame4.add(Label_rece_mobile); frame4.add(TextField_rece_mobile);
                    frame4.add(Label_rece_Address); frame4.add(TextField_rece_Address);frame4.add(Label_rece_username); frame4.add(TextField_rece_username);
                    frame4.add(Label_rece_password); frame4.add(TextField_rece_password);
                // view doctor  
                    frame5.add(TextField13);frame5.add(Label13);frame5.add(button_search3);frame5.add(button_viewback3);
                    frame5.add(Label_doc_id); frame5.add(TextField_doc_id);
                    frame5.add(Label_doc_name); frame5.add(TextField_doc_name);frame5.add(Label_doc_mobile); frame5.add(TextField_doc_mobile);
                    frame5.add(Label_doc_Address); frame5.add(TextField_doc_Address);frame5.add(Label_doc_username); frame5.add(TextField_doc_username);
                    frame5.add(Label_doc_password); frame5.add(TextField_doc_password);
                //view Resources Tracking 
                    frame6.add(head);/*frame6.add(MT);*/frame6.add(pane); frame6.add(BIN);frame6.add(barcode); frame6.add(brcdField);
                    frame6.add(name);frame6.add(nameField); frame6.add(cost);frame6.add(costField); frame6.add(Num); frame6.add(NumField);
                    frame6.add(UPD); frame6.add(barcode2);frame6.add(brcdField2); frame6.add(value); frame6.add(valueField);
                    frame6.add(DEL); frame6.add(brcdField3); frame6.add(barcode3); frame6.add(BackB);
                 //Revesion Bills
                    frame7.add(labelsearch);frame7.add(textsearch);
                    frame7.add(b9);frame7.add(b5);frame7.add(Tpane); frame7.add(pane2);frame7.add(button_search7); frame7.add(button_viewback7);                                       
                 //original frame   
                    frame.add(profile); frame.add(b1);  frame.add(b2); frame.add(b3); frame.add(b4);frame.add(b5);frame.add(b6); frame.add(b7); frame.add(b8); frame.add(b9);
                    frame.setVisible(true);
    }


//functions setter & getter
     //User_name
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
   
    
    
//user functions
    public void changepassword(String username , String user_old_password, String user_new_password) {
              user_password = user_new_password;
            update_pass( username ,  user_new_password);
            JOptionPane.showMessageDialog(frame, "Successfully Update", "Successful", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void logout() {
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
  
    
    
//functions insert date from database  
    private void insert_new_doc(Doctor doctor1){
     try{
        
        String insert = "INSERT INTO doctor(id , name , mobile , address , username , password ) VALUES (NULL,?,?,?,?,?)"; 
        PreparedStatement preparedStmt = fun().prepareStatement(insert);
        // create the java statement
            // execute the query,
      
        preparedStmt.setString(1, doctor1.getPerson_name());
        preparedStmt.setString(2, doctor1.getPerson_mobile());
        preparedStmt.setString(3,  doctor1.getPerson_address());
        preparedStmt.setString(4, doctor1.getUser_name());
        preparedStmt.setString(5,  doctor1.getUser_password());
        preparedStmt.execute();
             fun().close();
       
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }
    
    }  
    private void insert_new_recep(Receptionist repec){
     try{
     String insert = "INSERT INTO receptionist(id , name , mobile , address , username , password ) VALUES (NULL,?,?,?,?,?)"; 
     PreparedStatement preparedStmt = fun().prepareStatement(insert);
    // create the java statement
    // execute the query,
       
        preparedStmt.setString(1,repec.getPerson_name());
        preparedStmt.setString(2,repec.getPerson_mobile());
        preparedStmt.setString(3,repec.getPerson_address());
        preparedStmt.setString(4,repec.getUser_name());
        preparedStmt.setString(5,repec.getUser_password());
        preparedStmt.execute();
             fun().close();
    }
    catch(SQLException e){
      JOptionPane.showMessageDialog(null, e);
    }
    
    }
    private void insert_new_medicine (Medicine m){
         try{
     String insert = "INSERT INTO medicine (barcode, name, cost, number_pieces) VALUES (?, ?, ?, ?)"; 
     PreparedStatement preparedStmt = fun().prepareStatement(insert);
    // create the java statement
    // execute the query,
        preparedStmt.setInt(1,m.getBarcode());
        preparedStmt.setString(2,m.getName());
        preparedStmt.setFloat(3,m.getCost());
        preparedStmt.setInt(4,m.getNumber_pieces());
        preparedStmt.execute();
             fun().close();
    }
    catch(SQLException e){
      JOptionPane.showMessageDialog(null, e);
    }
    
   } 
    //Add Doctor
    public void Add_Doctor(String namedoc , String Addressdoc , String mobiledoc ) throws SQLException {
        
        String username=namedoc+"@Clinic";
        String password="123";
        Doctor doctor1 = new Doctor( namedoc, Addressdoc, mobiledoc,username,password);
        insert_new_doc(doctor1);
        
        
    }
    //Add_Receptionist
    public void Add_Receptionist(String namerec , String Addressrec , String mobilerec ) throws SQLException  {
      String username=namerec+"@Clinic";
        String password="123";
       Receptionist recep1 = new Receptionist( namerec, Addressrec, mobilerec,username,password); 
       insert_new_recep(recep1);
       
    }
    
    
    
//functions update & delete date from database  
    private void update_pass(String username , String newpass) {
    try{
         
        String update = "UPDATE Admin SET PASSWORD = ? WHERE USERNAME = ?"; 
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
    private void UPdate_medicine(int barcode ,int new_value)  {
       try{
        // create the java mysql update preparedstatement
      String query = "update medicine set number_pieces = ? where barcode = ?";
      PreparedStatement preparedStmt = fun().prepareStatement(query);
      preparedStmt.setInt   (1,new_value);
      preparedStmt.setInt(2, barcode);

      // execute the java preparedstatement
      preparedStmt.executeUpdate();
      
      fun().close();
    }
    catch(SQLException e )
    {
        JOptionPane.showMessageDialog( frame6, e);
    }
    
   } 
    private void delete_medicine(int barcode) {
       try{
        // create the java mysql update preparedstatement
      String query = "delete FROM medicine where barcode = ?";
      PreparedStatement preparedStmt = fun().prepareStatement(query);
      preparedStmt.setInt(1, barcode);

      // execute the java preparedstatement
      preparedStmt.executeUpdate();
      
      fun().close();
    }
    catch(SQLException e )
    {
        JOptionPane.showMessageDialog( frame6, e);
    }
    
   }



//functions retrive date from database    
    public DefaultTableModel viewBills() throws SQLException {
        
        DefaultTableModel dm = new DefaultTableModel();
        String[] arr = {"BILL_ID","PATIENT_ID","DOCTOR_ID","MEDICINE","DATE","Resources_Charges","medicine_price","Bed_Cost","Total_Charge"};
        
        for (int i = 0; i < arr.length; i++) {
            dm.addColumn(arr[i]);
        }
         
        String qSql = "SELECT * FROM bill";
        Statement stm = fun().createStatement();
        ResultSet rs = stm.executeQuery(qSql);
        while(rs.next())
        {
               Object [] row = {rs.getInt("BILL_ID"), rs.getInt("PATIENT_ID"), rs.getInt("DOCTOR_ID"), rs.getString("MEDICINE") , rs.getDate("DATE") ,rs.getFloat("Resources_Charges"), rs.getFloat("medicine_price") , rs.getFloat("Bed_Cost") ,rs.getFloat("Total_Charge") };
                dm.addRow(row);
        }
            
        return  dm; 
    }   
    public DefaultTableModel view_special_bill(int bill_id) throws SQLException {
        
        DefaultTableModel dm = new DefaultTableModel();
        String[] arr = {"BILL_ID","PATIENT_ID","DOCTOR_ID","MEDICINE","DATE","Resources_Charges","medicine_price","Bed_Cost","Total_Charge"};
        
        for (int i = 0; i < arr.length; i++) {
            dm.addColumn(arr[i]);
        }
         
        String qSql = "SELECT * FROM bill WHERE BILL_ID = '"+bill_id+"'";
        Statement stm = fun().createStatement();
        ResultSet rs = stm.executeQuery(qSql);
        while(rs.next())
        {
               Object [] row = {rs.getInt("BILL_ID"), rs.getInt("PATIENT_ID"), rs.getInt("DOCTOR_ID"), rs.getString("MEDICINE") , rs.getDate("DATE") ,rs.getFloat("Resources_Charges"), rs.getFloat("medicine_price") , rs.getFloat("Bed_Cost") ,rs.getFloat("Total_Charge") };
                dm.addRow(row);
        }
            
        return  dm; 
    }   
    private Receptionist retrive_data_rec( int id_recp){
     Receptionist recep =null;
        try{
      DefaultTableModel dm = new DefaultTableModel();
       String search = "SELECT * FROM receptionist WHERE id = '"+id_recp+"'"; 
   // create the java statement
        Statement pst= fun().createStatement();
    // execute the query,
        ResultSet rs = pst.executeQuery(search); 
        //data to be displayed in the table
       while (rs.next()) 
       {               
              recep = new Receptionist(rs.getString("name"),rs.getString("mobile"),rs.getString("address"),rs.getString("username"),rs.getString("password") );
              recep.setReceptionId(rs.getInt("id"));
       }
          
       fun().close();
     }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
 return recep ; 
    }
    private Patient retrive_data_patient( int id_patient){
     Patient pat = null; 
    try{
        String search = "SELECT * FROM patient WHERE id = '"+id_patient+"'"; 
        // create the java statement
    Statement pst= fun().createStatement();
    // execute the query,
        ResultSet rs = pst.executeQuery(search); 
         //data to be displayed in the table
       while (rs.next()) 
       {               
              pat = new Patient( rs.getString("name"), rs.getString("mobile"), rs.getString("address"), rs.getInt("doctor_id"), rs.getInt("token_id") ,rs.getDate("date") );
              pat.setPatient_id(rs.getInt("id"));
       }
          
       fun().close();
        
    }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
    return pat;
    }
    public DefaultTableModel viewMedicine() throws SQLException {
        
        DefaultTableModel dm = new DefaultTableModel();
        String[] arr = {"barcode","name","cost","number_pieces"};
        
        for (int i = 0; i < arr.length; i++) {
            dm.addColumn(arr[i]);
        }
        //dm.addRow(arr);
        String qSql = "SELECT * FROM medicine";
        Statement stm = fun().createStatement();
        ResultSet rs = stm.executeQuery(qSql);
        while(rs.next())
        {
               Object [] row = {rs.getInt("barcode"), rs.getString("name"), rs.getFloat("cost"), rs.getInt("number_pieces")};
                dm.addRow(row);
        }
            
        return  dm; 
    }
     
    // View Patient
    public void View_Patient( String id ) {
        frame.setVisible(false);
        frame3.setVisible(true);
    }
    //view Resources tracking 
    public void View_Resources_tracking( String id ) {
        frame.setVisible(false);
        frame6.setVisible(true);
    }
    private Doctor retrive_data_doc( int id_doc){
    Doctor Doc =null; 
    try{
        String search = "SELECT * FROM doctor WHERE id = '"+id_doc+"'"; 
        // create the java statement
        Statement pst= fun().createStatement();
        // execute the query,
        ResultSet rs = pst.executeQuery(search); 
        while (rs.next()) 
       {               
              Doc = new Doctor(rs.getString("name"),rs.getString("mobile"),rs.getString("address"),rs.getString("username"),rs.getString("password") );
              Doc.setDoctorId(rs.getInt("id"));
       }
       fun().close();
  }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
   return Doc;
    }
    
    
}