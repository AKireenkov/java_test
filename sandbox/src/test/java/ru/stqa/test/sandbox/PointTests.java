package ru.stqa.test.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {
  @Test
          public void testPoint() {
    Point1 p1 = new Point1(1, 2);
    Point1 p2 = new Point1(4, 6);
    Assert.assertEquals(p1.distance(p2), 5);
  }
}
