package org.project.excel.molecules;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;

public class SimpleXlsCell {
    private SXSSFRow row;
    private SXSSFCell cell;

    protected SimpleXlsCell(SXSSFRow row, SXSSFCell cell) {
        this.row = row;
        this.cell = cell;
    }
}
