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
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class addSectionDialog extends JDialog 
{

	JPanel contentPanel = new JPanel();
	addPreparation prep = new addPreparation();
	JTextField sectionTF;
	JButton addDialogBtn;
	JLabel addLbl;
	private JPanel panel;

	public static void main(String[] args) 
	{
		FlatMacLightLaf.setup();
		try 
		{
			addSectionDialog dialog = new addSectionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public addSectionDialog() 
	{
		addPreparation addPreparation = new addPreparation();
		addPreparation.fetchAndDisplaySubjects();
		
		setResizable(false);
        // Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = 500;
        int frameHeight = 220;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        setBounds(x, y, frameWidth, frameHeight);

		Image logo;
            try {
                logo = ImageIO.read(addSectionDialog.class.getResourceAsStream("/Images/addsubjecticon16x.png"));
                this.setIconImage(logo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
		contentPanel.setBounds(0, 0, 500, 140);
		setTitle("Add Section");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		//contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		sectionTF = new JTextField();
		sectionTF.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 41), 3, true), "Course Description:", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 15), new Color(0, 0, 0)));
		//sectionTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		sectionTF.setFont(new Font("Arial", Font.PLAIN, 15));
		sectionTF.setBounds(50, 60, 400, 55);
		contentPanel.add(sectionTF);
		sectionTF.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 128, 41));
		panel.setBounds(0, 0, 500, 30);
		contentPanel.add(panel);
		
		addLbl = new JLabel("Add Section");
		panel.add(addLbl);
		addLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addLbl.setBackground(SystemColor.textHighlight);
		addLbl.setFont(new Font("Arial", Font.BOLD, 20));
		
		
			JPanel buttonPane = new JPanel();
			//buttonPane.setBorder(null);
			buttonPane.setBounds(0, 140, 500, 50);
			buttonPane.setBackground(new Color(255, 128, 41));
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			
				addDialogBtn = new JButton("Add");
				addDialogBtn.setBackground(SystemColor.text);
				addDialogBtn.setForeground(Color.black);
				addDialogBtn.setFont(new Font("Arial", Font.BOLD, 15));
				addDialogBtn.setBounds(140, 10, 100, 30);
				addDialogBtn.setActionCommand("OK");
				buttonPane.add(addDialogBtn);
				getRootPane().setDefaultButton(addDialogBtn);
			
			
				JButton cancelDialogBtn = new JButton("Cancel");
				cancelDialogBtn.setBackground(SystemColor.text);
				cancelDialogBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				//cancelDialogBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				cancelDialogBtn.setFont(new Font("Arial", Font.BOLD, 15));
				cancelDialogBtn.setBounds(260, 10, 100, 30);
				cancelDialogBtn.setActionCommand("Cancel");
				buttonPane.add(cancelDialogBtn);
			
	}
}
