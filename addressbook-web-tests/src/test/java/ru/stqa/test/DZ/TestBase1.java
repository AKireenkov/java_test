package ru.stqa.test.DZ;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase1 {

  protected final ApplicationManager1 app1 = new ApplicationManager1();

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app1.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app1.stop();
  }

  //public ApplicationManager1 getApplicationManager1() {   //что то лишнее добавилось)
   // return app1;
 // }
}
