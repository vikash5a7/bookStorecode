package com.bridzelab.basics;

public class EmpWageBuilderSwitch {

	// Constants
	public static final int IS_PART_TIME = 1;
	public static final int IS_FULL_TIME = 2;
	public static final int EMP_RATE_PER_HOUR = 20;
	public static final int NUM_OF_WORKING_DAYS = 10;

	public static void main(String[] args) {

		// variables
		int empHrs = 0;
		int empWage = 0;
		int totalWage = 0;
		// Computation

		for (int day = 0; day < NUM_OF_WORKING_DAYS; day++) {

			// Computation
			int empCheck = (int) (Math.floor(Math.random() * 10) % 3);
			switch (empCheck) {

			case IS_FULL_TIME:
				empHrs = 8;
				break;
			case IS_PART_TIME:
				empHrs = 4;
				break;
			default:
				empHrs = 0;
			}
			empWage = empHrs * EMP_RATE_PER_HOUR;
			totalWage += empWage;
			System.out.println("Employee wage:" + empWage);

		}
		System.out.println("Total emloyee wage:" + totalWage);
	}


}
