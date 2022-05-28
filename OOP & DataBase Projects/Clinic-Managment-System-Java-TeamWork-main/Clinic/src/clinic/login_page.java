package clinic;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
 /**
 *
 * @author Seif
 */


public class login_page extends JFrame implements ActionListener {
    
    //make connection with database
     public Connection fun () throws SQLException
    {
        connection makeconn = new connection();
            
        Connection conn = makeconn.conn();
        
        return conn;
    }
    
    
    
    public login_page(){
        LoginPage();
    }
        //    Login Page Start
             JFrame logInFrame;
             JLabel loginHeaderLabel;
             JLabel usernameLabel;
        //     JLabel usernameHiddenLabel;
             JTextField usernameTextField;
             JLabel passwordLabel;
        //     JLabel passwordHiddenLabel;
             JPasswordField passwordTextField;
             JLabel jobLabel;
        //     JLabel jobHiddenLabel;
             JRadioButton adminRadioButton;
             JRadioButton doctorRadioButton;
             JRadioButton receptionistRadioButton;
             ButtonGroup jobButtonGroup;
             JButton loginButton;
             static JLabel hiddenWrongLogin;
    public void LoginPage(){
        //img
    ImageIcon icon = new ImageIcon("ig.jpg");
    JLabel img = new JLabel(icon);
    img.setBounds(0, -40, 500, 540);
        //        this Variable is used to 
        //        choose the role of the user(admin,doctor,receptionist)
        //        and then take the user to the page of it's role
        Username="";
        Password="";
        Job="";
        logInFrame=new JFrame();
        logInFrame.setTitle("Login Page");
        logInFrame.setSize(500,500);
        logInFrame.setLocation(400,120);
        logInFrame.setResizable(false);
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logInFrame.setLayout(null);
        
        loginHeaderLabel=new JLabel("Login");
        loginHeaderLabel.setBounds(210,20,200,40);
        loginHeaderLabel.setFont(new Font("Arial",Font.BOLD,30));
        logInFrame.add(loginHeaderLabel);
        
        usernameLabel=new JLabel("Username: ");
        usernameLabel.setBounds(50,120,110,30);
        usernameLabel.setFont(new Font("Arial",Font.BOLD,20));
        logInFrame.add(usernameLabel);
        
        usernameTextField=new JTextField();
        usernameTextField.setBounds(200,115,230,40);
        usernameTextField.setFont(new Font("Arial", Font.ROMAN_BASELINE,20));
        usernameTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        logInFrame.add(usernameTextField);
        
        passwordLabel=new JLabel("Password: ");
        passwordLabel.setBounds(50,220,200,30);
        passwordLabel.setFont(new Font("Arial",Font.BOLD,20));
        logInFrame.add(passwordLabel);        
        
        passwordTextField=new JPasswordField();
        passwordTextField.setBounds(200,215,230,40);
        passwordTextField.setFont(new Font("Arial", Font.ROMAN_BASELINE,20));
        passwordTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        logInFrame.add(passwordTextField);       
        
        jobLabel=new JLabel("User job: ");
        jobLabel.setBounds(50,310,200,40);
        jobLabel.setFont(new Font("Arial",Font.BOLD,20));
        logInFrame.add(jobLabel);
        
        adminRadioButton=new JRadioButton("Admin");
        adminRadioButton.setBounds(160,315,80,30);
        adminRadioButton.setFont(new Font("Arial",Font.ROMAN_BASELINE,20));
        logInFrame.add(adminRadioButton);

        doctorRadioButton=new JRadioButton("Doctor");
        doctorRadioButton.setBounds(390,315,90,30);
        doctorRadioButton.setFont(new Font("Arial",Font.ROMAN_BASELINE,20));
        logInFrame.add(doctorRadioButton);
        
        receptionistRadioButton=new JRadioButton("Receptionist");
        receptionistRadioButton.setBounds(245,315,140,30);
        receptionistRadioButton.setFont(new Font("Arial",Font.ROMAN_BASELINE,20));
        logInFrame.add(receptionistRadioButton);
        
        jobButtonGroup=new ButtonGroup();
        jobButtonGroup.add(adminRadioButton);
        jobButtonGroup.add(doctorRadioButton);
        jobButtonGroup.add(receptionistRadioButton);

        loginButton=new JButton("Login");
        loginButton.setBounds(200,400,100,30);
        loginButton.setFont(new Font("Arial",Font.BOLD,20));
        logInFrame.add(loginButton);
        
        hiddenWrongLogin=new JLabel("User is not Found");
        hiddenWrongLogin.setBounds(190,450,400,70);
        hiddenWrongLogin.setFont(new Font("Arial",Font.BOLD,15));
        hiddenWrongLogin.setForeground(Color.red);
        hiddenWrongLogin.setVisible(false);
        logInFrame.add(hiddenWrongLogin); 
        
        logInFrame.add(img);
        loginButton.addActionListener(this);
        adminRadioButton.addActionListener(this);
        logInFrame.setVisible(true);

    }
    
