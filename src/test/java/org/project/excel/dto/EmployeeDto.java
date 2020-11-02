package org.project.excel.dto;

import lombok.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.project.excel.annotation.*;
import org.project.excel.style.cellstyle.BlackCellStyle;
import org.project.excel.style.cellstyle.NormalCellStyle;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@ExcelSheet(sheetName = "사원목록")
@DefaultHeaderStyle(style = @ExcelCellStyle)
@DefaultBodyStyle(style = @ExcelCellStyle)
public class EmployeeDto {
    @ExcelColumn(
            columnWidth = 3,
            headerName = "ID",
            headerStyle = @ExcelCellStyle(
                    cellStyleClasses = BlackCellStyle.class,
                    textAlign = HorizontalAlignment.CENTER
            ),
            bodyStyle = @ExcelCellStyle(
                    textAlign = HorizontalAlignment.CENTER
            )
    )
    private String id;

    @ExcelColumn(
            columnWidth = 5,
            headerName = "성명",
            headerStyle = @ExcelCellStyle(
                    cellStyleClasses = {
                            BlackCellStyle.class
                    },
                    textAlign = HorizontalAlignment.CENTER
            ),
            bodyStyle = @ExcelCellStyle(
                    cellStyleClasses = {
                            NormalCellStyle.class
                    },
                    textAlign = HorizontalAlignment.CENTER
            )
    )
    private String name;

    @ExcelColumn(
            columnWidth = 7,
            headerName = "이메일",
            headerStyle = @ExcelCellStyle(
                    cellStyleClasses = BlackCellStyle.class,
                    textAlign = HorizontalAlignment.CENTER
            )
    )
    private String email;

    @ExcelColumn(
            columnWidth = 5,
            headerName = "급여",
            headerStyle = @ExcelCellStyle(
                    cellStyleClasses = {
                            BlackCellStyle.class
                    },
                    textAlign = HorizontalAlignment.CENTER
            ),
            bodyStyle = @ExcelCellStyle(
                    cellStyleClasses = {
                            NormalCellStyle.class
                    },
                    textAlign = HorizontalAlignment.RIGHT,
                    dataFormat = "#,##0"
            )
    )
    private Integer salary;
}
