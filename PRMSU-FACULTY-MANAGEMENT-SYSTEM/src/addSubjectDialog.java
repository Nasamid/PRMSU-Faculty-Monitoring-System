import javax.swing.DefaultComboBoxModel;
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

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
public class addSubjectDialog extends JDialog 
{

	JPanel contentPanel = new JPanel();
	addPreparation prep = new addPreparation();
	JTextField codeTF, decriptionTF;
	JButton addBtn;
	JLabel addLbl;
	JPanel panel;
	JComboBox acadYearCB, semesterCB;
	JLabel semesterLbl, academicYearLbl;

	public addSubjectDialog() 
	{
		// Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = 400;
        int frameHeight = 310;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, frameWidth, frameHeight);
        
		contentPanel.setBounds(0, 0, 386, 230);
		setTitle("Add Subject");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		//contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel codeLbl = new JLabel("Course Code :");
		codeLbl.setFont(new Font("Arial", Font.BOLD, 12));
		codeLbl.setBounds(15, 60, 120, 20);
		contentPanel.add(codeLbl);
		
		JLabel descriptionLbl = new JLabel("Course Description  :");
		descriptionLbl.setFont(new Font("Arial", Font.BOLD, 12));
		descriptionLbl.setBounds(15, 100, 120, 20);
		contentPanel.add(descriptionLbl);
		
		codeTF = new JTextField();
		//codeTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		codeTF.setBackground(SystemColor.text);
		codeTF.setFont(new Font("Arial", Font.PLAIN, 10));
		codeTF.setBounds(145, 60, 225, 20);
		contentPanel.add(codeTF);
		codeTF.setColumns(10);
		
		decriptionTF = new JTextField();
		//decriptionTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		decriptionTF.setBackground(SystemColor.text);
		decriptionTF.setFont(new Font("Arial", Font.PLAIN, 10));
		decriptionTF.setColumns(10);
		decriptionTF.setBounds(145, 100, 225, 20);
		contentPanel.add(decriptionTF);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 386, 35);
		contentPanel.add(panel);
		
		addLbl = new JLabel("Add Subject");
		panel.add(addLbl);
		addLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addLbl.setBackground(SystemColor.textHighlight);
		addLbl.setFont(new Font("Arial", Font.BOLD, 17));
		
		semesterCB = new JComboBox();
		semesterCB.setModel(new DefaultComboBoxModel(new String[] {"SEMESTER", "First Semester", "Second Semester", "Midyear"}));
		semesterCB.setFont(new Font("Arial", Font.PLAIN, 10));
		//semesterCB.setBorder(null);
		semesterCB.setBackground(SystemColor.text);
		semesterCB.setBounds(145, 140, 225, 20);
		contentPanel.add(semesterCB);
		
		acadYearCB = new JComboBox();
		acadYearCB.setFont(new Font("Arial", Font.PLAIN, 10));
		//acadYearCB.setBorder(null);
		acadYearCB.setBackground(SystemColor.text);
		acadYearCB.setBounds(145, 180, 225, 20);
		contentPanel.add(acadYearCB);
		
		semesterLbl = new JLabel("Semester :");
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 12));
		semesterLbl.setBounds(15, 140, 120, 20);
		contentPanel.add(semesterLbl);
		
		academicYearLbl = new JLabel("Academic Year :");
		academicYearLbl.setFont(new Font("Arial", Font.BOLD, 12));
		academicYearLbl.setBounds(15, 180, 120, 20);
		contentPanel.add(academicYearLbl);
		
		{
			JPanel buttonPane = new JPanel();
			//buttonPane.setBorder(null);
			buttonPane.setBounds(0, 230, 386, 40);
			buttonPane.setBackground(SystemColor.textHighlight);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				addBtn = new JButton("Add");
				addBtn.setBackground(SystemColor.text);
				addBtn.setForeground(Color.black);
				//addBtn.setBorder(null);
				addBtn.setFont(new Font("Arial", Font.BOLD, 12));
				addBtn.setBounds(115, 10, 75, 20);
				addBtn.setActionCommand("OK");
				buttonPane.add(addBtn);
				getRootPane().setDefaultButton(addBtn);
			}
			{
				JButton cancelDialogBtn = new JButton("Cancel");
				cancelDialogBtn.setBackground(SystemColor.text);
				cancelDialogBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				//cancelDialogBtn.setBorder(null);
				cancelDialogBtn.setFont(new Font("Arial", Font.BOLD, 12));
				cancelDialogBtn.setBounds(215, 10, 75, 20);
				cancelDialogBtn.setActionCommand("Cancel");
				buttonPane.add(cancelDialogBtn);
			}
		}
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int year = currentYear - 3; year <= currentYear + 3; year++) 
		{
			acadYearCB.addItem(String.valueOf(year) + " - " + String.valueOf(year +1));
        }
		acadYearCB.setSelectedItem(String.valueOf(currentYear + " - " + (currentYear + 1)));
	}
	
}
