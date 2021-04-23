package ru.stqa.test.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {
  @Test
          public void testPoint() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(4, 6);
    Assert.assertEquals(p1.distance(p2), 5);
  }
}
