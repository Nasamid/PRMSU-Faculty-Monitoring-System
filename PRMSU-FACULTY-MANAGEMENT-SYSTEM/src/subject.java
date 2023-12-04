import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class subject extends JPanel 
{
	JLabel subjectLbl, academicYearLbl, semesterLbl;
	JButton addBtn, editBtn, deleteBtn;
	private JSeparator separator;
	
	public subject() 
	{
		setBorder(null);
		setPreferredSize(new Dimension(950, 45));
		setBackground(Color.BLACK);
		setBackground(SystemColor.text);
		setLayout(null);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 5, 920, 35);
		add(panel);
		panel.setLayout(null);
		
		subjectLbl = new JLabel("Software Design");
		subjectLbl.setBounds(10, 0, 380, 35);
		panel.add(subjectLbl);
		subjectLbl.setLabelFor(subjectLbl);
		subjectLbl.setBorder(null);
		subjectLbl.setFont(new Font("Arial", Font.BOLD, 15));
		
		addBtn = new JButton("");
		addBtn.setIcon(new ImageIcon(subject.class.getResource("/Images/AddSection25x.png")));
		addBtn.setBorder(null);
		addBtn.setBackground(SystemColor.text);
		addBtn.setFont(new Font("Arial", Font.BOLD, 10));
		addBtn.setBounds(800, 2, 35, 30);
		panel.add(addBtn);
		
		editBtn = new JButton("");
		editBtn.setBorder(null);
		editBtn.setIcon(new ImageIcon(subject.class.getResource("/Images/Edit25x.png")));
		editBtn.setBackground(SystemColor.text);
		editBtn.setFont(new Font("Arial", Font.BOLD, 10));
		editBtn.setBounds(840, 2, 35, 30);
		panel.add(editBtn);
		
		deleteBtn = new JButton("");
		deleteBtn.setIcon(new ImageIcon(subject.class.getResource("/Images/Delete25x.png")));
		deleteBtn.setBorder(null);
		deleteBtn.setBackground(SystemColor.text);
		deleteBtn.setFont(new Font("Arial", Font.BOLD, 10));
		deleteBtn.setBounds(880, 2, 35, 30);
		panel.add(deleteBtn);
		
		separator = new JSeparator();
		separator.setBounds(0, 34, 920, 1);
		panel.add(separator);
		
		semesterLbl = new JLabel("Semester");
		semesterLbl.setHorizontalAlignment(SwingConstants.CENTER);
		semesterLbl.setFont(new Font("Arial", Font.BOLD, 15));
		semesterLbl.setBorder(null);
		semesterLbl.setBounds(410, 0, 180, 35);
		panel.add(semesterLbl);
		
		academicYearLbl = new JLabel("Academic Year");
		academicYearLbl.setHorizontalAlignment(SwingConstants.CENTER);
		academicYearLbl.setFont(new Font("Arial", Font.BOLD, 15));
		academicYearLbl.setBorder(null);
		academicYearLbl.setBounds(610, 0, 150, 35);
		panel.add(academicYearLbl);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(400, 3, 1, 29);
		panel.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setBounds(600, 3, 1, 29);
		panel.add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setOrientation(SwingConstants.VERTICAL);
		separator4.setBounds(770, 3, 1, 29);
		panel.add(separator4);
	}
}
