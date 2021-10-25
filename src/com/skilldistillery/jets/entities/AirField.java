package com.skilldistillery.jets.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirField {
	List<Jet> jets = new ArrayList<>();
	List<Jet> passengerJets = new ArrayList<>();
	List<Jet> fighterJets = new ArrayList<>();

	private Jet fastestJet;
	private Jet longestRangeJet;

	public AirField() {
		loadJetsFromFile();
	}

	private void loadJetsFromFile() {
		try (BufferedReader bufIn = new BufferedReader(new FileReader("jets.txt"))) {
			String line;
			String[] split;
			while ((line = bufIn.readLine()) != null) {
				split = line.split(",");
				buildJet(split);
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void calculateJets() {
		if (jets.size() <= 0) {
			return;
		}
		if (fastestJet == null) {
			fastestJet = jets.get(0);
		}
		if (longestRangeJet == null) {
			longestRangeJet = jets.get(0);
		}
		
		Jet jet = jets.get(jets.size() - 1);

		if (jet.getSpeed() > fastestJet.getSpeed()) {
			fastestJet = jet;
		}
		if (jet.getRange() > longestRangeJet.getRange()) {
			longestRangeJet = jet;
		}

		if (jet instanceof JetPassenger) {
			passengerJets.add(jet);
		} else if (jet instanceof JetFighter) {
			fighterJets.add(jet);
		}
	}

	private void buildJet(String[] stats) {
		double speed = Double.parseDouble(stats[2]);
		int range = Integer.parseInt(stats[3]);
		long price = Long.parseLong(stats[4]);
		buildJet(stats[0], stats[1], speed, range, price);

	}

	private void buildJet(String type, String model, double speed, int range, long price) {
		switch (type) {
		case "Passenger":
		case "passenger":
		case "P":
		case "p":
			jets.add(new JetPassenger(model, speed, range, price));
			break;
		case "Fighter":
		case "fighter":
		case "F":
		case "f":
			jets.add(new JetFighter(model, speed, range, price));
			break;
		case "Cargo":
		case "cargo":
		case "C":
		case "c":
			jets.add(new JetCargo(model, speed, range, price));
			break;

		default:
			break;
		}

		calculateJets();
	}

	public void askUserForJetInput(Scanner scanner) {
		String type = "", model = "";
		double speed = -1;
		int range = -1;
		long price = -1;
		boolean completed = false;

		while (!completed) {
			try {
				if (type.equals("")) {
					System.out.print("Enter the jet type (Passenger, Fighter, Cargo):");
					type = scanner.nextLine();
				}
				else if (model.equals("")) {
					System.out.print("Enter the model of the jet:");
					model = scanner.nextLine();
				}
				else if (speed == -1) {
					System.out.print("Enter the speed of the jet:");
					speed = scanner.nextDouble();
					scanner.nextLine();
				}
				else if (range == -1) {
					System.out.print("Enter the range of the jet:");
					range = scanner.nextInt();
					scanner.nextLine();
				}
				else if (price == -1) {
					System.out.print("Enter the price of the jet:");
					price = scanner.nextLong();
					scanner.nextLine();
				}
				else {
					completed = true;
				}

			} catch (Exception e) {
				System.out.println("Invalid Input! Try again.");
				scanner.nextLine();
			}
		}

		buildJet(type, model, speed, range, price);
	}

	public void removeJet(Scanner scanner) {
		viewFleet();

		System.out.println(
				"Which jet would you like to remove? Enter a number to remove a single Jet or use the name of jet to remove all of the specific kind.");
		String input = scanner.nextLine();
		int number = -1;
		try {
			number = Integer.parseInt(input);
		} catch (Exception e) {
		}

		if (number > 0) {
			jets.remove(number - 1);
		} else {
			switch (input) {
			case "Passenger":
			case "passenger":
			case "P":
			case "p":
				remove(JetPassenger.class);
				break;
			case "Fighter":
			case "fighter":
			case "F":
			case "f":
				remove(JetFighter.class);
				break;
			case "Cargo":
			case "cargo":
			case "C":
			case "c":
				remove(JetCargo.class);
				break;

			default:
				break;
			}
		}
	}

	private <T> void remove(Class<T> clazz) {
		for (int i = jets.size() - 1; i >= 0; i--) {
			Jet jet = jets.get(i);
			if (clazz.getClass().isInstance(jet)) {
				jets.remove(i);
			}
		}
	}

	public void takeOff() {
		for (Jet jet : jets) {
			jet.fly();
		}
	}
	
	public void viewFleet() {
		int counter = 1;
		System.out.println("------------------------");
		System.out.println("|        All Jets      |");
		System.out.println("------------------------");
		for (Jet jet : jets) {
			System.out.println("#" + counter + " " + jet + "\n");
			counter++;
		}
	}

	public void fastestJet() {
		System.out.println("------------------------");
		System.out.println("|      Fastest Jet     |");
		System.out.println("------------------------");
		System.out.println(fastestJet);
	}

	public void longestRangeJet() {
		System.out.println("------------------------");
		System.out.println("|  Longest Range Jet   |");
		System.out.println("------------------------");
		System.out.println(longestRangeJet);
	}

	public void loadPassengers() {
		System.out.println("------------------------");
		System.out.println("|  Loading Passengers  |");
		System.out.println("------------------------");
		for (Jet jet : passengerJets) {
			((JetPassenger) jet).loadPassengers();
		}
	}

	public void fight() {
		System.out.println("------------------------");
		System.out.println("|     Fighting Jets    |");
		System.out.println("------------------------");
		for (Jet jet : fighterJets) {
			((JetFighter) jet).battle();
		}
	}
}
