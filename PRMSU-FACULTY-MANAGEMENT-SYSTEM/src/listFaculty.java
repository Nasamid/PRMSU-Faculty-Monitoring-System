

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Comparator;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;


public class listFaculty extends JPanel 
{
	JPanel Body;
	JFrame frame = new JFrame();
	int currentRow = 0;
	private JTextField searchEngine;
	JButton addFacultyBtn;
	List<String> facultyNames = new ArrayList<>();
	List<Integer> facultyIndex = new ArrayList<>();
	
	public listFaculty() 
	{
		setBackground(SystemColor.text);
		setFont(new Font("Arial", Font.BOLD, 15));
		setBounds(180,0,1000, 720);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(SystemColor.text);
		scrollPane.setBounds(0, 165, 1000, 540);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		
		JPanel Header = new JPanel();
		Header.setBackground(SystemColor.text);
		Header.setBounds(0, 0, 1000, 70);
		add(Header);
		Header.setLayout(null);
		
		JLabel headerTitle = new JLabel("List of Faculty");
		headerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerTitle.setBounds(0, 0, 1000, 70);
		headerTitle.setFont(new Font("Arial", Font.BOLD, 25));
		Header.add(headerTitle);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(SystemColor.textHighlight);
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(25, 65, 950, 3);
		Header.add(separator_1);
		
		Body = new JPanel();
		Body.setBackground(SystemColor.text);
		Body.setLayout(new GridLayout(11,1));
		scrollPane.setViewportView(Body);
		
		addFacultyBtn = new JButton("Add Faculty");
		addFacultyBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				faculty faculty = new faculty();
				AddFaculty addFaculty = new AddFaculty();
				addFaculty.show();

				
				addFaculty.addBtn.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						String facultyName = addFaculty.firstNameTF.getText() + " " + addFaculty.middleNameTF.getText() + " " + addFaculty.lastNameTF.getText() + " " + addFaculty.extNameTF.getText();
						String department = (String) addFaculty.departmentCB.getSelectedItem();
						String academicYear = (String) addFaculty.acadYearCB.getSelectedItem();
						String semester = (String) addFaculty.semesterCB.getSelectedItem();
						
						if(		addFaculty.firstNameTF.getText().isEmpty() 
							|| 	addFaculty.lastNameTF.getText().isEmpty()
							||  addFaculty.semesterCB.getSelectedIndex() == 0
							||  addFaculty.departmentCB.getSelectedIndex() == 0) 
						{
							JOptionPane.showMessageDialog(frame, "Enter First Name.", "Error", JOptionPane.INFORMATION_MESSAGE);	
						}
						else 
						{
							faculty.facultyNameLbl.setText(facultyName);
							faculty.departmentLbl.setText(department);
							faculty.semesterLbl.setText(semester);
							faculty.academicYearLbl.setText(academicYear);
							
							facultyNames.add(facultyName);
							Body.add(faculty);
							currentRow++;
							
							if (Body.getComponentCount() >= 12) 
							{ 
			                    // Increase the preferred height of the rowPanel
			                    Dimension preferredSize = Body.getPreferredSize();
			                    preferredSize.height += 50;
			                    Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			                    Body.setPreferredSize(preferredSize);
			                    Body.revalidate();
							}
							
							addFaculty.dispose();
							Body.revalidate();
						}
					}
				});
				
				JButton addPreparation = faculty.addBtn;
				addPreparation.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						addPreparation preparation = new addPreparation();
						preparation.facultyName.setText(faculty.facultyNameLbl.getText());
						preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
						preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
						preparation.frame.show();
					}
				});
				
				
				JButton uploadDocument = faculty.uploadBtn;
				uploadDocument.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						UploadDocWindow upload = new UploadDocWindow();
						
					}
				});
				
				JButton deleteFaculty = faculty.deleteBtn;
				deleteFaculty.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						Body.remove(faculty);
						revalidate();
						repaint();
						
					}
				});
			}
		});
		addFacultyBtn.setFont(new Font("Arial", Font.BOLD, 15));
		addFacultyBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
		addFacultyBtn.setBackground(SystemColor.text);
		addFacultyBtn.setBounds(20, 80, 150, 30);
		add(addFacultyBtn);
		
		JButton searchBtn = new JButton("");
		searchBtn.setIcon(new ImageIcon(listFaculty.class.getResource("/Images/search25x.png")));
		searchBtn.setBackground(Color.WHITE);
		searchBtn.setBorder(null);
		searchBtn.setBounds(932, 82, 25, 25);
		add(searchBtn);
		
		searchEngine = new JTextField();
		
		searchEngine.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				searchEngine.setText("");
			}
		});
		searchEngine.setFont(new Font("Arial", Font.PLAIN, 13));
		searchEngine.setText("Search Faculty...");
		searchEngine.setBackground(new Color(255, 255, 255));
		searchEngine.setColumns(10);
		searchEngine.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		searchEngine.setBounds(730, 80, 200, 30);
		add(searchEngine);
		
		JPanel Header1 = new JPanel();
		Header1.setLayout(null);
		Header1.setBackground(SystemColor.textHighlight);
		Header1.setBounds(0, 120, 1000, 40);
		add(Header1);
		
		JLabel facultyLbl = new JLabel("FACULTY");
		facultyLbl.setForeground(Color.WHITE);
		facultyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		facultyLbl.setFont(new Font("Arial", Font.BOLD, 15));
		facultyLbl.setBounds(10, 10, 270, 20);
		Header1.add(facultyLbl);
		
		JLabel departmentLbl = new JLabel("DEPARTMENT");
		departmentLbl.setForeground(Color.WHITE);
		departmentLbl.setHorizontalAlignment(SwingConstants.CENTER);
		departmentLbl.setFont(new Font("Arial", Font.BOLD, 15));
		departmentLbl.setBounds(310, 10, 190, 20);
		Header1.add(departmentLbl);
		
		JLabel semesterLbl = new JLabel("SEMESTER");
		semesterLbl.setForeground(Color.WHITE);
		semesterLbl.setHorizontalAlignment(SwingConstants.CENTER);
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 15));
		semesterLbl.setBounds(530, 10, 150, 20);
		Header1.add(semesterLbl);
		
		JLabel academicYearLbl = new JLabel("A. Y.");
		academicYearLbl.setForeground(Color.WHITE);
		academicYearLbl.setHorizontalAlignment(SwingConstants.CENTER);
		academicYearLbl.setFont(new Font("Arial", Font.BOLD, 15));
		academicYearLbl.setBounds(710, 10, 100, 20);
		Header1.add(academicYearLbl);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		separator.setBounds(10, 5, 980, 1);
		Header1.add(separator);
		
		JSeparator separator1 = new JSeparator();
		separator1.setForeground(Color.BLACK);
		separator1.setBackground(Color.WHITE);
		separator1.setBounds(10, 35, 980, 1);
		Header1.add(separator1);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setForeground(Color.BLACK);
		separator2.setBackground(Color.WHITE);
		separator2.setBounds(295, 10, 1, 20);
		Header1.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setForeground(Color.BLACK);
		separator3.setBackground(Color.WHITE);
		separator3.setBounds(825, 10, 1, 20);
		Header1.add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setOrientation(SwingConstants.VERTICAL);
		separator4.setForeground(Color.BLACK);
		separator4.setBackground(Color.WHITE);
		separator4.setBounds(515, 10, 1, 20);
		Header1.add(separator4);
		
		JSeparator separator5 = new JSeparator();
		separator5.setOrientation(SwingConstants.VERTICAL);
		separator5.setForeground(Color.BLACK);
		separator5.setBackground(Color.WHITE);
		separator5.setBounds(695, 10, 1, 20);
		Header1.add(separator5);
		add(scrollPane);
		
		JComboBox semesterCB = new JComboBox();
		semesterCB.setModel(new DefaultComboBoxModel(new String[] {"SEMESTER", "First Semester", "Second Semester", "Midyear"}));
		semesterCB.setFont(new Font("Arial", Font.PLAIN, 10));
		semesterCB.setBorder(null);
		semesterCB.setBackground(SystemColor.text);
		semesterCB.setBounds(405, 85, 110, 20);
		add(semesterCB);
		
		JComboBox acadYearCB = new JComboBox();
		acadYearCB.setFont(new Font("Arial", Font.PLAIN, 10));
		acadYearCB.setBorder(null);
		acadYearCB.setBackground(SystemColor.text);
		acadYearCB.setBounds(525, 85, 110, 20);
		add(acadYearCB);
		
		JComboBox departmentCB = new JComboBox();
		departmentCB.setModel(new DefaultComboBoxModel(new String[] {"DEPARTMENT", "MECHANICAL ENGINEERING", "COMPUTER ENGINEERING", "CIVIL ENGINEERING", "ELECTRICAL ENGINEERING", "ALLIED"}));
		departmentCB.setFont(new Font("Arial", Font.PLAIN, 10));
		departmentCB.setBorder(null);
		departmentCB.setBackground(SystemColor.text);
		departmentCB.setBounds(285, 85, 110, 20);
		add(departmentCB);
		
		JLabel sortLbl = new JLabel("Sort :");
		sortLbl.setFont(new Font("Arial", Font.BOLD, 13));
		sortLbl.setBounds(240, 85, 40, 20);
		add(sortLbl);
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int year = currentYear - 3; year <= currentYear + 3; year++) {
			acadYearCB.addItem(String.valueOf(year) + " - " + String.valueOf(year +1));
        }
		acadYearCB.setSelectedItem(String.valueOf(currentYear + " - " + (currentYear + 1)));
		
		JDialog addDial0g = new JDialog();
		addDial0g.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel addPanel = new JPanel();
		addPanel.setBounds(0, 0, 300, 150);
		addPanel.setLayout(null);
	}
}



