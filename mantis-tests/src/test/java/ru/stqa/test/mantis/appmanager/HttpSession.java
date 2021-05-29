package ru.stqa.test.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
  private CloseableHttpClient httpclient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app) {
    this.app = app;
    //создание новой сессии, объекта отправляющего запросы на сервер
    //сессия для работы по http
    //LaxRedirectStrategy() стратегия перенаправления
    httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  /**
   * выполняет вход
   */
  public boolean login(String username, String password) throws IOException {
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");//запрос POST с телом и параметрами
    List<NameValuePair> params = new ArrayList<NameValuePair>(); //набор параметров
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    post.setEntity(new UrlEncodedFormEntity(params));//упаковываем параметры и помещаем в запрос post.setEntity
    CloseableHttpResponse response = httpclient.execute(post); //отправка
    String body = geTextFrom(response);
    return body.contains(String.format("<span class=\"user-info\">%s</span>", username)); //проверка: зашел ли пользак. Текст страницы содержит строку...
  }

  private String geTextFrom(CloseableHttpResponse response) throws IOException {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  /**
   * определяет какой пользователь зашел
   */
  public boolean isLoggedInAs(String username) throws IOException {
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php"); //get - запрос на получение параметров
    CloseableHttpResponse response = httpclient.execute(get);
    String body = geTextFrom(response);
    return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
  }
}
