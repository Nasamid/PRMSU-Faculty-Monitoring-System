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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class addSection extends JDialog {
	
	JButton addBtn;
	JPanel Body;
	addPreparation prep = new addPreparation();
	int currentRow;

	public addSection() {
		getContentPane().setBackground(SystemColor.text);
		
		// Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = 400;
        int frameHeight = 500;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        setBounds(x, y, frameWidth, frameHeight);
		setTitle("Add Subject");
		getContentPane().setLayout(null);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBounds(0, 425, 400, 38);
			buttonPane.setBackground(SystemColor.textHighlight);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				addBtn = new JButton("Add");
				addBtn.setBackground(SystemColor.text);
				addBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				addBtn.setFont(new Font("Arial", Font.BOLD, 12));
				addBtn.setBounds(115, 10, 75, 20);
				addBtn.setActionCommand("OK");
				buttonPane.add(addBtn);
				getRootPane().setDefaultButton(addBtn);
			}
			{
				JButton cancelButton = new JButton("Back");
				cancelButton.setBackground(SystemColor.text);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBorder(new LineBorder(SystemColor.textText, 1, true));
				cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
				cancelButton.setBounds(215, 10, 75, 20);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel HeaderPane = new JPanel();
			HeaderPane.setLayout(null);
			HeaderPane.setBorder(null);
			HeaderPane.setBackground(SystemColor.textHighlight);
			HeaderPane.setBounds(0, 0, 386, 40);
			getContentPane().add(HeaderPane);
			{
				JLabel addLbl = new JLabel("Add Section");
				addLbl.setHorizontalAlignment(SwingConstants.CENTER);
				addLbl.setFont(new Font("Arial", Font.BOLD, 17));
				addLbl.setBackground(SystemColor.textHighlight);
				addLbl.setBounds(0, 10, 400, 20);
				HeaderPane.add(addLbl);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBounds(0, 50, 415, 375);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		getContentPane().add(scrollPane);
		
		Body = new JPanel();
		scrollPane.setViewportView(Body);
		Body.setBorder(null);
		Body.setBackground(SystemColor.text);
		Body.setLayout(new GridLayout(10, 1));
	}
}
