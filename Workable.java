package lesson1;

import java.time.LocalTime;

public interface Workable {// отвечает только за рабочее поведение

    void work();
    void takeBreak();

    static boolean isWorkingHours(LocalTime time) {
        return time.isAfter(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(18, 0));
    }

    static void printWorkRules(){
        System.out.println("=== ПРАВИЛА РАБОТЫ ===");
        System.out.println("1. Приходить вовремя");
        System.out.println("2. Соблюдать дедлайны");
        System.out.println("3. Уважать коллег");
        System.out.println("======================");
    }
    //Статический метод в интерфейсе — это как инструкция,
    // которая относится ко ВСЕМ сотрудникам.
    //Она не требует конкретного человека. Она общая для всех.
    // Вы можете спросить:
//    boolean isWorkTime = Workable.isWorkingHours(LocalTime.of(14, 30));
//System.out.println(isWorkTime); // true

//    // Вы НЕ хотите делать так (это глупо):
//    Developer dev = new Developer.Builder(1)...build();
//dev.isWorkingHours(...); // Зачем? Время работы не зависит от Вани-разработчика

    private void log(String action) {
        System.out.println("[LOG] " + action + " at " + LocalTime.now());
    }

    default void startWork() {
        log("Начало работы");
        work();
    }

    default void endBreak() {
        log("Конец рабочего дня");
        takeBreak();
    }
}
