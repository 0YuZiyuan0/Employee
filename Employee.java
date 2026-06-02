package lesson1;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Employee {// отвечает только за хранение данных сотрудника S
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String email;

    private String position;
    private String department;
    private BigDecimal salary;
    private LocalDate hireDate;

    //private static final BigDecimal salary_min = BigDecimal.ZERO;
    //private static final int Scale = 2; количество знаков после запятой

   public Employee(Builder builder) {//сделала приватным, чтобы обьекто можно было создать только через билдер
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.position = builder.position;
        this.department = builder.department;
        this.salary = builder.salary;
        this.hireDate = builder.hireDate;

    }
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public static class Builder {
        private final long id;
        protected String firstName;
        protected String lastName;
        protected String email;

        private String position;
        private String department;
        private BigDecimal salary;
        private LocalDate hireDate;

        public Employee build() {
            // проверка обязательных полей
            if (firstName == null || lastName == null || email == null) {
                throw new IllegalStateException("firstName, lastName и email обязательны");
            }
            return new Employee(this);
        }

        public Builder(long id) {
            if (id <= 0) {
                throw new IllegalArgumentException("ID не может быть меньше 1");
            }
            this.id = id;
        }

        public Builder firstName(String firstName) {
            if (firstName == null || firstName.trim().isEmpty()) {
                throw new IllegalArgumentException("First Name cannot be null or empty");
            }
            this.firstName = firstName;
            return this;

        }

        public Builder lastName(String lastName) {
            if (lastName == null || lastName.trim().isEmpty()) {
                throw new IllegalArgumentException("Last Name cannot be null or empty");
            }
            this.lastName = lastName;
            return this;

        }

        public Builder email(String email) {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("email cannot be null or empty");
            }
            this.email = email;
            return this;

        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }// опциональные поля могут быть пустыми

        public Builder department(String department) {
            this.department = department;
            return this;

        }

        public Builder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Builder hireDate(LocalDate hireDate) {
            this.hireDate = hireDate;
            return this;
        }
    }
    protected String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {// хорошо ли переопределить этот метод?и нужен ли он
        String result = "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'';

        if (position != null) result += ", position='" + position + "'";//
        // если значение не ноль, то к result  добавляется строка "б position='manager
        // код постепенно собирает строку добавляя те поля которые не null
        if (department != null) result += ", department='" + department + "'";
        if (salary != null) result += ", salary=" + salary;
        if (hireDate != null) result += ", hireDate=" + hireDate;

        return result + "}";

    }
}

