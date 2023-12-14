import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class listFaculty extends JPanel 
{
	JPanel Body;
	JFrame frame = new JFrame();
	int currentRow = 0;
	private JTextField searchEngine;
	JButton addFacultyBtn;
	List<String> facultyNames = new ArrayList<>();
	List<Integer> facultyIndex = new ArrayList<>();
	String CB_SORT,SRCH_NAME;
	
	public listFaculty() {
	
		setBackground(SystemColor.text);
		setFont(new Font("Arial", Font.BOLD, 15));
		setBounds(180,0,1000, 720);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(SystemColor.text);
		scrollPane.setBounds(0, 160, 1000, 540);
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
		separator_1.setBackground(new Color(255, 77, 41));
		separator_1.setForeground(new Color(255, 77, 41));
		separator_1.setBounds(25, 65, 950, 5);
		Header.add(separator_1);
		
		Body = new JPanel();
		Body.setBackground(SystemColor.text);
		Body.setLayout(new GridLayout(11,1));
		scrollPane.setViewportView(Body);

		loadFacultyData();
		
		addFacultyBtn = new JButton("Add Faculty");
		addFacultyBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				faculty faculty = new faculty();
				AddFaculty addFaculty = new AddFaculty();
				addFaculty.setVisible(true);

				addFaculty.addBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String lastName = addFaculty.lastNameTF.getText();
						String facultyName = addFaculty.firstNameTF.getText() + " " + addFaculty.middleNameTF.getText() + " " + addFaculty.lastNameTF.getText() + " " + addFaculty.extNameTF.getText();
						String department = (String) addFaculty.departmentCB.getSelectedItem();
						String academicYear = (String) addFaculty.acadYearCB.getSelectedItem();
						String semester = (String) addFaculty.semesterCB.getSelectedItem();
				
						if (addFaculty.firstNameTF.getText().isEmpty() || addFaculty.lastNameTF.getText().isEmpty()
								|| addFaculty.semesterCB.getSelectedIndex() == 0
								|| addFaculty.departmentCB.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(frame, "Please insert complete information.", "Insufficient Data", JOptionPane.INFORMATION_MESSAGE);
						} else {

							// Get the corresponding IDs
							int departmentID = DatabaseHandler.getDepartmentID(department);
							int yearID = DatabaseHandler.getYearID(academicYear);
				
							// Dynamically get semesterID based on the selected semester
							int semesterID = DatabaseHandler.getSemesterID(semester);
				
							// Insert data into the SQLite database and get the generated facultyID
							int facultyID = DatabaseHandler.insertFaculty(facultyName, departmentID, yearID, semesterID);

							int fIDlast = FacultyData.getFacultyID();
				
							// Do something with the generated facultyID if needed
							DatabaseHandler.insertFLnID(facultyID, lastName);

							// Convert facultyID to a string
							String facultyIDString = String.valueOf(facultyID);
				
							faculty.facultyNameLbl.setText(facultyName);
							faculty.departmentLbl.setText(department);
							faculty.semesterLbl.setText(semester);
							faculty.academicYearLbl.setText(academicYear);

							documentfaculty docfaculty = new documentfaculty(facultyIDString, lastName);
        					docfaculty.register();
				
							facultyNames.add(facultyName);
							Body.add(faculty);
							currentRow++;
				
							if (Body.getComponentCount() >= 12) {
								// Increase the preferred height of the rowPanel
								Dimension preferredSize = Body.getPreferredSize();
								preferredSize.height += 80;
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
				addPreparation preparations = new addPreparation();
				addPreparation.addActionListener(new ActionListener() 
				{   

					public void actionPerformed(ActionEvent e) 
					{
						preparations.facultyName.setText(faculty.facultyNameLbl.getText());
						preparations.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
						preparations.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
						preparations.frame.setVisible(true);
						System.out.println("ADD SUBJECT BUTTON PRESSED");
					}
				});
				
				
				JButton uploadDocument = faculty.uploadBtn;
				uploadDocument.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						System.out.println("ADD SUBJECT BUTTON PRESSED");
						new UploadDocWindow();
					}
				});
				
				JButton deleteFaculty = faculty.deleteBtn;
				deleteFaculty.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
						
						if (confirm == JOptionPane.YES_OPTION) {
							// Assuming you have a method in DatabaseHandler to delete faculty by ID
							int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
							
							//int originfacultyID = DatabaseHandler.
							int lastnamefacultyID = DatabaseHandler.getFacultyIDByLastName(addFaculty.lastNameTF.getText());

							boolean deleted = DatabaseHandler.deleteFacultyByID(lastnamefacultyID);
							
							// Convert facultyID to a string
							String facultyIDString = String.valueOf(lastnamefacultyID);


							if (deleted) {
								Body.remove(faculty);
								documentfaculty docfaculty = new documentfaculty(facultyIDString, addFaculty.lastNameTF.getText());
								System.out.println("faculty ID: " + lastnamefacultyID);
        						docfaculty.deleteProfileFolder();
								revalidate();
								repaint();
							} else {
								JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
							}
							
						}
					}
				});
			}
		});
		addFacultyBtn.setFont(new Font("Arial", Font.BOLD, 15));
		//addFacultyBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
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
			public void mousePressed (MouseEvent e)
			{
				searchEngine.setText("");
			}
		});

		searchEngine.setFont(new Font("Arial", Font.PLAIN, 13));
		searchEngine.setText("Search Faculty....");
		searchEngine.setBackground(new Color(255, 255, 255));
		searchEngine.setColumns(10);
		//searchEngine.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		searchEngine.setBounds(730, 80, 200, 30);
		add(searchEngine);
		
		JPanel Header1 = new JPanel();
		Header1.setLayout(null);
		Header1.setBackground(new Color(255, 128, 41));
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
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setForeground(Color.WHITE);
		separator2.setBackground(Color.WHITE);
		separator2.setBounds(295, 10, 2, 20);
		Header1.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setForeground(Color.WHITE);
		separator3.setBackground(Color.WHITE);
		separator3.setBounds(825, 10, 2, 20);
		Header1.add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setOrientation(SwingConstants.VERTICAL);
		separator4.setForeground(Color.WHITE);
		separator4.setBackground(Color.WHITE);
		separator4.setBounds(515, 10, 2, 20);
		Header1.add(separator4);
		
		JSeparator separator5 = new JSeparator();
		separator5.setOrientation(SwingConstants.VERTICAL);
		separator5.setForeground(Color.WHITE);
		separator5.setBackground(Color.WHITE);
		separator5.setBounds(695, 10, 2, 20);
		Header1.add(separator5);
		add(scrollPane);
		
		JComboBox SortByCB = new JComboBox();
		SortByCB.setModel(new DefaultComboBoxModel(new String[] {"SORT BY", "DEPARTMENT", "SEMESTER", "YEAR"}));
		SortByCB.setFont(new Font("Arial", Font.PLAIN, 10));
		//SortByCB.setBorder(null);
		SortByCB.setBackground(SystemColor.text);
		SortByCB.setBounds(600, 80, 110, 30);
		add(SortByCB);
		
		JLabel sortLbl = new JLabel("Sort :");
		sortLbl.setFont(new Font("Arial", Font.BOLD, 13));
		sortLbl.setBounds(550, 80, 40, 30);
		add(sortLbl);
		
		JDialog addDial0g = new JDialog();
		addDial0g.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel addPanel = new JPanel();
		addPanel.setBounds(0, 0, 300, 150);
		addPanel.setLayout(null);
	
		searchBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String CB_SORT = SortByCB.getSelectedItem().toString();

				String searchname = searchEngine.getText();

				Body.removeAll();
				Body.revalidate();
				Body.repaint();

				
				//loadSearchFacultyData(searchname, CB_AY, CB_DEP, CB_SEM);
				scrollPane.revalidate();
				scrollPane.repaint();					

			}
		});

		//This is an item listener for the Sort Combo Box
		SortByCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object item = e.getItem();
					String sortString = item.toString();
					System.out.println(sortString);

					if(sortString.equalsIgnoreCase("DEPARTMENT")){
						Body.removeAll();
						Body.revalidate();
						Body.repaint();

						SortDepartmentFacultyData(sortString);

					}
					if(sortString.equalsIgnoreCase("SEMESTER")){
						Body.removeAll();
						Body.revalidate();
						Body.repaint();

						SortSemesterFacultyData(sortString);

					}

					if(sortString.equalsIgnoreCase("YEAR")){
						Body.removeAll();
						Body.revalidate();
						Body.repaint();

						SortYearFacultyData(sortString);

					}

				}
			}
		});

	}
	
	// Method to load faculty data from the database and populate the UI
	public void loadFacultyData() {
		List<FacultyData> facultyDataList = DatabaseHandler.getFacultyDataList();
		//System.out.println(facultyDataList);
		for (FacultyData facultyData : facultyDataList) {
			faculty faculty = new faculty();

			// Retrieve department details
			int departmentID = facultyData.getDepartmentID();
			String departmentName = DatabaseHandler.getDepartmentName(departmentID);
		
			// Retrieve academic year details
			int yearID = facultyData.getYearID();
			String academicYear = DatabaseHandler.getAcademicYear(yearID);
		
			// Retrieve semester details
			int semesterID = facultyData.getSemesterID();
			String semesterName = DatabaseHandler.getSemesterName(semesterID);

			JButton addPreparation = faculty.addBtn;
				addPreparation.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						addPreparation preparation = new addPreparation();
						preparation.facultyName.setText(faculty.facultyNameLbl.getText());
						preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
						preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
						preparation.frame.setVisible(true);
						preparation.fetchAndDisplaySubjects();
					}
				});
				
				
				JButton uploadDocument = faculty.uploadBtn;
				uploadDocument.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						new UploadDocWindow();
					}
				});
				
				JButton deleteFaculty = faculty.deleteBtn;
				deleteFaculty.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
						
						if (confirm == JOptionPane.YES_OPTION) {
							// Assuming you have a method in DatabaseHandler to delete faculty by ID
							int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
							boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

							if (deleted) {
								Body.remove(faculty);
								revalidate();
								repaint();
							} else {
								JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
							}
							
						}
					}
				});
	
			faculty.facultyNameLbl.setText(facultyData.getFacultyName());
			faculty.departmentLbl.setText(departmentName);
			faculty.semesterLbl.setText(semesterName);
			faculty.academicYearLbl.setText(academicYear);

			facultyNames.add(facultyData.getFacultyName());
			Body.add(faculty);
			currentRow++;

			if (Body.getComponentCount() >= 12) {
				// Increase the preferred height of the rowPanel
				Dimension preferredSize = Body.getPreferredSize();
				preferredSize.height += 80;
				Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
				Body.setPreferredSize(preferredSize);
				Body.revalidate();
			}
			Body.revalidate();
		}
		Body.revalidate();
		Body.repaint();
	}
