import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JComboBox;

public class editDialog extends JDialog {

	JPanel contentPanel = new JPanel();
	addPreparation prep = new addPreparation();
	JTextField SubjectLbl;
	JButton doneBtn, editSubject, addSection;
	JLabel addLbl;
	JPanel headerPanel, sectionPanel;
	private JScrollPane scrollPane;
	private JPanel Panel;
	private JLabel sectionLbl, semesterLbl, academicYearLbl;
	private JSeparator separator1, separator2, separator3;
	JComboBox semesterCB, acadYearCB;
	
	
	public static void main(String[] args) 
	{
		try 
		{
			editDialog dialog = new editDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public editDialog() 
	{
		// Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = 600;
        int frameHeight = 550;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, frameWidth, frameHeight);
        
		contentPanel.setBounds(0, 0, 600, 513);
		setTitle("Add Subject");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		SubjectLbl = new JTextField();
		SubjectLbl.setText("Software Design");
		SubjectLbl.setBorder(null);
		SubjectLbl.setBackground(SystemColor.text);
		SubjectLbl.setEditable(false);
		SubjectLbl.setFont(new Font("Arial", Font.PLAIN, 13));
		SubjectLbl.setBounds(30, 50, 250, 25);
		contentPanel.add(SubjectLbl);
		SubjectLbl.setColumns(10);
		
		headerPanel = new JPanel();
		headerPanel.setBackground(SystemColor.textHighlight);
		headerPanel.setBounds(0, 0, 600, 35);
		contentPanel.add(headerPanel);
		
		addLbl = new JLabel("Edit Subject");
		headerPanel.add(addLbl);
		addLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addLbl.setBackground(SystemColor.textHighlight);
		addLbl.setFont(new Font("Arial", Font.BOLD, 17));
		
		editSubject = new JButton("");
		editSubject.setIcon(new ImageIcon(editDialog.class.getResource("/Images/Edit25x.png")));
		editSubject.setBackground(SystemColor.text);
		editSubject.setBorder(null);
		editSubject.setBounds(510, 47, 30, 30);
		contentPanel.add(editSubject);
		
		addSection = new JButton("");
		addSection.setIcon(new ImageIcon(editDialog.class.getResource("/Images/AddSection25x.png")));
		addSection.setBorder(null);
		addSection.setBackground(SystemColor.text);
		addSection.setBounds(545, 47, 30, 30);
		contentPanel.add(addSection);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 475, 600, 40);
			contentPanel.add(buttonPane);
			buttonPane.setBorder(null);
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setLayout(null);
			{
				doneBtn = new JButton("Done");
				doneBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				doneBtn.setBackground(SystemColor.text);
				doneBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				doneBtn.setFont(new Font("Arial", Font.BOLD, 12));
				doneBtn.setBounds(200, 10, 75, 20);
				doneBtn.setActionCommand("OK");
				buttonPane.add(doneBtn);
				getRootPane().setDefaultButton(doneBtn);
			}
			{
				JButton cancelEdit = new JButton("Cancel");
				cancelEdit.setBackground(SystemColor.text);
				cancelEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelEdit.setBorder(new LineBorder(SystemColor.textText, 1, true));
				cancelEdit.setFont(new Font("Arial", Font.BOLD, 12));
				cancelEdit.setBounds(300, 10, 75, 20);
				cancelEdit.setActionCommand("Cancel");
				buttonPane.add(cancelEdit);
			}
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(SystemColor.text);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.setBounds(0, 120, 615, 350);
		contentPanel.add(scrollPane);
		
		sectionPanel = new JPanel();
		sectionPanel.setBorder(null);
		scrollPane.setViewportView(sectionPanel);
		sectionPanel.setBackground(SystemColor.text);
		sectionPanel.setLayout(new GridLayout(10,0));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 90, 550, 2);
		contentPanel.add(separator);
		
		Panel = new JPanel();
		Panel.setLayout(null);
		Panel.setBorder(null);
		Panel.setBackground(Color.WHITE);
		Panel.setBounds(10, 90, 550, 25);
		contentPanel.add(Panel);
		
		sectionLbl = new JLabel("Section");
		sectionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		sectionLbl.setFont(new Font("Arial", Font.BOLD, 17));
		sectionLbl.setBorder(null);
		sectionLbl.setBounds(15, 0, 230, 25);
		Panel.add(sectionLbl);
		
		separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setBounds(250, 2, 1, 20);
		Panel.add(separator1);
		
		semesterLbl = new JLabel("Semester");
		semesterLbl.setHorizontalAlignment(SwingConstants.CENTER);
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 17));
		semesterLbl.setBorder(null);
		semesterLbl.setBounds(255, 0, 135, 25);
		Panel.add(semesterLbl);
		
		separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(395, 2, 1, 20);
		Panel.add(separator2);
		
		academicYearLbl = new JLabel("A.Y.");
		academicYearLbl.setHorizontalAlignment(SwingConstants.CENTER);
		academicYearLbl.setFont(new Font("Arial", Font.BOLD, 17));
		academicYearLbl.setBorder(null);
		academicYearLbl.setBounds(400, 0, 135, 25);
		Panel.add(academicYearLbl);
		
		separator3 = new JSeparator();
		separator3.setBounds(25, 115, 550, 1);
		contentPanel.add(separator3);
		
		semesterCB = new JComboBox();
		semesterCB.setModel(new DefaultComboBoxModel(new String[] {"SEMESTER", "First Semester", "Second Semester", "Midyear"}));
		semesterCB.setFont(new Font("Arial", Font.PLAIN, 10));
		semesterCB.setBorder(null);
		semesterCB.setBackground(SystemColor.text);
		semesterCB.setBounds(290, 54, 110, 20);
		contentPanel.add(semesterCB);
		
		acadYearCB = new JComboBox();
		acadYearCB.setFont(new Font("Arial", Font.PLAIN, 10));
		acadYearCB.setBorder(null);
		acadYearCB.setBackground(SystemColor.text);
		acadYearCB.setBounds(410, 54, 90, 20);
		contentPanel.add(acadYearCB);
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int year = currentYear - 3; year <= currentYear + 3; year++) 
		{
			acadYearCB.addItem(String.valueOf(year) + " - " + String.valueOf(year +1));
        }
		acadYearCB.setSelectedItem(String.valueOf(currentYear + " - " + (currentYear + 1)));
	}
}
