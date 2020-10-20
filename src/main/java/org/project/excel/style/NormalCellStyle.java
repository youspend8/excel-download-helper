package org.project.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.project.excel.config.XlsCellStyleConfigurer;

public class NormalCellStyle extends AbstractCellStyle {
    public void apply(CellStyle cellStyle) {
        System.out.println("APPLY");
    }

    @Override
    public void configure(XlsCellStyleConfigurer configurer) {

    }
}
