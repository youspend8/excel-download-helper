package org.project.excel.annotation;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.project.excel.style.AbstractCellStyle;
import org.project.excel.style.cellstyle.NormalCellStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCellStyle {
    Class<? extends AbstractCellStyle>[] cellStyleClasses() default NormalCellStyle.class;
    HorizontalAlignment textAlign() default HorizontalAlignment.LEFT;
    String dataFormat() default "";
}
