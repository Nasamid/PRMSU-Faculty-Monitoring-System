import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class sections extends JPanel 
{
	JLabel sectionLbl, semesterLbl, academicYearLbl;
	JButton deleteBtn;
	
	public sections() 
	{
		setBorder(null);
		setPreferredSize(new Dimension(600, 35));
		setBackground(Color.BLACK);
		setBackground(SystemColor.text);
		setLayout(null);
		setLayout(null);
		
		JPanel Panel = new JPanel();
		//Panel.setBorder(null);
		Panel.setBackground(Color.WHITE);
		Panel.setBounds(10, 5, 600, 25);
		add(Panel);
		Panel.setLayout(null);
		
		sectionLbl = new JLabel("");
		sectionLbl.setBounds(15, 0, 230, 25);
		Panel.add(sectionLbl);
		sectionLbl.setLabelFor(sectionLbl);
		//sectionLbl.setBorder(null);
		sectionLbl.setFont(new Font("Arial", Font.BOLD, 10));
		
		semesterLbl = new JLabel("");
		semesterLbl.setHorizontalAlignment(SwingConstants.CENTER);
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 10));
		//semesterLbl.setBorder(null);
		semesterLbl.setBounds(255, 0, 135, 25);
		Panel.add(semesterLbl);
		
		academicYearLbl = new JLabel("");
		academicYearLbl.setHorizontalAlignment(SwingConstants.CENTER);
		academicYearLbl.setFont(new Font("Arial", Font.BOLD, 10));
		//academicYearLbl.setBorder(null);
		academicYearLbl.setBounds(400, 0, 135, 25);
		Panel.add(academicYearLbl);
		
		deleteBtn = new JButton("");
		deleteBtn.setIcon(new ImageIcon(sections.class.getResource("/Images/Delete16x.png")));
		deleteBtn.setBorder(null);
		deleteBtn.setBackground(SystemColor.text);
		deleteBtn.setFont(new Font("Arial", Font.BOLD, 10));
		deleteBtn.setBounds(540, 0, 30, 20);
		Panel.add(deleteBtn);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(250, 0, 1, 20);
		Panel.add(separator);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(395, 0, 1, 20);
		Panel.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(10, 32, 580, 1);
		add(separator3);
	}
}
