import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.awt.Font.*;
import static java.awt.font.TextAttribute.FONT;

public class AddUser implements ActionListener {
  public void addUser(){



      JFrame jfrm;
      JLabel jlbl = new JLabel("Add User to LMS");
      JLabel lblType = new JLabel("Select Type");;
      JLabel lblName = new JLabel("Name :");
      JLabel lblUsername = new JLabel("Username :");
      JLabel lblContact = new JLabel("Contact No :");
      JLabel lblAddress = new JLabel("Address :");
      JLabel lblEmail = new JLabel("Email :");
      JLabel lblPassword = new JLabel("Password :");
      JTextField fieldName = new JTextField();
      JTextField fieldUsername = new JTextField();
      JTextField fieldContact = new JTextField();
      JTextField fieldAddress = new JTextField();
      JTextField fieldEmail = new JTextField();
      JTextField fieldPassword = new JTextField();
      JTextField fieldType = new JTextField();

      JRadioButton radioAdmin = new JRadioButton("Admin");
      JRadioButton radioUser = new JRadioButton("User");
      ButtonGroup buttonGroup = new ButtonGroup();

      JButton btnSubmit = new JButton("Submit");




      // Setting Up JFrame

      jfrm = new JFrame("Add User");
      jfrm.setSize(600,600);
      jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      jfrm.setLayout(null);
      jfrm.setVisible(true);
      jfrm.setLocationRelativeTo(null);
      jfrm.setResizable(false);

      // Adding Components

      jlbl.setBounds(170,40,400,40);
      jlbl.setFont(new Font("Sans-Serif",Font.BOLD,32));
      jfrm.add(jlbl);

      lblName.setBounds(100,100,100,30); // Name
      jfrm.add(lblName);
      fieldName.setBounds(170,100,300,30);
      jfrm.add(fieldName);

      lblUsername.setBounds(100,150,100,30); // Username
      jfrm.add(lblUsername);
      fieldUsername.setBounds(170,150,300,30);
      jfrm.add(fieldUsername);

      lblEmail.setBounds(100,200,100,30); // Email
      jfrm.add(lblEmail);
      fieldEmail.setBounds(170,200,300,30);
      jfrm.add(fieldEmail);

      lblType.setBounds(100,250,100,30); // Type
      jfrm.add(lblType);
      radioAdmin.setBounds(200,250,100,30);
      radioUser.setBounds(300,250,100,30);
      buttonGroup.add(radioAdmin);
      buttonGroup.add(radioUser);
      jfrm.add(radioAdmin);
      jfrm.add(radioUser);

      lblContact.setBounds(100,300,100,30); // Contact
      jfrm.add(lblContact);
      fieldContact.setBounds(170,300,300,30);
      jfrm.add(fieldContact);

      lblAddress.setBounds(100,350,100,30); // Address
      jfrm.add(lblAddress);
      fieldAddress.setBounds(170,350,300,30);
      jfrm.add(fieldAddress);

      lblPassword.setBounds(100,400,100,30); // Password
      jfrm.add(lblPassword);
      fieldPassword.setBounds(170,400,300,30);
      jfrm.add(fieldPassword);

      btnSubmit.setBounds(270,450,100,30);
      jfrm.add(btnSubmit);

      btnSubmit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

              if (fieldUsername.getText().equals("") || fieldPassword.getText().equals("") || fieldName.getText().equals("") || fieldContact.getText().equals("") || fieldAddress.getText().equals("") || fieldEmail.getText().equals("") || (!radioUser.isSelected() && !radioAdmin.isSelected())){
                  JOptionPane.showMessageDialog(jfrm,"Please Fill all the given values");
              }


          }
      });



  }

    public static void main(String[] args) {
       AddUser au = new AddUser();
       au.addUser();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static Connection connect(){
        // Connecting with the database LMS
        try{
            // Connecting Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Load Driver");

            // Creating Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root","");
            System.out.println("Connected to mySql");
            return con;
        }catch (Exception ee){
            ee.printStackTrace();
        }
        return null;
    }
}
