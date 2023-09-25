import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddFaculty {

	private JFrame addFacultyFrame;
	private JTextField lastName_1;
	private JLabel addFacultyLabel;
	private JTextField firstName;
	private JTextField middleInitial;
	private JTextField extName;
	private JComboBox acadYearCBox;
	private JComboBox semesterCBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFaculty window = new AddFaculty();
					window.addFacultyFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddFaculty() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addFacultyFrame = new JFrame("Add Faculty");
		addFacultyFrame.setUndecorated(true);
		addFacultyFrame.setResizable(false);
		addFacultyFrame.setBounds(100, 100, 500, 650);
		addFacultyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the new X and Y positions to center the frame
        int x = (screenSize.width - addFacultyFrame.getWidth()) / 2;
        int y = (screenSize.height - addFacultyFrame.getHeight()) / 2;

        // Set the frame's location to be centered on the screen
        addFacultyFrame.setLocation(x, y);
        addFacultyFrame.getContentPane().setLayout(null);
        
        addFacultyLabel = new JLabel("Add Faculty");
        addFacultyLabel.setBorder(new LineBorder(new Color(192, 192, 192), 3, true));
        addFacultyLabel.setBackground(SystemColor.windowText);
        addFacultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addFacultyLabel.setFont(new Font("Elephant", Font.BOLD, 25));
        addFacultyLabel.setBounds(150, 15, 200, 35);
        addFacultyFrame.getContentPane().add(addFacultyLabel);
        
        lastName_1 = new JTextField();
        lastName_1.setFont(new Font("Rockwell", Font.BOLD, 20));
        lastName_1.setDisabledTextColor(Color.BLACK);
        lastName_1.setBackground(SystemColor.menu);
        lastName_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2, true), "LAST NAME", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        lastName_1.setBounds(30, 60, 440, 50);
        addFacultyFrame.getContentPane().add(lastName_1);
        lastName_1.setColumns(10);
        
        firstName = new JTextField();
        firstName.setFont(new Font("Rockwell", Font.BOLD, 20));
        firstName.setDisabledTextColor(Color.BLACK);
        firstName.setColumns(10);
        firstName.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2, true), "FIRST NAME", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        firstName.setBackground(SystemColor.menu);
        firstName.setBounds(30, 120, 440, 50);
        addFacultyFrame.getContentPane().add(firstName);
        
        middleInitial = new JTextField();
        middleInitial.setFont(new Font("Rockwell", Font.BOLD, 20));
        middleInitial.setDisabledTextColor(Color.BLACK);
        middleInitial.setColumns(10);
        middleInitial.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2, true), "MIDDLE INITIAL", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        middleInitial.setBackground(SystemColor.menu);
        middleInitial.setBounds(30, 180, 440, 50);
        addFacultyFrame.getContentPane().add(middleInitial);
        
        extName = new JTextField();
        extName.setFont(new Font("Rockwell", Font.BOLD, 20));
        extName.setDisabledTextColor(Color.BLACK);
        extName.setColumns(10);
        extName.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2, true), "EXTENSION NAME", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        extName.setBackground(SystemColor.menu);
        extName.setBounds(30, 240, 440, 50);
        addFacultyFrame.getContentPane().add(extName);
        
        JButton addFacultyBtn = new JButton("Add");
        // ** WAITING **
//        addFacultyBtn.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        	}
//        });
        addFacultyBtn.setBorder(new MatteBorder(5, 3, 5, 3, (Color) new Color(192, 192, 192)));
        addFacultyBtn.setBackground(new Color(240, 240, 240));
        addFacultyBtn.setFont(new Font("Elephant", Font.PLAIN, 20));
        addFacultyBtn.setBounds(150, 475, 200, 50);
        addFacultyFrame.getContentPane().add(addFacultyBtn);
        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getSource() == cancelBtn) {
        			addFacultyFrame.hide();
        		}
        		
        	}
        });
        cancelBtn.setFont(new Font("Elephant", Font.PLAIN, 20));
        cancelBtn.setBorder(new MatteBorder(5, 3, 5, 3, (Color) new Color(192, 192, 192)));
        cancelBtn.setBackground(new Color(240, 240, 240));
        cancelBtn.setBounds(150, 535, 200, 50);
        addFacultyFrame.getContentPane().add(cancelBtn);
        
        JComboBox departmentCBox = new JComboBox();
        departmentCBox.setBackground(new Color(240, 240, 240));
        departmentCBox.setName("DEPARTMENT");
        departmentCBox.setBounds(30, 300, 440, 40);
        addFacultyFrame.getContentPane().add(departmentCBox);
        
        acadYearCBox = new JComboBox();
        acadYearCBox.setName("ACADEMIC YEAR");
        acadYearCBox.setBounds(30, 350, 440, 40);
        addFacultyFrame.getContentPane().add(acadYearCBox);
        
        semesterCBox = new JComboBox();
        semesterCBox.setName("SEMESTER");
        semesterCBox.setBounds(30, 400, 440, 40);
        addFacultyFrame.getContentPane().add(semesterCBox);
        
        
        //Combo Box Items
        
        departmentCBox.addItem("DEPARTMENT");
        departmentCBox.addItem("MECHANICAL ENGINEERING");
        departmentCBox.addItem("COMPUTER ENGINEERING");
        departmentCBox.addItem("CIVIL ENGINEERING");
	departmentCBox.addItem("ELECTRICAL ENGINEERING");
	departmentCBox.addItem("ALLIED");
       
        
        
        acadYearCBox.addItem("ACADEMIC YEAR");
        acadYearCBox.addItem("A/Y  2023 - 2024");
        acadYearCBox.addItem("A/Y  2024 - 2025");
        acadYearCBox.addItem("A/Y  2025 - 2026");
        acadYearCBox.addItem("A/Y  2026 - 2027");
        acadYearCBox.addItem("A/Y  2027 - 2028");
        acadYearCBox.addItem("A/Y  2028 - 2029");
        acadYearCBox.addItem("A/Y  2029 - 2030");
        acadYearCBox.addItem("A/Y  2030 - 2031");
        acadYearCBox.addItem("A/Y  2031 - 2032");
        
        
        semesterCBox.addItem("SEMESTER");
        semesterCBox.addItem("First Semester");
        semesterCBox.addItem("Second Semester");
        semesterCBox.addItem("Midyear");
	}
}
