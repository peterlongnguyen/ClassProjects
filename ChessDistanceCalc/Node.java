public class Node implements Comparable<Node>{
	private double past = 0, present = 0;
	double choice = -999;
	private double euclid, manhat, chess, total;
	private double x1, y1, x2, y2;
	private String history = null;
	private boolean isTarget = false;
	
	public Node() {}
	
	public Node(double X1, double Y1, double X2, double Y2, double pas, double setDist, String hist, String newHist) {
		x1 = X1; y1 = Y1; x2 = X2; y2 = Y2;
		past = pas; present = past+1;
		history = hist + newHist; // appends current move to move history
		euclid = getEuclid(x1, y1, x2, y2);
		manhat = getManhat(x1, y1, x2, y2);
		chess = getChess(x1, y1, x2, y2);
		total = present + setDist;
		//setDistance(setDist);
		choice = setDist;
		if(x1 == x2 && y1 == y2) isTarget = true;
	}

	// setters
	
	public void setDistance(int i) { // chooses which distance to go by
		switch(i) {
		case 1: total = euclid + present; break;
		case 2: total = manhat + present; break;
		case 3: total = chess + present; break;
		default: break;
		}
	}
	
	public void setPast(int p) {
		past = p;
	}
	
	public void setPresent(int p) {
		present = p;
	}
	
	// getters
	
	/*
	public void getDist() {
		switch(choice) {
		case 1: System.out.println("Euclidean: " + total); break;
		case 2: System.out.println("Manhattan: " + total); break;
		case 3: System.out.println("Chess: " + total); break;
		default: break;
		}
	}*/
	
	public String getHistory() {
		return history;
	}
	
	public double getPast() {
		return past;
	}
	
	public double getPresent() {
		return present;
	}
	
	public double getEuclid() {
		return euclid;
	}
	
	public double getManhat() {
		return manhat;
	}
	
	public double getChess() {
		return chess;
	}
	
	public boolean isTarget() {
		return isTarget;
	}
	
	public void setTargetTrue() {
		isTarget = true;
	}
	
	public double getX1() {
		return x1;
	}
	
	public double getY1() {
		return y1;
	}
	
	public double getX2() {
		return x2;
	}
	
	public double getY2() {
		return y2;
	}
	
	// distance methods

	public double getEuclid(double x1, double y1, double x2, double y2) {
		return (Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)));
	}
	
	public double getManhat(double x1, double y1, double x2, double y2) {
		return (Math.abs(x1-x2) + Math.abs(y1-y2));
	}
	
	public double getChess(double x1, double y1, double x2, double y2) {
		return (Math.max(Math.abs(x1-x2), Math.abs(y1-y2)));
	}
	
	// comparing nodes
	
	public int compareTo(Node n) {
		if(this.total > n.total) {
			return 1;
		}
		else if(this.total < n.total) {
			return -1;
		}
		return 0;
	}
}
