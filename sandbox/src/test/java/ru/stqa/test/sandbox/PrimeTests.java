package ru.stqa.test.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
  @Test
  public void testPrime(){    //Число простое
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test(enabled = false)
  public void testNoNPrime(){ //Число не простое
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }

  @Test(enabled = false)
  public void testPrimeLong(){    //Число простое
    long n =Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
}
