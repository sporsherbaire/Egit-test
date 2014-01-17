package neuro_system;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Matrix_Trainer_for_Consequent_File_Input {

	public ArrayList<Double> consequentParams = new ArrayList<Double>();

	// final goal x =(AT*A)^{-1}AT*b

	public Matrix_Trainer_for_Consequent_File_Input(String inputFilePath)
			throws FileNotFoundException {

		Perceptron p;
		ArrayList<Double> aL;
		int numOfData = 0;

		try {
			InputStream is = new FileInputStream(inputFilePath);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			while (br.readLine() != null)
				numOfData++;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int rowOfA = numOfData, columnOfA = 45;
		int rowOfAT = columnOfA, columnOfAT = rowOfA;
		double[] input = new double[2];
		double[][] Asample = new double[rowOfA][columnOfA];
		double[][] A = new double[rowOfA][columnOfA]; // create memory space for
														// entire
		double[][] AT = new double[columnOfA][rowOfA]; // matrix
		double[][] b = new double[rowOfA][1];
		double[][] AT_A = new double[rowOfAT][columnOfA];
		double[][] AT_A_Inv = new double[rowOfAT][columnOfA];
		double[][] AT_A_Inv_multi_AT = new double[rowOfAT][columnOfA];
		double[][] AT_A_Inv_multi_AT_b = new double[rowOfA][1];

		// Fill the matrix with the values from the Input File
		try {
			InputStream is = new FileInputStream(inputFilePath);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			int row = 0;
			while ((line = br.readLine()) != null) {
				String[] splitData = line.split("\t");
				p = new Perceptron();
				aL = new ArrayList<Double>();
				input[0] = Double.parseDouble(splitData[0]);
				input[1] = Double.parseDouble(splitData[1]);
				b[row][0] = Double.parseDouble(splitData[2]);
				p.LayerOne(input);
				aL = p.consequentParamCoefficient;
				// System.out.println(aL.size());
				// System.out.println(aL);
				// int j = 0;
				for (int column = 0; column < columnOfA; column++) {

					A[row][column] = aL.get(column);
					// // if ((column + 1) < columnOfA)
					// A[row][column + 1] = aL.get(j) * input[1];
					// j++;
					// // AT[column][row] = A[row][column];
				}
				row++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int row, column;
		for (row = 0; row < rowOfA; row++) {
			

		}
		// for (row = 0; row < rowOfA; row++)
		// for (column = 0; column < columnOfA; column++) {
		// A[row][column] = 0 + (Math.random() * 24);
		// }

		// transverse of matrix A
		for (row = 0; row < rowOfA; row++)
			for (column = 0; column < columnOfA; column++) {
				AT[column][row] = A[row][column];
			} // Print averages only

		// for (row = 0; row < rowOfA; row++)
		// b[row][0] = 0 + (int) (Math.random() * 10);
		// System.out.println("Value of b: ");
		// for (row = 0; row < rowOfA; row++)
		// System.out.print(b[row][0] + "\n");
		// printing both matrices
		// for (row = 0; row < rowOfA; row++) {
		// for (column = 0; column < columnOfA; column++)
		// System.out.print(A[row][column] + "\t");
		//
		// System.out.print("\n");
		//
		// }
		// for (row = 0; row < rowOfAT; row++)
		// for (column = 0; column < columnOfAT; column++) {
		// if (column != 2)
		// System.out.print(AT[row][column] + "   ");
		// else
		// System.out.print(AT[row][column] + "\n");
		// }

		// multiplication of AT *A
		Matrix_Multiplication multi = new Matrix_Multiplication(A, AT);
		AT_A = multi.multiply;
		Inverse_matrix inv = new Inverse_matrix(AT_A);
		AT_A_Inv = inv.inverse;
		Matrix_Multiplication multi2 = new Matrix_Multiplication(AT, AT_A_Inv);
		AT_A_Inv_multi_AT = multi2.multiply;
		Matrix_Multiplication multi3 = new Matrix_Multiplication(b,
				AT_A_Inv_multi_AT);
		AT_A_Inv_multi_AT_b = multi3.multiply;
		// System.out.println("\nfinal output matrix: size("
		// + AT_A_Inv_multi_AT_b.length + ")");
		for (int c = 0; c < AT_A_Inv_multi_AT_b.length; c++) {
			for (int d = 0; d < AT_A_Inv_multi_AT_b[0].length; d++) {
				consequentParams.add(AT_A_Inv_multi_AT_b[c][d]);
				// System.out.print(AT_A_Inv_multi_AT_b[c][d] + "\t");
			}
			// System.out.print("\n");
		}
	}
}
