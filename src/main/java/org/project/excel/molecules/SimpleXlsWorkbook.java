package org.project.excel.molecules;

import lombok.Getter;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleXlsWorkbook {
    private final SXSSFWorkbook workbook;

    @Getter
    private List<SimpleXlsSheet<?>> sheetList;

    public SimpleXlsWorkbook() {
        this.sheetList = new ArrayList<>();
        this.workbook = new SXSSFWorkbook();
    }

    public <T> SimpleXlsSheet<T> addSheet(List<T> list, Class<T> clazz) {
        SimpleXlsSheet<T> sheet = new SimpleXlsSheet<>(this, this.workbook, list, clazz);
        this.sheetList.add(sheet);
        return sheet;
    }

    public void write(OutputStream ous) throws IOException {
        workbook.write(ous);
        workbook.close();
        workbook.dispose();
        System.out.println("OutputStream 안닫음");
    }

    public void write(File file) throws IOException {
        OutputStream ous = new FileOutputStream(file);
        this.write(ous);
    }

    public void write(String fileName) throws IOException {
        this.write(new File(fileName));
    }
}
