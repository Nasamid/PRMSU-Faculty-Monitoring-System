
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

class Functions{
	
	// This function allows for the adding of logged staff to be added to the JTable as well as communicate
	// to the Database to record that the staff logged in
	public void LoginTableFunction(String dbID, String dbFirst, String dbLast, String dbEmail, JTable StaffTable, Connection con) {
		
		DefaultTableModel model = (DefaultTableModel) StaffTable.getModel();
		
		Date currentDate = new java.util.Date();
		Timestamp timeIN = new java.sql.Timestamp(currentDate.getTime());

		PreparedStatement pstIN1;
		PreparedStatement pstIN2;
		
		try {
			pstIN1 = con.prepareStatement("insert into timein(Staff_ID, First_Name, Last_Name, Email, Clock_IN) values(?,?,?,?,?)");
			pstIN1.setString(1, dbID);
			pstIN1.setString(2, dbFirst);
			pstIN1.setString(3, dbLast);
			pstIN1.setString(4, dbEmail);
			pstIN1.setTimestamp(5, timeIN);
		
			pstIN2 = con.prepareStatement("insert into timediff(StaffID, Clock_IN) values(?,?)");
			pstIN2.setString(1, dbID);
			pstIN2.setTimestamp(2, timeIN);
		
			int okay = pstIN1.executeUpdate();
			int okay2 = pstIN2.executeUpdate();
			
			if (okay==1 && okay2==1) {
				System.out.println("Time IN and Difference table updated!");
			}
		
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		Object[] rowData = {dbID, dbFirst, dbLast, dbEmail, timeIN};
		model.addRow(rowData); 
	}
	
	// This function allows for the adding of logged-out staff to be removed to the JTable as well as communicate
	// to the Database to record that the staff logged out
	public void LogoutTableFunction(int row, JTable StaffTable, Connection con) {
		
		DefaultTableModel model = (DefaultTableModel) StaffTable.getModel();
		
		Date currentDate = new java.util.Date();
		Timestamp timeIN = new java.sql.Timestamp(currentDate.getTime());
		
		PreparedStatement pstOUT1;
		PreparedStatement pstOUT2;
		PreparedStatement pstOUT3;
		
		int SelectedRowIndex = StaffTable.getSelectedRow();
		
		String OutID = StaffTable.getValueAt(SelectedRowIndex, 0).toString();
		String OutFirst = StaffTable.getValueAt(SelectedRowIndex, 1).toString();
		String OutLast = StaffTable.getValueAt(SelectedRowIndex, 2).toString();
		String OutEmail = StaffTable.getValueAt(SelectedRowIndex, 3).toString();
		
		try {
			pstOUT1 = con.prepareStatement("insert into timeout(Staff_ID, First_Name, Last_Name, Email, Clock_OUT) values(?,?,?,?,?)");
			pstOUT1.setString(1, OutID);
			pstOUT1.setString(2, OutFirst);
			pstOUT1.setString(3, OutLast);
			pstOUT1.setString(4, OutEmail);
			pstOUT1.setTimestamp(5, timeIN);
			
			pstOUT2 = con.prepareStatement("update timediff set Clock_OUT = ? where staffID = ? and Clock_OUT is null");
			pstOUT2.setTimestamp(1, timeIN);
			pstOUT2.setString(2, OutID);
			
			pstOUT3 = con.prepareStatement("UPDATE timediff SET Total_Hour = TIME_FORMAT(SEC_TO_TIME(TIMESTAMPDIFF(SECOND, Clock_IN, Clock_OUT)), '%H:%i');");
			
			int okay = pstOUT1.executeUpdate();
			int okay2 = pstOUT2.executeUpdate();
			int okay3 = pstOUT3.executeUpdate();
			
			if (okay==1 && okay2==1 && okay3==1) {
				System.out.println("Time OUT and Difference table updated!");
			}
			
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		
		
		model.removeRow(row);
	
	}
	
	// This function allows for the export of the timein and timeout logs table from the database into a local .csv file
	// The .csv file's name is named after the current date
	public void ExportFunction() {
		String jdbcUrl = "jdbc:mysql://localhost/stafflogs";
        String username = "root";
        String password = "password";
        
        String INtableName = "timein";
        String OUTtableName = "timeout";
        
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
		
        String INcsvFilePath = "C:/Users/danie/Desktop/Coding/Java Eclipse/CpE Engineering Staff Login System/Exports/TimeINLogs-" + formattedDate + ".csv";
        String OUTcsvFilePath = "C:/Users/danie/Desktop/Coding/Java Eclipse/CpE Engineering Staff Login System/Exports/TimeOUTLogs-" + formattedDate + ".csv";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT * FROM " + INtableName;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query);
                 FileWriter writer = new FileWriter(INcsvFilePath)) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Write column names to the CSV file
                for (int i = 1; i <= columnCount; i++) {
                    writer.append(metaData.getColumnName(i));
                    if (i != columnCount) {
                        writer.append(",");
                    }
                }
                writer.append("\n");

                // Write data rows to the CSV file
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        writer.append(resultSet.getString(i));
                        if (i != columnCount) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }

                System.out.println("Table exported successfully to " + INcsvFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            String query2 = "SELECT * FROM " + OUTtableName;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query2);
                 FileWriter writer = new FileWriter(OUTcsvFilePath)) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Write column names to the CSV file
                for (int i = 1; i <= columnCount; i++) {
                    writer.append(metaData.getColumnName(i));
                    if (i != columnCount) {
                        writer.append(",");
                    }
                }
                writer.append("\n");

