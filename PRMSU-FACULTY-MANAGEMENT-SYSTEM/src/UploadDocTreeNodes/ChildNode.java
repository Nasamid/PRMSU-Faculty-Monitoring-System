package UploadDocTreeNodes;

import java.util.Arrays;

public class ChildNode extends Node {

	public ChildNode(String[] data) {
		super(data);
	}

    @Override
    public String toString() {
        return Arrays.toString(getData()); // or any meaningful representation for ChildNode
    }
    
}
