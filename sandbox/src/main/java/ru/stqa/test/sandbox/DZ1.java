package ru.stqa.test.sandbox;

public class DZ1 {
  public static void main(String[] args){
    Point p1 = new Point(); //точка с координатами (x,y)
    p1.x = 1;
    p1.y = 2;

    Point p2 = new Point();
    p2.x = 4;
    p2.y = 6;
    System.out.println("Расстояние от p1 до p2 = " + distance(p1,p2));
  }
  public static double distance (Point p1, Point p2){
    return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y- p1.y), 2));
  }
}