                // Write data rows to the CSV file
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        writer.append(resultSet.getString(i));
                        if (i != columnCount) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }

                System.out.println("Table exported successfully to " + OUTcsvFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}

class Register extends JFrame{
	
	//Establishing connection with SQL server
	String sqlForName = "com.mysql.cj.jdbc.Driver";
	String sqlURL = "jdbc:mysql://localhost/Stafflogs";
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	//This connects the program to the MySQL database
	public void Connect() {
		try {
			Class.forName(sqlForName);
			con = DriverManager.getConnection(sqlURL, "root","password");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Just Declarations for the Registration Form
	JFrame RegisterFrame;
	JButton Submit;
	JLabel LStaffID, LFirstName, LLastName, LMiddleName, LPhone, LEmail, LPassword, LPicture;
	JTextField TFStaffID, TFFirstName, TFLastName, TFMiddleName, TFPhone, TFEmail, TFPassword;
	
	Register(){
		Connect();
		this.setLayout(null);
		
		//Just Instantiations of the Declared variables
		Submit = new JButton();
		
		LStaffID = new JLabel();
		LFirstName = new JLabel();
		LLastName = new JLabel();
		LMiddleName = new JLabel();
		LPhone = new JLabel();
		LEmail = new JLabel();
		LPassword = new JLabel();
		LPicture = new JLabel();
		
		TFStaffID = new JTextField();
		TFFirstName = new JTextField();
		TFLastName = new JTextField();
		TFMiddleName = new JTextField();
		TFPhone = new JTextField();
		TFEmail = new JTextField();
		TFPassword = new JTextField();
		
		LStaffID.setBounds(6, 0, 390, 25);
		LStaffID.setText("Staff ID:");
		LStaffID.setFocusable(false);
		LStaffID.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		TFStaffID.setBounds(6, 25, 390, 25);
		TFStaffID.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		LFirstName.setBounds(6, 50, 390, 25);
		LFirstName.setText("First Name:");
		LFirstName.setFocusable(false);
		LFirstName.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		TFFirstName.setBounds(6, 75, 390, 25);
		TFFirstName.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		LLastName.setBounds(6, 150, 390, 25);
		LLastName.setText("Last Name:");
		LLastName.setFocusable(false);
		LLastName.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		TFLastName.setBounds(6, 175, 390, 25);
		TFLastName.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		LMiddleName.setBounds(6, 100, 390, 25);
		LMiddleName.setText("Middle Name:");
		LMiddleName.setFocusable(false);
		LMiddleName.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		TFMiddleName.setBounds(6, 125, 390, 25);
		TFMiddleName.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		LPhone.setBounds(6, 200, 390, 25);
		LPhone.setText("Phone #:");
		LPhone.setFocusable(false);
		LPhone.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		TFPhone.setBounds(6, 225, 390, 25);
		TFPhone.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		LEmail.setBounds(6, 250, 390, 25);
		LEmail.setText("Email:");
		LEmail.setFocusable(false);
		LEmail.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		TFEmail.setBounds(6, 275, 390, 25);
		TFEmail.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		
		LPassword.setBounds(6, 300, 390, 25);
		LPassword.setText("Set Password:");
		LPassword.setFocusable(false);
		LPassword.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		TFPassword.setBounds(6, 325, 390, 25);
		TFPassword.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		
		Submit.setBounds(105, 365, 200, 50);
		Submit.setText("SUBMIT");
		Submit.setFocusable(false);
		Submit.setFont(new Font("Lucida Bright", Font.BOLD, 24));
		
		//This ActionListener is for the Submit Button
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Prevents the user from submitting an empty text field
				if (TFStaffID.getText().isEmpty() || TFFirstName.getText().isEmpty() || 
						TFLastName.getText().isEmpty() || TFMiddleName.getText().isEmpty() || 
						TFPhone.getText().isEmpty() || TFEmail.getText().isEmpty() || 
						TFPassword.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Submit, "Please Enter All Fields", "Try Again", JOptionPane.ERROR_MESSAGE);
				}
				else {
				String StaffID = TFStaffID.getText();
				String FirstName = TFFirstName.getText();
				String LastName = TFLastName.getText();
				String MiddleName = TFMiddleName.getText();
				String Phone = TFPhone.getText();
				String Email = TFEmail.getText();
				String Password = TFPassword.getText();
				
				// This is responsible for submitting the registered user's info to the database
				try {
					pst = con.prepareStatement("insert into registration(StaffID, FirstName, MiddleName, LastName, Phone, Email, StaffPassword) values(?,?,?,?,?,?,?)");
					pst.setString(1, StaffID);
					pst.setString(2, FirstName);
					pst.setString(3, MiddleName);
					pst.setString(4, LastName);
					pst.setString(5, Phone);
					pst.setString(6, Email);
					pst.setString(7, Password);
					
					int okay = pst.executeUpdate();
					
					if (okay==1) {
					JFrame success = new JFrame();
					JOptionPane.showMessageDialog(success, "Registration Complete");
					dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block 
					e1.printStackTrace();
				}
				
				
			}
			}
			
		});
		
