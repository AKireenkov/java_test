package ru.stqa.test.sandbox;

public class DZ1 {
  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(4, 6);
    System.out.println("Расстояние между точками a и b = " + p1.distance(p2));
  }
}
