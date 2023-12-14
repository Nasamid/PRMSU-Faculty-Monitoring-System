import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

public class addSubjectDialog extends JDialog 
{

	JPanel contentPanel = new JPanel();
	addPreparation prep = new addPreparation();
	JTextField codeTF, decriptionTF;
	JButton addBtn;
	JLabel addLbl;
	JPanel panel;

	public addSubjectDialog() 
	{
		// Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = 500;
        int frameHeight = 295;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, frameWidth, frameHeight);
        setResizable(false);

		Image logo;
            try {
                logo = ImageIO.read(addSubjectDialog.class.getResourceAsStream("/Images/addsubjecticon16x.png"));
                this.setIconImage(logo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
		contentPanel.setBounds(0, 0, 500, 210);
		setTitle("Add Subject");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		//contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		codeTF = new JTextField();
		codeTF.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 41), 3, true), "Course Code:", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 15), new Color(0, 0, 0)));
		//codeTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		codeTF.setBackground(SystemColor.text);
		codeTF.setBounds(50, 60, 400, 55);
		contentPanel.add(codeTF);
		codeTF.setColumns(10);
		
		decriptionTF = new JTextField();
		decriptionTF.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 41), 3, true), "Course Description:", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 15), new Color(0, 0, 0)));
		//decriptionTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		decriptionTF.setBackground(SystemColor.text);
		decriptionTF.setFont(new Font("Arial", Font.PLAIN, 15));
		decriptionTF.setColumns(10);
		decriptionTF.setBounds(50, 130, 400, 55);
		contentPanel.add(decriptionTF);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 128, 41));
		panel.setBounds(0, 0, 500, 50);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		addLbl = new JLabel("Add Subject");
		panel.add(addLbl);
		addLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addLbl.setBackground(SystemColor.textHighlight);
		addLbl.setFont(new Font("Arial", Font.BOLD, 25));
		
		{
			JPanel buttonPane = new JPanel();
			//buttonPane.setBorder(null);
			buttonPane.setBounds(0, 210, 500, 50);
			buttonPane.setBackground(new Color(255, 128, 41));
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				addBtn = new JButton("Add");
				addBtn.setBackground(SystemColor.text);
				addBtn.setForeground(Color.black);
				//addBtn.setBorder(null);
				addBtn.setFont(new Font("Arial", Font.BOLD, 12));
				addBtn.setBounds(140, 10, 100, 30);
				addBtn.setActionCommand("OK");
				buttonPane.add(addBtn);
				getRootPane().setDefaultButton(addBtn);
			}
			{
				JButton cancelDialogBtn = new JButton("Cancel");
				cancelDialogBtn.setBackground(SystemColor.text);
				cancelDialogBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				//cancelDialogBtn.setBorder(null);
				cancelDialogBtn.setFont(new Font("Arial", Font.BOLD, 12));
				cancelDialogBtn.setBounds(260, 10, 100, 30);
				cancelDialogBtn.setActionCommand("Cancel");
				buttonPane.add(cancelDialogBtn);
			}
		}
		
		prep.fetchAndDisplaySubjects();
	}
	
}
