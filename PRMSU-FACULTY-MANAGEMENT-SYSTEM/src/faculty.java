import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class faculty extends JPanel 
{
	JLabel facultyNameLbl;
	JLabel departmentLbl;
	JLabel semesterLbl;
	JLabel academicYearLbl;
	JPanel panel;
	JButton deleteBtn, uploadBtn, addBtn;
	
	public faculty() 
	{
		setPreferredSize(new Dimension(1000, 45));
		setBackground(Color.BLACK);
		setBackground(SystemColor.text);
		setLayout(null);
		
		JSeparator separator4 = new JSeparator();
		separator4.setBounds(10, 44, 980, 1);
		add(separator4);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 5, 980, 35);
		add(panel);
		panel.setLayout(null);
		
		facultyNameLbl = new JLabel("");
		facultyNameLbl.setBounds(10, 0, 260, 35);
		panel.add(facultyNameLbl);
		facultyNameLbl.setLabelFor(facultyNameLbl);
		facultyNameLbl.setBorder(null);
		facultyNameLbl.setFont(new Font("Arial", Font.BOLD, 13));
		
		departmentLbl = new JLabel("");
		departmentLbl.setHorizontalAlignment(SwingConstants.CENTER);
		departmentLbl.setFont(new Font("Arial", Font.BOLD, 13));
		departmentLbl.setBorder(null);
		departmentLbl.setBounds(300, 0, 190, 35);
		panel.add(departmentLbl);
		
		semesterLbl = new JLabel("");
		semesterLbl.setHorizontalAlignment(SwingConstants.CENTER);
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 13));
		semesterLbl.setBorder(null);
		semesterLbl.setBounds(520, 0, 150, 35);
		panel.add(semesterLbl);
		
		academicYearLbl = new JLabel("");
		academicYearLbl.setHorizontalAlignment(SwingConstants.CENTER);
		academicYearLbl.setFont(new Font("Arial", Font.BOLD, 13));
		academicYearLbl.setBorder(null);
		academicYearLbl.setBounds(700, 0, 100, 35);
		panel.add(academicYearLbl);
		
		deleteBtn = new JButton("");
		deleteBtn.setIcon(new ImageIcon(faculty.class.getResource("/Images/Delete25x.png")));
		deleteBtn.setBorder(null);
		deleteBtn.setFont(new Font("Arial", Font.BOLD, 10));
		deleteBtn.setBackground(Color.WHITE);
		deleteBtn.setBounds(925, 1, 35, 35);
		panel.add(deleteBtn);
		
		uploadBtn = new JButton("");
		uploadBtn.setIcon(new ImageIcon(faculty.class.getResource("/Images/UploadDocLogo25x.png")));
		uploadBtn.setFont(new Font("Arial", Font.BOLD, 10));
		uploadBtn.setBorder(null);
		uploadBtn.setBackground(Color.WHITE);
		uploadBtn.setBounds(885, 1, 35, 35);
		panel.add(uploadBtn);
		
		addBtn = new JButton("");
		addBtn.setIcon(new ImageIcon(faculty.class.getResource("/Images/AddSection25x.png")));
		addBtn.setFont(new Font("Arial", Font.BOLD, 10));
		addBtn.setBorder(null);
		addBtn.setBackground(Color.WHITE);
		addBtn.setBounds(840, 1, 35, 35);
		panel.add(addBtn);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(815, 2, 2, 31);
		panel.add(separator);
		
		JSeparator separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setForeground(Color.BLACK);
		separator1.setBounds(685, 2, 2, 31);
		panel.add(separator1);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setForeground(Color.BLACK);
		separator2.setBounds(505, 2, 2, 31);
		panel.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setForeground(Color.BLACK);
		separator3.setBounds(285, 2, 2, 31);
		panel.add(separator3);
	}
}
