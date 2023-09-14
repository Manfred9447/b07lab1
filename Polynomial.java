public class Polynomial {
	private double[] coefficients;

	public Polynomial() {
		coefficients = new double[0];
	}

	public Polynomial(double[] coefficients) {
		this.coefficients = coefficients;
	}

	public double[] getCoefficients() {
		return coefficients;
	}

	public Polynomial add(Polynomial p) {
		double[] c1 = getCoefficients();
		double[] c2 = p.getCoefficients();
		if (c1.length > c2.length) {
			double[] c = new double[c1.length];
			for (int i = 0; i < c1.length; i++) {
				if (i < c2.length) {
					c[i] = c1[i] + c2[i];
				} else {
					c[i] = c1[i];
				}
			}
			return new Polynomial(c);
		} else {
			double[] c = new double[c2.length];
			for (int i = 0; i < c2.length; i++) {
				if (i < c1.length) {
					c[i] = c1[i] + c2[i];
				} else {
					c[i] = c2[i];
				}
			}
			return new Polynomial(c);
		}
	}

	public double evaluate(double x) {
		double res = 0.0;
		double[] c = getCoefficients();
		for (int i = 0; i < c.length; i++) {
			res += c[i] * Math.pow(x, i);
		}
		return res;
	}

	public boolean hasRoot(double x) {
		return Math.abs(x) < 1e6;
	}
}