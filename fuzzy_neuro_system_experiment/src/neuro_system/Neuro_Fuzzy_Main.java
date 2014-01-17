package neuro_system;

import java.util.ArrayList;
import java.util.Scanner;

public class Neuro_Fuzzy_Main {

	static double ConvertTimeToDouble(String time) {
		String[] splitData = time.split(":");
		double doubleTime = 0;
		doubleTime = Double.parseDouble(splitData[0])
				+ (Double.parseDouble(splitData[1]) / 60);
		return doubleTime;

	}

	public static void main(String[] args) {

		double[] inputs = new double[2];
		String[] stringInput = new String[2];
		stringInput[0] = "5:15";// Here are the inputs given first one is the
								// start
		// time and the last one is the end time.
		stringInput[1] = "15:15";

		inputs[0] = ConvertTimeToDouble(stringInput[0]);
		inputs[1] = ConvertTimeToDouble(stringInput[1]);
		ArrayList<Double> coefficient = new ArrayList<Double>();
		ArrayList<Double> consequentParamPL31TOHN1 = new ArrayList<Double>();
		ArrayList<Double> consequentParamPL31TOHN2 = new ArrayList<Double>();
		double outputSumPL31ToHN1 = 0;
		double outputSumPL31ToHN2 = 0;
		// double[][] matrix = new double[50][30];

		Perceptron p = new Perceptron();
		consequentParamPL31TOHN1 = p
				.TrainWithFile("F:\\Masters Courses\\Hiwi\\fuzzy neural net\\database\\Movement_PL31_TO_HN1.txt");
		consequentParamPL31TOHN2 = p
				.TrainWithFile("F:\\Masters Courses\\Hiwi\\fuzzy neural net\\database\\Movement_PL31_TO_HN2.txt");// here
																													// you
																													// give
																													// the
																													// File
																													// path
																													// of
																													// the
																													// data
																													// generated
																													// from
																													// Process_Input_File_simple_conditions.java
		// consequentParam = p.train(41328);
		// System.out.println(consequentParam.size() + "\n" + consequentParam);
		// double[] points = { 50, -12, 1 };
		// ArrayList<Double> layer2Result = p.feedforwardLayerTwo();
		// ArrayList<Double> layer3Result = p.feedforwardLayerThree();
		// for (int i = 0; i < layer2Result.size(); i++)
		// layer2Sum += layer2Result.get(i);
		// System.out.println(layer3Result.size());
		
		Perceptron pNew = new Perceptron();

		pNew.LayerOne(inputs);
		// System.out.println(p.LayerOne(inputs));

		coefficient = pNew.consequentParamCoefficient;
		// System.out.println(coefficient.size() + "\n" + coefficient);
		// System.out.println(consequentParam.size() + "\n" +
		// consequentParam);
		for (int i = 0; i < consequentParamPL31TOHN1.size(); i++) {
			outputSumPL31ToHN1 += consequentParamPL31TOHN1.get(i) * coefficient.get(i);
			outputSumPL31ToHN2 += consequentParamPL31TOHN2.get(i) * coefficient.get(i);
		}
		System.out.println("Predicted number of people Movement:\n \tPL31 to HN1: "+ outputSumPL31ToHN1+"\n\tPL31 to HN2: "+outputSumPL31ToHN2);
		// System.out.println(p.layerThreeNormWeight);
		// System.out.println(p.weightsForTime1[0]);
		// System.out.println(p.weightsForTime2[1]);

	}
}
