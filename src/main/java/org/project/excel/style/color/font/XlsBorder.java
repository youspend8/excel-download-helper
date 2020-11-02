package org.project.excel.style.color.font;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;

@AllArgsConstructor
public abstract class XlsBorder {
    protected boolean isUse;
    protected BorderStyle borderStyle;

    public abstract void apply(CellStyle cellStyle);
}