             String Username="";
             String Password="";
             String Job="";
             String namepage="-1"; 
    @Override
    public void actionPerformed(ActionEvent e){
//         Login Page Action Events Start
        if(e.getSource()==loginButton){
            Username=usernameTextField.getText();
            Password=passwordTextField.getText();
            if(adminRadioButton.isSelected()){
                Job="admin";
                usernameTextField.setText("");
                passwordTextField.setText("");
                 boolean f = check_admin(Username, Password);
                if(f){
                try {
                    logInFrame.setVisible(false); 
                   new Admin(Username , Password);
                } catch (SQLException ex) {
                    Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
                else 
                {
                  JOptionPane.showMessageDialog(logInFrame, "please enter valid email please cheack username or password ...","error" , JOptionPane.ERROR_MESSAGE);
                }
             }
            else if(doctorRadioButton.isSelected()){
                Job="doctor"; 
                usernameTextField.setText("");
                passwordTextField.setText(""); 
                boolean f = check_doctor(Username, Password);
                 if(f){
                try { 
                    logInFrame.setVisible(false); 
                  new Doctor(Username,Password);
                } catch (SQLException ex) {
                    Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
                }
                 }
                else 
                {
                  JOptionPane.showMessageDialog(logInFrame, "please enter valid email please cheack username or password ...","error" , JOptionPane.ERROR_MESSAGE);
                }
    
            }
            else if(receptionistRadioButton.isSelected()){
                Job="receptionist";
                 usernameTextField.setText("");
                passwordTextField.setText(""); 
                boolean f = check_Receptionist(Username, Password);
                 if(f){
                 try {  
                     logInFrame.setVisible(false);
                  new Receptionist(Username, Password);
                } catch (SQLException ex) {
                    Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
                }
                  }
                else 
                {
                  JOptionPane.showMessageDialog(logInFrame, "please enter valid email please cheack username or password ...","error" , JOptionPane.ERROR_MESSAGE);
                }
            }

            
        }
    }
private boolean check_admin(String username ,String password){
      boolean flag =false;
        try{
      DefaultTableModel dm = new DefaultTableModel();
       String search = "SELECT * FROM admin WHERE  USERNAME = '"+username+"' AND PASSWORD = '"+password+"'"; 
      // create the java statement
        Statement pst= fun().createStatement();
    // execute the query,
        ResultSet rs = pst.executeQuery(search); 
        //data to be displayed in the table
       if (rs.next()) 
       {               
          flag = true;          
       }
          
       fun().close();
     }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
 return flag ; 
    
  }
private boolean check_doctor(String username ,String password){
  
      boolean flag =false;
        try{
      DefaultTableModel dm = new DefaultTableModel();
       String search = "SELECT * FROM doctor WHERE  username = '"+username+"' AND password = '"+password+"'"; 
   // create the java statement
        Statement pst= fun().createStatement();
    // execute the query,
        ResultSet rs = pst.executeQuery(search); 
        //data to be displayed in the table
       if (rs.next()) 
       {               
          flag = true;          
       }
          
       fun().close();
     }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
 return flag ; 
    
  }
private boolean check_Receptionist(String username ,String password){
  
      boolean flag =false;
        try{
      DefaultTableModel dm = new DefaultTableModel();
       String search = "SELECT * FROM receptionist WHERE  username = '"+username+"' AND password = '"+password+"'"; 
   // create the java statement
        Statement pst= fun().createStatement();
    // execute the query,
        ResultSet rs = pst.executeQuery(search); 
        //data to be displayed in the table
       if (rs.next()) 
       {               
          flag = true;          
       }
          
       fun().close();
     }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
 return flag ; 
    
  }
 
}
