package org.project.excel;

import org.project.excel.dto.EmployeeDto;
import org.project.excel.molecules.SimpleXlsWorkbook;

import java.util.Arrays;
import java.util.List;

public class OutputTests {
    public static void main(String[] args) {
        SimpleXlsWorkbook workbook = new SimpleXlsWorkbook();

        workbook.addSheet(list, EmployeeDto.class);

        try {
            workbook.write("D:/TEST.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<EmployeeDto> list = Arrays.asList(
            EmployeeDto.builder()
                    .id("A")
                    .name("David")
                    .email("david@gmail.com")
                    .salary(24000)
                    .build(),
            EmployeeDto.builder()
                    .id("B")
                    .name("Alex")
                    .email("alex@gmail.com")
                    .salary(3500)
                    .build(),
            EmployeeDto.builder()
                    .id("C")
                    .name("Lex")
                    .email("lex@gmail.com")
                    .salary(11240)
                    .build(),
            EmployeeDto.builder()
                    .id("D")
                    .name("Steven")
                    .email("steven@gmail.com")
                    .salary(7000)
                    .build(),
            EmployeeDto.builder()
                    .id("E")
                    .name("Diana")
                    .email("diana@gmail.com")
                    .salary(8430)
                    .build());
}