		this.add(Submit);
		this.add(LStaffID);
		this.add(TFStaffID);
		this.add(LFirstName);
		this.add(TFFirstName);
		this.add(LLastName);
		this.add(TFLastName);
		this.add(LMiddleName);
		this.add(TFMiddleName);
		this.add(LPhone);
		this.add(TFPhone);
		this.add(LEmail);
		this.add(TFEmail);
		this.add(LPassword);
		this.add(TFPassword);
		this.add(LPicture);
		this.setBackground(Color.blue);
		
		
}}

public class ForRefMainFrame extends Functions{

	//Establishing connection with SQL server
	String sqlForName = "com.mysql.cj.jdbc.Driver";
	String sqlURL = "jdbc:mysql://localhost/Stafflogs";
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	//This connects the program to the MySQL database
	public void Connect() {
		try {
			Class.forName(sqlForName);
			con = DriverManager.getConnection(sqlURL, "root","password");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Declaration for the MainFrame
	JFrame mainFrame;
	JPanel loginPanel, bgPanel, HeaderPanel, CounterPanel, TablePanel;
	
	JLabel HeaderText, LStaffID, LStaffPass, Counter, CounterDesc, WrongCred,AlreadyLoggedin, TimeIs, ICpEPLogo, EngrLogo, Failedreg;
	
	JButton LoginButton, LogoutButton, RegisterButton, ExportDay, SecretAdmin;
	
	JTextField StaffID, StaffPass;
	
	JScrollPane TableScroll;
	private JScrollPane TableScrollPane;
	private JTable StaffTable;
	
	String CurrentDate, time, TimeInsFileName, TimeOutsFileName;

	ForRefMainFrame(){
		
	Connect();
		
	//Instantiation for the MainFrame
	mainFrame = new JFrame("PRMSU CpE Faculty Login System");
	
	loginPanel = new JPanel();
	CounterPanel = new JPanel();
	bgPanel = new JPanel();
	HeaderPanel = new JPanel();
	TablePanel = new JPanel();
	
	HeaderText = new JLabel();
	ICpEPLogo = new JLabel();
	EngrLogo = new JLabel();
	LStaffID = new JLabel();
	LStaffPass = new JLabel();
	Counter = new JLabel();
	WrongCred = new JLabel();
	AlreadyLoggedin = new JLabel();
	TimeIs = new JLabel();
	Failedreg = new JLabel();

	CounterDesc = new JLabel();
	
	StaffID = new JTextField();
	StaffPass = new JPasswordField();
	
	LoginButton = new JButton();
	LogoutButton = new JButton();
	ExportDay = new JButton();
	RegisterButton = new JButton();
	SecretAdmin = new JButton();
	
	//Panels and Text
	
	ImageIcon HeaderIcon = new ImageIcon("icpep-logo.png");
	ImageIcon HeaderIcon2 = new ImageIcon("COELOGOTRANS.png");
	ImageIcon ProgramIcon = new ImageIcon("icpep-logo-small.png");
	ImageIcon ExportIcon = new ImageIcon("Export.png");
	
	HeaderText.setBounds(290, 20, 522, 45);
	HeaderText.setText("CpE Faculty Log System");
	HeaderText.setFont(new Font("Lucida Bright", Font.BOLD, 43));
	
	ICpEPLogo.setBounds(859,5,67,67);
	ICpEPLogo.setFont(new Font("Lucida Bright", Font.BOLD, 43));
	ICpEPLogo.setIcon(HeaderIcon);
	
	EngrLogo.setBounds(175,5,67,67);
	EngrLogo.setFont(new Font("Lucida Bright", Font.BOLD, 43));
	EngrLogo.setIcon(HeaderIcon2);
	
	SecretAdmin.setBounds(859,5,67,67);
	SecretAdmin.setText("Admin");
	SecretAdmin.setFont(new Font("Lucida Bright", Font.BOLD, 24));
	SecretAdmin.setFocusable(false);
	SecretAdmin.setOpaque(false);
	SecretAdmin.setContentAreaFilled(false);
	SecretAdmin.setBorderPainted(false);
	
	HeaderPanel.setBounds(6, 0, 1052, 76);
	HeaderPanel.setBackground(new Color(110, 134, 255));
	HeaderPanel.setLayout(null);
	//HeaderPanel.setOpaque(false);
	
	HeaderPanel.add(EngrLogo);
	HeaderPanel.add(HeaderText);
	HeaderPanel.add(ICpEPLogo);
	HeaderPanel.add(SecretAdmin);
	
	ImageIcon bgpic = new ImageIcon("Background.png");
	//bgPanel.setBackground(Color.darkGray);
	
	bgPanel.setOpaque(false);
	bgPanel.setLayout(null);
	bgPanel.setBounds(0, 0, 1080, 720);
	
	JLabel background = new JLabel(bgpic);
	background.setBounds(0,0,bgpic.getIconWidth(),bgpic.getIconHeight());
	bgPanel.add(background);
	
	loginPanel.setBounds(6, 76, 450, 374);	
	loginPanel.setBackground(new Color(171, 200, 254));
	//loginPanel.setOpaque(false);
	loginPanel.setLayout(null);
	
	
	CounterPanel.setBounds(6, 450, 450, 226);
	CounterPanel.setBackground(new Color(171, 200, 254));
	//CounterPanel.setOpaque(false);
	CounterPanel.setLayout(null);
	
	TablePanel.setBounds(456, 76, 602, 600);
	//TablePanel.setOpaque(false);
	TablePanel.setBackground(new Color(114, 128, 169));
	TablePanel.setLayout(null);
	

	//Buttons
	LoginButton.setBounds(10, 250, 200, 50);
	LoginButton.setText("Login");
	LoginButton.setFont(new Font("Lucida Bright", Font.BOLD, 24));
	LoginButton.setFocusable(false);
	
	//Message in the main frame that appears when staff inputs wrong credentials
	WrongCred.setBounds(6, 200, 434, 50);
	WrongCred.setText("Staff Not Found/Wrong Credentials");
	WrongCred.setFont(new Font("Lucida Bright", Font.BOLD, 16));
	WrongCred.setForeground(Color.red);
	WrongCred.setHorizontalAlignment(SwingConstants.CENTER);
	WrongCred.setFocusable(false);
	WrongCred.setVisible(false);
	
	//Message in the main frame that appears when staff inputs tries to register without admin permission
		Failedreg.setBounds(6, 200, 434, 50);
		Failedreg.setText("Admin Permission Required!");
		Failedreg.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		Failedreg.setForeground(Color.red);
		Failedreg.setHorizontalAlignment(SwingConstants.CENTER);
		Failedreg.setFocusable(false);
		Failedreg.setVisible(false);
		
	
	//Message in the main frame that appears when staff is already logged in but tries to log in again
	AlreadyLoggedin.setBounds(6, 200, 434, 50);
	AlreadyLoggedin.setText("Staff Already Logged In");
	AlreadyLoggedin.setFont(new Font("Lucida Bright", Font.BOLD, 16));
	AlreadyLoggedin.setForeground(Color.red);
	AlreadyLoggedin.setHorizontalAlignment(SwingConstants.CENTER);
	AlreadyLoggedin.setFocusable(false);
	AlreadyLoggedin.setVisible(false);
	
	
	// This ActionListener is responsible for the login button
	LoginButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			boolean notAlreadyLogged = false; 
			String ID = StaffID.getText().toString();
			String Pass = StaffPass.getText().toString();
			
			int checkrowCount = StaffTable.getRowCount();
			
			//Checks if the user is already Logged-In
			if (checkrowCount!=0) {
				for (int i = 0;i != checkrowCount; i++) {
					if(ID.equals(StaffTable.getValueAt(i, StaffTable.getColumnCount()-5))) {
						notAlreadyLogged = false;
					}
					else {
						notAlreadyLogged = true;
					}
				}
				
			}
			else {
				notAlreadyLogged = true;
			}
			
			try {
				
				//Take the value from the database and prepares it to be passed into the JTable
				pst = con.prepareStatement("select staffID, StaffPassword, FirstName, LastName, Email from registration");
				rs = pst.executeQuery();
				while (rs.next()) {
					String dbID = rs.getString("staffID");
					String dbPass = rs.getString("StaffPassword");
					String dbFirst = rs.getString("FirstName");
					String dbLast = rs.getString("LastName");
					String dbEmail = rs.getString("Email");
					
					// If the staff is already logged in:
					if ((ID.equals(dbID) && Pass.equals(dbPass)) && notAlreadyLogged == false) {
						System.out.println("Already Logged in");
						AlreadyLoggedin.setVisible(true);
						WrongCred.setVisible(false);
						Failedreg.setVisible(false);
					}
					
					// If the staff inputed incorrect credentials:
					if ((!ID.equals(dbID) || !Pass.equals(dbPass)) && notAlreadyLogged == true) {
						System.out.println("Incorrect Credentials");
						WrongCred.setVisible(true);
						AlreadyLoggedin.setVisible(false);
						Failedreg.setVisible(false);
					}
					
					// Successful Logged in:
					if ((ID.equals(dbID) && Pass.equals(dbPass)) && notAlreadyLogged == true){
						
						Functions functions = new Functions();
						functions.LoginTableFunction(dbID, dbFirst, dbLast, dbEmail, StaffTable, con);
						StaffID.setText("");
						StaffPass.setText("");
						
						
						WrongCred.setVisible(false);
						AlreadyLoggedin.setVisible(false);
						break;
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	
	loginPanel.add(WrongCred);
	loginPanel.add(AlreadyLoggedin);
	loginPanel.add(Failedreg);
	loginPanel.add(LoginButton);
	
	LogoutButton.setBounds(240, 250, 200, 50);
	LogoutButton.setText("Logout");
	LogoutButton.setFont(new Font("Lucida Bright", Font.BOLD, 24));
	LogoutButton.setFocusable(false);
	
	//This ActionListener is for the logout button. 
	LogoutButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int row = StaffTable.getSelectedRow();
			
			//Prevents the user from logging out without a staff selected
			if (row<0) {
				JOptionPane.showMessageDialog(LogoutButton, 
						"No row selected! please select one row",
						"Select Row",
						JOptionPane.ERROR_MESSAGE
						);
			}
			//Initiates the LogoutTableFunction from the Functions class
			else {
				Functions functions = new Functions();
				functions.LogoutTableFunction(row, StaffTable, con);
			}
			
		}
	});
	
	//This ActionListener is for the secret admin button
	SecretAdmin.addActionListener(new ActionListener() {
		
		//Basically, to open the admin commands, the user needs to type into the id and password TextField
		// "admin" and "password"; you can change this; and clicking on the ICpEP logo three times.
		int click = 0;
		public void actionPerformed(ActionEvent e) {
			if (click < 2) {
				System.out.println("Hehe click me " +  (2 - click) + " more times!"  );
				click += 1;
			}
			else {
				String ID = StaffID.getText().toString();
				String Pass = StaffPass.getText().toString();
				
				if (ID.equals("admin") && Pass.equals("password")) {
					System.out.println("Welcome, Admin");
					AdminFrame secret = new AdminFrame();
					click = 0;
				}
				else {
					System.out.println("Okay Okay, STOP!");
				}
			}
				
			
		}
	});
	
	loginPanel.add(LogoutButton);
	
	RegisterButton.setBounds(75, 310, 300, 50);
	RegisterButton.setText("Register");
	RegisterButton.setFont(new Font("Lucida Bright", Font.BOLD, 24));
	RegisterButton.setFocusable(false);
	
	//This ActionListener is for the register button. It instantiates a new instance of the registration form
	RegisterButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String ID = StaffID.getText().toString();
			String Pass = StaffPass.getText().toString();
			
			if (ID.equals("admin") && Pass.equals("password")) {
				System.out.println("Registration Successfully Initiated!");
				Register page = new Register();
				page.setTitle("Registration Form");
				page.setVisible(true);
				page.setBounds(800, 0, 420, 470);
				page.setResizable(false);
				page.setIconImage(ProgramIcon.getImage());
				Failedreg.setVisible(false);
			}
			else {
				Failedreg.setVisible(true);
				System.out.println("Admin Permission NOT GRANTED!");
			}
		}
	});
	
