package org.project.excel.style.cellstyle;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.project.excel.style.AbstractCellStyle;
import org.project.excel.style.XlsCellStyleConfigurer;

public class BlackAndTextBoldCellStyle extends AbstractCellStyle {

    @Override
    public void configure(XlsCellStyleConfigurer configurer) {
        configurer.fontBold(true)
                .textAlign(HorizontalAlignment.CENTER)
                .backgroundColor(0, 0, 0)
                .fontColor(255, 255, 255);
    }
}
