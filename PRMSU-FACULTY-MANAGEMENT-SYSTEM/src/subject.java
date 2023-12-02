import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class subject extends JPanel {
	JLabel subjectLbl;
	JButton addBtn, editBtn, deleteBtn;
	
	public subject() {
		setBorder(null);
		setPreferredSize(new Dimension(950, 45));
		setBackground(Color.BLACK);
		setBackground(SystemColor.text);
		setLayout(null);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 5, 920, 35);
		add(panel);
		panel.setLayout(null);
		
		subjectLbl = new JLabel("");
		subjectLbl.setBounds(15, 0, 600, 35);
		panel.add(subjectLbl);
		subjectLbl.setLabelFor(subjectLbl);
		subjectLbl.setBorder(null);
		subjectLbl.setFont(new Font("Arial", Font.BOLD, 15));
		
		addBtn = new JButton("");
		addBtn.setIcon(new ImageIcon(subject.class.getResource("/Images/AddSection25x.png")));
		addBtn.setBorder(null);
		addBtn.setBackground(SystemColor.text);
		addBtn.setFont(new Font("Arial", Font.BOLD, 10));
		addBtn.setBounds(730, 3, 35, 30);
		panel.add(addBtn);
		
		editBtn = new JButton("");
		editBtn.setBorder(null);
		editBtn.setIcon(new ImageIcon(subject.class.getResource("/Images/Edit25x.png")));
		editBtn.setBackground(SystemColor.text);
		editBtn.setFont(new Font("Arial", Font.BOLD, 10));
		editBtn.setBounds(780, 3, 35, 30);
		panel.add(editBtn);
		
		deleteBtn = new JButton("");
		deleteBtn.setIcon(new ImageIcon(subject.class.getResource("/Images/Delete25x.png")));
		deleteBtn.setBorder(null);
		deleteBtn.setBackground(SystemColor.text);
		deleteBtn.setFont(new Font("Arial", Font.BOLD, 10));
		deleteBtn.setBounds(830, 3, 35, 30);
		panel.add(deleteBtn);
	}
	
}
