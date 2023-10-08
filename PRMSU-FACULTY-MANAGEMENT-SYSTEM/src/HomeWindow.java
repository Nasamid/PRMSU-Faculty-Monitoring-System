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
import javax.print.attribute.standard.MediaSize.NA;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class HomeWindow {
        JFrame HomeWindow;
        JPanel NaviPanel, HomePanel, DashPanel, ReportPanel;
        JButton HomeButton, ListFacButton, ReportButton, HelpButton, LogoutButton;
        JLabel UnivLogo;
    
        HomeWindow(){

            //Constant Variables
            int textboxheight = 20;
            Color backgroundColor = new Color(0,0,122);
            Color complimentColor = new Color(0, 122, 122);
            Color TextHighlightColor = new Color(236,189,68);

            //Instantiations
            HomeWindow = new JFrame();

            NaviPanel = new JPanel();
            HomePanel = new JPanel();
            DashPanel = new JPanel();
            ReportPanel = new JPanel();

            HomeButton = new JButton();
            ListFacButton = new JButton();
            ReportButton = new JButton();
            HelpButton = new JButton();
            LogoutButton = new JButton();

            UnivLogo = new JLabel();
            
            //The Navigation Panel is the Panel for the buttons such as "Home", "List", "Report", etc.
            NaviPanel.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            NaviPanel.setBounds(0,0,180, 720);
            NaviPanel.setBackground(backgroundColor);
            NaviPanel.setLayout(null);

            //The Home Panel will host the video slideshow Marlou and Ralph proposed
            //The Color BG is a placeholder
            HomePanel.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            HomePanel.setBounds(180,0,1000, 720);
            HomePanel.setBackground(Color.red);  //Remove this pag gagawin nyo na code nyo
            HomePanel.setLayout(null);

            //The Dash Panel will host the List of Faculty and all of its components, tables, etc.
            //The Color BG is a placeholder
            DashPanel.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            DashPanel.setBounds(180,0,1000, 720);
            DashPanel.setBackground(Color.green);   //Remove this pag gagawin nyo na code nyo
            DashPanel.setLayout(null);
            DashPanel.setVisible(false);

            //The Report Panel will host the Report page and all of its component, tables, etc.
            //The Color BG is a placeholder
            ReportPanel.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            ReportPanel.setBounds(180,0,1000, 720);
            ReportPanel.setBackground(Color.yellow);    //Remove this pag gagawin nyo na code nyo
            ReportPanel.setLayout(null);
            ReportPanel.setVisible(false);


                                        //GUIs of NaviPanel//
            //Code for Logo
            UnivLogo = new JLabel();
            Image image;
            try {
                image = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/UniLogox100.png"));
                 ImageIcon imageIcon = new ImageIcon(image);
                UnivLogo.setIcon(imageIcon);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            UnivLogo.setBounds(40, 20, 100, 100);    

            HomeButton.setText("Home");
            HomeButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            HomeButton.setBackground(complimentColor);
            HomeButton.setForeground(Color.WHITE);
            HomeButton.setBounds(40,150,100,30);
            HomeButton.setFocusable(false);

            ListFacButton.setText("List of Faculty");
            ListFacButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            ListFacButton.setBackground(complimentColor);
            ListFacButton.setForeground(Color.WHITE);
            ListFacButton.setBounds(40,200,100,30);
            ListFacButton.setFocusable(false);

            ReportButton.setText("Report");
            ReportButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            ReportButton.setBackground(complimentColor);
            ReportButton.setForeground(Color.WHITE);
            ReportButton.setBounds(40,250,100,30);
            ReportButton.setFocusable(false);

            HelpButton.setText("Help");
            HelpButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            HelpButton.setBackground(complimentColor);
            HelpButton.setForeground(Color.WHITE);
            HelpButton.setBounds(40,300,100,30);
            HelpButton.setFocusable(false);

            LogoutButton.setText("Logout");
            LogoutButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            LogoutButton.setBackground(complimentColor);
            LogoutButton.setForeground(Color.WHITE);
            LogoutButton.setBounds(40,350,100,30);
            LogoutButton.setFocusable(false);

            NaviPanel.add(UnivLogo);
            NaviPanel.add(HomeButton);
            NaviPanel.add(ListFacButton);
            NaviPanel.add(ReportButton);
            NaviPanel.add(HelpButton);
            NaviPanel.add(LogoutButton);


            //Action listeners of buttons

            HomeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                        HomePanel.setVisible(true);
                        HomePanel.setEnabled(true);

                        DashPanel.setVisible(false);
                        DashPanel.setEnabled(false);

                        ReportPanel.setVisible(false);
                        ReportPanel.setEnabled(false);
                    }
            });

            ListFacButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                        HomePanel.setVisible(false);
                        HomePanel.setEnabled(false);

                        DashPanel.setVisible(true);
                        DashPanel.setEnabled(true);

                        ReportPanel.setVisible(false);
                        ReportPanel.setEnabled(false);
                    }
            });

            ReportButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                        HomePanel.setVisible(false);
                        HomePanel.setEnabled(false);

                        DashPanel.setVisible(false);
                        DashPanel.setEnabled(false);
                        
                        ReportPanel.setVisible(true);
                        ReportPanel.setEnabled(true);
                    }
            });

            HelpButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                        //Maybe have this button open up the user manual
                        //once we've ever get to making it
                    }
            });

            LogoutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                        System.out.println("Welcome!");
                        LoginWindow loginWin = new LoginWindow();
                        HomeWindow.dispose();
                        
                    }
            });


            //Adding of Components to the window
            HomeWindow.getContentPane().add(NaviPanel);
            HomeWindow.getContentPane().add(HomePanel);
            HomeWindow.getContentPane().add(DashPanel);
            HomeWindow.getContentPane().add(ReportPanel);

            //Window Essentials
            HomeWindow.setTitle("COE Faculty Monitoring System");
            HomeWindow.setSize(1080, 720);
            HomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            HomeWindow.setLayout(null);
            HomeWindow.setUndecorated(true);
            HomeWindow.setResizable(false);

            //Responsible for making the window open on the center of the screen on start up
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int w = HomeWindow.getSize().width;
            int h = HomeWindow.getSize().height;
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;
            HomeWindow.setLocation(x,y);
            HomeWindow.setVisible(true);
        
        }

        public static void main(String[] args) {
            new HomeWindow();
        }
}
