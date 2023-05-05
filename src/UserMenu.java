import javax.swing.*;

public class UserMenu {
    public void user_menu(){
        JFrame jfrm = new JFrame("User Menu");
        jfrm.setSize(1280,720);
        jfrm.setVisible(true);
        jfrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jfrm.setUndecorated(true);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
