import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

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

public class addPreparation extends JPanel 
{
JTextField facultyName;
JButton addSubjectBtn;
JPanel Body;
JFrame frame;
JComboBox<String> semesterCB, acadYearCB;
int currentRow = 0;
int currentFacultyID = -1;

	public addPreparation() 
	{
		//new listFaculty();
		
		setBackground(SystemColor.text);
		setFont(new Font("Arial", Font.BOLD, 15));
		setBounds(0,0,1000, 720);
		setLayout(null);
		
		frame = new JFrame();
		frame.setSize(1000, 720);
		frame.getContentPane().add(this);
		
		JPanel Header = new JPanelGradient();
		Header.setBackground(SystemColor.textHighlight);
		add(Header);
		Header.setBounds(0,0,1000, 110);
		Header.setLayout(null);
		
		JLabel panelLbl = new JLabel("Preparation");
		panelLbl.setFont(new Font("Arial", Font.BOLD, 20));
		panelLbl.setBounds(10, 10, 159, 25);
		Header.add(panelLbl);
		
		facultyName = new JTextField();
		//facultyName.setText("Danilo Llaga Jr.");
		facultyName.setBackground(SystemColor.text);
		facultyName.setHorizontalAlignment(SwingConstants.CENTER);
		facultyName.setEditable(false);
		//facultyName.setBorder(new LineBorder(SystemColor.textText, 1, true));
		facultyName.setFont(new Font("Arial", Font.BOLD, 15));
		facultyName.setBounds(45, 50, 350, 35);
		Header.add(facultyName);
		facultyName.setColumns(10);
		
		JLabel acadLbl = new JLabel("Academic Year :");
		acadLbl.setFont(new Font("Arial", Font.BOLD, 15));
		acadLbl.setBounds(415, 50, 135, 35);
		Header.add(acadLbl);
		
		JLabel semLbl = new JLabel("Semester :");
		semLbl.setFont(new Font("Arial", Font.BOLD, 15));
		semLbl.setBounds(735, 50, 90, 35);
		Header.add(semLbl);
		
		acadYearCB = new JComboBox();
		acadYearCB.setFont(new Font("Arial", Font.BOLD, 13));
		//acadYearCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		acadYearCB.setBackground(SystemColor.text);
		acadYearCB.setBounds(540, 55, 150, 25);
		Header.add(acadYearCB);
		
		JPanel Footer = new JPanelGradient();
		Footer.setBackground(SystemColor.textHighlight);
		Footer.setBounds(0, 610, 1000, 110);
		add(Footer);
		Footer.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(SystemColor.text);
		scrollPane.setBounds(0, 150, 1010, 450);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(scrollPane);
		
		Body = new JPanel();
		Body.setBackground(SystemColor.text);
		Body.setLayout(new GridLayout(10,1));
		scrollPane.setViewportView(Body);
		
		addSubjectBtn = new JButton("Add Subject");
		addSubjectBtn.setBackground(SystemColor.text);
		
		//addSubjectBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
		addSubjectBtn.setFont(new Font("Arial", Font.BOLD, 20));
		addSubjectBtn.setFocusable(false);

		addSubjectBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			subject sub = new subject();

			addSubjectDialog add = new addSubjectDialog();

			add.show();
		
			add.addBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String code = add.codeTF.getText();
					String description = add.decriptionTF.getText();
					String subjectDisplay = code + " " + description;
			
					if (code.isEmpty() || description.isEmpty()) {
						JOptionPane.showMessageDialog(Body, "Invalid Input!", "Error", JOptionPane.INFORMATION_MESSAGE);
					} else {
						// Check if the subject already exists
						int existingSubjectID = DatabaseHandler.getSubjectID(subjectDisplay);
			
						if (existingSubjectID != -1) {
							// Subject already exists, use the existing ID
							int facultyID = DatabaseHandler.getFacultyID(facultyName.getText());
							DatabaseHandler.associateFacultyWithSubject(facultyID, existingSubjectID);
							JOptionPane.showMessageDialog(Body, "Subject Already exists", "Error", JOptionPane.INFORMATION_MESSAGE);
						} else {
							// Subject doesn't exist, insert it into the database
							int subjectID = DatabaseHandler.insertSubject(subjectDisplay);
			
							// Fetch the latest subject data from the database
							SubjectData latestSubject = DatabaseHandler.getLatestSubject();
			
							// Update UI components with the new data
							sub.subjectLbl.setText(latestSubject.getSubjectName());
			
							// Add the subject to the panel
							Body.add(sub);
							currentRow++;
			
							if (Body.getComponentCount() > 10) {
								// Increase the preferred height of the rowPanel
								Dimension preferredSize = Body.getPreferredSize();
								preferredSize.height += 50;
								Body.setLayout(new GridLayout(Body.getComponentCount(), 1));
								Body.setPreferredSize(preferredSize);
								Body.revalidate();
							}
			
							int facultyID = DatabaseHandler.getFacultyID(facultyName.getText());
							DatabaseHandler.associateFacultyWithSubject(facultyID, subjectID);
			
							Body.revalidate();
						}
						add.dispose();
					}
				}
			});
		}
	});
		
		
		addSubjectBtn.setBounds(120, 20, 150, 35);
		Footer.add(addSubjectBtn);
		
		JPanel Panel = new JPanel();
		Panel.setLayout(null);
		Panel.setBorder(null);
		Panel.setBackground(Color.WHITE);
		Panel.setBounds(20, 120, 980, 25);
		add(Panel);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject.setFont(new Font("Arial", Font.BOLD, 17));
		lblSubject.setBorder(null);
		lblSubject.setBounds(10, 0, 380, 25);
		Panel.add(lblSubject);
		
		JSeparator separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setBounds(400, 2, 1, 20);
		Panel.add(separator1);
		
		JLabel semesterLbl = new JLabel("Semester");
		semesterLbl.setHorizontalAlignment(SwingConstants.CENTER);
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 17));
		semesterLbl.setBorder(null);
		semesterLbl.setBounds(410, 0, 180, 25);
		Panel.add(semesterLbl);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(600, 2, 1, 20);
		Panel.add(separator2);
		
		JLabel academicYearLbl = new JLabel("Academic Year");
		academicYearLbl.setHorizontalAlignment(SwingConstants.CENTER);
		academicYearLbl.setFont(new Font("Arial", Font.BOLD, 17));
		academicYearLbl.setBorder(null);
		academicYearLbl.setBounds(610, 0, 150, 25);
		Panel.add(academicYearLbl);
		
		JSeparator separator4 = new JSeparator();
		separator4.setOrientation(SwingConstants.VERTICAL);
		separator4.setBounds(770, 2, 1, 20);
		Panel.add(separator4);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(20, 145, 920, 1);
		add(separator3);
		
		semesterCB = new JComboBox<>();
		String[] semester = {
				"First Semester", "Second Semester", "Midyear"
			};

		for (String sem : semester) {
		semesterCB.addItem(sem);
		semesterCB.setFont(new Font("Arial", Font.BOLD, 13));
		//semesterCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		semesterCB.setBounds(820, 55, 150, 25);
		Header.add(semesterCB);
		}
		
		//sets the ComboBox to current School Year
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int year = currentYear - 3; year <= currentYear + 3; year++) 
		{
			acadYearCB.addItem(String.valueOf(year) + " - " + String.valueOf(year+1));
		}

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// Handle the selected academic year
				currentFacultyID = DatabaseHandler.getFacultyID(facultyName.getText());
				String selectedAcademicYear = (String) acadYearCB.getSelectedItem();
				String selectedSemester = (String) semesterCB.getSelectedItem();
				if (currentFacultyID != -1) {
					DatabaseHandler.updateAcademicYear(selectedAcademicYear, currentFacultyID);
					DatabaseHandler.updateSemester(selectedSemester, currentFacultyID);
			///////////////////////////////////////////////////////////////////////////////////////////
	// NEED TO IMPLEMENT A METHOD TO UPDATE listFaculty labels when choosing year level and sem in cboxes //
			//////////////////////////////////////////////////////////////////////////////////////////
					frame.setVisible(false);
				}		
			}
		});
		
		backBtn.setBackground(SystemColor.text);
		//backBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
		backBtn.setFont(new Font("Arial", Font.BOLD, 20));
		backBtn.setBounds(775, 20, 125, 35);
		Footer.add(backBtn);

		JDialog addDial0g = new JDialog();
		addDial0g.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel addPanel = new JPanel();
		addPanel.setBounds(0, 0, 300, 150);
		addPanel.setLayout(null);
		
		//Responsible for making the window open on the center of the screen on start up
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = frame.getSize().width;
		int h = frame.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		frame.setLocation(x,y);
		
	}

	public void fetchAndDisplaySubjects() {
		// Fetch subjects associated with the current faculty from the database
		int facultyID = DatabaseHandler.getFacultyID(facultyName.getText());
		List<SubjectData> subjects = DatabaseHandler.getSubjectsByFaculty(facultyID);

		// Iterate through the subjects and add them to the Body panel
		for (SubjectData subjectData : subjects) {
			subject sub = new subject();
			sub.subjectLbl.setText(subjectData.getSubjectName());
			sub.semesterLbl.setText((String) semesterCB.getSelectedItem());
			sub.academicYearLbl.setText((String) acadYearCB.getSelectedItem());

			Body.add(sub);
			
			//new add section button on Subjects
			JButton addSection = sub.addBtn;
			addSection.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mousePressed(MouseEvent e) 
				{
					addSection addSection = new addSection();
					addSection.show();
					DatabaseHandler dbH = new DatabaseHandler();
					int subjectID =  DatabaseHandler.getSubjectID(subjectData.getSubjectName());
					System.out.println(subjectData.getSubjectName());
					// Fetch sections associated with the current faculty and subject
					List<String> sections = dbH.getSectionsByFacultyAndSubject(facultyID, subjectID);
					

					// Iterate through sections and display them
					for (String sectionb : sections) {
						sections secb = new sections();
						secb.sectionLbl.setText(sectionb);

						System.out.println(sectionb);

						// Add the section to the Body panel
						addSection.Body.add(secb);
						addSection.Body.revalidate();
						addSection.Body.repaint();

						secb.deleteBtn.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								// Get the sectionID associated with the section label
								int sectionID = DatabaseHandler.getSectionID(sectionb);
					
								// Delete entry in faculty_subject_section table
								DatabaseHandler.deleteFacultySubjectSectionBySectionID(sectionID);
					
								// Remove the section from the Body panel
								addSection.Body.remove(secb);
								addSection.revalidate();
								addSection.repaint();
							}
						});
					}
					
					addSection.addBtn.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
						{
							addSectionDialog addSectionDialog = new addSectionDialog();
							addSectionDialog.show();
							
							addSectionDialog.addDialogBtn.addActionListener(new ActionListener() 
							{
								public void actionPerformed(ActionEvent e) 
								{
									sections sec = new sections();
									String section = addSectionDialog.sectionTF.getText();
									
									// Add section to the database
									int sectionID = DatabaseHandler.addSection(section);
									DatabaseHandler.associateSectionWithFacultySubject(facultyID, DatabaseHandler.getSubjectID(subjectData.getSubjectName()), sectionID);
									
									if(section.isEmpty()) 
									{
										JOptionPane.showMessageDialog(Body, "invalid Input!", "Error", JOptionPane.INFORMATION_MESSAGE); //edit the frame
									}
									else 
									{
										int subjectID =  DatabaseHandler.getSubjectID(subjectData.getSubjectName());
										int facultyID = DatabaseHandler.getFacultyID(facultyName.getText());
										DatabaseHandler.associateFacultyWithSubject(facultyID, subjectID);
										
										DatabaseHandler dbH = new DatabaseHandler();
										// Fetch sections associated with the current faculty and subject
										List<String> sections = dbH.getSectionsByFacultyAndSubject(facultyID, subjectID);
										sections secb = new sections();
										// Iterate through sections and display them
										for (String sectionb : sections) {
											secb.sectionLbl.setText(sectionb);
										}
										// Add the section to the Body panel
										addSection.Body.add(secb);
										addSection.Body.revalidate();
										addSection.Body.repaint();
										
										if (addSection.Body.getComponentCount() > 10) 
										{ 
											// Increase the preferred height of the rowPanel
											Dimension preferredSize = addSection.Body.getPreferredSize();
											preferredSize.height += 40;
											addSection.Body.setLayout(new GridLayout(addSection.Body.getComponentCount(), 1));
											addSection.Body.setPreferredSize(preferredSize);
											addSection.Body.revalidate();
										}

										addSection.Body.revalidate();
										addSectionDialog.dispose();
									}	
								}
							});
						

						}
					});
				}
			});
		
			
			//new edit button on Subjects
			JButton editSection = sub.editBtn;
			editSection.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mousePressed(MouseEvent e) 
				{
					editDialog edit = new editDialog();
					addSubjectDialog editSubject = new addSubjectDialog();
					DatabaseHandler dbH = new DatabaseHandler();
					int subjectID =  DatabaseHandler.getSubjectID(subjectData.getSubjectName());
					List<String> sections = dbH.getSectionsByFacultyAndSubject(facultyID, subjectID);
					

					// Iterate through sections and display them
					for (String sectionb : sections) {
						sections secb = new sections();
						secb.sectionLbl.setText(sectionb);

						System.out.println(sectionb);

						// Add the section to the Body panel
						editSection.add(secb);
						editSection.revalidate();
						editSection.repaint();
					}
					edit.show();
					
					edit.editSubject.addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mousePressed(MouseEvent e) 
						{
							editSubject.addLbl.setText("Edit Subject");
							editSubject.addBtn.setText("Done");
							editSubject.show();
							
							editSubject.addBtn.addMouseListener(new MouseAdapter() 
							{
								public void mousePressed(MouseEvent e) 
								{
									sub.subjectLbl.setText(editSubject.codeTF.getText() + " - " + editSubject.decriptionTF.getText());
									edit.SubjectLbl.setText(editSubject.codeTF.getText() + " - " + editSubject.decriptionTF.getText());
									editSubject.dispose();
								}
							});
						
						}
					});
							
					edit.addSection.addMouseListener(new MouseAdapter() 
					{
						public void mousePressed(MouseEvent e) 
						{
							addSectionDialog addSectionDialog = new addSectionDialog();
							addSectionDialog.show();
							System.out.println("BPRESSED");
							
							addSectionDialog.addDialogBtn.addActionListener(new ActionListener() 
							{
								public void actionPerformed(ActionEvent e) 
								{
									sections sec = new sections();
									System.out.println("BPRESSEDs");
									String section = addSectionDialog.sectionTF.getText();

									// Add section to the database
									int sectionID = DatabaseHandler.addSection(section);
									
									sec.deleteBtn.addMouseListener(new MouseAdapter() 
									{
										public void mousePressed(MouseEvent e) 
										{
											System.out.println("BPRESSED3e");
											edit.sectionPanel.remove(sec);
											edit.revalidate();
										}
									});
									
									if(section.isEmpty()) 
									{
										JOptionPane.showMessageDialog(Body, "invalid Code or Description", "Error", JOptionPane.INFORMATION_MESSAGE);
									}
									else 
									{
										
										edit.sectionPanel.add(sec);
										sec.sectionLbl.setText(section);
										currentRow++;
										
										if (edit.sectionPanel.getComponentCount() > 10) 
										{ 
											// Increase the preferred height of the rowPanel
											Dimension preferredSize = edit.sectionPanel.getPreferredSize();
											preferredSize.height += 35;
											edit.sectionPanel.setLayout(new GridLayout(edit.sectionPanel.getComponentCount(), 1));
											edit.sectionPanel.setPreferredSize(preferredSize);
											edit.sectionPanel.revalidate();
										}
										
										sec.deleteBtn.addMouseListener(new MouseAdapter() 
										{
											public void mousePressed(MouseEvent e) 
											{
												System.out.println("BUTTON PRESSED");
												edit.sectionPanel.remove(sec);
												edit.revalidate();
												edit.repaint();
											}
										});
										
										edit.sectionPanel.revalidate();
										addSectionDialog.dispose();
									}
								}
							});
						}
					});
					
					sub.revalidate();
					edit.show();
				}
			});
			
			//new delete button on Subjects										
			JButton deleteSection = sub.deleteBtn;
			deleteSection.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mousePressed(MouseEvent e) 
				{
					System.out.println("Delete pressed");
					Body.remove(sub);
					Body.revalidate();
					Body.repaint();
				}
			});
		
		// Revalidate and repaint the Body panel
		Body.revalidate();
		Body.repaint();
	}
	}
	class JPanelGradient extends JPanel{
		protected void paintComponent(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			int width = getWidth();
			int height = getHeight();


			Color C1 = new Color(255, 198, 43);
			Color C2 = new Color(255, 77, 41);
			GradientPaint gp = new GradientPaint(75,0,C1,180,height,C2);
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, width, height);
			
		}
	}
}

