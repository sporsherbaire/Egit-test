package neuro_system;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class Perceptron {
	// [full] The Perceptron stores its weights and learning constants.
	double[] weightsForTime1, weightsForTime2;
	double[] time = new double[3];;
	ArrayList<Double> layerTwoWeight = new ArrayList<Double>();
	ArrayList<Double> layerThreeNormWeight = new ArrayList<Double>();
	ArrayList<Double> changedWeight = new ArrayList<Double>();
	ArrayList<Double> consequentParamCoefficient = new ArrayList<Double>();

	// double bias = 1;

	// processing the membership function
	double BellFunction(double x, double a, double c) {
		double membership = 0;
		membership = 1 / (1 + Math.pow(Math.abs((x - c) / a), 8));
		return membership;
	}

	// double outputMemFunction(int numPerson) {
	// double membership = 0;
	// membership = numPerson / 10;
	// return membership;
	// }
	//
	// // defuzzification/ activation function of the output
	// int crispOutput(double fuzzyOutput) {
	// String tmpNumOfPeople = String.valueOf(fuzzyOutput * 10);
	// return Integer.parseInt(tmpNumOfPeople);
	// }

	// layer one getting the membership
	double LayerOne(double[] inputs) {
		layerTwoWeight = new ArrayList<Double>();
		layerThreeNormWeight = new ArrayList<Double>();
		changedWeight = new ArrayList<Double>();
		consequentParamCoefficient = new ArrayList<Double>();
		weightsForTime1 = new double[5];
		weightsForTime2 = new double[5];
		time[0] = inputs[0];
		time[1] = inputs[1];
		time[2] = 1;
		// [full] Weights start off random.
		for (int i = 0; i < 2; i++) {

			// wights for all 5 regions
			if (i == 0) {
				weightsForTime1[0] = BellFunction(time[i], 5, 4);
				weightsForTime1[1] = BellFunction(time[i], 1.5, 10.5);
				weightsForTime1[2] = BellFunction(time[i], 1, 13);
				weightsForTime1[3] = BellFunction(time[i], 2, 16);
				weightsForTime1[4] = BellFunction(time[i], 3.5, 21.5);
			} else if (i == 1) {

				weightsForTime2[0] = BellFunction(time[i], 5, 4);
				weightsForTime2[1] = BellFunction(time[i], 1.5, 10.5);
				weightsForTime2[2] = BellFunction(time[i], 1, 13);
				weightsForTime2[3] = BellFunction(time[i], 2, 16);
				weightsForTime2[4] = BellFunction(time[i], 3.5, 21.5);
			}
		}
//		for (int i = 0; i < 5; i++)
//			System.out.println(weightsForTime1[i]);
//		for (int i = 0; i < 5; i++)
//			System.out.println(weightsForTime2[i]);
		feedforwardLayerTwo();
		feedforwardLayerThree();
		double output = LayerFour(layerThreeNormWeight);

		// for (int i = 0; i < layerThreeNormWeight.size(); i++) {
		// // if (layerThreeNormWeight.get(i) > 0.01)
		// // System.out.println(layerThreeNormWeight.get(i));
		// }
		return output;
	}

	// [full] Return an output based on inputs.
	ArrayList<Double> feedforwardLayerTwo() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (j >= i) {
					Double tempWeight = weightsForTime1[i] * weightsForTime2[j];
					layerTwoWeight.add(tempWeight);
				}
			}

		}
		return layerTwoWeight;
	}

	// cumputes the normalized wieght for layer 3
	ArrayList<Double> feedforwardLayerThree() {
		double layer2Sum = 0;
		for (int i = 0; i < layerTwoWeight.size(); i++)
			layer2Sum += layerTwoWeight.get(i);
		for (int i = 0; i < layerTwoWeight.size(); i++)
			layerThreeNormWeight.add(layerTwoWeight.get(i) / layer2Sum);
		return layerThreeNormWeight;
	}

	// Calculating the output with a bias
	double LayerFour(ArrayList<Double> weight) {
		double outputSum = 0;
		for (int i = 0; i < weight.size(); i++) {
			consequentParamCoefficient.add(weight.get(i) * time[0]);
			consequentParamCoefficient.add(weight.get(i) * time[1]);
			consequentParamCoefficient.add(weight.get(i) * time[2]);
			outputSum += weight.get(i) * time[0] + weight.get(i) * time[1]+ weight.get(i) * time[2];
		}
		// outputSum += bias;
		return outputSum;
	}

	// [full] Train the network against known data.
	ArrayList<Double> train(int numOfData) {
		Matrix_Trainer_for_Consequent mT = new Matrix_Trainer_for_Consequent(
				numOfData);

		return mT.consequentParams;
	}
	ArrayList<Double> TrainWithFile(String filePath) {
		Matrix_Trainer_for_Consequent_File_Input mT = null;
		try {
			mT = new Matrix_Trainer_for_Consequent_File_Input(
					filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return mT.consequentParams;
	}
	// [end]
}