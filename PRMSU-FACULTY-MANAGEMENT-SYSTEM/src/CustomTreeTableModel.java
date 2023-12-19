import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;

public class CustomTreeTableModel extends AbstractTreeTableModel {

    private final String[] columnNames;

    public CustomTreeTableModel(DefaultMutableTreeNode rootNode, String[] columnNames) {
        super(rootNode);
        this.columnNames = columnNames;
    }

    //This Method gets the column count of the JXTreeTable
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    //This Method gets the name for each of the column of the JXTreeTable
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    //This method gets the value of the input in order to add the row data
    // @Override
    // public Object getValueAt(Object node, int column) {
    //     if (node instanceof DefaultMutableTreeNode) {
    //         Object userObject = ((DefaultMutableTreeNode) node).getUserObject();
    //         if (userObject instanceof String[]) {
    //             String[] rowData = (String[]) userObject;
    //             return rowData[column];
    //         }
    //     }
    //     return null;
    // }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof DefaultMutableTreeNode) {
            Object userObject = ((DefaultMutableTreeNode) node).getUserObject();
    
            // Check if the user object is not null
            if (userObject != null) {
                // Modify this part based on the actual structure of your user object
                // For example, if it's a custom class, you might have methods or fields to access data
                // Adjust this part according to the structure of your user object
                if (userObject instanceof String[]) {
                    String[] rowData = (String[]) userObject;
    
                    // Ensure the column index is within bounds
                    if (column >= 0 && column < rowData.length) {
                        return rowData[column];
                    }
                } else  if (userObject != null && userObject instanceof FileNode) {
                    FileNode fileNode = (FileNode) userObject;
        
                    switch (column) {
                        case 0:
                            return fileNode.getFileName();
                        case 1:
                            return fileNode.getStatus();
                        case 2:
                            return fileNode.getDateSubmitted();
                    }
                }
            }
        }
        return null;
    }
    

    //This method gets the Child node for the Model
    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof TreeTableNode) {
            TreeTableNode parentNode = (TreeTableNode) parent;
            return parentNode.getChildAt(index);
        }
        return null;
    }

    //This method counts the number of children the parent node has
    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof TreeTableNode) {
            TreeTableNode parentNode = (TreeTableNode) parent;
            return parentNode.getChildCount();
        }
        return 0;
    }

    //This method gets the index of the the child node
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof TreeTableNode && child instanceof TreeTableNode) {
            TreeTableNode parentNode = (TreeTableNode) parent;
            return parentNode.getIndex((TreeTableNode) child);
        }
        return -1;
    }

    //This method determines if the Child Node is a Leaf Node
    @Override
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }
}
