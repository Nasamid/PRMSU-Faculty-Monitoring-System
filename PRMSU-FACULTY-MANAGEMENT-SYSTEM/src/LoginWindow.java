import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class LoginWindow extends javax.swing.JFrame{

    JFrame LoginWindow;
    JPanel PTextHolder, PButtonHolder,BgPanel;
    JPanel PTextM,PTextV,PTextQP;

    JLabel LBGimage;
    JLabel UnivText, CollegeText, UnivLogo, TitleMission, TitleVision, TitleQP, TitleQP2;
    JLabel mtext1, mtext2, mtext3, mtext4, mtext5, mtext6, mtext7, mtext8, mtext9;
    JLabel vtext1, vtext2, vtext3, vtext4;
    JLabel qptext1, qptext2, qptext3, qptext4, qptext5, qptext6, qptext7, qptext8, qptext9;
    
    JButton LoginButton, OffButton;
    JTextField Login ;
    
   LoginWindow(){  
    LoginWindow = new JFrame("Faculty Monitoring System");

    //Back panel and Background design
    BgPanel = new JPanel();
    
    LBGimage = new JLabel();
            Image bg;
            try {
                bg = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/loginimage1.png"));
                 ImageIcon imageIcon = new ImageIcon(bg);
                LBGimage.setIcon(imageIcon);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    LBGimage.setBounds(0, 0, 1080, 645);
    BgPanel.setBounds(0,75,1080, 645);

    BgPanel.setLayout(null);
    
    
    //Variable Constants
    int textboxheight = 20;
    Color backgroundColor = new Color(0,0,122);
    Color TextHighlightColor = new Color(236,189,68);
    Color textpanel = new Color(0,0,0);
    Color BGpanel = new Color(255,255,255,150);
    Color HeadText = new Color(251,201,1);
    Color HeadText2 = new Color(255,255,255);
    Color complimentColor = new Color(255, 96, 28);
    
    //Instantiaions of Panels
    PTextHolder = new JPanelGradient();
    PButtonHolder = new JPanel();
    PTextM = new JPanel();
    PTextV = new JPanel();
    PTextQP = new JPanel();
    
    //Top Panel Graphics
    UnivText = new JLabel("President Ramon Magsaysay State University",null,SwingConstants.LEADING);
    UnivText.setBounds(100, 10, 720,30);
    UnivText.setForeground(HeadText2);
    UnivText.setFont(new Font("Arial", Font.BOLD, 25));
    
    CollegeText = new JLabel("College of Engineering",null,SwingConstants.LEADING);
    CollegeText.setBounds(100, 35, 720,30);
    CollegeText.setForeground(HeadText2);
    CollegeText.setFont(new Font("Arial", Font.BOLD, 25));

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
    Login.addKeyListener(new KeyListener() {
        public void keyPressed(java.awt.event.KeyEvent e) {
            if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
                String ID = Login.getText();
                if(ID.equals("admin")){
                    new HomeWindow();
                    LoginWindow.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Username.");
                    System.out.println(ID);
                    System.out.println("Invalid Credentials");
                }
            }
        }
        public void keyReleased(java.awt.event.KeyEvent e) {
        }
        public void keyTyped(java.awt.event.KeyEvent e) {
        }
    });
    

    LoginButton = new JButton("Login");
    //LoginButton.setBackground(complimentColor);
    //LoginButton.setForeground(Color.WHITE);
    LoginButton.setBounds(935,24,100,30);
    LoginButton.setFocusable(false);

    OffButton = new JButton();
    Image image2;
            try {
                image2 = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/offbuttonblue1.png"));
                 ImageIcon imageIcon = new ImageIcon(image2);
                OffButton.setIcon(imageIcon);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    OffButton.setBounds(980,640,100,50);
    OffButton.setFocusable(false);
    OffButton.setContentAreaFilled(false);
    OffButton.setBorderPainted(false);
    OffButton.setBorder(null);
    

    //This is the section for the Mission, Vision, and Quality Policy
    TitleMission = new JLabel("MISSION",null,SwingConstants.CENTER);
    TitleMission.setBounds(25, 25, 250,textboxheight);
    TitleMission.setForeground(textpanel);
    TitleMission.setFont(new Font("Arial", Font.BOLD, 28));
    
    mtext1 = new JLabel("The PRMSU shall primarily",null,SwingConstants.CENTER);
    mtext1.setBounds(25, 100, 250,textboxheight);
    mtext1.setForeground(textpanel);
    mtext1.setFont(new Font("Arial", Font.BOLD, 13));
    
    mtext2 = new JLabel("provide advance and higher",null,SwingConstants.CENTER);
    mtext2.setBounds(25, 120, 250,textboxheight);
    mtext2.setForeground(textpanel);
    mtext2.setFont(new Font("Arial", Font.BOLD, 13));
    
    mtext3 = new JLabel("professional, technical, and",null,SwingConstants.CENTER);
    mtext3.setBounds(25, 140, 250,textboxheight);
    mtext3.setForeground(textpanel);
    mtext3.setFont(new Font("Arial", Font.BOLD, 13));
    
    mtext4 = new JLabel("special instructions in various",null,SwingConstants.CENTER);
    mtext4.setBounds(25, 160, 250,textboxheight);
    mtext4.setForeground(textpanel);
    mtext4.setFont(new Font("Arial", Font.BOLD, 13));
    
    mtext5 = new JLabel("disciplines; undertake research,",null,SwingConstants.CENTER);
    mtext5.setBounds(25, 180, 250,textboxheight);
    mtext5.setForeground(textpanel);
    mtext5.setFont(new Font("Arial", Font.BOLD, 13));
    
    mtext6 = new JLabel("extension and income generation",null,SwingConstants.CENTER);
    mtext6.setBounds(25, 200, 250,textboxheight);
    mtext6.setForeground(textpanel);
    mtext6.setFont(new Font("Arial", Font.BOLD, 13));
    
    mtext7 = new JLabel("programs for the sustainable",null,SwingConstants.CENTER);
    mtext7.setBounds(25, 220, 250,textboxheight);
    mtext7.setForeground(textpanel);
    mtext7.setFont(new Font("Arial", Font.BOLD, 13));

    mtext8 = new JLabel("development of Zambales,",null,SwingConstants.CENTER);
    mtext8.setBounds(25, 240, 250,textboxheight);
    mtext8.setForeground(textpanel);
    mtext8.setFont(new Font("Arial", Font.BOLD, 13));

    mtext9 = new JLabel(" the region and the country. ",null,SwingConstants.CENTER);
    mtext9.setBounds(25, 260, 250,textboxheight);
    mtext9.setForeground(textpanel);
    mtext9.setFont(new Font("Arial", Font.BOLD, 13));    
    
    TitleVision = new JLabel("VISION",null,SwingConstants.CENTER);
    TitleVision.setBounds(25, 25, 250,textboxheight);
    TitleVision.setForeground(textpanel);
    TitleVision.setFont(new Font("Arial", Font.BOLD, 28));
    
    
    vtext1 = new JLabel("PRMSU shall be a premier",null,SwingConstants.CENTER);
    vtext1.setBounds(25, 100, 250,textboxheight);
    vtext1.setForeground(textpanel);
    vtext1.setFont(new Font("Arial", Font.BOLD, 13));
    
    vtext2 = new JLabel("learner-centered and proactive",null,SwingConstants.CENTER);
    vtext2.setBounds(25, 120, 250,textboxheight);
    vtext2.setForeground(textpanel);
    vtext2.setFont(new Font("Arial", Font.BOLD, 13));
    
    vtext3 = new JLabel("university in a digital",null,SwingConstants.CENTER);
    vtext3.setBounds(25, 140, 250,textboxheight);
    vtext3.setForeground(textpanel);
    vtext3.setFont(new Font("Arial", Font.BOLD, 13));
    
    vtext4 = new JLabel(" and global society. ",null,SwingConstants.CENTER);
    vtext4.setBounds(25, 160, 250,textboxheight);
    vtext4.setForeground(textpanel);
    vtext4.setFont(new Font("Arial", Font.BOLD, 13));
    
    
    TitleQP = new JLabel("QUALITY",null,SwingConstants.CENTER);
    TitleQP.setBounds(25, 25, 250,textboxheight);
    TitleQP.setForeground(textpanel);
    TitleQP.setFont(new Font("Arial", Font.BOLD, 28));
    
    TitleQP2 = new JLabel("POLICY",null,SwingConstants.CENTER);
    TitleQP2.setBounds(25, 60, 250,textboxheight);
    TitleQP2.setForeground(textpanel);
    TitleQP2.setFont(new Font("Arial", Font.BOLD, 28));

    qptext1 = new JLabel("The President Ramon Magsaysay",null,SwingConstants.CENTER);
    qptext1.setBounds(25, 100, 250,textboxheight);
    qptext1.setForeground(textpanel);
    qptext1.setFont(new Font("Arial", Font.BOLD, 13));
    
    qptext2 = new JLabel("State University is committed to ",null,SwingConstants.CENTER);
    qptext2.setBounds(25, 120, 250,textboxheight);
    qptext2.setForeground(textpanel);
    qptext2.setFont(new Font("Arial", Font.BOLD, 13));
    
    qptext3 = new JLabel("continually strive for excellence",null,SwingConstants.CENTER);
    qptext3.setBounds(25, 140, 250,textboxheight);
    qptext3.setForeground(textpanel);
    qptext3.setFont(new Font("Arial", Font.BOLD, 13));
    
    qptext4 = new JLabel("in instruction, research,",null,SwingConstants.CENTER);
    qptext4.setBounds(25, 160, 250,textboxheight);
    qptext4.setForeground(textpanel);
    qptext4.setFont(new Font("Arial", Font.BOLD, 13));
    
    qptext5 = new JLabel("extension and production to",null,SwingConstants.CENTER);
    qptext5.setBounds(25, 180, 250,textboxheight);
    qptext5.setForeground(textpanel);
    qptext5.setFont(new Font("Arial", Font.BOLD, 13));
    
    qptext6 = new JLabel("strengthen global competitiveness",null,SwingConstants.CENTER);
    qptext6.setBounds(25, 200, 250,textboxheight);
    qptext6.setForeground(textpanel);
    qptext6.setFont(new Font("Arial", Font.BOLD, 13));
    
    qptext7 = new JLabel("adhering to quality standards",null,SwingConstants.CENTER);
    qptext7.setBounds(25, 220, 250,textboxheight);
    qptext7.setForeground(textpanel);
    qptext7.setFont(new Font("Arial", Font.BOLD, 13));
    
    qptext8 = new JLabel("for the utmost satisfaction",null,SwingConstants.CENTER);
    qptext8.setBounds(25, 240, 250,textboxheight);
    qptext8.setForeground(textpanel);
    qptext8.setFont(new Font("Arial", Font.BOLD, 13));
    
    qptext9 = new JLabel("of its valued customers.",null,SwingConstants.CENTER);
    qptext9.setBounds(25, 260, 250,textboxheight);
    qptext9.setForeground(textpanel);
    qptext9.setFont(new Font("Arial", Font.BOLD, 13));
   

    //Graphics for the Panels
    PTextHolder.setBorder(BorderFactory.createLineBorder(new Color(172, 29, 0)));
    PTextHolder.setBackground(SystemColor.textHighlight);
    PTextHolder.setBounds(0,0,1365, 75);
    PTextHolder.setLayout(null);
    
    PTextM.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextM.setBackground(BGpanel);
    PTextM.setOpaque(true);
    PTextM.setBounds(45,200,300, 370);
    PTextM.setLayout(null);

    PTextV.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextV.setBackground(BGpanel);
    PTextV.setOpaque(true);
    PTextV.setBounds(395,200,300, 370);
    PTextV.setLayout(null);

    PTextQP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    PTextQP.setBackground(BGpanel);
    PTextQP.setOpaque(true);
    PTextQP.setBounds(740,200,300, 370);
    PTextQP.setLayout(null);
    

    //Action Listener for the Buttons
    LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                String ID = Login.getText();
                if(ID.equals("admin")){
                    new HomeWindow();
                    LoginWindow.dispose();
                }
                else {
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
        
    //This section is for adding of components to the Panels
    PTextHolder.add(UnivText);
    PTextHolder.add(CollegeText);
    PTextHolder.add(UnivLogo);
    PTextHolder.add(LoginButton);
    PTextHolder.add(Login);

    BgPanel.add(LBGimage);
    
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

    //This section is for adding of Panels etc. to the actual window
    LoginWindow.getContentPane().add(PTextHolder);
    LoginWindow.getContentPane().add(PTextM);
    LoginWindow.getContentPane().add(PTextV);
    LoginWindow.getContentPane().add(PTextQP);
    LoginWindow.getContentPane().add(PButtonHolder);
    LoginWindow.getContentPane().add(OffButton);
    LoginWindow.getContentPane().add(BgPanel);

    //Window Essential
    LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    LoginWindow.setUndecorated(true);
    LoginWindow.setSize(1080, 720);
    LoginWindow.setResizable(false);
    LoginWindow.getContentPane().setLayout(null);    

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
        FlatMacLightLaf.registerCustomDefaultsSource("Properties");
        FlatMacLightLaf.setup();
        new LoginWindow();
    }
    
    //This class is to add gradient to the JPanels
    class JPanelGradient extends JPanel{
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();


            Color C1 = new Color(255, 198, 43);
            Color C2 = new Color(255, 77, 41);
            GradientPaint gp = new GradientPaint(0,0,C1,180,height,C2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
            
        }
    }
}