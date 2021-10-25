package com.skilldistillery.jets.entities;

public class JetCargo extends Jet {

	public JetCargo(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println("Cargo Jet flying here!");
	}
	
	@Override
	public String toString() {
		return "JetCargo " + super.toString();
	}

}
