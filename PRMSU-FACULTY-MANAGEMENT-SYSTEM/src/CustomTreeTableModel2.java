import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Arrays;

public class CustomTreeTableModel2 extends AbstractTreeTableModel {

    private final String[] columnNames = {"Name", "Status", "Date Modified"};

    public CustomTreeTableModel2(File facultyFolder) {
        super(new DefaultMutableTreeNode(facultyFolder));
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
            if (userObject instanceof File) {
                File file = (File) userObject;
                switch (column) {
                    case 0:
                        return file.getName();
                    case 1:
                        String Status = "Not Submitted";
                        return Status;
                    case 2:
                        String Date = "20/12/2023";
                        return Date;
                }
            }
        }
        return null;
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((DefaultMutableTreeNode) node).isLeaf();
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parent;
            File parentFile = (File) parentNode.getUserObject();
            File[] files = parentFile.listFiles();
            System.out.println("GET CHILD - List of files in " + parentFile.getName() + ": " + Arrays.toString(files));
            if (files != null && index >= 0 && index < files.length) {
                return new DefaultMutableTreeNode(files[index]);
            }
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parent;
            File parentFile = (File) parentNode.getUserObject();
            File[] files = parentFile.listFiles();
            System.out.println("ChildCOUNT - List of files in " + parentFile.getName() + ": " + Arrays.toString(files));

            if (files != null) {
                return files.length;
            }
        }
        return 0;
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof DefaultMutableTreeNode && child instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parent;
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) child;

            for (int i = 0; i < getChildCount(parent); i++) {
                if (getChild(parent, i) == child) {
                    return i;
                }
            }
        }
        return -1;
    }
}
