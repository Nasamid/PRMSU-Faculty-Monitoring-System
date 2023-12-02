import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class sections extends JPanel {
	JLabel sectionLbl;
	JButton deleteBtn;
	
	public sections() {
		setBorder(null);
		setPreferredSize(new Dimension(400, 35));
		setBackground(Color.BLACK);
		setBackground(SystemColor.text);
		setLayout(null);
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 5, 350, 25);
		add(panel_1);
		panel_1.setLayout(null);
		
		sectionLbl = new JLabel("");
		sectionLbl.setBounds(15, 0, 247, 25);
		panel_1.add(sectionLbl);
		sectionLbl.setLabelFor(sectionLbl);
		sectionLbl.setBorder(null);
		sectionLbl.setFont(new Font("Arial", Font.BOLD, 10));
		
		deleteBtn = new JButton("");
		deleteBtn.setIcon(new ImageIcon(sections.class.getResource("/Images/Delete16x.png")));
		deleteBtn.setBorder(null);
		deleteBtn.setBackground(SystemColor.text);
		deleteBtn.setFont(new Font("Arial", Font.BOLD, 10));
		deleteBtn.setBounds(310, 3, 30, 20);
		panel_1.add(deleteBtn);
	}
	
}
