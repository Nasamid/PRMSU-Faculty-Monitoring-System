import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

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

        int frameWidth = 400;
        int frameHeight = 420;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, 400, 420);
        
		contentPanel.setBounds(0, 0, 400, 400);
		setTitle("Add Subject");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		headLbl = new JLabel("Add Faculty");
		headLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headLbl.setBackground(SystemColor.textHighlight);
		headLbl.setFont(new Font("Arial", Font.BOLD, 17));
		headLbl.setBounds(0, 10, 386, 20);
		contentPanel.add(headLbl);
		
		JLabel fistName = new JLabel("First Name :");
		fistName.setFont(new Font("Arial", Font.BOLD, 12));
		fistName.setBounds(15, 60, 120, 20);
		contentPanel.add(fistName);
		
		JLabel lastName = new JLabel("Last Name :");
		lastName.setFont(new Font("Arial", Font.BOLD, 12));
		lastName.setBounds(15, 100, 120, 20);
		contentPanel.add(lastName);
		
		JLabel middleName = new JLabel("Middle Name :");
		middleName.setFont(new Font("Arial", Font.BOLD, 12));
		middleName.setBounds(15, 140, 120, 20);
		contentPanel.add(middleName);
		
		extName = new JLabel("Extension Name :");
		extName.setFont(new Font("Arial", Font.BOLD, 12));
		extName.setBounds(15, 180, 120, 20);
		contentPanel.add(extName);
		
		departmentLbl = new JLabel("Department :");
		departmentLbl.setFont(new Font("Arial", Font.BOLD, 12));
		departmentLbl.setBounds(15, 220, 120, 20);
		contentPanel.add(departmentLbl);
		
		acadYear = new JLabel("Academic Year :");
		acadYear.setFont(new Font("Arial", Font.BOLD, 12));
		acadYear.setBounds(15, 260, 100, 20);
		contentPanel.add(acadYear);
		
		semesterLbl = new JLabel("Semester :");
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 12));
		semesterLbl.setBounds(15, 300, 74, 20);
		contentPanel.add(semesterLbl);
		
		firstNameTF = new JTextField();
		firstNameTF.setFont(new Font("Arial", Font.PLAIN, 10));
		//firstNameTF.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		firstNameTF.setBounds(145, 60, 225, 20);
		contentPanel.add(firstNameTF);
		firstNameTF.setColumns(10);
		
		lastNameTF = new JTextField();
		lastNameTF.setFont(new Font("Arial", Font.PLAIN, 10));
		//lastNameTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		lastNameTF.setColumns(10);
		lastNameTF.setBounds(145, 100, 225, 20);
		contentPanel.add(lastNameTF);
		
		middleNameTF = new JTextField();
		middleNameTF.setFont(new Font("Arial", Font.PLAIN, 10));
		//middleNameTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		middleNameTF.setColumns(10);
		middleNameTF.setBounds(145, 140, 225, 20);
		contentPanel.add(middleNameTF);
		
		extNameTF = new JTextField();
		extNameTF.setFont(new Font("Arial", Font.PLAIN, 10));
		//extNameTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		extNameTF.setColumns(10);
		extNameTF.setBounds(145, 180, 225, 20);
		contentPanel.add(extNameTF);
		
		departmentCB = new JComboBox();
		departmentCB.setModel(new DefaultComboBoxModel(new String[] {"DEPARTMENT", "MECHANICAL ENGINEERING", "COMPUTER ENGINEERING", "CIVIL ENGINEERING", "ELECTRICAL ENGINEERING", "ALLIED"}));
		departmentCB.setFont(new Font("Arial", Font.PLAIN, 10));
		//departmentCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		departmentCB.setBackground(SystemColor.text);
		departmentCB.setBounds(145, 220, 225, 20);
		contentPanel.add(departmentCB);
		
		acadYearCB = new JComboBox();
		acadYearCB.setFont(new Font("Arial", Font.PLAIN, 10));
		//acadYearCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		acadYearCB.setBackground(SystemColor.text);
		acadYearCB.setBounds(145, 260, 225, 20);
		contentPanel.add(acadYearCB);
		
		semesterCB = new JComboBox();
		semesterCB.setModel(new DefaultComboBoxModel(new String[] {"SEMESTER", "First Semester", "Second Semester", "Midyear"}));
		semesterCB.setFont(new Font("Arial", Font.PLAIN, 10));
		//semesterCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		semesterCB.setBackground(SystemColor.text);
		semesterCB.setBounds(145, 300, 225, 20);
		contentPanel.add(semesterCB);
	
		{
			JPanel footerPanel = new JPanel();
			footerPanel.setBounds(0, 345, 400, 38);
			contentPanel.add(footerPanel);
			footerPanel.setBorder(null);
			footerPanel.setBackground(SystemColor.textHighlight);
			footerPanel.setLayout(null);
			{
				addBtn = new JButton("Add");
				addBtn.setBackground(SystemColor.text);
				addBtn.setForeground(Color.BLACK);
				//addBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				addBtn.setFont(new Font("Arial", Font.BOLD, 12));
				addBtn.setBounds(115, 10, 75, 20);
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
				cancelDialogBtn.setBounds(215, 10, 75, 20);
				cancelDialogBtn.setActionCommand("Cancel");
				footerPanel.add(cancelDialogBtn);
			}
		}
		
		headerPanel = new JPanel();
		headerPanel.setBackground(SystemColor.textHighlight);
		headerPanel.setBounds(0, 0, 400, 40);
		contentPanel.add(headerPanel);
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int year = currentYear - 3; year <= currentYear + 3; year++) 
		{
			acadYearCB.addItem(String.valueOf(year) + " - " + String.valueOf(year +1));
        }
		acadYearCB.setSelectedItem(String.valueOf(currentYear + " - " + (currentYear + 1)));
		
	}
}

