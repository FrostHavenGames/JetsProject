package com.skilldistillery.jets.entities;

public class JetFighter extends Jet implements Fight{

	public JetFighter(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println("Fighter jet flying here!");
	}

	@Override
	public void battle() {
		System.out.println("Fighter jet fighting here!");
	}
	
	@Override
	public String toString() {
		return "JetFighter " + super.toString();
	}

}
