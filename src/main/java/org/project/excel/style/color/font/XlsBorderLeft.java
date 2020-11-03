package org.project.excel.style.color.font;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;

public class XlsBorderLeft extends XlsBorder {
    public XlsBorderLeft(boolean isUse, BorderStyle borderStyle) {
        super(isUse, borderStyle);
    }

    public void apply(CellStyle cellStyle) {
        if (isUse) {
            cellStyle.setBorderLeft(borderStyle);
        }
    }
}
