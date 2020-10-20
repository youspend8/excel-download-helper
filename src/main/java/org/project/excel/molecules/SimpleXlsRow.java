package org.project.excel.molecules;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;

public class SimpleXlsRow {
    private SXSSFSheet sheet;
    private SXSSFRow row;

    protected SimpleXlsRow(SXSSFSheet sheet, SXSSFRow row) {
        this.sheet = sheet;
        this.row = row;
    }
}
