package org.project.excel.molecules;

import lombok.Getter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.project.excel.XlsSheet;
import org.project.excel.XlsSheetMetaMap;
import org.project.excel.annotation.ExcelSheet;
import org.project.excel.constants.CellRowType;
import org.project.excel.exception.ExcelRuntimeException;
import org.project.excel.factory.XlsSheetMetaMapFactory;
import org.project.excel.utils.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.project.excel.utils.ReflectionUtils.getField;

public class SimpleXlsSheet<T> implements XlsSheet {
    private SXSSFWorkbook workbook;
    private SXSSFSheet sheet;

    private static final int ROW_START_INDEX = 0;
    private static final int COLUMN_START_INDEX = 0;

    @Getter
    private SimpleXlsRow headerRow;

    @Getter
    private List<SimpleXlsRow> rowList;

    @Getter
    private XlsSheetMetaMap xlsSheetMetaMap;

    @Getter
    private SimpleXlsWorkbook simpleWorkbook;

    protected SimpleXlsSheet(SimpleXlsWorkbook simpleWorkbook, SXSSFWorkbook workbook, List<T> list, Class<T> clazz) {
        this.simpleWorkbook = simpleWorkbook;
        this.workbook = workbook;
        this.xlsSheetMetaMap = XlsSheetMetaMapFactory.getSingletonInstance().create(clazz, workbook);
        this.rowList = new ArrayList<>();

        this.buildSheet(list, clazz);
    }

    /**
     * Sheet Name 수정
     * @param sheetName
     */
    public void setSheetName(String sheetName) {
        int index = this.workbook.getSheetIndex(this.sheet);
        this.workbook.setSheetName(index, sheetName);
    }

    private void buildSheet(List<T> list, Class<T> clazz) {
        Annotation annotation = ReflectionUtils.getAnnotation(clazz, ExcelSheet.class);

        if (annotation == null) {
            sheet = workbook.createSheet(clazz.getName());
        } else {
            ExcelSheet excelSheet = (ExcelSheet) annotation;

            if (excelSheet.sheetName().equals("")) {
                sheet = workbook.createSheet(clazz.getName());
            } else {
                sheet = workbook.createSheet(excelSheet.sheetName());
            }
        }

        this.headerRow = new SimpleXlsRow(this.sheet, this.buildHeaders(sheet));

        int rowIndex = ROW_START_INDEX + 1;

        for (T t : list) {
            this.rowList.add(
                    new SimpleXlsRow(this.sheet, this.buildRows(sheet, rowIndex++, t)));
        }
    }

    private SXSSFCell buildCells(SXSSFRow row, int columnIndex, Object item) {
        SXSSFCell cell = row.createCell(columnIndex);
        if (Number.class.isAssignableFrom(item.getClass())) {
            cell.setCellValue(((Number) item).doubleValue());
        } else if (item instanceof String) {
            cell.setCellValue((String) item);
        } else if (item instanceof Date) {
            cell.setCellValue((Date) item);
        } else if (item instanceof LocalDate) {
            cell.setCellValue((LocalDate) item);
        } else if (item instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime) item);
        }
        return cell;
    }

    private SXSSFRow buildRows(SXSSFSheet sheet, int rowIndex, T item) {
        SXSSFRow row = sheet.createRow(rowIndex);

        Class<?> clazz = item.getClass();

        int columnIndex = COLUMN_START_INDEX;

        for (String fieldName : this.xlsSheetMetaMap.getFieldNames()) {
            Field field = getField(clazz, fieldName);

            try {
                Object data = Optional.ofNullable(field)
                        .orElseThrow(ExcelRuntimeException::new)
                        .get(item);

                SXSSFCell cell = this.buildCells(row, columnIndex++, data);

                CellStyle cellStyle = this.xlsSheetMetaMap.getCellStyle(fieldName, CellRowType.BODY);

                cell.setCellStyle(cellStyle);

                if (Number.class.isAssignableFrom(field.getType())) {
                    cell.setCellType(CellType.NUMERIC);
                } else {
                    cell.setCellType(CellType.STRING);
                }
            } catch (IllegalAccessException e) {
                throw new ExcelRuntimeException(e);
            }
        }
        return row;
    }

    private SXSSFRow buildHeaders(SXSSFSheet sheet) {
        SXSSFRow row = sheet.createRow(SimpleXlsSheet.ROW_START_INDEX);

        int columnIndex = SimpleXlsSheet.COLUMN_START_INDEX;

        for (String fieldName : xlsSheetMetaMap.getFieldNames()) {
            int cellWidth = this.xlsSheetMetaMap.getCellWidth(fieldName);
            sheet.setColumnWidth(columnIndex, cellWidth * 1024);

            SXSSFCell cell = row.createCell(columnIndex++);
            cell.setCellValue(xlsSheetMetaMap.getHeaderName(fieldName));

            CellStyle cellStyle = this.xlsSheetMetaMap.getCellStyle(fieldName, CellRowType.HEADER);
            cell.setCellStyle(cellStyle);
        }
        return row;
    }
}
