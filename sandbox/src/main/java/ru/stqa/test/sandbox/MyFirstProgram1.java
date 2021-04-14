package ru.stqa.test.sandbox;

public class MyFirstProgram1 {
	public static void main(String[] args){
		hello("world");
		hello("user");
		hello("Andrey");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());			//находит функцию area в классе объекта s. В методе, на ссылаемся на объекты a,b через this

		Rectangle r = new Rectangle(8, 9);
		System.out.println("Площадь квадрата со сторонами " + r.a + " и " + r.b + " = " + r.area());
	}
	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}
}