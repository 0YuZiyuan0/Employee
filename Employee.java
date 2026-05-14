package lesson1;

import java.math.BigDecimal;
import java.time.LocalDate;


public abstract class Employee {// отвечает только за хранение данных сотрудника S
    private final long id;
    protected final String firstName;
    protected final String lastName;
    protected final String email;

    private String position;
    private String department;
    private BigDecimal salary;
    private LocalDate hireDate;
    private int experienceYears;

    //private static final BigDecimal salary_min = BigDecimal.ZERO;
    //private static final int Scale = 2; количество знаков после запятой

   Employee(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.position = builder.position;
        this.department = builder.department;
        this.salary = builder.salary;
        this.hireDate = builder.hireDate;
        this.experienceYears = builder.experienceYears;

    }

    public static abstract class Builder {
        private final long id;
        protected String firstName;
        protected String lastName;
        protected String email;

        private String position;
        private String department;
        private BigDecimal salary;
        private LocalDate hireDate;
        private int experienceYears;

        public  Builder(long id) {
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
            if (position == null || position.trim().isEmpty()) {
                throw new IllegalArgumentException("Position cannot be null or empty");
            }
            this.position = position;
            return this;

        }

        public Builder department(String department) {
            if (department == null || department.trim().isEmpty()) {
                throw new IllegalArgumentException("Department cannot be null or empty");
            }
            this.department = department;
            return this;

        }

        public Builder salary(BigDecimal salary) {
            if (salary.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Salary cannot be null");
            }
            this.salary = salary;
            return this;
        }

        public Builder hireDate(LocalDate hireDate) {
            if (hireDate == null) {
                throw new IllegalArgumentException("hireDate cannot be null");
            }
            if (hireDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("hireDate cannot be in the future");
            }
            this.hireDate = hireDate;
            return this;
        }
        public Builder experienceYears(int experienceYears){
            if(experienceYears<0){
                throw  new IllegalArgumentException("Опыт не может быть отрицательным");
            }
            this.experienceYears =experienceYears;
            return this;
        }


        public abstract Employee build();
        //правильный вариант, вместо перегруженного метода
        // Почему плохо: Родитель вынужден знать обо всех наследниках.
        // При добавлении нового типа сотрудника придётся менять Employee.
        // Builder — нарушение принципа Open/Closed.


    }

    protected String getFullName(){
       return firstName + " " +lastName;
    }

    @Override
    public String toString(){// хорошо ли переопределить этот метод?
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
        if (experienceYears > 0) result += ", experienceYears=" + experienceYears;

        return result + "}";

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

    public int getExperienceYears() {
        return experienceYears;
    }


    public abstract BigDecimal calculateBonus();
}


