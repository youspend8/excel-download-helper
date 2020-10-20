package org.project.excel.style.color;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

@AllArgsConstructor
public class XlsRgbBackgroundColor implements XlsBackgroundColor {
    private int red;
    private int blue;
    private int green;

    @Override
    public void apply(CellStyle cellStyle) {
        try {
            XSSFColor color = new XSSFColor(new byte[] {
                    (byte) red, (byte) green, (byte) blue
            }, new DefaultIndexedColorMap());

            XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
            xssfCellStyle.setFillForegroundColor(color);
        } catch (Exception e) {
            throw new UnsupportedOperationException(
                    String.format("Excel Type %s is not supported now", cellStyle.getClass()));
        }
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}
