# Excel Download Helper Project


## Usage Example
- #### 라이브러리 추가 (maven)
```xml
<repositories>
    <repository>
        <id>repository2</id>
        <url>https://github.com/youspend8/excel-download-helper/raw/master/snapshots</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.project</groupId>
        <artifactId>excel</artifactId>
        <version>0.1.0</version>
    </dependency>
</dependencies>
```

- #### Entity 정의
```java
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
```

- #### Output File
```java
public class DownloadXLS {
    public void output() {
        SimpleXlsWorkbook workbook = new SimpleXlsWorkbook();
        
        workbook.addSheet(list, EmployeeDto.class);
        
        try {
            workbook.write("output.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

- #### Customizing CellStyleClass
```java
public class BlackAndTextBoldCellStyle extends AbstractCellStyle {

    @Override
    public void configure(XlsCellStyleConfigurer configurer) {
        configurer.fontBold(true)
                .textAlign(HorizontalAlignment.CENTER)
                .backgroundColor(0, 0, 0)
                .fontColor(255, 255, 255);
    }
}
```
```java
@ExcelColumn(
        headerStyle = @ExcelCellStyle(
                cellStyleClasses = {
                        BlackAndTextBoldCellStyle.class
                }
        )
)
```
