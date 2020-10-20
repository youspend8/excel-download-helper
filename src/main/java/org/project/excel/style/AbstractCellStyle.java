package org.project.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.project.excel.config.XlsCellStyleConfigurer;

public abstract class AbstractCellStyle {
    private XlsCellStyleConfigurer configurer = new XlsCellStyleConfigurer();

    public AbstractCellStyle() {
        configure(configurer);
    }

    public abstract void configure(XlsCellStyleConfigurer configurer);

    public void apply(CellStyle cellStyle) {
        configurer.configure(cellStyle);
    }
}
