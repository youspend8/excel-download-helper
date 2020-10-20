package org.project.excel.style;

import org.project.excel.config.XlsCellStyleConfigurer;

public class BlackCellStyle extends AbstractCellStyle {
    @Override
    public void configure(XlsCellStyleConfigurer configurer) {
        configurer.backgroundColor(0, 0, 0)
                .fontColor(255, 255, 255);
    }
}
