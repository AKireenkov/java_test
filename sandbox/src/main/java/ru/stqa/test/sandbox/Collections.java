package ru.stqa.test.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main (String[] args){
    String[] langs = {"Java", "C#", "Python", "PHP"};
    List<String> languages =  Arrays.asList("Java", "C#", "Python");

            for (String i: languages){
              System.out.println("Я хочу выучить язык " + i);
            }
  }
}
