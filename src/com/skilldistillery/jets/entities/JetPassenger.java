package com.skilldistillery.jets.entities;

public class JetPassenger extends Jet implements PassengerCarrier {

	public JetPassenger(String mode, double speed, int range, long price) {
		super(mode, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println("Passenger Jet flying here!");
	}

	@Override
	public void loadPassengers() {
		System.out.println("Loading passengers onto the plane.");
	}

	@Override
	public String toString() {
		return "JetPassenger " + super.toString();
	}
}
