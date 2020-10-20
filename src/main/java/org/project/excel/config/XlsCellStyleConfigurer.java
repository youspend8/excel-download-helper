package org.project.excel.config;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.project.excel.style.color.XlsBackgroundColor;
import org.project.excel.style.color.XlsRgbBackgroundColor;
import org.project.excel.style.color.font.XlsFontBold;
import org.project.excel.style.color.font.XlsFontColor;
import org.project.excel.style.color.font.XlsRgbFontColor;
import org.project.excel.style.color.font.XlsTextAlign;

public class XlsCellStyleConfigurer {
    private XlsBackgroundColor backgroundColor;
    private XlsFontColor fontColor;
    private XlsTextAlign textAlign;
    private XlsFontBold fontBold;

    public XlsCellStyleConfigurer backgroundColor(int red, int blue, int green) {
        this.backgroundColor = new XlsRgbBackgroundColor(red, blue, green);
        return this;
    }

    public XlsCellStyleConfigurer fontColor(int red, int blue, int green) {
        this.fontColor = new XlsRgbFontColor(red, blue, green);
        return this;
    }

    public XlsCellStyleConfigurer textAlign(HorizontalAlignment alignment) {
        this.textAlign = new XlsTextAlign(alignment);
        return this;
    }

    public XlsCellStyleConfigurer fontBold(boolean isBold) {
        this.fontBold = new XlsFontBold(isBold);
        return this;
    }

    //  TODO : Initializing this class field to default Object
    public void configure(CellStyle cellStyle) {
        if (backgroundColor != null)
            backgroundColor.apply(cellStyle);
        if (fontColor != null)
            fontColor.apply(cellStyle);
        if (textAlign != null)
            textAlign.apply(cellStyle);
        if (fontBold != null)
            fontBold.apply(cellStyle);
    }
}
