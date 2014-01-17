package neuro_system;

class Matrix_Multiplication {
	double multiply[][];

	Matrix_Multiplication(double[][] A, double[][] AT) {

		int m, n, p, q, c, d, k;
		double sum = 0;

		// first matrix
		m = AT.length;
		n = AT[0].length;

		double[][] first = new double[m][n];
		first = AT;

		p = A.length;
		q = A[0].length;

		if (n != p)
			System.out
					.println("Matrices with entered orders can't be multiplied with each other.");
		else {
			double second[][] = new double[p][q];
			multiply = new double[m][q];

			second = A;

			for (c = 0; c < m; c++) {
				for (d = 0; d < q; d++) {
					for (k = 0; k < p; k++) {
						sum = sum + first[c][k] * second[k][d];
					}

					multiply[c][d] = sum;
					sum = 0;
				}
			}

//			System.out.println("Product of entered matrices:-");

//			for (c = 0; c < m; c++) {
//				for (d = 0; d < q; d++)
//					System.out.print(multiply[c][d] + "\t");
//
//				System.out.print("\n");
//			}
		}
	}
}