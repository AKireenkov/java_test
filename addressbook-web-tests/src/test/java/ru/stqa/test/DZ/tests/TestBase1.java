package ru.stqa.test.DZ.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.test.DZ.appmanager.ApplicationManager1;

public class TestBase1 {

  protected static final ApplicationManager1 app1 = new ApplicationManager1(BrowserType.CHROME);

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app1.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app1.stop();
  }

  public ApplicationManager1 getApplicationManager1() {
    return app1;
  }
}