// Method to SORT the Department of the Faculty in Descending order
public void SortDepartmentFacultyData(String CB_SORT) {
	List<FacultyData> facultyDataList = DatabaseHandler.getFacultyDataList();
	//System.out.println(facultyDataList);


	for (FacultyData facultyData : facultyDataList) {

		if((facultyData.getDepartmentName().equalsIgnoreCase("Allied"))){
		faculty faculty = new faculty();

		// Retrieve department details
		int departmentID = facultyData.getDepartmentID();
		String departmentName = DatabaseHandler.getDepartmentName(departmentID);

		// Retrieve academic year details
		int yearID = facultyData.getYearID();
		String academicYear = DatabaseHandler.getAcademicYear(yearID);

		// Retrieve semester details
		int semesterID = facultyData.getSemesterID();
		String semesterName = DatabaseHandler.getSemesterName(semesterID);

		JButton addPreparation = faculty.addBtn;
			addPreparation.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addPreparation preparation = new addPreparation();
					preparation.facultyName.setText(faculty.facultyNameLbl.getText());
					preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
					preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
					preparation.frame.setVisible(true);
					preparation.fetchAndDisplaySubjects();
				}
			});


			JButton uploadDocument = faculty.uploadBtn;
			uploadDocument.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					new UploadDocWindow();
				}
			});

			JButton deleteFaculty = faculty.deleteBtn;
			deleteFaculty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						// Assuming you have a method in DatabaseHandler to delete faculty by ID
						int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
						boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

						if (deleted) {
							Body.remove(faculty);
							revalidate();
							repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});

		faculty.facultyNameLbl.setText(facultyData.getFacultyName());
		faculty.departmentLbl.setText(departmentName);
		faculty.semesterLbl.setText(semesterName);
		faculty.academicYearLbl.setText(academicYear);

		facultyNames.add(facultyData.getFacultyName());
		Body.add(faculty);
		currentRow++;

		if (Body.getComponentCount() >= 12) {
			// Increase the preferred height of the rowPanel
			Dimension preferredSize = Body.getPreferredSize();
			preferredSize.height += 80;
			Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			Body.setPreferredSize(preferredSize);
			Body.revalidate();
		}
		Body.revalidate();
		}

	}


	for (FacultyData facultyData : facultyDataList) {

		if((facultyData.getDepartmentName().equalsIgnoreCase("Civil Engineering"))){
		faculty faculty = new faculty();

		// Retrieve department details
		int departmentID = facultyData.getDepartmentID();
		String departmentName = DatabaseHandler.getDepartmentName(departmentID);

		// Retrieve academic year details
		int yearID = facultyData.getYearID();
		String academicYear = DatabaseHandler.getAcademicYear(yearID);

		// Retrieve semester details
		int semesterID = facultyData.getSemesterID();
		String semesterName = DatabaseHandler.getSemesterName(semesterID);

		JButton addPreparation = faculty.addBtn;
			addPreparation.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addPreparation preparation = new addPreparation();
					preparation.facultyName.setText(faculty.facultyNameLbl.getText());
					preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
					preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
					preparation.frame.setVisible(true);
					preparation.fetchAndDisplaySubjects();
				}
			});


			JButton uploadDocument = faculty.uploadBtn;
			uploadDocument.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					new UploadDocWindow();
				}
			});

			JButton deleteFaculty = faculty.deleteBtn;
			deleteFaculty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						// Assuming you have a method in DatabaseHandler to delete faculty by ID
						int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
						boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

						if (deleted) {
							Body.remove(faculty);
							revalidate();
							repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});

		faculty.facultyNameLbl.setText(facultyData.getFacultyName());
		faculty.departmentLbl.setText(departmentName);
		faculty.semesterLbl.setText(semesterName);
		faculty.academicYearLbl.setText(academicYear);

		facultyNames.add(facultyData.getFacultyName());
		Body.add(faculty);
		currentRow++;

		if (Body.getComponentCount() >= 12) {
			// Increase the preferred height of the rowPanel
			Dimension preferredSize = Body.getPreferredSize();
			preferredSize.height += 80;
			Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			Body.setPreferredSize(preferredSize);
			Body.revalidate();
		}
		Body.revalidate();
		}

	}



	for (FacultyData facultyData : facultyDataList) {

		if((facultyData.getDepartmentName().equalsIgnoreCase("Computer Engineering"))){
		faculty faculty = new faculty();

		// Retrieve department details
		int departmentID = facultyData.getDepartmentID();
		String departmentName = DatabaseHandler.getDepartmentName(departmentID);

		// Retrieve academic year details
		int yearID = facultyData.getYearID();
		String academicYear = DatabaseHandler.getAcademicYear(yearID);

		// Retrieve semester details
		int semesterID = facultyData.getSemesterID();
		String semesterName = DatabaseHandler.getSemesterName(semesterID);

		JButton addPreparation = faculty.addBtn;
			addPreparation.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addPreparation preparation = new addPreparation();
					preparation.facultyName.setText(faculty.facultyNameLbl.getText());
					preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
					preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
					preparation.frame.setVisible(true);
					preparation.fetchAndDisplaySubjects();
				}
			});


			JButton uploadDocument = faculty.uploadBtn;
			uploadDocument.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					new UploadDocWindow();
				}
			});

			JButton deleteFaculty = faculty.deleteBtn;
			deleteFaculty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						// Assuming you have a method in DatabaseHandler to delete faculty by ID
						int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
						boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

						if (deleted) {
							Body.remove(faculty);
							revalidate();
							repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});

		faculty.facultyNameLbl.setText(facultyData.getFacultyName());
		faculty.departmentLbl.setText(departmentName);
		faculty.semesterLbl.setText(semesterName);
		faculty.academicYearLbl.setText(academicYear);

		facultyNames.add(facultyData.getFacultyName());
		Body.add(faculty);
		currentRow++;

		if (Body.getComponentCount() >= 12) {
			// Increase the preferred height of the rowPanel
			Dimension preferredSize = Body.getPreferredSize();
			preferredSize.height += 80;
			Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			Body.setPreferredSize(preferredSize);
			Body.revalidate();
		}
		Body.revalidate();
		}

	}



	for (FacultyData facultyData : facultyDataList) {

		if((facultyData.getDepartmentName().equalsIgnoreCase("Electrical Engineering"))){
		faculty faculty = new faculty();

		// Retrieve department details
		int departmentID = facultyData.getDepartmentID();
		String departmentName = DatabaseHandler.getDepartmentName(departmentID);

		// Retrieve academic year details
		int yearID = facultyData.getYearID();
		String academicYear = DatabaseHandler.getAcademicYear(yearID);

		// Retrieve semester details
		int semesterID = facultyData.getSemesterID();
		String semesterName = DatabaseHandler.getSemesterName(semesterID);

		JButton addPreparation = faculty.addBtn;
			addPreparation.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addPreparation preparation = new addPreparation();
					preparation.facultyName.setText(faculty.facultyNameLbl.getText());
					preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
					preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
					preparation.frame.setVisible(true);
					preparation.fetchAndDisplaySubjects();
				}
			});


			JButton uploadDocument = faculty.uploadBtn;
			uploadDocument.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					new UploadDocWindow();
				}
			});

			JButton deleteFaculty = faculty.deleteBtn;
			deleteFaculty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						// Assuming you have a method in DatabaseHandler to delete faculty by ID
						int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
						boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

						if (deleted) {
							Body.remove(faculty);
							revalidate();
							repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});

		faculty.facultyNameLbl.setText(facultyData.getFacultyName());
		faculty.departmentLbl.setText(departmentName);
		faculty.semesterLbl.setText(semesterName);
		faculty.academicYearLbl.setText(academicYear);

		facultyNames.add(facultyData.getFacultyName());
		Body.add(faculty);
		currentRow++;

		if (Body.getComponentCount() >= 12) {
			// Increase the preferred height of the rowPanel
			Dimension preferredSize = Body.getPreferredSize();
			preferredSize.height += 80;
			Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			Body.setPreferredSize(preferredSize);
			Body.revalidate();
		}
		Body.revalidate();
		}

	}


	for (FacultyData facultyData : facultyDataList) {

		if((facultyData.getDepartmentName().equalsIgnoreCase("Mechanical Engineering"))){
		faculty faculty = new faculty();

		// Retrieve department details
		int departmentID = facultyData.getDepartmentID();
		String departmentName = DatabaseHandler.getDepartmentName(departmentID);

		// Retrieve academic year details
		int yearID = facultyData.getYearID();
		String academicYear = DatabaseHandler.getAcademicYear(yearID);

		// Retrieve semester details
		int semesterID = facultyData.getSemesterID();
		String semesterName = DatabaseHandler.getSemesterName(semesterID);

		JButton addPreparation = faculty.addBtn;
			addPreparation.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addPreparation preparation = new addPreparation();
					preparation.facultyName.setText(faculty.facultyNameLbl.getText());
					preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
					preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
					preparation.frame.setVisible(true);
					preparation.fetchAndDisplaySubjects();
				}
			});


			JButton uploadDocument = faculty.uploadBtn;
			uploadDocument.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					new UploadDocWindow();
				}
			});

			JButton deleteFaculty = faculty.deleteBtn;
			deleteFaculty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						// Assuming you have a method in DatabaseHandler to delete faculty by ID
						int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
						boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

						if (deleted) {
							Body.remove(faculty);
							revalidate();
							repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});

		faculty.facultyNameLbl.setText(facultyData.getFacultyName());
		faculty.departmentLbl.setText(departmentName);
		faculty.semesterLbl.setText(semesterName);
		faculty.academicYearLbl.setText(academicYear);

		facultyNames.add(facultyData.getFacultyName());
		Body.add(faculty);
		currentRow++;

		if (Body.getComponentCount() >= 12) {
			// Increase the preferred height of the rowPanel
			Dimension preferredSize = Body.getPreferredSize();
			preferredSize.height += 80;
			Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			Body.setPreferredSize(preferredSize);
			Body.revalidate();
		}
		Body.revalidate();
		}

	}

	Body.revalidate();
	Body.repaint();
}


