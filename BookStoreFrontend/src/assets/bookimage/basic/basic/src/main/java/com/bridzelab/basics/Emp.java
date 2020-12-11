package com.bridzelab.basics;

public class Emp {
	 public static void main (String[]args){
		 final int IS_FULL_TIME=1;

		double check=Math.floor(Math.random()*10)%2;
		if(check==IS_FULL_TIME){
		System.out.println(" ok");
		}
		else{
		System.out.println("not ok");
		}
		}

}
