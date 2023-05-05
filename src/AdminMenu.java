import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Vector;

import static java.awt.event.ActionEvent.*;

public class AdminMenu implements ActionListener {
    JFrame jfrm = new JFrame("Admin Page");
    JPanel panel = new JPanel(); // JPanel
    JButton btnUser = new JButton("Users"); // Users Button
    JButton btnBooks = new JButton("Books"); // Books Button
    JTable table; // JTable here
    JScrollPane scrollPane;
    JButton userAddBtn = new JButton("Add"); // User Add
    JButton userDeleteBtn = new JButton("Delete"); // User Delete
    JButton bookAddBtn = new JButton("Add"); // Book Add
    JButton bookDelBtn = new JButton("Delete"); // Book Del

    // Database Part Definations
    Vector columnNames = new Vector(); // It is a dynamic array which can grow or shrink it's size. So, to store column names in user, this column name is created
    Vector rowData = new Vector (); // To store row data of columns in given table ( User )

    Vector columnNames1 = new Vector();
    Vector rowData1 = new Vector ();
    public void admin_menu(){

        // Setting up Admin Dashboard
        try {
            usersPanel();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

            // Setting up JFrame
        jfrm.setSize(1380,800);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.getContentPane().setLayout(null);
//        jfrm.getContentPane().setBackground(Color.lightGray);
        jfrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jfrm.setVisible(true);

//         Setting up JPanel
        panel.setBounds(0,120,1380,700);
        panel.setLayout(new FlowLayout());
//        panel.setBackground(Color.white);
        jfrm.add(panel);


        // Setting the Buttons in JFrame
        btnUser.setBounds(550,70,70,50);
        jfrm.add(btnUser);
        btnBooks.setBounds(700,70,70,50);
        jfrm.add(btnBooks);

        // Adding Action Listeners
        btnUser.addActionListener(this);
        btnBooks.addActionListener(this);

        }

    public static void main(String[] args) {
        AdminMenu am = new AdminMenu();
        am.admin_menu();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void usersPanel() throws SQLException { // JPanel if user clicks btnUsers



        Vector columnNames = new Vector();
        Vector rowData = new Vector ();


        Connection conn = connect(); // Creating Connection
        String sqlUser = "SELECT * FROM `users`";
        Statement st = conn.createStatement(); // Creating Statement
        ResultSet rs = st.executeQuery(sqlUser); // Passing Statement to Result set i.e. Executing Query
        ResultSetMetaData metaData = rs.getMetaData(); // Retrieving all the imformation from ResultSet
        int columnsCount = metaData.getColumnCount(); // Counting the number of Columns in selected table ( Users )
        System.out.println(columnsCount); // Prints the number of column presented in the users table
        for (int i=1;i<=columnsCount;i++){
            System.out.println("For loop is running");
            columnNames.addElement(metaData.getColumnName(i));
        }
        while (rs.next()){
            System.out.println("While Loop is running");
            Vector row = new Vector (columnsCount);
            for (int i=1;i<=columnsCount;i++){
                row.addElement(rs.getObject(i)); // The rs.getObject() method in Java is used to retrieve the value of a column
                                                 // in a JDBC ResultSet object as an Object. If there is integer stored. it willl return int object.
            }
            rowData.addElement(row);
        }
        conn.close();
        System.out.println("Closing Connection");
        st.close();
        System.out.println("Closing Statement");
        rs.close();
        System.out.println("Closing Resultset");

        // Now Creating Table
        System.out.println(columnNames);
        System.out.println(rowData);

        DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1370,400));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
//        panel.setLayout(new BorderLayout());
        panel.add(scrollPane);

        // Setting up the Add and Delete Button

        panel.add(userAddBtn);
        panel.add(userDeleteBtn);

        jfrm.revalidate(); // This will validate if any change is made to the JFrame

        btnUser.setEnabled(false);
        btnBooks.setEnabled(true);

        // Creating an Action Event Object
        btnBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                jfrm.revalidate();;
                jfrm.repaint();


                try {
                    booksPanel();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Action Listener for Add User
        userAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddUser addUser = new AddUser();
                addUser.addUser();

            }
        });

        // Action Listener for Delete User Button
        userDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DeleteUser deleteUser = new DeleteUser();
                deleteUser.delUser();

            }
        });


    }
    // Method which will run if user clicks on books button
    public void booksPanel() throws SQLException { // JPanel in user clicks btnBooks



        Vector columnNames1 = new Vector();
        Vector rowData1 = new Vector ();

        System.out.println("Entered Books Panel");

        Connection conn1 = connect(); // Creating Connection
        String sqlBooks = "SELECT * FROM `books`";
        Statement st1 = conn1.createStatement(); // Creating Statement
        ResultSet rs1 = st1.executeQuery(sqlBooks); // Passing Statement to Result set i.e. Executing Query
        ResultSetMetaData metaData1 = rs1.getMetaData(); // Retrieving all the imformation from ResultSet
        int columnsCount1 = metaData1.getColumnCount(); // Counting the number of Columns in selected table ( Users )
        System.out.println(columnsCount1); // Prints the number of column presented in the users table
        for (int i=1;i<=columnsCount1;i++){
            System.out.println("For loop is running");
            columnNames1.addElement(metaData1.getColumnName(i));
        }
        while (rs1.next()){
            System.out.println("While Loop is running");
            Vector row1 = new Vector (columnsCount1);
            for (int i=1;i<=columnsCount1;i++){
                row1.addElement(rs1.getObject(i)); // The rs.getObject() method in Java is used to retrieve the value of a column
                // in a JDBC ResultSet object as an Object. If there is integer stored. it willl return int object.
            }
            rowData1.addElement(row1);
        }
        conn1.close();
        System.out.println("Closing Connection-1");
        st1.close();
        System.out.println("Closing Statement-1");
        rs1.close();
        System.out.println("Closing Resultset-1");

        // Now Creating Table
        System.out.println(columnNames1);
        System.out.println(rowData1);

        DefaultTableModel model = new DefaultTableModel(rowData1,columnNames1);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1370,400));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
//        panel.setLayout(new BorderLayout());
        panel.add(scrollPane);

//         Setting up the Add and Delete Button

        panel.add(bookAddBtn);
        panel.add(bookDelBtn);

        jfrm.revalidate();

        btnUser.setEnabled(true);
        btnBooks.setEnabled(false);

        // Creating an Action Event Object
        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                System.out.println("ya samma aaxa");
                jfrm.revalidate();;
                jfrm.repaint();

                try {
                    usersPanel();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



    }
    // Creating Connection
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

