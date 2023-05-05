import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login implements ActionListener {

    JFrame jfrm = new JFrame("LMS Login");
    JLabel lblUserName = new JLabel("Username"); // Username
    JLabel lblPassword = new JLabel("Password"); // Password
    JTextField fieldUserName = new JTextField(); // TextField Username
    JTextField fieldPassword = new JTextField(); // TextField Password
    JRadioButton radioAdmin = new JRadioButton("Admin"); // Admin RadioButton
    JRadioButton radioUser = new JRadioButton("User"); // User RadioButton

    ButtonGroup btnGroup = new ButtonGroup(); // Button Group

    JButton btnLogin = new JButton("Login"); // Login
    JButton btnSignUp = new JButton("Sign Up"); // Sign Up

    Statement st; // creating Statement
    ResultSet rs,rs1; // Creating ResultSet


    // Calling the Constructor
    Login(){
        initUI();
        addComponents();
        addActionEvents();
    }

    public void initUI(){
        jfrm.setSize(600,500);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLayout(null);
        jfrm.setVisible(true);
        jfrm.setLocationRelativeTo(null);
        jfrm.setResizable(false);
    }
    public void addComponents(){
        lblUserName.setBounds(100,100,100,30); // Username
        jfrm.add(lblUserName);
        fieldUserName.setBounds(170,100,300,30);
        jfrm.add(fieldUserName);

        lblPassword.setBounds(100,150,100,30); // Password
        jfrm.add(lblPassword);
        fieldPassword.setBounds(170,150,300,30);
        jfrm.add(fieldPassword);

        radioAdmin.setBounds(190,180,80,30);
        jfrm.add(radioAdmin);
        radioUser.setBounds(290,180,80,30);
        jfrm.add(radioUser);

        btnGroup.add(radioAdmin);
        btnGroup.add(radioUser);

        btnLogin.setBounds(190,240,100,30); // Login
        jfrm.add(btnLogin);

        btnSignUp.setBounds(350,240,100,30); // Sign Up
        jfrm.add(btnSignUp);

    }
    public void addActionEvents(){
        btnSignUp.addActionListener(this);
        btnLogin.addActionListener(this);
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

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==btnLogin){
            
            // validating the form at first

            if (fieldUserName.getText().equals("") && fieldPassword.getText().equals("")){
                JOptionPane.showMessageDialog(jfrm,"Please enter your Username and Password");
            } else if (fieldUserName.getText().equals("")) {
                JOptionPane.showMessageDialog(jfrm,"Please enter your Username");
            }else if (fieldPassword.getText().equals("")) {
                JOptionPane.showMessageDialog(jfrm,"Please enter your Password");
            } else if ((!radioUser.isSelected() && !radioAdmin.isSelected())){
                JOptionPane.showMessageDialog(jfrm,"Please choose the user option");
            } else{
                System.out.println("Login Connect");
                Connection connection = connect(); // Connect to the database
                try{
                    System.out.println(fieldUserName.getText());
                    System.out.println(fieldPassword.getText());
                    Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); // Creating Statement
                    ResultSet rs = st.executeQuery("SELECT * FROM USERS WHERE USERNAME='"+fieldUserName.getText()+"' AND PASSWORD='"+fieldPassword.getText()+"'");

                    try{
                        if (rs.first()==false){
                            System.out.println("Invalid user");
                            JOptionPane.showMessageDialog(jfrm,"Invalid Username or Password"); // Displaying error message
                        } else if (radioAdmin.isSelected()) {
                            try{
                                String type1 ="  SELECT `type` FROM `users` WHERE `username` = '"+fieldUserName.getText()+"' AND `type` = 1";
                                ResultSet rs1 = st.executeQuery(type1);


                                if (rs1.first() == true){
                                    System.out.println(rs1.first());
//                                    rs1.beforeFirst();
                                    jfrm.dispose();
                                    AdminMenu objAdminMenu = new AdminMenu();
                                    System.out.println("Admin is selected");
                                    objAdminMenu.admin_menu();
                                }
                            }catch (Exception ee){
                                System.out.println(ee);
                            }

                        }else if (radioUser.isSelected()) {
                            try{
                                String type0 =" SELECT `type` FROM `users` WHERE `username` = '"+fieldUserName.getText()+"' AND `type` = 0";
                                ResultSet rs0 = st.executeQuery(type0);


                                if (rs0.first() == true){
                                    System.out.println(rs0.first());
//                                    rs0.beforeFirst();
                                    jfrm.dispose();
                                    UserMenu objUserMenu = new UserMenu();
                                    System.out.println("User is selected");
                                    objUserMenu.user_menu();
                                }
                            }catch (Exception ee){
                                System.out.println(ee);
                            }

                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }catch (Exception ee){
                        ee.printStackTrace();}


                }
            }

        }
    }

