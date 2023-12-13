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
import javax.swing.border.MatteBorder;

public class sections extends JPanel 
{
	JLabel sectionLbl;
	JButton deleteBtn;
	
	public sections() 
	{
		setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(255, 77, 41)));
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
		sectionLbl.setBounds(15, 0, 490, 25);
		Panel.add(sectionLbl);
		sectionLbl.setLabelFor(sectionLbl);
		//sectionLbl.setBorder(null);
		sectionLbl.setFont(new Font("Arial", Font.BOLD, 15));
		
		deleteBtn = new JButton("");
		deleteBtn.setIcon(new ImageIcon(sections.class.getResource("/Images/Delete16x.png")));
		deleteBtn.setBorder(null);
		deleteBtn.setBackground(SystemColor.text);
		deleteBtn.setFont(new Font("Arial", Font.BOLD, 10));
		deleteBtn.setBounds(545, 0, 30, 20);
		Panel.add(deleteBtn);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 77, 41));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(530, 0, 2, 20);
		Panel.add(separator);
	}
}
