package UploadDocTreeNodes;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

//This Class Contains the Model of the table in the Upload Docs
public class TreeTable {

	private String[] headings = { " ", "Status", "Date Submitted" };
	private Node root;
	private DefaultTreeTableModel model;
	private JXTreeTable table;
	private List<String[]> content;

	public TreeTable(List<String[]> content) {
		this.content = content;
	}

	public JXTreeTable getTreeTable() {
		root = new RootNode("Root");

		ChildNode mainHeader  = null;
        ChildNode subHeader = null;
		for (String[] data : this.content) {
			ChildNode child = new ChildNode(data);
            //If the data inputed is only for column 1 and 2, then it becomes a main header
			if (data.length <= 5 && Integer.parseInt(data[3]) == 1) {
				root.add(child);
				mainHeader = child;
                subHeader = null;
            }
			//If the data inputed is 4 long and the data at index 1 the data is empty, then it is a subheader
            else if (data.length == 5 && Integer.parseInt(data[3]) == 2) {
                if (mainHeader != null) {
                    mainHeader.add(child);
                    subHeader = child;
                }

			//Else, it is a subheader of the sub
			} else {
				if (subHeader != null) {
                    subHeader.add(child);
                } else if (mainHeader != null) {
                    mainHeader.add(child);
                }
			}
		}

		model = new DefaultTreeTableModel(root, Arrays.asList(headings));
		table = new JXTreeTable(model);

        for (int i = 0; i < table.getRowCount(); i++) {
            table.expandRow(i);
        }

		table.setShowGrid(true, false);
		table.setColumnControlVisible(true);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		table.packAll();

		return table;
	}
}