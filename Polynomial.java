
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Polynomial {
	private double[] coefficients;
	private int[] exponents;

	public Polynomial() {
		coefficients = new double[0];
		exponents = new int[0];
	}

	public Polynomial(double[] coefficients, int[] exponents) {
		this.coefficients = coefficients;
		this.exponents = exponents;
	}

	public Polynomial(File file) {
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			String s = scanner.nextLine();
			Map<Integer, Double> m = new TreeMap<>();
			String coe = "";
			String exp = "";
			boolean isCoe = true;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '-' || s.charAt(i) == '+') {
					if (coe.length() > 0) {
						if (exp.length() == 0) {
							exp = "0";
						}
						m.put(Integer.parseInt(exp), Double.parseDouble(coe));
					}
					coe = "";
					exp = "";
					isCoe = true;
					coe += s.charAt(i);
				} else if (s.charAt(i) == 'x') {
					isCoe = false;
				} else {
					if (isCoe) {
						coe += s.charAt(i);
					} else {
						exp += s.charAt(i);
					}
				}
			}
			if (coe.length() > 0) {
				if (exp.length() == 0) {
					exp = "0";
				}
				m.put(Integer.parseInt(exp), Double.parseDouble(coe));
			}

			coefficients = new double[m.size()];
			exponents = new int[m.size()];
			int index = 0;
			for (int e : m.keySet()) {
				exponents[index] = e;
				coefficients[index] = m.get(e);
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Polynomial add(Polynomial p) {
		Map<Integer, Double> m = new TreeMap<>();
		int length;
		if (coefficients.length < p.coefficients.length) {
			length = p.coefficients.length;
		} else {
			length = coefficients.length;
		}

		for (int i = 0; i < length; i++) {
			if (i < exponents.length) {
				m.put(exponents[i], m.getOrDefault(exponents[i], 0.0) + coefficients[i]);
			}
			if (i < p.exponents.length) {
				m.put(p.exponents[i], m.getOrDefault(p.exponents[i], 0.0) + p.coefficients[i]);
			}
		}
		double[] coe = new double[m.size()];
		int[] exp = new int[m.size()];
		int index = 0;
		for (int e : m.keySet()) {
			exp[index] = e;
			coe[index] = m.get(e);
			index++;
		}
		return new Polynomial(coe, exp);
	}

	public double evaluate(double x) {
		double res = 0.0;
		for (int i = 0; i < coefficients.length; i++) {
			res += coefficients[i] * Math.pow(x, exponents[i]);
		}
		return res;
	}

	public boolean hasRoot(double x) {
		return Math.abs(evaluate(x)) < 1e-6;
	}

	public Polynomial multiply(Polynomial p) {
		Map<Integer, Double> m = new TreeMap<>();
		for (int i = 0; i < exponents.length; i++) {
			for (int j = 0; j < p.exponents.length; j++) {
				m.put(exponents[i] + p.exponents[j],
						m.getOrDefault(exponents[i] + p.exponents[j], 0.0) + coefficients[i] * p.coefficients[j]);
			}
		}
		double[] coe = new double[m.size()];
		int[] exp = new int[m.size()];
		int index = 0;
		for (int e : m.keySet()) {
			exp[index] = e;
			coe[index] = m.get(e);
			index++;
		}
		return new Polynomial(coe, exp);
	}

	public void saveToFile(String path) {
		String out = "";
		for (int i = 0; i < coefficients.length; i++) {
			if (exponents[i] == 0) {
				out += coefficients[i];
			} else if (coefficients[i] > 0) {
				out += "+" + coefficients[i] + "x" + exponents[i];
			} else {
				out += coefficients[i] + "x" + exponents[i];
			}
		}
		System.out.println(out);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.append(out + "\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// read from file
}