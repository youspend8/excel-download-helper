package org.project.excel.style.cellstyle;

import org.project.excel.style.AbstractCellStyle;
import org.project.excel.style.XlsCellStyleConfigurer;

public class BlackCellStyle extends AbstractCellStyle {
    @Override
    public void configure(XlsCellStyleConfigurer configurer) {
        configurer.backgroundColor(0, 0, 0)
                .fontColor(255, 255, 255);
    }
}
