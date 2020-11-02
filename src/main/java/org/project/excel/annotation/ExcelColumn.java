package org.project.excel.annotation;

import org.project.excel.style.cellstyle.NormalCellStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    int columnWidth() default 0;
    String headerName() default "";
    ExcelCellStyle headerStyle() default @ExcelCellStyle(cellStyleClasses = NormalCellStyle.class);
    ExcelCellStyle bodyStyle() default @ExcelCellStyle(cellStyleClasses = NormalCellStyle.class);
}
