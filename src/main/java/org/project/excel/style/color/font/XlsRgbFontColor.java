package org.project.excel.style.color.font;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

@AllArgsConstructor
public class XlsRgbFontColor implements XlsFontColor {
    private int red;
    private int blue;
    private int green;

    @Override
    public void apply(CellStyle cellStyle) {
        try {
            XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;

            XSSFColor color = new XSSFColor(new byte[] {
                    (byte) red, (byte) green, (byte) blue
            }, new DefaultIndexedColorMap());

            XSSFFont font = xssfCellStyle.getFont();
            font.setColor(color);
        } catch (Exception e) {
            throw new UnsupportedOperationException(
                    String.format("Excel Type %s is not supported now", cellStyle.getClass()));
        }
    }
}
