import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.border.Border;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.MediaSize.NA;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import UploadDocTreeNodes.*;
import java.awt.*;
import java.io.IOException;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class HomeWindow 
{
        JFrame HomeWindow;
        JPanel RootPanel, NaviPanel, HomePanel, ReportPanel;
        JButton HomeButton, ListFacButton, ReportButton, HelpButton, LogoutButton;
        JLabel UnivLogo, HPimage;
        JPanel VideoPanel;
        Canvas canvas;

        //For Reporting
        JPanel jt2;
        DefaultTableModel model2;
        JTable table2;
        JScrollPane scrollPane2;
        JLabel report;
        JButton button2;
        JTextField Search2;
        
        listFaculty faculty;    // List of Faculty Panel
        report reportpage; // Report class
    
        HomeWindow()
        {
            //Constant Variables
            int textboxheight = 20;
            Color backgroundColor = new Color(0,0,122);
            Color complimentColor = new Color(0, 122, 122);
            Color TextHighlightColor = new Color(236,189,68);

            //Instantiations
            HomeWindow = new JFrame();

            RootPanel = new JPanel();
            NaviPanel = new JPanel();
            HomePanel = new JPanel();
            ReportPanel = new JPanel();
            faculty = new listFaculty();
            reportpage = new report();

            HomeButton = new JButton();
            ListFacButton = new JButton();
            ReportButton = new JButton();
            HelpButton = new JButton();
            LogoutButton = new JButton();

            UnivLogo = new JLabel();
            
            faculty.setVisible(false);  // sets List of Faculty Panel not visible
            reportpage.setVisible(false); // sets Report page not visible

        //Root Panel is the master of all sub panels
        RootPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //RootPanel.setBackground(bgColor);
        RootPanel.setBounds(0,0,1080, 720);
        RootPanel.setLayout(null);

            
        //The Navigation Panel is the Panel for the buttons such as "Home", "List", "Report", etc.
        NaviPanel.setBorder(null);
        NaviPanel.setBounds(0,0,180, 720);
        NaviPanel.setBackground(SystemColor.textHighlight);
        NaviPanel.setLayout(null);

        VideoPanel = new JPanel(new BorderLayout());
        canvas = new Canvas();

        //The Home Panel will host the video slideshow Marlou and Ralph proposed
        //The Color BG is a placeholder
        HomePanel.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
        HomePanel.setBounds(180,0,900, 720);
        HomePanel.setLayout(null);

            VideoPanel.setVisible(true);

            //The Report Panel will host the Report page and all of its component, tables, etc.
            //The Color BG is a placeholder
            ReportPanel.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            ReportPanel.setBounds(180,0,1000 , 750);
            ReportPanel.setLayout(null);
            ReportPanel.setVisible(false);


            //GUIs of NaviPanel
            //Code for Logo
            UnivLogo = new JLabel();
            Image image;
            try {
                image = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/logocoebg.png"));
                 ImageIcon imageIcon = new ImageIcon(image);
                UnivLogo.setIcon(imageIcon);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            UnivLogo.setBounds(40, 20, 100, 100);    

            HomeButton.setText("Home");
            //HomeButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            HomeButton.setBackground(complimentColor);
            HomeButton.setForeground(Color.WHITE);
            HomeButton.setBounds(30,150,120,30);
            HomeButton.setFocusable(false);

            ListFacButton.setText("List of Faculty");
            //ListFacButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            ListFacButton.setBackground(complimentColor);
            ListFacButton.setForeground(Color.WHITE);
            ListFacButton.setBounds(30,200,120,30);
            ListFacButton.setFocusable(false);

            ReportButton.setText("Report");
            //ReportButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            ReportButton.setBackground(complimentColor);
            ReportButton.setForeground(Color.WHITE);
            ReportButton.setBounds(30,250,120,30);
            ReportButton.setFocusable(false);

            HelpButton.setText("Help");
            //HelpButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            HelpButton.setBackground(complimentColor);
            HelpButton.setForeground(Color.WHITE);
            HelpButton.setBounds(30,300,120,30);
            HelpButton.setFocusable(false);

            LogoutButton.setText("Logout");
            //LogoutButton.setBorder(BorderFactory.createLineBorder(TextHighlightColor));
            LogoutButton.setBackground(complimentColor);
            LogoutButton.setForeground(Color.WHITE);
            LogoutButton.setBounds(30,350,120,30);
            LogoutButton.setFocusable(true);

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

                        VideoPanel.setVisible(true);
                        VideoPanel.setEnabled(true);

                        faculty.show(false);
                        reportpage.show(false);

                        ReportPanel.setVisible(false);
                        ReportPanel.setEnabled(false);
                    }
            });

            ListFacButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                        HomePanel.setVisible(false);
                        HomePanel.setEnabled(false);

                        VideoPanel.setVisible(false);
                        VideoPanel.setEnabled(false);

                        faculty.show();
                        reportpage.show(false);

                        ReportPanel.setVisible(false);
                        ReportPanel.setEnabled(false);
                    }
            });

            ReportButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                        HomePanel.setVisible(false);
                        HomePanel.setEnabled(false);

                        VideoPanel.setVisible(false);
                        VideoPanel.setEnabled(false);

                        faculty.show(false);
                        reportpage.show();
                        
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

            //VLC video player
            VideoPanel.add(canvas);
            VideoPanel.setPreferredSize(new Dimension(900, 720));

            //Adding of Components to the window
            RootPanel.add(NaviPanel);
            RootPanel.add(HomePanel);
            RootPanel.add(ReportPanel);
            RootPanel.add(VideoPanel);
            RootPanel.add(faculty);
            RootPanel.add(reportpage);

            //Window Essentials
            HomeWindow.setTitle("COE Faculty Monitoring System");
            HomeWindow.getContentPane().setLayout(null);
            HomeWindow.setSize(1180, 720);
            HomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            HomeWindow.setContentPane(RootPanel);
            
            HomeWindow.setUndecorated(true);
            HomeWindow.setResizable(false);
            HomeWindow.getContentPane().setLayout(null);
            HomeWindow.setVisible(true);

            VideoPanel.setBounds(180, 0, 1000, 720);

            //Responsible for making the window open on the center of the screen on start up
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int w = HomeWindow.getSize().width;
            int h = HomeWindow.getSize().height;
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;
            HomeWindow.setLocation(x,y);
                
        
        }

        public static void main(String[] args) {
            FlatMacLightLaf.setup();
        	EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					HomeWindow window = new HomeWindow();
    					window.HomeWindow.setVisible(true);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
        }
}