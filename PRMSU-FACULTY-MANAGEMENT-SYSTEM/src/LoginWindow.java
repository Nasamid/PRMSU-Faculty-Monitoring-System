import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class LoginWindow {

    //NEED SOMEONE NA PAGANDAHIN PA
    //Add Icons, images, basta estetik

    //Instantiation of GUI components
    JFrame LoginWindow;
    JPanel PTextHolder, PButtonHolder;

    JTextField StaffID ;
    JPasswordField StaffPass;

    JLabel COEText, ProgNameText;

    JButton LoginButton;

    LoginWindow(){

        LoginWindow = new JFrame("Faculty Monitoring System");
        LoginWindow.setSize(1380, 800);
        LoginWindow.setLayout(new BorderLayout());

        PTextHolder = new JPanel();
        PButtonHolder = new JPanel();
        //PTextHolder.setBackground(Color.red);
        //PButtonHolder.setBackground(Color.blue);
        PButtonHolder.setLayout(new GridBagLayout()); // Use GridBagLayout
        PTextHolder.setLayout(new BorderLayout());

        //PTextHolder.setPreferredSize(new Dimension(1360, 400));
       // PButtonHolder.setPreferredSize(new Dimension(1360, 400));

        StaffID = new JTextField();
        StaffPass = new JPasswordField();
        LoginButton = new JButton();

        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);

        COEText = new JLabel("College of Engineering", null, SwingConstants.CENTER);
        ProgNameText = new JLabel("Faculty Monitoring System", null, SwingConstants.CENTER);

        COEText.setPreferredSize(new Dimension(1360, 40));
        COEText.setFont(new Font("Lucida Bright", Font.BOLD, 35));

        ProgNameText.setPreferredSize(new Dimension(1360, 40));
        ProgNameText.setFont(new Font("Lucida Bright", Font.BOLD, 35));

        StaffID.setPreferredSize(new Dimension(300, 40));
        StaffID.setFont(new Font("Lucida Bright", Font.BOLD, 28));

        StaffPass.setPreferredSize(new Dimension(300, 40));
        StaffPass.setFont(new Font("Lucida Bright", Font.BOLD, 28));

        LoginButton.setPreferredSize(new Dimension(200, 40));
        LoginButton.setText("Login");
        LoginButton.setFont(new Font("Lucida Bright", Font.BOLD, 12));
        LoginButton.setFocusable(false);
        LoginButton.setForeground(Color.BLACK);
        LoginButton.setBackground(Color.decode("#00b4d8"));
        LoginButton.setBorder(compound);

        PTextHolder.add(COEText, BorderLayout.CENTER);
        PTextHolder.add(ProgNameText, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        PButtonHolder.add(StaffID, gbc);

        gbc.gridy = 1;
        PButtonHolder.add(StaffPass, gbc);

        gbc.gridy = 2;
        PButtonHolder.add(LoginButton, gbc);

        LoginWindow.add(PTextHolder, BorderLayout.NORTH);
        LoginWindow.add(PButtonHolder, BorderLayout.CENTER);


        //Action Listener lang para sa button. Pag tama credentials magcloclose tong
        //Login Page tas bubukas Homepage
        //Need someone magimplement ng JLabel na nagsasabi pag wrong password o wrong ID
        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = StaffID.getText().toString();
                String Pass = StaffPass.getText().toString(); //Someone please implement .getpassword instead
                
                if (ID.equals("admin") && Pass.equals("password")) {
                    System.out.println("Welcome!");
                    HomeWindow homepage = new HomeWindow();
                    LoginWindow.dispose();
                    //page.setIconImage(ProgramIcon.getImage());
                }
                else {
                    //FailedLogin.setVisible(true);
                    System.out.println(ID);
                    System.out.println(Pass);
                    System.out.println("Invalid Credentials");
                }
            }
        });


        //Essentials lang ng JFrame
        LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginWindow.setResizable(true);
        //LoginWindow.setIconImage(ProgramIcon.getImage());
        LoginWindow.setVisible(true);
    }
}
