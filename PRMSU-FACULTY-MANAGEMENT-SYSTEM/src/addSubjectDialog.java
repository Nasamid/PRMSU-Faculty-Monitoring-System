import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addSubjectDialog extends JDialog {

	JPanel contentPanel = new JPanel();
	addPreparation prep = new addPreparation();
	JTextField codeTF, decriptionTF;
	JButton addBtn;
	JLabel addLbl;
	private JPanel panel;

	public addSubjectDialog() {
		
		// Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = 400;
        int frameHeight = 230;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, frameWidth, frameHeight);
        
		contentPanel.setBounds(0, 0, 386, 156);
		setTitle("Add Subject");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		
		JLabel codeLbl = new JLabel("Course Code :");
		codeLbl.setFont(new Font("Arial", Font.BOLD, 12));
		codeLbl.setBounds(15, 60, 120, 20);
		contentPanel.add(codeLbl);
		
		JLabel descriptionLbl = new JLabel("Course Description  :");
		descriptionLbl.setFont(new Font("Arial", Font.BOLD, 12));
		descriptionLbl.setBounds(15, 100, 120, 20);
		contentPanel.add(descriptionLbl);
		
		codeTF = new JTextField();
		codeTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		codeTF.setBackground(SystemColor.text);
		codeTF.setFont(new Font("Arial", Font.PLAIN, 10));
		codeTF.setBounds(145, 60, 225, 20);
		contentPanel.add(codeTF);
		codeTF.setColumns(10);
		
		decriptionTF = new JTextField();
		decriptionTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		decriptionTF.setBackground(SystemColor.text);
		decriptionTF.setFont(new Font("Arial", Font.PLAIN, 10));
		decriptionTF.setColumns(10);
		decriptionTF.setBounds(145, 100, 225, 20);
		contentPanel.add(decriptionTF);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 386, 35);
		contentPanel.add(panel);
		
		addLbl = new JLabel("Add Subject");
		panel.add(addLbl);
		addLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addLbl.setBackground(SystemColor.textHighlight);
		addLbl.setFont(new Font("Arial", Font.BOLD, 17));
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBounds(0, 155, 386, 38);
			buttonPane.setBackground(SystemColor.textHighlight);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				addBtn = new JButton("Add");
				addBtn.setBackground(SystemColor.text);
				addBtn.setBorder(null);
				addBtn.setFont(new Font("Arial", Font.BOLD, 12));
				addBtn.setBounds(115, 10, 75, 20);
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
				cancelDialogBtn.setBorder(null);
				cancelDialogBtn.setFont(new Font("Arial", Font.BOLD, 12));
				cancelDialogBtn.setBounds(215, 10, 75, 20);
				cancelDialogBtn.setActionCommand("Cancel");
				buttonPane.add(cancelDialogBtn);
			}
		}
	}
}
