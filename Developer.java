package lesson1;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Developer extends Employee implements Workable, Reportable{

    public String programmingLanguage;

    private Developer(Builder builder) {
        super(builder);
        this.programmingLanguage = builder.programmingLanguage;
    }

    @Override
    public BigDecimal calculateBonus() {// Бонус для разработчика 10%
        return getSalary().multiply(BigDecimal.valueOf(0.1));
    }

    @Override
    public void work() {
        System.out.println("Пишет код на " + programmingLanguage);
    }

    @Override
    public void takeBreak() {
        System.out.println(getFirstName() + "Перерыв на какавушку в 14.00");
    }

    @Override
    public String generateReport() {
        String report = "Разработчик: " + getFullName() +  "\n";
        report+= "Язык: " + programmingLanguage +  "\n";

        if (getExperienceYears() == 0) {
            report += "Опыт: нет данных\n";
        } else {
            report += "Опыт: " + getExperienceYears() + " лет\n";
        }
        report += "Бонус: " + String.format("%.2f", calculateBonus().doubleValue()) + " руб.";

        return report;
    }

    public static class Builder extends Employee.Builder {//
        // Если бы мы оставили build() в
        // Employee.Builder, он должен был бы возвращать Employee.
        // Но Employee теперь абстрактный – нельзя создать его экземпляр.
        private String programmingLanguage;

        public Builder(long id) {
            super(id);
        }

        public Builder programmingLanguage(String programmingLanguage) {
            this.programmingLanguage = programmingLanguage;
            return this;
        }

        @Override
        public Developer build() {// проверяем заполнены ли верно обязательные поля
            if (firstName == null || lastName == null || email == null) {
                throw new IllegalStateException("Заполните обязательные поля");
            }
            return new Developer(this);
        }
        @Override
        public Builder firstName(String firstName) {
            super.firstName(firstName);
            return this;  // возвращаем Developer.Builder, а не Employee.Builder
        }

        @Override
        public Builder lastName(String lastName) {
            super.lastName(lastName);
            return this;
        }

        @Override
        public Builder email(String email) {
            super.email(email);
            return this;
        }

        @Override
        public Builder position(String position) {
            super.position(position);
            return this;
        }

        @Override
        public Builder department(String department) {
            super.department(department);
            return this;
        }

        @Override
        public Builder salary(BigDecimal salary) {
            super.salary(salary);
            return this;
        }

        @Override
        public Builder hireDate(LocalDate hireDate) {
            super.hireDate(hireDate);
            return this;
        }

        @Override
        public Builder experienceYears(int experienceYears) {
            super.experienceYears(experienceYears);
            return this;
        }

        @Override
        public String toString() {
            return "Developer{" +
                    super.toString() +
                    ", programmingLanguage='" + programmingLanguage + '\'' +
                    '}';
        }

    }
}