	loginPanel.add(RegisterButton);
	
	//Login TextFields
	LStaffID.setText("Staff-ID:");
	LStaffID.setBounds(6, 0, 300, 50);
	LStaffID.setHorizontalTextPosition(JLabel.RIGHT);
	LStaffID.setVerticalTextPosition(JLabel.CENTER);
	LStaffID.setFont(new Font("Lucida Bright", Font.BOLD, 28));
	
	loginPanel.add(LStaffID);
	
	StaffID.setBounds(6, 45, 434, 50);
	StaffID.setFont(new Font("Lucida Bright", Font.BOLD, 28));
	
	loginPanel.add(StaffID);
	
	LStaffPass.setText("Staff Password:");
	LStaffPass.setBounds(6, 94, 300, 50);
	LStaffPass.setHorizontalTextPosition(JLabel.RIGHT);
	LStaffPass.setVerticalTextPosition(JLabel.CENTER);
	LStaffPass.setFont(new Font("Lucida Bright", Font.BOLD, 28));
	
	loginPanel.add(LStaffPass);
	
	StaffPass.setBounds(6, 139, 434, 50);
	StaffPass.setFont(new Font("Lucida Bright", Font.BOLD, 28));
	
	loginPanel.add(StaffPass);
	
	
	//Table
	mainFrame.getContentPane().add(TablePanel);
	
