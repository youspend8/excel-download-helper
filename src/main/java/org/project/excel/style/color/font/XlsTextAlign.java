package org.project.excel.style.color.font;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

@AllArgsConstructor
public class XlsTextAlign {
    private HorizontalAlignment alignment;

    public void apply(CellStyle cellStyle) {
        cellStyle.setAlignment(alignment);
    }
}
