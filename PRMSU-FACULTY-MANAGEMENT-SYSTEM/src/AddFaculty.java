import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddFaculty extends JDialog 
{

	JPanel contentPanel = new JPanel();
	addPreparation prep = new addPreparation();
	JTextField firstNameTF, lastNameTF;
	JButton addBtn;
	JLabel headLbl;
	JPanel headerPanel;
	JTextField middleNameTF;
	JLabel extName;
	JTextField extNameTF;
	JLabel departmentLbl;
	JLabel acadYear;
	JLabel semesterLbl;
	JComboBox departmentCB, acadYearCB, semesterCB;

	public AddFaculty() 
	{
		
		// Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = 550;
        int frameHeight = 515;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, frameWidth, frameHeight);

		Image logo;
            try {
                logo = ImageIO.read(AddFaculty.class.getResourceAsStream("/Images/addfacultyicon16x.png"));
                this.setIconImage(logo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
		contentPanel.setBounds(0, 0, 536, 563);
		setTitle("Add Subject");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		headLbl = new JLabel("Add Faculty");
		headLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headLbl.setBackground(SystemColor.textHighlight);
		headLbl.setFont(new Font("Arial", Font.BOLD, 25));
		headLbl.setBounds(0, 15, 550, 20);
		contentPanel.add(headLbl);
		
		JLabel fistName = new JLabel("First Name :");
		fistName.setFont(new Font("Arial", Font.BOLD, 17));
		fistName.setBounds(15, 75, 150, 20);
		contentPanel.add(fistName);
		
		JLabel lastName = new JLabel("Last Name :");
		lastName.setFont(new Font("Arial", Font.BOLD, 17));
		lastName.setBounds(15, 125, 150, 20);
		contentPanel.add(lastName);
		
		JLabel middleName = new JLabel("Middle Name :");
		middleName.setFont(new Font("Arial", Font.BOLD, 17));
		middleName.setBounds(15, 175, 146, 20);
		contentPanel.add(middleName);
		
		extName = new JLabel("Extension Name :");
		extName.setFont(new Font("Arial", Font.BOLD, 17));
		extName.setBounds(15, 225, 146, 20);
		contentPanel.add(extName);
		
		departmentLbl = new JLabel("Department :");
		departmentLbl.setFont(new Font("Arial", Font.BOLD, 17));
		departmentLbl.setBounds(15, 275, 146, 20);
		contentPanel.add(departmentLbl);
		
		acadYear = new JLabel("Academic Year :");
		acadYear.setFont(new Font("Arial", Font.BOLD, 17));
		acadYear.setBounds(14, 325, 151, 20);
		contentPanel.add(acadYear);
		
		semesterLbl = new JLabel("Semester :");
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 17));
		semesterLbl.setBounds(19, 375, 146, 20);
		contentPanel.add(semesterLbl);
		
		firstNameTF = new JTextField();
		firstNameTF.setFont(new Font("Arial", Font.PLAIN, 15));
		//firstNameTF.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		firstNameTF.setBounds(175, 70, 350, 30);
		contentPanel.add(firstNameTF);
		firstNameTF.setColumns(10);
		
		lastNameTF = new JTextField();
		lastNameTF.setFont(new Font("Arial", Font.PLAIN, 15));
		//lastNameTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		lastNameTF.setColumns(10);
		lastNameTF.setBounds(175, 120, 350, 30);
		contentPanel.add(lastNameTF);
		
		middleNameTF = new JTextField();
		middleNameTF.setFont(new Font("Arial", Font.PLAIN, 15));
		//middleNameTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		middleNameTF.setColumns(10);
		middleNameTF.setBounds(175, 170, 350, 30);
		contentPanel.add(middleNameTF);
		
		extNameTF = new JTextField();
		extNameTF.setFont(new Font("Arial", Font.PLAIN, 15));
		//extNameTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		extNameTF.setColumns(10);
		extNameTF.setBounds(175, 220, 350, 30);
		contentPanel.add(extNameTF);
		
		departmentCB = new JComboBox();
		departmentCB.setModel(new DefaultComboBoxModel(new String[] {"DEPARTMENT", "MECHANICAL ENGINEERING", "COMPUTER ENGINEERING", "CIVIL ENGINEERING", "ELECTRICAL ENGINEERING", "ALLIED"}));
		departmentCB.setFont(new Font("Arial", Font.PLAIN, 15));
		//departmentCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		departmentCB.setBackground(SystemColor.text);
		departmentCB.setBounds(175, 270, 350, 30);
		contentPanel.add(departmentCB);
		
		acadYearCB = new JComboBox();
		acadYearCB.setFont(new Font("Arial", Font.PLAIN, 15));
		//acadYearCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		acadYearCB.setBackground(SystemColor.text);
		acadYearCB.setBounds(175, 320, 350, 30);
		contentPanel.add(acadYearCB);
		
		semesterCB = new JComboBox();
		semesterCB.setModel(new DefaultComboBoxModel(new String[] {"SEMESTER", "First Semester", "Second Semester", "Midyear"}));
		semesterCB.setFont(new Font("Arial", Font.PLAIN, 15));
		//semesterCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		semesterCB.setBackground(SystemColor.text);
		semesterCB.setBounds(175, 370, 350, 30);
		contentPanel.add(semesterCB);
	
		{
			JPanel footerPanel = new JPanel();
			footerPanel.setBounds(0, 430, 550, 50);
			contentPanel.add(footerPanel);
			footerPanel.setBorder(null);
			footerPanel.setBackground(new Color(255, 128, 41));
			footerPanel.setLayout(null);
			{
				addBtn = new JButton("Add");
				addBtn.setBackground(SystemColor.text);
				addBtn.setForeground(Color.BLACK);
				//addBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				addBtn.setFont(new Font("Arial", Font.BOLD, 12));
				addBtn.setBounds(175, 10, 100, 30);
				addBtn.setActionCommand("OK");
				footerPanel.add(addBtn);
				getRootPane().setDefaultButton(addBtn);
			}
			{
				JButton cancelDialogBtn = new JButton("Cancel");
				cancelDialogBtn.setBackground(SystemColor.text);
				cancelDialogBtn.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
				});
				//cancelDialogBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				cancelDialogBtn.setFont(new Font("Arial", Font.BOLD, 12));
				cancelDialogBtn.setBounds(295, 10, 100, 30);
				cancelDialogBtn.setActionCommand("Cancel");
				footerPanel.add(cancelDialogBtn);
			}
		}
		
		headerPanel = new JPanel();
		headerPanel.setBackground(new Color(255, 128, 41));
		headerPanel.setBounds(0, 0, 550, 50);
		contentPanel.add(headerPanel);
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int year = currentYear - 3; year <= currentYear + 3; year++) 
		{
			acadYearCB.addItem(String.valueOf(year) + " - " + String.valueOf(year +1));
        }
		acadYearCB.setSelectedItem(String.valueOf(currentYear + " - " + (currentYear + 1)));
		
	}
}

