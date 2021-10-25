package com.skilldistillery.jets.app;

import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;

public class JetsApplication {

	private Scanner scanner;

	private AirField airField;
	
	private boolean running = true;

	public JetsApplication() {
		scanner = new Scanner(System.in);
		airField = new AirField();
		
		launch();

		shutdown();
	}

	private void launch() {
		
		while (running) {
			displayMenu();
			processInput();
		}
		
		System.out.println("Thanks for using the Jet application.");
	}

	private void displayMenu() {
		System.out.println("------------------------");
		System.out.println("|       Jet Menu       |");
		System.out.println("------------------------");
		System.out.println("1.List Fleet\n2.Fly all jets\n3.View fastest jet\n4.View jet with longest range\n5.Load Passengers\n6.Fight\n7.Add a jet to the fleet\n8.Remove jet from fleet\n9.Quit");
	}
	
	private void processInput() {
		int input = -1;
		while (input == -1) {
			try {
				input = scanner.nextInt();
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Try again! Please type a number.");
				input = -1;
			}
		}
		
		switch (input) {
		case 1:
			airField.viewFleet();
			break;
		case 2:
			airField.takeOff();
			break;
		case 3:
			airField.fastestJet();
			break;
		case 4:
			airField.longestRangeJet();
			break;
		case 5:
			airField.loadPassengers();
			break;
		case 6:
			airField.fight();
			break;
		case 7:
			airField.askUserForJetInput(scanner);
			break;
		case 8:
			airField.removeJet(scanner);
			break;
		case 9:
			running = false;
			break;

		default:
			System.out.println("Try again. Invalid input!");
			break;
		}
	}


	private void shutdown() {
		scanner.close();
	}

	public static void main(String[] args) {
		new JetsApplication();
	}

}
