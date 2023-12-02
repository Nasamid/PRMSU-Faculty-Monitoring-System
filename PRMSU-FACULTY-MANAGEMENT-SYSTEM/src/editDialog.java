import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;

public class editDialog extends JDialog {

	JPanel contentPanel = new JPanel();
	addPreparation prep = new addPreparation();
	JTextField SubjectLbl;
	JButton doneBtn, editSubject, addSection;
	JLabel addLbl;
	JPanel headerPanel, sectionPanel;
	private JScrollPane scrollPane;
	
	
	public static void main(String[] args) {
		try {
			editDialog dialog = new editDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public editDialog() {
		setUndecorated(true);
		
		// Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = 400;
        int frameHeight = 450;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, 400, 415);
        
		contentPanel.setBounds(0, 0, 400, 450);
		setTitle("Add Subject");
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.text);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		SubjectLbl = new JTextField();
		SubjectLbl.setBorder(null);
		SubjectLbl.setBackground(SystemColor.text);
		SubjectLbl.setEditable(false);
		SubjectLbl.setFont(new Font("Arial", Font.PLAIN, 13));
		SubjectLbl.setBounds(30, 52, 250, 25);
		contentPanel.add(SubjectLbl);
		SubjectLbl.setColumns(10);
		
		headerPanel = new JPanel();
		headerPanel.setBackground(SystemColor.textHighlight);
		headerPanel.setBounds(0, 0, 400, 35);
		contentPanel.add(headerPanel);
		
		addLbl = new JLabel("Edit Subject");
		headerPanel.add(addLbl);
		addLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addLbl.setBackground(SystemColor.textHighlight);
		addLbl.setFont(new Font("Arial", Font.BOLD, 17));
		
		editSubject = new JButton("");
		editSubject.setIcon(new ImageIcon(editDialog.class.getResource("/Images/Edit25x.png")));
		editSubject.setBackground(SystemColor.text);
		editSubject.setBorder(null);
		editSubject.setBounds(290, 50, 30, 30);
		contentPanel.add(editSubject);
		
		addSection = new JButton("");
		addSection.setIcon(new ImageIcon(editDialog.class.getResource("/Images/AddSection25x.png")));
		addSection.setBorder(null);
		addSection.setBackground(SystemColor.text);
		addSection.setBounds(328, 50, 30, 30);
		contentPanel.add(addSection);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 375, 400, 40);
			contentPanel.add(buttonPane);
			buttonPane.setBorder(null);
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setLayout(null);
			{
				doneBtn = new JButton("Done");
				doneBtn.setBackground(SystemColor.text);
				doneBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				doneBtn.setFont(new Font("Arial", Font.BOLD, 12));
				doneBtn.setBounds(110, 10, 75, 20);
				doneBtn.setActionCommand("OK");
				buttonPane.add(doneBtn);
				getRootPane().setDefaultButton(doneBtn);
			}
			{
				JButton cancelEdit = new JButton("Cancel");
				cancelEdit.setBackground(SystemColor.text);
				cancelEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelEdit.setBorder(new LineBorder(SystemColor.textText, 1, true));
				cancelEdit.setFont(new Font("Arial", Font.BOLD, 12));
				cancelEdit.setBounds(210, 10, 75, 20);
				cancelEdit.setActionCommand("Cancel");
				buttonPane.add(cancelEdit);
			}
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(SystemColor.text);
		scrollPane.setBorder(null);
		scrollPane.setBounds(0, 95, 400, 280);
		contentPanel.add(scrollPane);
		
		sectionPanel = new JPanel();
		scrollPane.setViewportView(sectionPanel);
		sectionPanel.setBackground(SystemColor.text);
		sectionPanel.setLayout(new GridLayout(8, 1));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 90, 350, 2);
		contentPanel.add(separator);
	}
}
