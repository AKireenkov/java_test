package ru.stqa.test.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {
  private final ApplicationManager app;
  private final FTPClient ftp;

  public FtpHelper(ApplicationManager app) {
    this.app = app;
    ftp = new FTPClient();  //инициализация
  }

  //загружает новый файл и старый переименовывает
  public void upload(File file, String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host")); //устанавливаем соединение
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));  //вход
    ftp.deleteFile(backup); // удаляем предыдущий рез. файл
    ftp.rename(target, backup); //переименовываем + делаем рез. копию
    ftp.enterLocalPassiveMode();  //пассивный режим передачи данных
    ftp.storeFile(target, new FileInputStream(file)); //передача файла file на удаленную машину в файл target
    ftp.disconnect();
  }

  //удаляет загруженный файл, восстанавливает оригинальный файл из резервной копии
  public void restore(String backup, String target) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(backup);
    ftp.rename(target, backup);
    ftp.disconnect();
  }
}
