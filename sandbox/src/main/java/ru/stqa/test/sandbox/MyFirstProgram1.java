package ru.stqa.test.sandbox;

public class MyFirstProgram1 {
	public static void main(String[] args){
		hello("world");
		hello("user");
		hello("Andrey");

		double len = 5;
		System.out.println("Площадь квадрата со стороной " + len +" = " + area(len));

		double a = 8;
		double b = 9;
		System.out.println("Площадь квадрата со сторонами " + a + " и " + b + " = " + area(a, b));
	}
	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double area (double l) {
		return l * l;
	}
	public static double area(double a, double b){
		return a*b;
	}
}