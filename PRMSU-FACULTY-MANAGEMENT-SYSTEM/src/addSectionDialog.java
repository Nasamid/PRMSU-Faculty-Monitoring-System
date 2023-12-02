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

public class addSectionDialog extends JDialog {

	JPanel contentPanel = new JPanel();
	addPreparation prep = new addPreparation();
	JTextField sectionTF;
	JButton addDialogBtn;
	JLabel addLbl;
	private JPanel panel;
	
	

	public static void main(String[] args) {
		try {
			addSectionDialog dialog = new addSectionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public addSectionDialog() {

        // Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = 400;
        int frameHeight = 175;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        setBounds(x, y, frameWidth, frameHeight);
        
		contentPanel.setBounds(0, 0, 400, 100);
		setTitle("Add Section");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		
		JLabel sectionLbl = new JLabel("Section :");
		sectionLbl.setFont(new Font("Arial", Font.BOLD, 12));
		sectionLbl.setBounds(15, 50, 102, 20);
		contentPanel.add(sectionLbl);
		
		sectionTF = new JTextField();
		sectionTF.setBorder(new LineBorder(SystemColor.textText, 1, true));
		sectionTF.setFont(new Font("Arial", Font.PLAIN, 10));
		sectionTF.setBounds(125, 50, 225, 20);
		contentPanel.add(sectionTF);
		sectionTF.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 386, 30);
		contentPanel.add(panel);
		
		addLbl = new JLabel("Add Section");
		panel.add(addLbl);
		addLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addLbl.setBackground(SystemColor.textHighlight);
		addLbl.setFont(new Font("Arial", Font.BOLD, 17));
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBounds(0, 100, 386, 40);
			buttonPane.setBackground(SystemColor.textHighlight);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				addDialogBtn = new JButton("Add");
				addDialogBtn.setBackground(SystemColor.text);
				addDialogBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				addDialogBtn.setFont(new Font("Arial", Font.BOLD, 12));
				addDialogBtn.setBounds(115, 10, 75, 20);
				addDialogBtn.setActionCommand("OK");
				buttonPane.add(addDialogBtn);
				getRootPane().setDefaultButton(addDialogBtn);
			}
			{
				JButton cancelDialogBtn = new JButton("Cancel");
				cancelDialogBtn.setBackground(SystemColor.text);
				cancelDialogBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelDialogBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				cancelDialogBtn.setFont(new Font("Arial", Font.BOLD, 12));
				cancelDialogBtn.setBounds(215, 10, 75, 20);
				cancelDialogBtn.setActionCommand("Cancel");
				buttonPane.add(cancelDialogBtn);
			}
		}
	}
}
