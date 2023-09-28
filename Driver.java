import java.io.File;

public class Driver {
	public static void main(String[] args) {
		double[] c1 = { -6, -2, 4, 5 };
		int[] e1 = { 0, 1, 2, 3 };
		Polynomial p1 = new Polynomial(c1, e1);
		double[] c2 = { 7, -2, -9 };
		int[] e2 = { 0, 1, 3 };
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		Polynomial p3 = p1.multiply(p2);

		p1.saveToFile("p1.txt");
		Polynomial p11 = new Polynomial(new File("p1.txt"));
		p11.saveToFile("p11.txt");
		p2.saveToFile("p2.txt");
		s.saveToFile("s.txt");
		p3.saveToFile("p3.txt");

		Polynomial c = new Polynomial(new double[] { 0 }, new int[] { 0 });
		c.saveToFile("c.txt");
		Polynomial cc = new Polynomial(new File("c.txt"));
		cc.saveToFile("cc.txt");
		
				
		System.out.println("p1(2) = " + p1.evaluate(2));
		System.out.println("p2(-1) = " + p2.evaluate(-1));
		if (p1.hasRoot(1)) {
			System.out.println("1 is a root of p1");
		} else {
			System.out.println("1 is not a root of p1");
		}
		if (p2.hasRoot(-1)) {
			System.out.println("-1 is a root of p2");
		} else {
			System.out.println("-1 is not a root of p2");
		}
	}
}
