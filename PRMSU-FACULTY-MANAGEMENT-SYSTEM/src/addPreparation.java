import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class addPreparation extends JPanel {
	private JTextField facultyName;
	private JButton addSubjectBtn;
	JPanel Body;
	int currentRow = 0;
	
	public addPreparation() {
		
		setBackground(SystemColor.text);
		setFont(new Font("Arial", Font.BOLD, 15));
		setBounds(180,0,1000, 720);
		setLayout(null);
		
		JPanel Header = new JPanel();
		Header.setBackground(SystemColor.textHighlight);
		add(Header);
		Header.setBounds(0,0,1000, 110);
		Header.setLayout(null);
		
		JLabel panelLbl = new JLabel("Preparation");
		panelLbl.setFont(new Font("Arial", Font.BOLD, 20));
		panelLbl.setBounds(10, 10, 159, 25);
		Header.add(panelLbl);
		
		facultyName = new JTextField();
		facultyName.setText("Danilo Llaga Jr.");
		facultyName.setBackground(SystemColor.text);
		facultyName.setHorizontalAlignment(SwingConstants.CENTER);
		facultyName.setEditable(false);
		facultyName.setBorder(new LineBorder(SystemColor.textText, 1, true));
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
		
		JComboBox acadYearCB = new JComboBox();
		acadYearCB.setFont(new Font("Arial", Font.BOLD, 13));
		acadYearCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		acadYearCB.setBackground(SystemColor.text);
		acadYearCB.setBounds(540, 55, 150, 25);
		Header.add(acadYearCB);
		
		JPanel Footer = new JPanel();
		Footer.setBackground(SystemColor.textHighlight);
		Footer.setBounds(0, 610, 1000, 110);
		add(Footer);
		Footer.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(SystemColor.text);
		scrollPane.setBounds(0, 125, 1000, 450);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(scrollPane);
		
		Body = new JPanel();
		Body.setBorder(null);
		Body.setBackground(SystemColor.text);
		Body.setLayout(new GridLayout(10,1));
		scrollPane.setViewportView(Body);
		
		/* 
		 * Adding functions to the buttons using concatination
		*/
		addSubjectBtn = new JButton("Add Subject");
		addSubjectBtn.setBackground(SystemColor.text);
		addSubjectBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
		addSubjectBtn.setFont(new Font("Arial", Font.BOLD, 20));
		addSubjectBtn.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
			subject sub = new subject();
			addSubjectDialog add = new addSubjectDialog();
			add.show();
			add.addBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String code = add.codeTF.getText();
					String description = add.decriptionTF.getText();
					if(code.isEmpty() || description.isEmpty()) {
						JOptionPane.showMessageDialog(Body, "invalid Code or Description", "Error", JOptionPane.INFORMATION_MESSAGE); //edit frame
					}else {
						sub.subjectLbl.setText(code + " - " + description);
						Body.add(sub);
						currentRow++;
						if (Body.getComponentCount() > 10) { 
							// Increase the preferred height of the rowPanel
							Dimension preferredSize = Body.getPreferredSize();
							preferredSize.height += 50;
							Body.setLayout(new GridLayout(currentRow, 1));
							Body.setPreferredSize(preferredSize);
							Body.revalidate();
						}
						Body.revalidate();
						add.dispose();
					}
				}
			});
				
		//new add section button on Subjects
		JButton addSection = sub.addBtn;
		addSection.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				addSection addSection = new addSection();
				addSection.show();
				
				addSection.addBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						addSectionDialog addSectionDialog = new addSectionDialog();
						addSectionDialog.show();
						
						addSectionDialog.addDialogBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								sections sec = new sections();
								String section = addSectionDialog.sectionTF.getText();
								
								if(section.isEmpty()) {
									JOptionPane.showMessageDialog(Body, "invalid Code or Description", "Error", JOptionPane.INFORMATION_MESSAGE); //edit the frame
								}else {
									
									addSection.Body.add(sec);
									sec.sectionLbl.setText(section);
									currentRow++;
									if (addSection.Body.getComponentCount() > 10) { 
										// Increase the preferred height of the rowPanel
										Dimension preferredSize = addSection.Body.getPreferredSize();
										preferredSize.height += 30;
										addSection.Body.setLayout(new GridLayout(currentRow, 1));
										addSection.Body.setPreferredSize(preferredSize);
										addSection.Body.revalidate();
									}
									
									sec.deleteBtn.addMouseListener(new MouseAdapter() {
										public void mousePressed(MouseEvent e) {
											addSection.Body.remove(sec);
											addSection.revalidate();
											addSection.repaint();
										}
									});
									
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
		editSection.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				editDialog edit = new editDialog();
				addSubjectDialog editSubject = new addSubjectDialog();
				edit.SubjectLbl.setText(sub.subjectLbl.getText());
				edit.show();
				
				edit.editSubject.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						
						editSubject.addLbl.setText("Edit Subject");
						editSubject.addBtn.setText("Done");
						editSubject.show();
						
						editSubject.addBtn.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								sub.subjectLbl.setText(editSubject.codeTF.getText() + " - " + editSubject.decriptionTF.getText());
								edit.SubjectLbl.setText(editSubject.codeTF.getText() + " - " + editSubject.decriptionTF.getText());
								editSubject.dispose();
							}
						});
					
					}
				});
						
				edit.addSection.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						addSectionDialog addSectionDialog = new addSectionDialog();
						addSectionDialog.show();
						
						addSectionDialog.addDialogBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								sections sec = new sections();
								String section = addSectionDialog.sectionTF.getText();
								
								sec.deleteBtn.addMouseListener(new MouseAdapter() {
									public void mousePressed(MouseEvent e) {
										edit.sectionPanel.remove(sec);
										edit.revalidate();
									}
								});
								
								if(section.isEmpty()) {
									JOptionPane.showMessageDialog(Body, "invalid Code or Description", "Error", JOptionPane.INFORMATION_MESSAGE); //edit the frame
								}else {
									
									edit.sectionPanel.add(sec);
									sec.sectionLbl.setText(section);
									currentRow++;
									if (edit.sectionPanel.getComponentCount() > 8) { 
										// Increase the preferred height of the rowPanel
										Dimension preferredSize = edit.sectionPanel.getPreferredSize();
										preferredSize.height += 35;
										edit.sectionPanel.setLayout(new GridLayout(currentRow, 1));
										edit.sectionPanel.setPreferredSize(preferredSize);
										edit.sectionPanel.revalidate();
									}
									
									sec.deleteBtn.addMouseListener(new MouseAdapter() {
										public void mousePressed(MouseEvent e) {
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
				deleteSection.addMouseListener(new MouseAdapter() {
					@Override
						public void mousePressed(MouseEvent e) {
						Body.remove(sub);
						revalidate();
						repaint();
						}
				});
			}
		});
		addSubjectBtn.setBounds(120, 20, 150, 35);
		Footer.add(addSubjectBtn);
		
		JButton backBtn = new JButton("Back");
		backBtn.setBackground(SystemColor.text);
		backBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
		backBtn.setFont(new Font("Arial", Font.BOLD, 20));
		backBtn.setBounds(775, 20, 125, 35);
		Footer.add(backBtn);
		
		JComboBox<String> semesterCB = new JComboBox<>();
		String[] semester = {
	            "First Semester", "Second Semester", "Mid Year"
	        };
		for (String sem : semester) {
		semesterCB.addItem(sem);
		semesterCB.setFont(new Font("Arial", Font.BOLD, 13));
		semesterCB.setBorder(new LineBorder(SystemColor.textText, 1, true));
		semesterCB.setBounds(825, 55, 140, 25);
		Header.add(semesterCB);
		}
		
		//sets the ComboBox to current School Year
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int year = currentYear - 3; year <= currentYear + 3; year++) {
			acadYearCB.addItem(String.valueOf(year) + " - " + String.valueOf(year+1));
		}
		acadYearCB.setSelectedItem(String.valueOf(currentYear) + " - " + (currentYear + 1));
	
		JDialog addDial0g = new JDialog();
		addDial0g.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel addPanel = new JPanel();
		addPanel.setBounds(0, 0, 300, 150);
		addPanel.setLayout(null);
		
	}
}

