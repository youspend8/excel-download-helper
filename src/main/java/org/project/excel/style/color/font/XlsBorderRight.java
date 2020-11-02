package org.project.excel.style.color.font;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;

public class XlsBorderRight extends XlsBorder {
    public XlsBorderRight(boolean isUse, BorderStyle borderStyle) {
        super(isUse, borderStyle);
    }

    public void apply(CellStyle cellStyle) {
        if (isUse) {
            cellStyle.setBorderRight(borderStyle);
        }
    }
}
