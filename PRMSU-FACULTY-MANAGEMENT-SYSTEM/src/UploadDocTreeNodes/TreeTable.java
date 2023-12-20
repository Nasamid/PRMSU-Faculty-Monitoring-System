package UploadDocTreeNodes;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.TreePath;

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

		// // Define your desired order
		// List<String> desiredOrder = Arrays.asList("load", "syllabus", "class record", "grade sheet", "exam with answer key", "tables of specification", "item analysis");

		// // Sort the content based on the desired order
		// content.sort((data1, data2) -> {
		// 	String name1 = data1[0];
		// 	String name2 = data2[0];
			
		// 	return Integer.compare(desiredOrder.indexOf(name1), desiredOrder.indexOf(name2));
		// });

		// for (String[] entry : content) {
		// 	    for (String element : entry) {
		// 	        System.out.print(element + " ");
		// 	    }
		// 	    System.out.println();
		// 	}

		ChildNode mainHeader  = null;
        ChildNode subHeader = null;
		ChildNode innerSubHeader = null;
		
		for (String[] data : this.content) {
			ChildNode child = new ChildNode(data);

			int depth = Integer.parseInt(data[3]);
		
			if (data.length <= 5 && depth == 1) {
				root.add(child);
				mainHeader = child;
				subHeader = null;
				innerSubHeader = null;
			} else if (data.length == 5 && depth == 2) {
				if (mainHeader != null) {
					mainHeader.add(child);
					subHeader = child;
					innerSubHeader = null;
				}
			} else if (data.length == 5 && depth == 3) {
				if (subHeader != null) {
					subHeader.add(child);
					innerSubHeader = child;
				} else if (mainHeader != null) {
					mainHeader.add(child);
					innerSubHeader = null;
				}
			} else {
				if (innerSubHeader != null) {
					innerSubHeader.add(child);
				} else if (subHeader != null) {
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

	public void addTreeSelectionListener(TreeSelectionListener listener) {
        table.addTreeSelectionListener(listener);
    }

// 	public String treePathToString(TreePath path) {
//     Object[] pathArray = path.getPath();
//     StringBuilder stringBuilder = new StringBuilder();
    
//     for (Object pathComponent : pathArray) {
//         if (pathComponent instanceof String) {
//             stringBuilder.append((String) pathComponent);
//         } else if (pathComponent != null) {
//             stringBuilder.append(pathComponent.toString());
//         }
//         stringBuilder.append(" / "); // Adjust the separator as needed
//     }
    
//     // Remove the trailing separator
//     if (stringBuilder.length() > 0) {
//         stringBuilder.setLength(stringBuilder.length() - 3);
//     }
    
//     return stringBuilder.toString();
// }

public String treePathToString(TreePath path) {
    Object lastPathComponent = path.getLastPathComponent();
    if (lastPathComponent instanceof String) {
        return (String) lastPathComponent;
    } else if (lastPathComponent instanceof Object[]) {
        Object[] pathComponentArray = (Object[]) lastPathComponent;
        if (pathComponentArray.length > 0) {
            return pathComponentArray[pathComponentArray.length - 1].toString();
        }
    } else if (lastPathComponent instanceof Node) {
        Object[] data = ((Node) lastPathComponent).getData();
        if (data.length > 0) {
            return data[data.length - 1].toString();
        }
    }
    return "";
}


public String treePathLastComponentToString(TreePath path) {
    Object lastPathComponent = path.getLastPathComponent();

    if (lastPathComponent instanceof String) {
        return (String) lastPathComponent;
    } else if (lastPathComponent instanceof Node) {
        Object[] data = ((Node) lastPathComponent).getData();
        if (data.length > 0) {
            return data[data.length - 1].toString();
        }
    } else if (lastPathComponent != null) {
        return lastPathComponent.toString();
    }

    return "";
}



}