package lesson1;

import java.math.BigDecimal;
import java.time.LocalDate;


public class MainEmployee {
        public static void main(String[] args) {
            String csvData = ("John,Doe,john.doe@company.com,Software Engineer,IT,75000.50,2023-01-15\nJane,Smith,jane.smith@company.com,Team Lead,Finance," +
                    "95000.00,2022-06-01\nBob,Johnson,bob.j@company.com,,Sales,,2024-03-10");
           String [] emp = csvData.split("\n");
           for (String emps: emp ){
               String [] parts = emps.split(",");
               Employee employee = new Employee.Builder(1)
                       .firstName(parts[0].trim())   // trim() убирает пробелы
                       .lastName(parts[1].trim())
                       .email(parts[2].trim())
                       .position(parts[3].trim())
                       .department(parts[4].trim())
                       .salary(parts[5].isEmpty() ? null : new BigDecimal(parts[5]))
                       //перед созданием проверять пустая строка или нет. тернарный f-else
                       .hireDate(parts[6].isEmpty() ? null : LocalDate.parse(parts[6]))
                       .build();

               System.out.println(employee.getFirstName() + " " + employee.getLastName());

           }

}}
