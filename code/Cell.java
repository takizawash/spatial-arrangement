
public class Cell {
	int a = 0;
	int newa = 0;
	int type = 0;

	public void calc0(double para0, double para6c) {
		newa = 2;
		double defr1 = Math.random();
		double defr2 = Math.random();
		if (defr1 < para0) {
			if (defr2 < para6c) {
				newa = 1;
			} else {
				newa = 0;
			}
		}
	}

	public void calc1(double para1) {
		newa = 0;
		double defr = Math.random();
		if (defr < para1) {
			newa = 2;
		}
	}

	public void calc2(double para2) {
		newa = 0;
		double defr = Math.random();
		if (defr < para2) {
			newa = 3;
		}
	}

	public void calc3(double para3) {
		newa = 1;
		double defr = Math.random();
		if (defr < para3) {
			newa = 0;
		}
	}

	public void calc4(double para4) {
		newa = 2;
		double defr = Math.random();
		if (defr < para4) {
			newa = 0;
		}
	}

	public void calc5(double para5) {
		newa = 3;
		double defr = Math.random();
		if (defr < para5) {
			newa = 0;
		}
	}

	public void update() {
		a = newa;
	}

	public int get_a() {
		return a;
	}

	public int get_type() {
		return type;
	}

	public void set_type(int i) {
		type = i;
	}

	public void set_a(int i) {
		a = i;
	}
}
