package lesson1;

import java.math.BigDecimal;

public class Intern extends Employee implements Workable, Reportable{

    private String mentorName;
    private String internshipTopic;//тема для изучения

    private Intern(Builder builder) {
        super(builder);
        this.mentorName = builder.mentorName;
        this.internshipTopic = builder.internshipTopic;
    }

    @Override
    public BigDecimal calculateBonus() {
        return BigDecimal.ZERO;
    }

    @Override
    public void work() {
        System.out.println(getFirstName() + " изучает " + internshipTopic + " под руководством " + mentorName);
    }

    @Override
    public void takeBreak() {
        System.out.println(getFirstName() + " обедает с " + mentorName);
    }

    @Override
    public String generateReport() {
        String report = "Стажер: " + getFullName()+"\n";
        report += "Тема: " + internshipTopic.toLowerCase() + "\n";
        report+= "Наставник: " + mentorName + "\n";
        report += "Бонус: " + String.format("%.2f", calculateBonus().doubleValue()) + " руб.";

        return report;
    }

    public String getInternshipDescription(){
        return "Стажёр " + getFullName() + " изучает " + internshipTopic.toLowerCase() + ", наставник: " + mentorName;
    }

    public static class Builder extends Employee.Builder {
        private String mentorName;
        private String internshipTopic;

        public Builder(long id) {
            super(id);
        }

        @Override
        public Employee build() {
            if (firstName == null || lastName == null || email == null) {
                throw new IllegalStateException("Заполните обязательные поля");
            }
            if (mentorName == null) {
                throw new IllegalStateException("К интерну должен быть закреплен Ментор");
            }
            if(internshipTopic == null){
                throw  new IllegalStateException("Укажите профиль обучения");
            }
            return new Intern ( this);
        }

        public Builder mentorName(String mentorName) {// не забывай про параметр!!!!
            if (mentorName == null || mentorName.trim().isEmpty()) {  // проверяем параметр
                throw new IllegalArgumentException("Имя ментора не может быть пустым");
            }
            this.mentorName = mentorName;  // сохраняем параметр в поле
            return this;
        }

        public Builder internshipTopic(String internshipTopic) {
            if (internshipTopic == null || internshipTopic.trim().isEmpty()) {
                throw new IllegalArgumentException("Тема стажировки не может быть пустой");
            }
            this.internshipTopic = internshipTopic;
            return this;
        }
    }
}
