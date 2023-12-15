import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class CustomTreeTableModel extends AbstractTreeTableModel {

    private final String[] columnNames;

    public CustomTreeTableModel(DefaultMutableTreeNode rootNode, String[] columnNames) {
        super(rootNode);
        this.columnNames = columnNames;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof DefaultMutableTreeNode) {
            Object userObject = ((DefaultMutableTreeNode) node).getUserObject();
            if (userObject instanceof String[]) {
                String[] rowData = (String[]) userObject;
                return rowData[column];
            }
        }
        return null;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof TreeTableNode) {
            TreeTableNode parentNode = (TreeTableNode) parent;
            return parentNode.getChildAt(index);
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof TreeTableNode) {
            TreeTableNode parentNode = (TreeTableNode) parent;
            return parentNode.getChildCount();
        }
        return 0;
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof TreeTableNode && child instanceof TreeTableNode) {
            TreeTableNode parentNode = (TreeTableNode) parent;
            return parentNode.getIndex((TreeTableNode) child);
        }
        return -1;
    }

    @Override
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }
}