// Method to load new faculty data from the database and populate the UI based on the search parameters
public void SortSemesterFacultyData(String CB_SORT) {
	List<FacultyData> facultyDataList = DatabaseHandler.getFacultyDataList();
	//System.out.println(facultyDataList);
	for (FacultyData facultyData : facultyDataList) {

		if((facultyData.getSemesterName().equalsIgnoreCase("First Semester"))){
		faculty faculty = new faculty();

		// Retrieve department details
		int departmentID = facultyData.getDepartmentID();
		String departmentName = DatabaseHandler.getDepartmentName(departmentID);

		// Retrieve academic year details
		int yearID = facultyData.getYearID();
		String academicYear = DatabaseHandler.getAcademicYear(yearID);

		// Retrieve semester details
		int semesterID = facultyData.getSemesterID();
		String semesterName = DatabaseHandler.getSemesterName(semesterID);

		JButton addPreparation = faculty.addBtn;
			addPreparation.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addPreparation preparation = new addPreparation();
					preparation.facultyName.setText(faculty.facultyNameLbl.getText());
					preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
					preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
					preparation.frame.setVisible(true);
					preparation.fetchAndDisplaySubjects();
				}
			});


			JButton uploadDocument = faculty.uploadBtn;
			uploadDocument.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					new UploadDocWindow();
				}
			});

			JButton deleteFaculty = faculty.deleteBtn;
			deleteFaculty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						// Assuming you have a method in DatabaseHandler to delete faculty by ID
						int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
						boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

						if (deleted) {
							Body.remove(faculty);
							revalidate();
							repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});

		faculty.facultyNameLbl.setText(facultyData.getFacultyName());
		faculty.departmentLbl.setText(departmentName);
		faculty.semesterLbl.setText(semesterName);
		faculty.academicYearLbl.setText(academicYear);

		facultyNames.add(facultyData.getFacultyName());
		Body.add(faculty);
		currentRow++;

		if (Body.getComponentCount() >= 12) {
			// Increase the preferred height of the rowPanel
			Dimension preferredSize = Body.getPreferredSize();
			preferredSize.height += 80;
			Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			Body.setPreferredSize(preferredSize);
			Body.revalidate();
		}
		Body.revalidate();
		}

	}


	for (FacultyData facultyData : facultyDataList) {

		if((facultyData.getSemesterName().equalsIgnoreCase("Second Semester"))){
		faculty faculty = new faculty();

		// Retrieve department details
		int departmentID = facultyData.getDepartmentID();
		String departmentName = DatabaseHandler.getDepartmentName(departmentID);

		// Retrieve academic year details
		int yearID = facultyData.getYearID();
		String academicYear = DatabaseHandler.getAcademicYear(yearID);

		// Retrieve semester details
		int semesterID = facultyData.getSemesterID();
		String semesterName = DatabaseHandler.getSemesterName(semesterID);

		JButton addPreparation = faculty.addBtn;
			addPreparation.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addPreparation preparation = new addPreparation();
					preparation.facultyName.setText(faculty.facultyNameLbl.getText());
					preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
					preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
					preparation.frame.setVisible(true);
					preparation.fetchAndDisplaySubjects();
				}
			});


			JButton uploadDocument = faculty.uploadBtn;
			uploadDocument.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					new UploadDocWindow();
				}
			});

			JButton deleteFaculty = faculty.deleteBtn;
			deleteFaculty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						// Assuming you have a method in DatabaseHandler to delete faculty by ID
						int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
						boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

						if (deleted) {
							Body.remove(faculty);
							revalidate();
							repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});

		faculty.facultyNameLbl.setText(facultyData.getFacultyName());
		faculty.departmentLbl.setText(departmentName);
		faculty.semesterLbl.setText(semesterName);
		faculty.academicYearLbl.setText(academicYear);

		facultyNames.add(facultyData.getFacultyName());
		Body.add(faculty);
		currentRow++;

		if (Body.getComponentCount() >= 12) {
			// Increase the preferred height of the rowPanel
			Dimension preferredSize = Body.getPreferredSize();
			preferredSize.height += 80;
			Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			Body.setPreferredSize(preferredSize);
			Body.revalidate();
		}
		Body.revalidate();
		}

	}



	for (FacultyData facultyData : facultyDataList) {

		if((facultyData.getSemesterName().equalsIgnoreCase("Midyear"))){
		faculty faculty = new faculty();

		// Retrieve department details
		int departmentID = facultyData.getDepartmentID();
		String departmentName = DatabaseHandler.getDepartmentName(departmentID);

		// Retrieve academic year details
		int yearID = facultyData.getYearID();
		String academicYear = DatabaseHandler.getAcademicYear(yearID);

		// Retrieve semester details
		int semesterID = facultyData.getSemesterID();
		String semesterName = DatabaseHandler.getSemesterName(semesterID);

		JButton addPreparation = faculty.addBtn;
			addPreparation.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addPreparation preparation = new addPreparation();
					preparation.facultyName.setText(faculty.facultyNameLbl.getText());
					preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
					preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
					preparation.frame.setVisible(true);
					preparation.fetchAndDisplaySubjects();
				}
			});


			JButton uploadDocument = faculty.uploadBtn;
			uploadDocument.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					new UploadDocWindow();
				}
			});

			JButton deleteFaculty = faculty.deleteBtn;
			deleteFaculty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						// Assuming you have a method in DatabaseHandler to delete faculty by ID
						int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
						boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

						if (deleted) {
							Body.remove(faculty);
							revalidate();
							repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});

		faculty.facultyNameLbl.setText(facultyData.getFacultyName());
		faculty.departmentLbl.setText(departmentName);
		faculty.semesterLbl.setText(semesterName);
		faculty.academicYearLbl.setText(academicYear);

		facultyNames.add(facultyData.getFacultyName());
		Body.add(faculty);
		currentRow++;

		if (Body.getComponentCount() >= 12) {
			// Increase the preferred height of the rowPanel
			Dimension preferredSize = Body.getPreferredSize();
			preferredSize.height += 80;
			Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
			Body.setPreferredSize(preferredSize);
			Body.revalidate();
		}
		Body.revalidate();
		}

	}


	Body.revalidate();
	Body.repaint();
}

