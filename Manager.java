package lesson1;

import java.math.BigDecimal;

public class Manager extends Employee implements Workable, Reportable{// отвечает только за свой тип S

    private int teamSize;

    private String departmentName;

    private Manager(Builder builder) {// Конструктор должен быть приватным,
        // чтобы объект Manager можно было создать только через Builder.build().
        super(builder);
        this.teamSize = builder.teamSize;
        this.departmentName = builder.departmentName;
    }

    @Override
    public BigDecimal calculateBonus() {
        return getSalary().multiply(BigDecimal.valueOf(0.2)).add(BigDecimal.valueOf(teamSize * 50));
    }

    @Override
    public void work() {
        System.out.println(getFirstName() + " управляет отделом " + departmentName + " (" + teamSize + " человек)");
    }

    @Override
    public void takeBreak() {
        System.out.println(getFirstName() + " проводит встречу с клиентом");
    }
     public String getDepartmentInfo(){
         return "Отдел" + departmentName.toUpperCase() + " (размер команды: "
         + teamSize + ")";
     }

    @Override
    public String generateReport() {
        String report = "Менеджер: " + getFullName() + "\n";
        report += "Отдел: " + departmentName + "\n";
        report += "Команда: " + teamSize + "человек"+"\n";
        if (getExperienceYears() == 0) {
            report += "Опыт: нет данных\n";
        } else {
            report += "Опыт: " + getExperienceYears() + " лет\n";
        }
        report += "Бонус: " + String.format("%.2f", calculateBonus().doubleValue()) + " руб.";

        return report;

    }

    public static class Builder extends Employee.Builder{//Это статический вложенный класс,
        // который отвечает за создание объектов Manager. Он "наследует" всё, что умеет Employee.Builder,
        // и добавляет свои специфические методы.
        // public – чтобы можно было использовать вне класса Manager
        //static – чтобы можно было создать Builder без существующего объекта Manager
        private int teamSize;
        private String departmentName;

        public Builder(long id){
            super(id);//если не вызвать род констр будет ошибка, у род.нет уконстр по умолчанию
        }

        public Builder departmentName(String departmentName){
            if (departmentName == null || departmentName.trim().length() < 3) {
                throw new IllegalArgumentException("Название отдела должно быть не менее 3 символов");
            }
            this.departmentName = departmentName;
            return this;
        }

        @Override
        public Employee build() {
            if (firstName == null || lastName == null || email == null) {
                throw new IllegalStateException("Заполните обязательные поля");
            }
            if (departmentName == null) {
                throw new IllegalStateException("Название отдела необходимо указать для должности менеджера.");
            }
            return new Manager ( this);

        }

        public Builder teamSize(int teamSize) {
            if (teamSize < 0) {
                throw new IllegalArgumentException("Размер команды не может быть отрицательным");
            }
            this.teamSize = teamSize;
            return this;
        }

        @Override
        public String toString() {
            return "Manager{" +
                    super.toString()+
            ", Размер команды: " + teamSize + + '\'' +
                    '}';
        }
    }
}