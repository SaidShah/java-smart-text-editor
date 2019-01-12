package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class TimeCalculations implements Serializable {

	long[] oneLoopArrayHundredWords;
	long[] threeLoopArrayHundredWords;
	long[] oneLoopThousandWordArray;
	long[] threeLoopThousandWordArray;
	long[] oneLoopTenThousandWordArray;
	long[] threeLoopTenThousandWordArray;
	long[] oneLoopHundredThousandWordArray;
	long[] threeLoopHundredThousandWordArray;
	int oneLoopCounterHundredWords;
	int threeLoopHundredWordCounter;
	int oneLoopThousandWordArrayCounter;
	int threeLoopThousandWordArrayCounter;
	int oneLoopTenThousandWordArrayCounter;
	int threeLoopTenThousandWordArrayCounter;
	int oneLoopHundredThousandWordArrayCounter;
	int threeLoopHundredThousandWordArrayCounter;

	public TimeCalculations() {
		oneLoopArrayHundredWords = new long[101];
		threeLoopArrayHundredWords = new long[101];
		oneLoopThousandWordArray = new long[1001];
		threeLoopThousandWordArray = new long[1001];
		oneLoopTenThousandWordArray = new long[10001];
		threeLoopTenThousandWordArray = new long[10001];
		oneLoopHundredThousandWordArray = new long[100001];
		threeLoopHundredThousandWordArray = new long[100001];
		oneLoopCounterHundredWords = 0;
		threeLoopHundredWordCounter = 0;
		oneLoopThousandWordArrayCounter = 0;
		threeLoopThousandWordArrayCounter = 0;
		oneLoopTenThousandWordArrayCounter = 0;
		threeLoopTenThousandWordArrayCounter = 0;
		oneLoopHundredThousandWordArrayCounter = 0;
		threeLoopHundredThousandWordArrayCounter = 0;
	}

	public void insertOneLoopHundredWordTime(long time) {
		oneLoopArrayHundredWords[oneLoopCounterHundredWords++] = time;
	}

	public void insertThreeLoopHundredWordTime(long time) {
		threeLoopArrayHundredWords[threeLoopHundredWordCounter++] = time;
	}

	public void insertOneLoopThousandWordTime(long time) {
		oneLoopThousandWordArray[oneLoopThousandWordArrayCounter++] = time;
	}

	public void insertThreeLoopThousandWordTime(long time) {
		threeLoopThousandWordArray[threeLoopThousandWordArrayCounter++] = time;
	}

	public void insertOneLoopTenThousandWordTime(long time) {
		oneLoopTenThousandWordArray[oneLoopTenThousandWordArrayCounter++] = time;
	}

	public void insertThreeLoopTenThousandWordTime(long time) {
		threeLoopTenThousandWordArray[threeLoopTenThousandWordArrayCounter++] = time;
	}

	public void insertOneLoopHundredThousandWordTime(long time) {
		oneLoopHundredThousandWordArray[oneLoopHundredThousandWordArrayCounter++] = time;
	}

	public void insertThreeLoopHundredThousandWordTime(long time) {
		threeLoopHundredThousandWordArray[threeLoopHundredThousandWordArrayCounter++] = time;
	}

	public long[] getOneLoopHundredWordArray() {
		return Arrays.copyOf(oneLoopArrayHundredWords, oneLoopCounterHundredWords);
	}

	public long[] getThreeLoopHundredWordArray() {
		return Arrays.copyOf(threeLoopArrayHundredWords, threeLoopHundredWordCounter);
	}

	public long[] getThousandWordOneLoopArray() {
		return Arrays.copyOf(oneLoopThousandWordArray, oneLoopThousandWordArrayCounter);
	}

	public long[] getThousandWordThreeLoopArray() {
		return Arrays.copyOf(threeLoopThousandWordArray, threeLoopThousandWordArrayCounter);
	}

	public long[] getTenThousandWordOneLoopArray() {
		return Arrays.copyOf(oneLoopTenThousandWordArray, oneLoopTenThousandWordArrayCounter);
	}

	public long[] getTenThousandWordThreeLoopArray() {
		return Arrays.copyOf(threeLoopTenThousandWordArray, threeLoopTenThousandWordArrayCounter);
	}

	public long[] getHundredThousandWordOneLoopArray() {
		return Arrays.copyOf(oneLoopHundredThousandWordArray, oneLoopHundredThousandWordArrayCounter);
	}

	public long[] getHundredThousandWordThreeLoopArray() {
		return Arrays.copyOf(threeLoopHundredThousandWordArray, threeLoopHundredThousandWordArrayCounter);
	}

	public void displayHundredWordOneLoopTimes() {
		for (int i = 0; i < oneLoopCounterHundredWords; i++) {
			System.out.println(oneLoopArrayHundredWords[i]);
		}
	}

	public void displayHundredWordThreeLoopTimes() {
		for (int i = 0; i < threeLoopHundredWordCounter; i++) {
			System.out.println(threeLoopArrayHundredWords[i]);
		}
	}

	public int getOneLoopCounterHundredWords() {
		return oneLoopCounterHundredWords;
	}

	public int getThreeLoopHundredWordCounter() {
		return threeLoopHundredWordCounter;
	}

	public int getOneLoopThousandWordArrayCounter() {
		return oneLoopThousandWordArrayCounter;
	}

	public int getThreeLoopThousandWordArrayCounter() {
		return threeLoopThousandWordArrayCounter;
	}

	public int getOneLoopTenThousandWordArrayCounter() {
		return oneLoopTenThousandWordArrayCounter;
	}

	public int getThreeLoopTenThousandWordArrayCounter() {
		return threeLoopTenThousandWordArrayCounter;
	}

	public int getOneLoopHundredThousandWordArrayCounter() {
		return oneLoopHundredThousandWordArrayCounter;
	}

	public int getThreeLoopHundredThousandWordArrayCounter() {
		return threeLoopHundredThousandWordArrayCounter;
	}

	public void saveHundredWordOneLoopArray() {
		try {
			FileOutputStream oneLoopHundredWordsCounterFile = new FileOutputStream(
					"InputData/OneLoopOneHundredWordsCounter.dat");
			DataOutputStream oneLoopHundredWordsCounterStream = new DataOutputStream(oneLoopHundredWordsCounterFile);
			FileOutputStream oneLoopHundredWordArrayFile = new FileOutputStream(
					"InputData/OneLoopOneHundredWordsArray.dat");
			ObjectOutputStream oneLoopHundredWordArrayStream = new ObjectOutputStream(oneLoopHundredWordArrayFile);
			oneLoopHundredWordArrayStream.writeObject(oneLoopArrayHundredWords);
			oneLoopHundredWordArrayStream.close();
			oneLoopHundredWordsCounterStream.writeInt(oneLoopCounterHundredWords);
			oneLoopHundredWordsCounterStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadHundredWordOneLoopArray() {

		try {
			FileInputStream OneLoophundredWordArrayFile = new FileInputStream(
					"InputData/OneLoopOneHundredWordsArray.dat");
			ObjectInputStream OneLoophundredWordArrayStream = new ObjectInputStream(OneLoophundredWordArrayFile);
			FileInputStream oneLoopHundredWordsCounterFile = new FileInputStream(
					"InputData/OneLoopOneHundredWordsCounter.dat");
			DataInputStream oneLoopHundredWordsCounterStream = new DataInputStream(oneLoopHundredWordsCounterFile);
			oneLoopCounterHundredWords = oneLoopHundredWordsCounterStream.readInt();
			oneLoopArrayHundredWords = (long[]) OneLoophundredWordArrayStream.readObject();
			OneLoophundredWordArrayStream.close();
			oneLoopHundredWordsCounterStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveHundredWordThreeLoopArray() {
		try {
			FileOutputStream threeLoopHundredWordsCounterFile = new FileOutputStream(
					"InputData/threeLoopOneHundredWordsCounter.dat");
			DataOutputStream threeLoopHundredWordsCounterStream = new DataOutputStream(
					threeLoopHundredWordsCounterFile);
			FileOutputStream threeLoopHundredWordArrayFile = new FileOutputStream(
					"InputData/threeLoopOneHundredWordsArray.dat");
			ObjectOutputStream threeLoopHundredWordArrayStream = new ObjectOutputStream(threeLoopHundredWordArrayFile);
			threeLoopHundredWordsCounterStream.writeInt(threeLoopHundredWordCounter);
			threeLoopHundredWordsCounterStream.close();
			threeLoopHundredWordArrayStream.writeObject(threeLoopArrayHundredWords);
			threeLoopHundredWordArrayStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadHundredWordThreeLoopArray() {

		try {
			FileInputStream threeLoophundredWordArrayFile = new FileInputStream(
					"InputData/threeLoopOneHundredWordsArray.dat");
			ObjectInputStream threeLoophundredWordArrayStream = new ObjectInputStream(threeLoophundredWordArrayFile);
			FileInputStream threeLoopHundredWordsCounterFile = new FileInputStream(
					"InputData/threeLoopOneHundredWordsCounter.dat");
			DataInputStream threeLoopHundredWordsCounterStream = new DataInputStream(threeLoopHundredWordsCounterFile);
			threeLoopHundredWordCounter = threeLoopHundredWordsCounterStream.readInt();
			threeLoopArrayHundredWords = (long[]) threeLoophundredWordArrayStream.readObject();
			threeLoophundredWordArrayStream.close();
			threeLoopHundredWordsCounterStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveThousandWordOneLoopArray() {
		try {
			FileOutputStream oneLoopThousandWordsCounterFile = new FileOutputStream(
					"InputData/OneLoopOneThousandWordsCounter.dat");
			DataOutputStream oneLoopThousandHundredWordsCounterStream = new DataOutputStream(
					oneLoopThousandWordsCounterFile);
			FileOutputStream oneLoopThousandWordArrayFile = new FileOutputStream(
					"InputData/OneLoopOneThousandWordsArray.dat");
			ObjectOutputStream oneLoopThousandWordArrayStream = new ObjectOutputStream(oneLoopThousandWordArrayFile);
			oneLoopThousandWordArrayStream.writeObject(oneLoopThousandWordArray);
			oneLoopThousandWordArrayStream.close();
			oneLoopThousandHundredWordsCounterStream.writeInt(oneLoopThousandWordArrayCounter);
			oneLoopThousandHundredWordsCounterStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadThousandWordOneLoopArray() {

		try {
			FileInputStream OneLoopThousandWordArrayFile = new FileInputStream(
					"InputData/OneLoopOneThousandWordsArray.dat");
			ObjectInputStream OneLoopThousandWordArrayStream = new ObjectInputStream(OneLoopThousandWordArrayFile);
			FileInputStream oneLoopThousandWordsCounterFile = new FileInputStream(
					"InputData/OneLoopOneThousandWordsCounter.dat");
			DataInputStream oneLoopThousandWordsCounterStream = new DataInputStream(oneLoopThousandWordsCounterFile);
			oneLoopThousandWordArrayCounter = oneLoopThousandWordsCounterStream.readInt();
			oneLoopThousandWordArray = (long[]) OneLoopThousandWordArrayStream.readObject();
			OneLoopThousandWordArrayStream.close();
			oneLoopThousandWordsCounterStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveThousandWordThreeLoopArray() {
		try {
			FileOutputStream ThreeLoopThousandWordsCounterFile = new FileOutputStream(
					"InputData/ThreeLoopOneThousandWordsCounter.dat");
			DataOutputStream ThreeLoopThousandHundredWordsCounterStream = new DataOutputStream(
					ThreeLoopThousandWordsCounterFile);
			FileOutputStream ThreeLoopThousandWordArrayFile = new FileOutputStream(
					"InputData/ThreeLoopOneThousandWordsArray.dat");
			ObjectOutputStream ThreeLoopThousandWordArrayStream = new ObjectOutputStream(
					ThreeLoopThousandWordArrayFile);
			ThreeLoopThousandWordArrayStream.writeObject(threeLoopThousandWordArray);
			ThreeLoopThousandWordArrayStream.close();
			ThreeLoopThousandHundredWordsCounterStream.writeInt(threeLoopThousandWordArrayCounter);
			ThreeLoopThousandHundredWordsCounterStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadThousandWordThreeLoopArray() {

		try {
			FileInputStream threeLoopThousandWordArrayFile = new FileInputStream(
					"InputData/ThreeLoopOneThousandWordsArray.dat");
			ObjectInputStream threeLoopThousandWordArrayStream = new ObjectInputStream(threeLoopThousandWordArrayFile);
			FileInputStream threeLoopThousandWordsCounterFile = new FileInputStream(
					"InputData/ThreeLoopOneThousandWordsCounter.dat");
			DataInputStream threeLoopThousandWordsCounterStream = new DataInputStream(
					threeLoopThousandWordsCounterFile);
			threeLoopThousandWordArrayCounter = threeLoopThousandWordsCounterStream.readInt();
			threeLoopThousandWordArray = (long[]) threeLoopThousandWordArrayStream.readObject();
			threeLoopThousandWordArrayStream.close();
			threeLoopThousandWordsCounterStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveTenThousandWordOneLoopArray() {
		try {
			FileOutputStream oneLoopTenThousandWordsCounterFile = new FileOutputStream(
					"InputData/oneLoopTenThousandWordsCounter.dat");
			DataOutputStream oneLoopTenThousandWordsCounterStream = new DataOutputStream(
					oneLoopTenThousandWordsCounterFile);
			FileOutputStream oneLoopTenThousandWordArrayFile = new FileOutputStream(
					"InputData/oneLoopTenThousandWordsArray.dat");
			ObjectOutputStream oneLoopTenThousandWordArrayStream = new ObjectOutputStream(
					oneLoopTenThousandWordArrayFile);
			oneLoopTenThousandWordArrayStream.writeObject(oneLoopTenThousandWordArray);
			oneLoopTenThousandWordArrayStream.close();
			oneLoopTenThousandWordsCounterStream.writeInt(oneLoopTenThousandWordArrayCounter);
			oneLoopTenThousandWordsCounterStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadTenThousandWordOneLoopArray() {

		try {
			FileInputStream OneLoopTenThousandWordArrayFile = new FileInputStream(
					"InputData/oneLoopTenThousandWordsArray.dat");
			ObjectInputStream OneLoopTenThousandWordArrayStream = new ObjectInputStream(
					OneLoopTenThousandWordArrayFile);
			FileInputStream oneLoopTenThousandWordsCounterFile = new FileInputStream(
					"InputData/oneLoopTenThousandWordsCounter.dat");
			DataInputStream oneLoopTenThousandWordsCounterStream = new DataInputStream(
					oneLoopTenThousandWordsCounterFile);
			oneLoopTenThousandWordArrayCounter = oneLoopTenThousandWordsCounterStream.readInt();
			oneLoopTenThousandWordArray = (long[]) OneLoopTenThousandWordArrayStream.readObject();
			oneLoopTenThousandWordsCounterStream.close();
			OneLoopTenThousandWordArrayStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveTenThousandWordThreeLoopArray() {
		try {
			FileOutputStream ThreeLoopTenThousandWordsCounterFile = new FileOutputStream(
					"InputData/ThreeLoopTenThousandWordsCounter.dat");
			DataOutputStream ThreeLoopTenThousandWordsCounterStream = new DataOutputStream(
					ThreeLoopTenThousandWordsCounterFile);
			FileOutputStream ThreeLoopTenThousandWordArrayFile = new FileOutputStream(
					"InputData/ThreeLoopTenThousandWordsArray.dat");
			ObjectOutputStream ThreeLoopTenThousandWordArrayStream = new ObjectOutputStream(
					ThreeLoopTenThousandWordArrayFile);
			ThreeLoopTenThousandWordArrayStream.writeObject(threeLoopTenThousandWordArray);
			ThreeLoopTenThousandWordArrayStream.close();
			ThreeLoopTenThousandWordsCounterStream.writeInt(threeLoopTenThousandWordArrayCounter);
			ThreeLoopTenThousandWordsCounterStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadTenThousandWordThreeLoopArray() {

		try {
			FileInputStream threeLoopTenThousandWordArrayFile = new FileInputStream(
					"InputData/ThreeLoopTenThousandWordsArray.dat");
			ObjectInputStream threeLoopTenThousandWordArrayStream = new ObjectInputStream(
					threeLoopTenThousandWordArrayFile);
			FileInputStream threeLoopTenThousandWordsCounterFile = new FileInputStream(
					"InputData/ThreeLoopTenThousandWordsCounter.dat");
			DataInputStream threeLoopTenThousandWordsCounterStream = new DataInputStream(
					threeLoopTenThousandWordsCounterFile);
			threeLoopTenThousandWordArrayCounter = threeLoopTenThousandWordsCounterStream.readInt();
			threeLoopTenThousandWordArray = (long[]) threeLoopTenThousandWordArrayStream.readObject();
			threeLoopTenThousandWordArrayStream.close();
			threeLoopTenThousandWordsCounterStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveHundredThousandWordOneLoopArray() {
		try {
			FileOutputStream oneLoopHundredThousandWordsCounterFile = new FileOutputStream(
					"InputData/oneLoopHundredThousandWordsCounter.dat");
			DataOutputStream oneLoopHundredThousandWordsCounterStream = new DataOutputStream(
					oneLoopHundredThousandWordsCounterFile);
			FileOutputStream oneLoopHundredThousandWordArrayFile = new FileOutputStream(
					"InputData/oneLoopHundredThousandWordsArray.dat");
			ObjectOutputStream oneLoopHundredThousandWordArrayStream = new ObjectOutputStream(
					oneLoopHundredThousandWordArrayFile);
			oneLoopHundredThousandWordArrayStream.writeObject(oneLoopHundredThousandWordArray);
			oneLoopHundredThousandWordArrayStream.close();
			oneLoopHundredThousandWordsCounterStream.writeInt(oneLoopHundredThousandWordArrayCounter);
			oneLoopHundredThousandWordsCounterStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadHundredThousandWordOneLoopArray() {

		try {
			FileInputStream OneLoopHundredThousandWordArrayFile = new FileInputStream(
					"InputData/oneLoopHundredThousandWordsArray.dat");
			ObjectInputStream OneLoopHundredThousandWordArrayStream = new ObjectInputStream(
					OneLoopHundredThousandWordArrayFile);
			FileInputStream oneLoopHundredThousandWordsCounterFile = new FileInputStream(
					"InputData/oneLoopHundredThousandWordsCounter.dat");
			DataInputStream oneLoopHundredThousandWordsCounterStream = new DataInputStream(
					oneLoopHundredThousandWordsCounterFile);
			oneLoopHundredThousandWordArrayCounter = oneLoopHundredThousandWordsCounterStream.readInt();
			oneLoopHundredThousandWordArray = (long[]) OneLoopHundredThousandWordArrayStream.readObject();
			oneLoopHundredThousandWordsCounterStream.close();
			OneLoopHundredThousandWordArrayStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveHundredThousandWordThreeLoopArray() {
		try {
			FileOutputStream ThreeLoopHundredThousandWordsCounterFile = new FileOutputStream(
					"InputData/ThreeLoopHundredThousandWordsCounter.dat");
			DataOutputStream ThreeLoopHundredThousandWordsCounterStream = new DataOutputStream(
					ThreeLoopHundredThousandWordsCounterFile);
			FileOutputStream ThreeLoopHundredThousandWordArrayFile = new FileOutputStream(
					"InputData/ThreeLoopHundredThousandWordsArray.dat");
			ObjectOutputStream ThreeLoopHundredThousandWordArrayStream = new ObjectOutputStream(
					ThreeLoopHundredThousandWordArrayFile);
			ThreeLoopHundredThousandWordArrayStream.writeObject(threeLoopHundredThousandWordArray);
			ThreeLoopHundredThousandWordArrayStream.close();
			ThreeLoopHundredThousandWordsCounterStream.writeInt(threeLoopHundredThousandWordArrayCounter);
			ThreeLoopHundredThousandWordsCounterStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadHundredThousandWordThreeLoopArray() {

		try {
			FileInputStream threeLoopHundredThousandWordArrayFile = new FileInputStream(
					"InputData/ThreeLoopHundredThousandWordsArray.dat");
			ObjectInputStream threeLoopHundredThousandWordArrayStream = new ObjectInputStream(
					threeLoopHundredThousandWordArrayFile);
			FileInputStream threeLoopHundredThousandWordsCounterFile = new FileInputStream(
					"InputData/ThreeLoopHundredThousandWordsCounter.dat");
			DataInputStream threeLoopHundredThousandWordsCounterStream = new DataInputStream(
					threeLoopHundredThousandWordsCounterFile);
			threeLoopHundredThousandWordArrayCounter = threeLoopHundredThousandWordsCounterStream.readInt();
			threeLoopHundredThousandWordArray = (long[]) threeLoopHundredThousandWordArrayStream.readObject();
			threeLoopHundredThousandWordArrayStream.close();
			threeLoopHundredThousandWordsCounterStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
