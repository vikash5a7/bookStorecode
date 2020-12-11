package com.bridzelab.basics;

public class EmpWageBuilder2 {

public static void main(String[] args) {
		// Constants
		final int IS_PART_TIME = 1;
		final int IS_FULL_TIME = 2;
		final int EMP_RATE_PER_HOUR = 20;
		// variables
		int empHrs = 0;
		int empWage = 0;
		// Computation
		double empCheck = Math.floor(Math.random() * 10) % 3;
		if (empCheck == IS_FULL_TIME)
			empHrs = 8;
		else if (empCheck == IS_PART_TIME)
			empHrs = 4;
		else
			empHrs = 0;
		empWage = empHrs * EMP_RATE_PER_HOUR;
		System.out.println("Employee wage:" + empWage);
	}

}