// Method to load new faculty data from the database and populate the UI based on the search parameters
public void SortYearFacultyData(String CB_SORT) {
	List<FacultyData> facultyDataList = DatabaseHandler.getFacultyDataList();
	int sortYearID = 2020;
	while(sortYearID <= 2026){
		for (FacultyData facultyData : facultyDataList) {

			if((facultyData.getYearID() == sortYearID)){
			faculty faculty = new faculty();

			// Retrieve department details
			int departmentID = facultyData.getDepartmentID();
			String departmentName = DatabaseHandler.getDepartmentName(departmentID);

			// Retrieve academic year details
			int yearID = facultyData.getYearID();
			String academicYear = DatabaseHandler.getAcademicYear(yearID);

			// Retrieve semester details
			int semesterID = facultyData.getSemesterID();
			String semesterName = DatabaseHandler.getSemesterName(semesterID);

			JButton addPreparation = faculty.addBtn;
				addPreparation.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						addPreparation preparation = new addPreparation();
						preparation.facultyName.setText(faculty.facultyNameLbl.getText());
						preparation.acadYearCB.setSelectedItem(faculty.academicYearLbl.getText());
						preparation.semesterCB.setSelectedItem(faculty.semesterLbl.getText());
						preparation.frame.setVisible(true);
						preparation.fetchAndDisplaySubjects();
					}
				});


				JButton uploadDocument = faculty.uploadBtn;
				uploadDocument.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						new UploadDocWindow();
					}
				});

				JButton deleteFaculty = faculty.deleteBtn;
				deleteFaculty.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this faculty?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

						if (confirm == JOptionPane.YES_OPTION) {
							// Assuming you have a method in DatabaseHandler to delete faculty by ID
							int facultyID = FacultyData.getFacultyID(); // Assuming you have this method in your FacultyData class
							boolean deleted = DatabaseHandler.deleteFacultyByID(facultyID);

							if (deleted) {
								Body.remove(faculty);
								revalidate();
								repaint();
							} else {
								JOptionPane.showMessageDialog(frame, "Failed to delete faculty from the database.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
							}

						}
					}
				});

			faculty.facultyNameLbl.setText(facultyData.getFacultyName());
			faculty.departmentLbl.setText(departmentName);
			faculty.semesterLbl.setText(semesterName);
			faculty.academicYearLbl.setText(academicYear);

			facultyNames.add(facultyData.getFacultyName());
			Body.add(faculty);
			currentRow++;

			if (Body.getComponentCount() >= 12) {
				// Increase the preferred height of the rowPanel
				Dimension preferredSize = Body.getPreferredSize();
				preferredSize.height += 80;
				Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
				Body.setPreferredSize(preferredSize);
				Body.revalidate();
			}
			Body.revalidate();
			}

		}
		sortYearID++;
	}
	Body.revalidate();
	Body.repaint();
}

	//This class is to add gradient to the JPanels
	class JPanelGradient extends JPanel{
		protected void paintComponent(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			int width = getWidth();
			int height = getHeight();


			Color C1 = new Color(255, 198, 43);
			Color C2 = new Color(255, 77, 41);
			GradientPaint gp = new GradientPaint(75,0,C2,180,height,C2);
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, width, height);
			
		}
	}
		
}



