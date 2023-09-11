
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

class AdminFunctions{

	//This function fetches the data from the database, specifically from the registration table,  and inserts it to the JTable
	public void FetchStaffTable(ResultSet rs, JTable DataTable, Connection con, JScrollPane scrollPane) {
		
		//Instantiations
		DefaultTableModel model = (DefaultTableModel) DataTable.getModel();
		String query = "SELECT * FROM registration";
		
		model.setRowCount(0);
		model.setColumnCount(0);
		scrollPane.revalidate();
		scrollPane.repaint();
		
		//Executes the Query to MySQL
		try {
			Statement statement = con.createStatement();
			rs = statement.executeQuery(query);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			
			
		}
		
		//Adds the Data from the Table in MySQL to the JTable
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			for (int i = 1; i <= columnCount; i++) {
				model.addColumn(metaData.getColumnName(i));
			}
			
			while (rs.next()) {
			    Object[] rowData = new Object[columnCount];
			    for (int i = 1; i <= columnCount; i++) {
			        rowData[i - 1] = rs.getObject(i);
			    }
			    model.addRow(rowData);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	
	//This function fetches the data from the database, specifically from the timediff table, and inserts it to the JTable
	public void FetchLogsTable(ResultSet rs, JTable DataTable, Connection con, JScrollPane scrollPane) {
		//Instantiations
				DefaultTableModel model = (DefaultTableModel) DataTable.getModel();
				String query = "SELECT * FROM timediff";
				
				model.setRowCount(0);
				model.setColumnCount(0);
				scrollPane.revalidate();
				scrollPane.repaint();
				
				//Executes the Query to MySQL
				try {
					Statement statement = con.createStatement();
					rs = statement.executeQuery(query);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
					
				}
				
				//Adds the Data from the Table in MySQL to the JTable
				try {
					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();
					
					for (int i = 1; i <= columnCount; i++) {
						model.addColumn(metaData.getColumnName(i));
					}
					
					while (rs.next()) {
					    Object[] rowData = new Object[columnCount];
					    for (int i = 1; i <= columnCount; i++) {
					        rowData[i - 1] = rs.getObject(i);
					    }
					    model.addRow(rowData);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
	}
	
	
	//This function fetches a specified user in the timediff table
		public void FetchSearchTable(ResultSet rs, JTable DataTable, Connection con, JScrollPane scrollPane, String searchID) {
			//Instantiations
					DefaultTableModel model = (DefaultTableModel) DataTable.getModel();
					String query = "SELECT * FROM timediff where staffid = ?";
					
					
					model.setRowCount(0);
					model.setColumnCount(0);
					scrollPane.revalidate();
					scrollPane.repaint();
					
					//Executes the Query to MySQL
					try {
						PreparedStatement statement = con.prepareStatement(query);
						statement.setString(1, searchID);
						rs = statement.executeQuery();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();	
					}
					
					//Adds the Data from the Table in MySQL to the JTable
					try {
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						
						for (int i = 1; i <= columnCount; i++) {
							model.addColumn(metaData.getColumnName(i));
						}
						
						while (rs.next()) {
						    Object[] rowData = new Object[columnCount];
						    for (int i = 1; i <= columnCount; i++) {
						        rowData[i - 1] = rs.getObject(i);
						    }
						    model.addRow(rowData);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
		}
	
	//This function removes the selected staff in the JTable from the database
	public void RemoveFromDatabase(ResultSet rs, JTable DataTable, Connection con, JScrollPane scrollPane) {
		
		DefaultTableModel model = (DefaultTableModel) DataTable.getModel();
		int row = DataTable.getSelectedRow();
		PreparedStatement psREM;
		
		int SelectedRowIndex = DataTable.getSelectedRow();
		
		String StaffID = DataTable.getValueAt(SelectedRowIndex, 0).toString();
		
			try {
				psREM = con.prepareStatement("DELETE FROM registration WHERE staffID = ?");
				psREM.setString(1, StaffID);
				int okay = psREM.executeUpdate();
				
				if (okay==1) {
					System.out.println(StaffID + " has been removed!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		model.removeRow(row);
		
		AdminFunctions adminfunc = new AdminFunctions();
		adminfunc.FetchStaffTable(rs, DataTable, con, scrollPane);
		
		scrollPane.revalidate();
		scrollPane.repaint();
	}
	
	//This function submits any edits the admin makes to the registration table JTable to the database
	//Only works if the table shown is from the FetchStaffTable. Will not work on FetchLogsTable
	public void EditFromDatabase(ResultSet rs, JTable DataTable, Connection con, JScrollPane scrollPane) {
		
		PreparedStatement psED;
		
		for (int row = 0; row < DataTable.getRowCount(); row++) {
		    
			// Retrieve the values from the table cells
		    String staffID = DataTable.getValueAt(row, 0).toString();
		    String firstName = DataTable.getValueAt(row, 1).toString();
		    String middleName = DataTable.getValueAt(row, 2).toString();
		    String lastName = DataTable.getValueAt(row, 3).toString();
		    String phone = DataTable.getValueAt(row, 4).toString();
		    String Email = DataTable.getValueAt(row, 5).toString();
		    String Password = DataTable.getValueAt(row, 6).toString();

			try {
				psED = con.prepareStatement("UPDATE registration SET StaffID = ?, FirstName = ?, MiddleName = ?, LastName = ?, Phone = ?, Email = ?, StaffPassword = ? WHERE StaffID = ?");
				psED.setString(1, staffID);
				psED.setString(2, firstName);
				psED.setString(3, middleName);
				psED.setString(4, lastName);
				psED.setString(5, phone);
				psED.setString(6, Email);
				psED.setString(7, Password);
				psED.setString(8, staffID);
				
				 int okay = psED.executeUpdate();
				 if (okay==1) {
						System.out.println(staffID + " Data Updated!");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			AdminFunctions adminfunc = new AdminFunctions();
			adminfunc.FetchStaffTable(rs, DataTable, con, scrollPane);
			
			scrollPane.revalidate();
			scrollPane.repaint();
		    
		}
	}
	
	//This function clears the entire table of the timediff table. Useful if the admin wants to start a
	//fresh record of logs weekly/monthly
	public void ClearLogs(ResultSet rs, JTable DataTable, Connection con, JScrollPane scrollPane) {
		PreparedStatement psCLR1, psCLR2, psCLR3;
		try {
			psCLR1 = con.prepareStatement("DELETE FROM timediff");
			psCLR2 = con.prepareStatement("DELETE FROM timein");
			psCLR3 = con.prepareStatement("DELETE FROM timeout");
			
			int okay1 = psCLR1.executeUpdate();
			int okay2 = psCLR2.executeUpdate();
			int okay3 = psCLR3.executeUpdate();
			
			if (okay1 == 1 && okay2 == 1 && okay3 == 1) {
				System.out.println("All Logs Cleared!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AdminFunctions adminfunc = new AdminFunctions();
		adminfunc.FetchLogsTable(rs, DataTable, con, scrollPane);
		
		scrollPane.revalidate();
		scrollPane.repaint();
	}
	
	//This function exports the timediff table as a .csv file.
	public void Export() {
		String jdbcUrl = "jdbc:mysql://localhost/stafflogs";
        String username = "root";
        String password = "password";
        
        String tablename = "timediff";
        
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
		
        String csvFilePath = "C:/Users/danie/Desktop/Coding/Java Eclipse/CpE Engineering Staff Login System/Exports/Combined_Logs-" + formattedDate + ".csv";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT * FROM " + tablename;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query);
                 FileWriter writer = new FileWriter(csvFilePath)) {

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

                System.out.println("Table exported successfully to " + csvFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
}
}

public class AdminFrame {
	
	//Just Declarations for variables
	JFrame adminFrame;
	ImageIcon HeaderIcon;
	ImageIcon ProgramIcon;
	JLabel ICpEPLogo;
	private JTable DataTable;
	
	//Establishing connection with SQL server
		String sqlForName = "com.mysql.cj.jdbc.Driver";
		String sqlURL = "jdbc:mysql://localhost/Stafflogs";
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		private JTextField SearchField;
		
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
		
	AdminFrame(){
		Connect();
		
		//Most of the next code is mainly for GUI. Nothing special; I've tried my best to keep it
		//Descriptive so most of it is self explanatory. If it starts with an L then its a JLabel
		//If it start with btn then its a Button, etc, etc. 
		adminFrame = new JFrame("Admin Tools");
		HeaderIcon = new ImageIcon("icpep-logo.png");
		ProgramIcon = new ImageIcon("icpep-logo-small.png");

		
		JPanel ButtonsPanel = new JPanel();
		ButtonsPanel.setBackground(new Color(0, 128, 255));
		ButtonsPanel.setBounds(0, 0, 203, 386);
		ButtonsPanel.setLayout(null);
		
		
		JLabel LWelcome = new JLabel("Welcome Admin!");
		LWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		LWelcome.setBounds(10, 11, 183, 36);
		LWelcome.setFont(new Font("Lucida Bright", Font.BOLD, 17));
		
		ButtonsPanel.add(LWelcome);
		
		
		JButton btnStaffData = new JButton("Fetch Staff Data");
		
		btnStaffData.setBounds(26, 73, 145, 23);
		btnStaffData.setFocusable(false);
		btnStaffData.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		ButtonsPanel.add(btnStaffData);
		
		
		JButton btnLogsData = new JButton("Fetch Logs Data");
		
		btnLogsData.setBounds(26, 100, 145, 23);
		btnLogsData.setFocusable(false);
		btnLogsData.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		ButtonsPanel.add(btnLogsData);
		
		
		JLabel LFetchTable = new JLabel("Fetch Table:");
		LFetchTable.setHorizontalAlignment(SwingConstants.CENTER);
		LFetchTable.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		LFetchTable.setBounds(35, 58, 124, 15);
		ButtonsPanel.add(LFetchTable);
		
		
		JLabel LFunctions = new JLabel("Staff Data Functions:");
		LFunctions.setHorizontalAlignment(SwingConstants.CENTER);
		LFunctions.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		LFunctions.setBounds(26, 144, 145, 15);
		ButtonsPanel.add(LFunctions);
		
		
		JButton btnSubmitEdit = new JButton("Submit Edit");
		
		btnSubmitEdit.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		btnSubmitEdit.setFocusable(false);
		btnSubmitEdit.setBounds(26, 160, 145, 23);
		ButtonsPanel.add(btnSubmitEdit);
		
		JButton btnDeleteStaff = new JButton("Delete Staff");
		btnDeleteStaff.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		btnDeleteStaff.setFocusable(false);
		btnDeleteStaff.setBounds(26, 189, 145, 23);
		ButtonsPanel.add(btnDeleteStaff);
		
		JLabel lblLogsFunctions = new JLabel("Logs Functions:");
		lblLogsFunctions.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogsFunctions.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		lblLogsFunctions.setBounds(26, 228, 145, 15);
		ButtonsPanel.add(lblLogsFunctions);
		
		JButton btnExportLogs = new JButton("Export Hour Logs");
		
		btnExportLogs.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		btnExportLogs.setFocusable(false);
		btnExportLogs.setBounds(26, 244, 145, 23);
		ButtonsPanel.add(btnExportLogs);
		
		JButton btnClear = new JButton("Clear Logs");
		
		btnClear.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		btnClear.setFocusable(false);
		btnClear.setBounds(26, 273, 145, 23);
		ButtonsPanel.add(btnClear);
		
		JLabel SearchFunctions = new JLabel("Search Logs Functions:");
		SearchFunctions.setHorizontalAlignment(SwingConstants.CENTER);
		SearchFunctions.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		SearchFunctions.setBounds(16, 307, 167, 15);
		ButtonsPanel.add(SearchFunctions);
		
		
		JPanel TablePanel = new JPanel();
		TablePanel.setBackground(new Color(0, 64, 128));
		TablePanel.setBounds(202, 0, 502, 386);
		TablePanel.setLayout(null);
		
		//This is the JTable that will hold the requested data from the database
		//It's empty so that it can flexible in returning columns and rows from the database
		
		DataTable = new JTable();
		DataTable.setCellSelectionEnabled(true);
		DataTable.setColumnSelectionAllowed(true);
		DataTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		DataTable.setBounds(10, 11, 482, 378);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 11, 482, 364);
		TablePanel.add(scrollPane);
		scrollPane.setViewportView(DataTable);
		
		JPanel CMDpanel = new JPanel();
		CMDpanel.setBackground(new Color(0, 0, 64));
		CMDpanel.setBounds(0, 386, 704, 55);
		CMDpanel.setLayout(null);
		
		
		adminFrame.setIconImage(ProgramIcon.getImage());
		adminFrame.setVisible(true);
		
		adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		adminFrame.setSize(720, 480);
		adminFrame.setResizable(false);
		adminFrame.getContentPane().setLayout(null);
		
		adminFrame.getContentPane().add(TablePanel);
		adminFrame.getContentPane().add(ButtonsPanel);
		
		SearchField = new JTextField();
		SearchField.setBounds(26, 328, 145, 20);
		ButtonsPanel.add(SearchField);
		SearchField.setColumns(10);
		adminFrame.getContentPane().add(CMDpanel);
		
		//Just some JLabel for credits hehe
		JLabel lblDevelopedByDaniel = new JLabel("Developed By: Daniel Zachary M. Mercurio");
		lblDevelopedByDaniel.setForeground(new Color(255, 255, 255));
		lblDevelopedByDaniel.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevelopedByDaniel.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		lblDevelopedByDaniel.setBounds(10, 8, 684, 15);
		CMDpanel.add(lblDevelopedByDaniel);
		
		JLabel lblBscpeb = new JLabel("BSCpE-2B");
		lblBscpeb.setHorizontalAlignment(SwingConstants.CENTER);
		lblBscpeb.setForeground(Color.WHITE);
		lblBscpeb.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		lblBscpeb.setBounds(10, 24, 684, 15);
		CMDpanel.add(lblBscpeb);
		
		JLabel lblDevelopedByDaniel_1_1 = new JLabel("Data Structure and Analysis Finals Project");
		lblDevelopedByDaniel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevelopedByDaniel_1_1.setForeground(Color.WHITE);
		lblDevelopedByDaniel_1_1.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		lblDevelopedByDaniel_1_1.setBounds(10, 37, 684, 15);
		CMDpanel.add(lblDevelopedByDaniel_1_1);
		ICpEPLogo = new JLabel();
		ICpEPLogo.setBounds(-55, 50, 67, 67);
		CMDpanel.add(ICpEPLogo);
		ICpEPLogo.setFont(new Font("Lucida Bright", Font.BOLD, 43));
		ICpEPLogo.setIcon(new ImageIcon("C:\\Users\\danie\\Desktop\\Coding\\Java Eclipse\\CpE Engineering Staff Login System\\icpep-logo.png"));
		ICpEPLogo.setVisible(false);
		
		//This Part beyond holds all of the ActionListeners for the JButtons used in this JFrame
		
		//This ActionListener is for fetching the Staff Information Table from the Database
		btnStaffData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				AdminFunctions adminfunc = new AdminFunctions();
				adminfunc.FetchStaffTable(rs, DataTable,con, scrollPane);
				
			}
		});
		
		//This ActionListener is for fetching the Logs Table from the Database
		btnLogsData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminFunctions adminfunc = new AdminFunctions();
				adminfunc.FetchLogsTable(rs, DataTable, con, scrollPane);
				
			}
		});
		
		//This ActionListener is for deleting a from the Database
		btnDeleteStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = DataTable.getSelectedRow();
				
				// The Admin cannot use this function without selecting a row first
				if (row<0) {
					JOptionPane.showMessageDialog(btnDeleteStaff, 
							"No row selected! please select one row",
							"Select Row",
							JOptionPane.ERROR_MESSAGE
							);
				}
				else {
					AdminFunctions adminfunc = new AdminFunctions();
					adminfunc.RemoveFromDatabase(rs, DataTable, con, scrollPane);
				}
			}
		});
		
		//This ActionListener is for submitting the editted table back to the database registration table
		btnSubmitEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFunctions adminfunc = new AdminFunctions();
				adminfunc.EditFromDatabase(rs, DataTable, con, scrollPane);	
			}
		});
		
		//This ActionListener is for the clear button. It just calls the clear function; self explanatory
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFunctions adminfunc = new AdminFunctions();
				adminfunc.ClearLogs(rs, DataTable, con, scrollPane);	
			}
		});
		
		//This ActionListener calls the export function. Only exports the timediff table from the database
		btnExportLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFunctions adminfunc = new AdminFunctions();
				adminfunc.Export();
			}
		});
		
		JButton SearchButton = new JButton("Search");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String SearchID = SearchField.getText();
				if (SearchID != "") {
					AdminFunctions adminfunc = new AdminFunctions();
					adminfunc.FetchSearchTable(rs, DataTable, con, scrollPane, SearchID);
				}
				else {
					JFrame noInput = new JFrame();
					JOptionPane.showMessageDialog(noInput, "Please Insert ID");
				}
				
				
			}
		});
		
		SearchButton.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		SearchButton.setFocusable(false);
		SearchButton.setBounds(26, 352, 145, 23);
		ButtonsPanel.add(SearchButton);
		
	}
}
