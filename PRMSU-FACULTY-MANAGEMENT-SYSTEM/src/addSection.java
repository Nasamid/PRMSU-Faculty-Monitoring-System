import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.w3c.dom.events.MouseEvent;

public class addSection extends JDialog 
{
	
	JButton addBtn;
	JPanel Body;
	addPreparation prep = new addPreparation();
	int currentRow;

	public addSection() 
	{
		prep.fetchAndDisplaySubjects();
		getContentPane().setBackground(SystemColor.text);
		setResizable(false);
		
		// Set the dimensions and location of the JFrame to center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = 610;
        int frameHeight = 480;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

		Image logo;
            try {
                logo = ImageIO.read(addSection.class.getResourceAsStream("/Images/addsubjecticon16x.png"));
                this.setIconImage(logo);
            } catch (IOException e) {
                e.printStackTrace();
            }

        setBounds(x, y, frameWidth, frameHeight);
		setTitle("Add Subject");
		getContentPane().setLayout(null);

		{
			JPanel HeaderPane = new JPanel();
			HeaderPane.setLayout(null);
			HeaderPane.setBorder(null);
			HeaderPane.setBackground(new Color(255, 128, 41));
			HeaderPane.setBounds(0, 0, 600, 50);
			getContentPane().add(HeaderPane);
			{
				JLabel addLbl = new JLabel("Add Section");
				addLbl.setHorizontalAlignment(SwingConstants.CENTER);
				addLbl.setFont(new Font("Arial", Font.BOLD, 25));
				addLbl.setBackground(SystemColor.textHighlight);
				addLbl.setBounds(0, 15, 600, 20);
				HeaderPane.add(addLbl);
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBounds(0, 395, 600, 50);
			buttonPane.setBackground(new Color(255, 128, 41));
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				addBtn = new JButton("Add");
				addBtn.setBackground(SystemColor.text);
				addBtn.setForeground(Color.BLACK);
				//addBtn.setBorder(new LineBorder(SystemColor.textText, 1, true));
				addBtn.setFont(new Font("Arial", Font.BOLD, 12));
				addBtn.setBounds(190, 10, 100, 30);
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
				//cancelButton.setBorder(new LineBorder(SystemColor.textText, 1, true));
				cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
				cancelButton.setBounds(310, 10, 100, 30);	
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBounds(0, 45, 615, 350);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		getContentPane().add(scrollPane);

		GridLayout Layout = new GridLayout(10, 1);
    Layout.setVgap(0);
		
		Body = new JPanel();
		Body.setBorder(null);
		Body.setBackground(SystemColor.text);
		Body.setLayout(Layout);
		scrollPane.setViewportView(Body);
		}
	}

}
