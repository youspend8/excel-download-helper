package org.project.excel.style;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.project.excel.style.color.XlsBackgroundColor;
import org.project.excel.style.color.XlsRgbBackgroundColor;
import org.project.excel.style.color.font.*;

public class XlsCellStyleConfigurer {
    private XlsBackgroundColor backgroundColor;
    private XlsFontColor fontColor;
    private XlsTextAlign textAlign;
    private XlsFontBold fontBold;
    private XlsBorderLeft borderLeft;
    private XlsBorderBottom borderBottom;
    private XlsBorderRight borderRight;
    private XlsBorderTop borderTop;

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

    public XlsCellStyleConfigurer border(boolean isTop, boolean isBottom, boolean isLeft, boolean isRight, BorderStyle borderStyle) {
        this.borderTop = new XlsBorderTop(isTop, borderStyle);
        this.borderBottom = new XlsBorderBottom(isBottom, borderStyle);
        this.borderLeft = new XlsBorderLeft(isLeft, borderStyle);
        this.borderRight = new XlsBorderRight(isRight, borderStyle);
        return this;
    }

    public XlsCellStyleConfigurer borderTop(BorderStyle borderStyle) {
        this.borderTop = new XlsBorderTop(true, borderStyle);
        return this;
    }

    public XlsCellStyleConfigurer borderBottom(BorderStyle borderStyle) {
        this.borderBottom = new XlsBorderBottom(true, borderStyle);
        return this;
    }

    public XlsCellStyleConfigurer borderLeft(BorderStyle borderStyle) {
        this.borderLeft = new XlsBorderLeft(true, borderStyle);
        return this;
    }

    public XlsCellStyleConfigurer borderRight(BorderStyle borderStyle) {
        this.borderRight = new XlsBorderRight(true, borderStyle);
        return this;
    }

    //  TODO : Initializing this class field to default Object
    protected void configure(CellStyle cellStyle) {
        if (backgroundColor != null)
            backgroundColor.apply(cellStyle);
        if (fontColor != null)
            fontColor.apply(cellStyle);
        if (textAlign != null)
            textAlign.apply(cellStyle);
        if (fontBold != null)
            fontBold.apply(cellStyle);
        if (borderTop != null)
            borderTop.apply(cellStyle);
        if (borderBottom != null)
            borderBottom.apply(cellStyle);
        if (borderLeft != null)
            borderLeft.apply(cellStyle);
        if (borderRight != null)
            borderRight.apply(cellStyle);
    }
}
