import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.imageio.ImageIO;
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
    JLabel UnivText, UnivLogo, TitleMission, TitleVision, TitleQP, TitleQP2;
    JLabel mtext1, mtext2, mtext3, mtext4, mtext5, mtext6, mtext7, mtext8, mtext9;
    JLabel vtext1, vtext2, vtext3, vtext4;
    JLabel qptext1, qptext2, qptext3, qptext4, qptext5, qptext6, qptext7, qptext8, qptext9;
    
    JButton LoginButton, OffButton;
    JTextField Login ;
    
   LoginWindow(){  
    LoginWindow = new JFrame("Faculty Monitoring System");
    
    int textboxheight = 20;
    Color backgroundColor = new Color(0,0,122);
    Color TextHighlightColor = new Color(236,189,68);
    
    PTextHolder = new JPanel();
    PButtonHolder = new JPanel();
    PTextM = new JPanel();
    PTextV = new JPanel();
    PTextQP = new JPanel();
    
            //Border line = new LineBorder(Color.BLACK);
            //Border margin = new EmptyBorder(5, 15, 5, 15);
            //Border compound = new CompoundBorder(line, margin);
    


    UnivText = new JLabel("President Ramon Magsaysay State University",null,SwingConstants.TRAILING);
    UnivText.setBounds(0, 17, 748,40);
    UnivText.setForeground(TextHighlightColor);
    UnivText.setFont(new Font("Arial", Font.BOLD, 30));

            //This is for adding the university logo
    UnivLogo = new JLabel();
            Image image;
            try {
                image = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/UniLogo.png"));
                 ImageIcon imageIcon = new ImageIcon(image);
                UnivLogo.setIcon(imageIcon);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    UnivLogo.setBounds(30, 7, 60, 60);    

    Login = new JTextField("");
    Login.setBounds(778,24,150,30);
    

    LoginButton = new JButton("Login");
    LoginButton.setBackground(Color.GRAY);
    LoginButton.setForeground(Color.WHITE);
    LoginButton.setBounds(938,24,100,30);
    LoginButton.setFocusable(false);

    OffButton = new JButton();
    Image image2;
            try {
                image2 = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/OffButton.png"));
                 ImageIcon imageIcon = new ImageIcon(image2);
                OffButton.setIcon(imageIcon);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    OffButton.setBounds(1000,640,100,50);
    OffButton.setFocusable(false);
    OffButton.setContentAreaFilled(false);
    OffButton.setBorderPainted(false);
    OffButton.setBorder(null);
    


    TitleMission = new JLabel("MISSION",null,SwingConstants.CENTER);
    TitleMission.setBounds(25, 25, 250,textboxheight);
    TitleMission.setFont(new Font("Arial", Font.BOLD, 25));
    
    mtext1 = new JLabel("The PRMSU shall primarily",null,SwingConstants.CENTER);
    mtext1.setBounds(25, 100, 250,textboxheight);
    mtext1.setFont(new Font("Arial", Font.BOLD, 11));
    
    mtext2 = new JLabel("provide advance and higher",null,SwingConstants.CENTER);
    mtext2.setBounds(25, 120, 250,textboxheight);
    mtext2.setFont(new Font("Arial", Font.BOLD, 11));
    
    mtext3 = new JLabel("professional, technical, and",null,SwingConstants.CENTER);
    mtext3.setBounds(25, 140, 250,textboxheight);
    mtext3.setFont(new Font("Arial", Font.BOLD, 11));
    
    mtext4 = new JLabel("special instructions in various",null,SwingConstants.CENTER);
    mtext4.setBounds(25, 160, 250,textboxheight);
    mtext4.setFont(new Font("Arial", Font.BOLD, 11));
    
    mtext5 = new JLabel("disciplines; undertake research,",null,SwingConstants.CENTER);
    mtext5.setBounds(25, 180, 250,textboxheight);
    mtext5.setFont(new Font("Arial", Font.BOLD, 11));
    
    mtext6 = new JLabel("extension and income generation",null,SwingConstants.CENTER);
    mtext6.setBounds(25, 200, 250,textboxheight);
    mtext6.setFont(new Font("Arial", Font.BOLD, 11));
    
    mtext7 = new JLabel("programs for the sustainable",null,SwingConstants.CENTER);
    mtext7.setBounds(25, 220, 250,textboxheight);
    mtext7.setFont(new Font("Arial", Font.BOLD, 11));

    mtext8 = new JLabel("development of Zambales,",null,SwingConstants.CENTER);
    mtext8.setBounds(25, 240, 250,textboxheight);
    mtext8.setFont(new Font("Arial", Font.BOLD, 11));

    mtext9 = new JLabel(" the region and the country. ",null,SwingConstants.CENTER);
    mtext9.setBounds(25, 260, 250,textboxheight);
    mtext9.setFont(new Font("Arial", Font.BOLD, 11));    
    
    TitleVision = new JLabel("VISION",null,SwingConstants.CENTER);
    TitleVision.setBounds(25, 25, 250,textboxheight);
    TitleVision.setFont(new Font("Arial", Font.BOLD, 25));
    
    
    vtext1 = new JLabel("PRMSU shall be a premier",null,SwingConstants.CENTER);
    vtext1.setBounds(25, 100, 250,textboxheight);
    vtext1.setFont(new Font("Arial", Font.BOLD, 11));
    
    vtext2 = new JLabel("learner-centered and proactive",null,SwingConstants.CENTER);
    vtext2.setBounds(25, 120, 250,textboxheight);
    vtext2.setFont(new Font("Arial", Font.BOLD, 11));
    
    vtext3 = new JLabel("university in a digital",null,SwingConstants.CENTER);
    vtext3.setBounds(25, 140, 250,textboxheight);
    vtext3.setFont(new Font("Arial", Font.BOLD, 11));
    
    vtext4 = new JLabel(" and global society. ",null,SwingConstants.CENTER);
    vtext4.setBounds(25, 160, 250,textboxheight);
    vtext4.setFont(new Font("Arial", Font.BOLD, 11));
    
    
    TitleQP = new JLabel("QUALITY",null,SwingConstants.CENTER);
    TitleQP.setBounds(25, 25, 250,textboxheight);
    TitleQP.setFont(new Font("Arial", Font.BOLD, 25));
    
    TitleQP2 = new JLabel("POLICY",null,SwingConstants.CENTER);
    TitleQP2.setBounds(25, 60, 250,textboxheight);
    TitleQP2.setFont(new Font("Arial", Font.BOLD, 25));

    qptext1 = new JLabel("The President Ramon Magsaysay",null,SwingConstants.CENTER);
    qptext1.setBounds(25, 100, 250,textboxheight);
    qptext1.setFont(new Font("Arial", Font.BOLD, 11));
    
    qptext2 = new JLabel("State University is committed to ",null,SwingConstants.CENTER);
    qptext2.setBounds(25, 120, 250,textboxheight);
    qptext2.setFont(new Font("Arial", Font.BOLD, 11));
    
    qptext3 = new JLabel("continually strive for excellence",null,SwingConstants.CENTER);
    qptext3.setBounds(25, 140, 250,textboxheight);
    qptext3.setFont(new Font("Arial", Font.BOLD, 11));
    
    qptext4 = new JLabel("in instruction, research,",null,SwingConstants.CENTER);
    qptext4.setBounds(25, 160, 250,textboxheight);
    qptext4.setFont(new Font("Arial", Font.BOLD, 11));
    
    qptext5 = new JLabel("extension and production to",null,SwingConstants.CENTER);
    qptext5.setBounds(25, 180, 250,textboxheight);
    qptext5.setFont(new Font("Arial", Font.BOLD, 11));
    
    qptext6 = new JLabel("strengthen global competitiveness",null,SwingConstants.CENTER);
    qptext6.setBounds(25, 200, 250,textboxheight);
    qptext6.setFont(new Font("Arial", Font.BOLD, 11));
    
    qptext7 = new JLabel("adhering to quality standards",null,SwingConstants.CENTER);
    qptext7.setBounds(25, 220, 250,textboxheight);
    qptext7.setFont(new Font("Arial", Font.BOLD, 11));
    
    qptext8 = new JLabel("for the utmost satisfaction",null,SwingConstants.CENTER);
    qptext8.setBounds(25, 240, 250,textboxheight);
    qptext8.setFont(new Font("Arial", Font.BOLD, 11));
    
    qptext9 = new JLabel("of its valued customers.",null,SwingConstants.CENTER);
    qptext9.setBounds(25, 260, 250,textboxheight);
    qptext9.setFont(new Font("Arial", Font.BOLD, 11));
   
    

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
    PTextHolder.setBackground(backgroundColor);
    PTextHolder.setBounds(0,0,1365, 75);
    PTextHolder.setLayout(null);
    
    PTextM.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextM.setBounds(45,200,300, 370);
    PTextM.setLayout(null);

    PTextV.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextV.setBounds(395,200,300, 370);
    PTextV.setLayout(null);

    PTextQP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextQP.setBounds(740,200,300, 370);
    PTextQP.setLayout(null);
    


    LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                String ID = Login.getText();
                if(ID.equals("admin")){

                    //Pag okay na yung Home page, tanggalin na natin tong message dialog
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



    OffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
                }
            }
        );
    //PButtonHolder.setBounds(6, 150, 1360, 500);
    //PButtonHolder.setLayout(null);
        


    PTextHolder.add(UnivText);
    PTextHolder.add(UnivLogo);
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

    LoginWindow.getContentPane().add(PTextHolder);
    LoginWindow.getContentPane().add(PTextM);
    LoginWindow.getContentPane().add(PTextV);
    LoginWindow.getContentPane().add(PTextQP);
    LoginWindow.getContentPane().add(PButtonHolder);
    LoginWindow.getContentPane().add(OffButton);

    //essential
    LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    LoginWindow.setUndecorated(true);
    
    LoginWindow.setSize(1080, 720);
    LoginWindow.setResizable(false);
    LoginWindow.getContentPane().setLayout(null);
    //LoginWindow.setIconImage(ProgramIcon.getImage());
    

    //This is the function that is responsible for making the window appear centered on startup
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    int w = LoginWindow.getSize().width;
    int h = LoginWindow.getSize().height;
    int x = (dim.width-w)/2;
    int y = (dim.height-h)/2;
    LoginWindow.setLocation(x,y);
    LoginWindow.setVisible(true);
}

    public static void main(String[] args) {
        new LoginWindow();
    }
    
}