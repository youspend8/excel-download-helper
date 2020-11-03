package org.project.excel.style.color.font;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

@AllArgsConstructor
public class XlsFontBold {
    private boolean isBold;

    public void apply(CellStyle cellStyle) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
        XSSFFont font = xssfCellStyle.getFont();
        font.setBold(isBold);
    }
}
