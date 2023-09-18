import java.awt.Color;
import java.awt.Font;
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

        PTextHolder = new JPanel();
        PButtonHolder = new JPanel();

        StaffID = new JTextField();
        StaffPass = new JPasswordField();

        LoginButton = new JButton();


        //Para estetik yung JButton, temporary lang, pakigandahan pa ng design
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
  

        //Need someone na magimplement ng system para palaging CENTERED yung texts, Tfields, buttons, etc    
        COEText = new JLabel("College of Engineering", null, SwingConstants.CENTER);    
        ProgNameText = new JLabel("Faculty Monitoring System", null, SwingConstants.CENTER);
        
        COEText.setBounds(0, 100, 1360, 40);
        COEText.setFont(new Font("Lucida Bright", Font.BOLD, 35));

        ProgNameText.setBounds(0, 60, 1360, 40);
        ProgNameText.setFont(new Font("Lucida Bright", Font.BOLD, 35));

        LoginButton.setBounds(600, 200, 150, 75);
	    LoginButton.setText("Login");
	    LoginButton.setFont(new Font("Lucida Bright", Font.BOLD, 12));
	    LoginButton.setFocusable(false);
        LoginButton.setForeground(Color.BLACK);
        LoginButton.setBackground(Color.decode("#00b4d8"));
        LoginButton.setBorder(compound);

        StaffID.setBounds(475, 50, 400, 50);
        StaffID.setFont(new Font("Lucida Bright", Font.BOLD, 28));

        StaffPass.setBounds(475, 125, 400, 50);
        StaffPass.setFont(new Font("Lucida Bright", Font.BOLD, 28));

        PTextHolder.setBounds(6,0,1360, 200);
        PTextHolder.setLayout(null);

        PButtonHolder.setBounds(6, 150, 1360, 500);
        PButtonHolder.setLayout(null);

        PTextHolder.add(COEText);
        PTextHolder.add(ProgNameText);

        PButtonHolder.add(StaffPass);
        PButtonHolder.add(StaffID);
        PButtonHolder.add(LoginButton);

        LoginWindow.add(PTextHolder);
        LoginWindow.add(PButtonHolder);


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
        LoginWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); //Ito yung line of code na nagfufullscreen ng frame
        LoginWindow.setResizable(false);
        LoginWindow.setLayout(null);
        //LoginWindow.setIconImage(ProgramIcon.getImage());
        LoginWindow.setVisible(true);
    }
}
