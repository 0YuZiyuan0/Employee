package lesson1;

import java.math.BigDecimal;
import java.time.LocalDate;


public class MainEmployee {
        public static void main(String[] args) {
            Developer dev = new Developer.Builder(1)
                    .firstName("Иван")
                    .lastName("Иванов")
                    .email("ivan@company.com")
                    .position("Senior")
                    .department("IT")
                    .programmingLanguage("Java")
                    .salary(new BigDecimal("5000"))
                    .hireDate(LocalDate.of(2023, 1, 1))
                    .experienceYears(5)
                    .build();

            System.out.println(dev.generateReport());


}}
