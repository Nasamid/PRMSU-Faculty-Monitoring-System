
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
public class LoginWindow {
    
    JFrame LoginWindow;
    JPanel PTextHolder, PButtonHolder,ImgHolder;
    JPanel PTextM,PTextV,PTextQP;


    //JPasswordField StaffPass;

    JLabel UnivText,TitleMission,TitleVision,TitleQP,TitleQP2;
    JLabel mtext1,mtext2,mtext3,mtext4,mtext5,mtext6,mtext7,mtext8,mtext9;
    JLabel vtext1, vtext2,vtext3,vtext4;
    JLabel qptext1,qptext2,qptext3,qptext4,qptext5,qptext6,qptext7,qptext8,qptext9;
    
   JButton LoginButton;
   JTextField Login ;
    
   LoginWindow(){  
    LoginWindow = new JFrame("Faculty Monitoring System");
    
    PTextHolder = new JPanel();
    PButtonHolder = new JPanel();
    PTextM = new JPanel();
    PTextV = new JPanel();
    PTextQP = new JPanel();
    
    
    
    
    
    
            
    //Border line = new LineBorder(Color.BLACK);
    //Border margin = new EmptyBorder(5, 15, 5, 15);
    //Border compound = new CompoundBorder(line, margin);
    
    UnivText = new JLabel("President Ramon Magsaysay State University",null,SwingConstants.LEADING);
    UnivText.setBounds(10, 15, 1000,40);
    UnivText.setFont(new Font("Arial", Font.BOLD, 35));
    
    Login = new JTextField("");
    Login.setBounds(1010,25,150,30);
    
    LoginButton = new JButton("Login");
    LoginButton.setBackground(Color.GRAY);
    LoginButton.setForeground(Color.WHITE);
    LoginButton.setBounds(1160,25,100,30);
    
    TitleMission = new JLabel("MISSION",null,SwingConstants.CENTER);
    TitleMission.setBounds(25, 25, 250,40);
    TitleMission.setFont(new Font("Arial", Font.BOLD, 35));
    
    mtext1 = new JLabel("The PRMSU shall primarily",null,SwingConstants.CENTER);
    mtext1.setBounds(25, 100, 250,40);
    mtext1.setFont(new Font("Arial", Font.BOLD, 15));
    
    mtext2 = new JLabel("provide advance and higher",null,SwingConstants.CENTER);
    mtext2.setBounds(25, 140, 250,40);
    mtext2.setFont(new Font("Arial", Font.BOLD, 15));
    
    mtext3 = new JLabel("professional, technical, and",null,SwingConstants.CENTER);
    mtext3.setBounds(25, 180, 250,40);
    mtext3.setFont(new Font("Arial", Font.BOLD, 15));
    
    mtext4 = new JLabel("special instructions in various",null,SwingConstants.CENTER);
    mtext4.setBounds(25, 220, 250,40);
    mtext4.setFont(new Font("Arial", Font.BOLD, 15));
    
    mtext5 = new JLabel("disciplines; undertake research,",null,SwingConstants.CENTER);
    mtext5.setBounds(25, 260, 250,40);
    mtext5.setFont(new Font("Arial", Font.BOLD, 15));
    
    mtext6 = new JLabel("extension and income generation",null,SwingConstants.CENTER);
    mtext6.setBounds(25, 300, 250,40);
    mtext6.setFont(new Font("Arial", Font.BOLD, 15));
    
    mtext7 = new JLabel("programs for the sustainable",null,SwingConstants.CENTER);
    mtext7.setBounds(25, 340, 250,40);
    mtext7.setFont(new Font("Arial", Font.BOLD, 15));

    mtext8 = new JLabel("development of Zambales,",null,SwingConstants.CENTER);
    mtext8.setBounds(25, 380, 250,40);
    mtext8.setFont(new Font("Arial", Font.BOLD, 15));

    mtext9 = new JLabel(" the region and the country. ",null,SwingConstants.CENTER);
    mtext9.setBounds(25, 420, 250,40);
    mtext9.setFont(new Font("Arial", Font.BOLD, 15));    
    
    TitleVision = new JLabel("VISION",null,SwingConstants.CENTER);
    TitleVision.setBounds(25, 25, 250,40);
    TitleVision.setFont(new Font("Arial", Font.BOLD, 35));
    
    
    vtext1 = new JLabel("PRMSU shall be a premier",null,SwingConstants.CENTER);
    vtext1.setBounds(25, 100, 250,40);
    vtext1.setFont(new Font("Arial", Font.BOLD, 15));
    
    vtext2 = new JLabel("learner-centered and proactive",null,SwingConstants.CENTER);
    vtext2.setBounds(25, 140, 250,40);
    vtext2.setFont(new Font("Arial", Font.BOLD, 15));
    
    vtext3 = new JLabel("university in a digital",null,SwingConstants.CENTER);
    vtext3.setBounds(25, 180, 250,40);
    vtext3.setFont(new Font("Arial", Font.BOLD, 15));
    
    vtext4 = new JLabel(" and global society. ",null,SwingConstants.CENTER);
    vtext4.setBounds(25, 220, 250,40);
    vtext4.setFont(new Font("Arial", Font.BOLD, 15));
    
    
    TitleQP = new JLabel("QUALITY",null,SwingConstants.CENTER);
    TitleQP.setBounds(25, 25, 250,40);
    TitleQP.setFont(new Font("Arial", Font.BOLD, 35));
    
    TitleQP2 = new JLabel("POLICY",null,SwingConstants.CENTER);
    TitleQP2.setBounds(25, 60, 250,40);
    TitleQP2.setFont(new Font("Arial", Font.BOLD, 35));

    qptext1 = new JLabel("The President Ramon Magsaysay",null,SwingConstants.CENTER);
    qptext1.setBounds(25, 100, 250,40);
    qptext1.setFont(new Font("Arial", Font.BOLD, 15));
    
    qptext2 = new JLabel("State University is committed to ",null,SwingConstants.CENTER);
    qptext2.setBounds(25, 140, 250,40);
    qptext2.setFont(new Font("Arial", Font.BOLD, 15));
    
    qptext3 = new JLabel("continually strive for excellence",null,SwingConstants.CENTER);
    qptext3.setBounds(25, 180, 250,40);
    qptext3.setFont(new Font("Arial", Font.BOLD, 15));
    
    qptext4 = new JLabel("in instruction, research,",null,SwingConstants.CENTER);
    qptext4.setBounds(25, 220, 250,40);
    qptext4.setFont(new Font("Arial", Font.BOLD, 15));
    
    qptext5 = new JLabel("extension and production to",null,SwingConstants.CENTER);
    qptext5.setBounds(25, 260, 250,40);
    qptext5.setFont(new Font("Arial", Font.BOLD, 15));
    
    qptext6 = new JLabel("strengthen global competitiveness",null,SwingConstants.CENTER);
    qptext6.setBounds(25, 300, 250,40);
    qptext6.setFont(new Font("Arial", Font.BOLD, 15));
    
    qptext7 = new JLabel("adhering to quality standards",null,SwingConstants.CENTER);
    qptext7.setBounds(25, 340, 250,40);
    qptext7.setFont(new Font("Arial", Font.BOLD, 15));
    
    qptext8 = new JLabel("for the utmost satisfaction",null,SwingConstants.CENTER);
    qptext8.setBounds(25, 380, 250,40);
    qptext8.setFont(new Font("Arial", Font.BOLD, 15));
    
    qptext9 = new JLabel("of its valued customers.",null,SwingConstants.CENTER);
    qptext9.setBounds(25, 420, 250,40);
    qptext9.setFont(new Font("Arial", Font.BOLD, 15));
   
    

   // LoginButton.setBounds(600, 200, 150, 75);
    //LoginButton.setText("Login");
   // LoginButton.setFont(new Font("Lucida Bright", Font.BOLD, 12));
   // LoginButton.setFocusable(false);
    //LoginButton.setForeground(Color.BLACK);
   // LoginButton.setBackground(Color.decode("#00b4d8"));
    //LoginButton.setBorder(compound);

    //StaffID.setBounds(475, 50, 400, 50);
    //StaffID.setFont(new Font("Lucida Bright", Font.BOLD, 28));

    //StaffPass.setBounds(475, 125, 400, 50);
    //StaffPass.setFont(new Font("Lucida Bright", Font.BOLD, 28));
    PTextHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextHolder.setBackground(Color.yellow);
    PTextHolder.setBounds(0,0,1365, 75);
    PTextHolder.setLayout(null);
    
    PTextM.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextM.setBounds(150,200,300, 500);
    PTextM.setLayout(null);

    PTextV.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextV.setBounds(525,200,300, 500);
    PTextV.setLayout(null);

    PTextQP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextQP.setBounds(900,200,300, 500);
    PTextQP.setLayout(null);
    
    LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                String ID = Login.getText();
                if(ID.equals("admin")){
                    JOptionPane.showMessageDialog(null, "WELCOME!");
                    System.out.println("Welcome!");
                    HomeWindow homepage = new HomeWindow();
                    LoginWindow.dispose();
                    //page.setIconImage(ProgramIcon.getImage());
                }
                else {
                    //FailedLogin.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Invalid Username.");
                    System.out.println(ID);
                    System.out.println("Invalid Credentials");
                }
            }
        });

    //PButtonHolder.setBounds(6, 150, 1360, 500);
    //PButtonHolder.setLayout(null);
        
    PTextHolder.add(UnivText);
    PTextHolder.add(LoginButton);
    PTextHolder.add(Login);
    
    PTextM.add(TitleMission);
    PTextM.add(mtext1);
    PTextM.add(mtext2);
    PTextM.add(mtext3);
    PTextM.add(mtext4);
    PTextM.add(mtext5);
    PTextM.add(mtext6);
    PTextM.add(mtext7);
    PTextM.add(mtext8);
    PTextM.add(mtext9);

    PTextV.add(TitleVision);
    PTextV.add(vtext1);
    PTextV.add(vtext2);
    PTextV.add(vtext3);
    PTextV.add(vtext4);
    
    
    
    PTextQP.add(TitleQP);
    PTextQP.add(TitleQP2);
    PTextQP.add(qptext1);
    PTextQP.add(qptext2);
    PTextQP.add(qptext3);
    PTextQP.add(qptext4);
    PTextQP.add(qptext5);
    PTextQP.add(qptext6);
    PTextQP.add(qptext7);
    PTextQP.add(qptext8);
    PTextQP.add(qptext9);


   // PButtonHolder.add(StaffPass);
   // PButtonHolder.add(StaffID);
   // PButtonHolder.add(LoginButton);

    LoginWindow.add(PTextHolder);
    LoginWindow.add(PTextM);
    LoginWindow.add(PTextV);
    LoginWindow.add(PTextQP);
    LoginWindow.add(PButtonHolder);
    
    //essential
    LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    LoginWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); //Ito yung line of code na nagfufullscreen ng frame
    LoginWindow.setResizable(false);
    LoginWindow.setLayout(null);
    //LoginWindow.setIconImage(ProgramIcon.getImage());
    LoginWindow.setVisible(true);
}
    public static void main(String[] args) {
        new LoginWindow();
    }
    
}