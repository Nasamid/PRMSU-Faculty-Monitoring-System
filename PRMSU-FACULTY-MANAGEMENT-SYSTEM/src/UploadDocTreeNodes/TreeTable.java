package UploadDocTreeNodes;

import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

// This Class Contains the Model of the table in the Upload Docs
public class TreeTable extends JXTreeTable {

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

        ChildNode mainHeader = null;
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

	// public void clearAndRefresh() {
    //     DefaultMutableTreeTableNode newRoot = new DefaultMutableTreeTableNode("Root");
    //     // Add nodes to the newRoot as needed

    //     // Create a new DefaultTreeTableModel
    //     DefaultTreeTableModel newModel = new DefaultTreeTableModel(newRoot, Arrays.asList(headings));

    //     // Set the new model on JXTreeTable
    //     setTreeTableModel(newModel);
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

	public void collapseAll() {
		int rowCount = getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			TreePath path = getPathForRow(i);
			collapseNode(path);
		}
	}

	public void expandAll() {
		int rowCount = getRowCount();
		for (int i = 0; i < rowCount; i++) {
			TreePath path = getPathForRow(i);
			expandNode(path);
		}
	}

	private void expandNode(TreePath path) {
		if (path != null) {
			expandPath(path);
			Object node = path.getLastPathComponent();
			if (node instanceof DefaultMutableTreeTableNode) {
				DefaultMutableTreeTableNode treeNode = (DefaultMutableTreeTableNode) node;
				for (int i = 0; i < treeNode.getChildCount(); i++) {
					expandNode(path.pathByAddingChild(treeNode.getChildAt(i)));
				}
			}
		}
	}

	private void collapseNode(TreePath path) {
		if (path != null) {
			collapsePath(path);
			Object node = path.getLastPathComponent();
			if (node instanceof DefaultMutableTreeTableNode) {
				DefaultMutableTreeTableNode treeNode = (DefaultMutableTreeTableNode) node;
				for (int i = 0; i < treeNode.getChildCount(); i++) {
					collapseNode(path.pathByAddingChild(treeNode.getChildAt(i)));
				}
			}
		}
	}
}