	TableScrollPane = new JScrollPane();
	TableScrollPane.setBounds(10, 11, 582, 578);
	TablePanel.add(TableScrollPane);
	
	StaffTable = new JTable() {
		private static final long serialVersionUID = 1L;

        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
		
	};
	
	StaffTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Staff ID","First Name","Last Name","Email","Time-in"
			}
		));
	
	TableScrollPane.setViewportView(StaffTable);
	mainFrame.getContentPane().add(HeaderPanel);
	mainFrame.getContentPane().add(CounterPanel);
	mainFrame.getContentPane().add(loginPanel);
	mainFrame.getContentPane().add(bgPanel);
	
	//This is responsible for counting the number of staff currently logged in
	StaffTable.getModel().addTableModelListener(new TableModelListener() {
		public void tableChanged(TableModelEvent e) {
			Counter.setText(Integer.toString(StaffTable.getRowCount()));
		}
	});
	
	int row = StaffTable.getRowCount();
	
	for(int i = 0; i<=row;i++) {
	Counter.setText(Integer.toString(i));
	}
	
	//This is the Export Button
	ExportDay.setBounds(10, 180, 40, 40);
	ExportDay.setFont(new Font("Lucida Bright", Font.BOLD, 24));
	ExportDay.setFocusable(false);
	ExportDay.setIcon(ExportIcon);
	
	//This ActionListener is for the export button. It calls the export function from the
	//Functions class.
	ExportDay.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String ID = StaffID.getText().toString();
			String Pass = StaffPass.getText().toString();
			
			if (ID.equals("admin") && Pass.equals("password")) {
				System.out.println("File Exported!");
				Functions functions = new Functions();
				functions.ExportFunction();
				Failedreg.setVisible(false);
			}
			else {
				Failedreg.setVisible(true);
				System.out.println("Admin Permission NOT GRANTED!");
			}
				
	        }
	});
	
	CounterPanel.add(ExportDay);
	
	Counter.setFont(new Font("Lucida Bright", Font.BOLD, 150));
	Counter.setBounds(10, 47, 430, 168);
	Counter.setForeground(new Color(255, 0, 0));
	Counter.setHorizontalAlignment(SwingConstants.CENTER);
	CounterPanel.add(Counter);
	
	CounterDesc.setText("Staff Logged-in:");
	CounterDesc.setFont(new Font("Lucida Bright", Font.BOLD, 40));
	CounterDesc.setBounds(10, 11, 430, 47);
	CounterDesc.setHorizontalAlignment(SwingConstants.CENTER);
	CounterPanel.add(CounterDesc);
	
	//MainFrame Essentials
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setSize(1080, 720);
	mainFrame.setResizable(false);
	mainFrame.getContentPane().setLayout(null);
	mainFrame.setIconImage(ProgramIcon.getImage());
	mainFrame.setVisible(true);
		

	}

}

