package lesson1;

import java.time.LocalDate;

public interface Reportable {
   String generateReport();// не может быть статик  должен возвращать разный результат для каждого сотрудника (разработчик — одно, менеджер — другое). Статический метод принадлежит интерфейсу,
    // а не объекту, и не может видеть поля конкретного сотрудника.


   default void printReport(){
       System.out.println(generateReport());
       System.out.println("------------------------");
   }

   private String addSeparator(){
       return "********************";
   }
   static  void printHead(){
       System.out.println("********** ОТЧЁТЫ СОТРУДНИКОВ **********\n" +
               "Дата: " + LocalDate.now().toString());
   }
}
