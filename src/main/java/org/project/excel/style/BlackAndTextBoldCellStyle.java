package org.project.excel.style;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.project.excel.config.XlsCellStyleConfigurer;

public class BlackAndTextBoldCellStyle extends AbstractCellStyle {

    @Override
    public void configure(XlsCellStyleConfigurer configurer) {
        configurer.fontBold(true)
                .textAlign(HorizontalAlignment.CENTER)
                .backgroundColor(0, 0, 0)
                .fontColor(255, 255, 255);
    }
}
