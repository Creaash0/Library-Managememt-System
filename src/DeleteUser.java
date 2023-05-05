import javax.swing.*;
import java.awt.*;

public class DeleteUser {
    public void delUser(){
        JFrame jfrm;
        JLabel lblSearch = new JLabel();
        JLabel lblUid = new JLabel("Enter UID :");
        JTextField fieldUID = new JTextField();
        JButton btnSearch = new JButton("Search");
        JButton btnDelete = new JButton("Delete");

        // Setting up JFrame
        jfrm = new JFrame("Delete User");
        jfrm.setSize(400,400);
        jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfrm.setLayout(null);
        jfrm.setVisible(true);
        jfrm.setLocationRelativeTo(null);
        jfrm.setResizable(false);

        // Adding Components
        lblUid.setBounds(70,40,200,30);
        lblUid.setFont(new Font("Sans-Serif",Font.BOLD,25));
        jfrm.add(lblUid);

        fieldUID.setBounds(220,40,100,30);
        jfrm.add(fieldUID);

        btnSearch.setBounds(150,90,100,30);
        jfrm.add(btnSearch);
    }

    public static void main(String[] args) {
        DeleteUser deleteUser = new DeleteUser();
        deleteUser.delUser();
    }
}
