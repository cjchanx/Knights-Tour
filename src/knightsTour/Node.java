package knightsTour;

public class Node {
	private int data; // Data of the node
	private Node right; // Node link to the right
	private Node left; // Node link to the left
	private Node up; // Node link up
	private Node down; // Node link down
	private Node path1; // Path 1 is UUL
	private Node path2; // Path 2 is UUR
	private Node path3; // Path 3 is RRU
	private Node path4; // Path 4 is DRR
	private Node path5; // Path 5 is DDR
	private Node path6; // Path 6 is DDL
	private Node path7; // Path 7 is DLL
	private Node path8; // Path 8 is LLU
	
	// Constructors
	// Base (empty) Node constructor
	public Node() {
		data = 0;
		up = null;
		down = null;
		left = null;
		right = null;
	}
	// Node with data constructor
	public Node(int data) {
		this.data = data;
		up = null;
		down = null;
		left = null;
		right = null;
	}
	
	// Getters and setters
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getUp() {
		return up;
	}
	public void setUp(Node up) {
		this.up = up;
	}
	public Node getDown() {
		return down;
	}
	public Node getPath1() {
		return path1;
	}
	public void setPath1(Node path1) {
		this.path1 = path1;
	}
	public Node getPath2() {
		return path2;
	}
	public void setPath2(Node path2) {
		this.path2 = path2;
	}
	public Node getPath3() {
		return path3;
	}
	public void setPath3(Node path3) {
		this.path3 = path3;
	}
	public Node getPath4() {
		return path4;
	}
	public void setPath4(Node path4) {
		this.path4 = path4;
	}
	public Node getPath5() {
		return path5;
	}
	public void setPath5(Node path5) {
		this.path5 = path5;
	}
	public Node getPath6() {
		return path6;
	}
	public void setPath6(Node path6) {
		this.path6 = path6;
	}
	public Node getPath7() {
		return path7;
	}
	public void setPath7(Node path7) {
		this.path7 = path7;
	}
	public Node getPath8() {
		return path8;
	}
	public void setPath8(Node path8) {
		this.path8 = path8;
	}
	public void setDown(Node down) {
		this.down = down;
	}
	
	
}
