package org.project.excel.style.color.font;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;

public class XlsBorderBottom extends XlsBorder {
    public XlsBorderBottom(boolean isUse, BorderStyle borderStyle) {
        super(isUse, borderStyle);
    }

    @Override
    public void apply(CellStyle cellStyle) {
        if (isUse) {
            cellStyle.setBorderBottom(borderStyle);
        }
    }
}
