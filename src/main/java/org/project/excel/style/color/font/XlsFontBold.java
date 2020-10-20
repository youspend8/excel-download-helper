package org.project.excel.style.color.font;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

@AllArgsConstructor
public class XlsFontBold {
    private boolean isBold;

    public void apply(CellStyle cellStyle) {
        XSSFFont font = (XSSFFont) cellStyle;
        font.setBold(isBold);
    }
}
