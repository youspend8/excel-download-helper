package org.project.excel.factory;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.project.excel.XlsSheetMetaMap;
import org.project.excel.annotation.ExcelCellStyle;
import org.project.excel.annotation.ExcelColumn;
import org.project.excel.constants.CellRowType;
import org.project.excel.exception.UnSupportDataFormatException;
import org.project.excel.molecules.cell.CellFieldRowType;
import org.project.excel.style.AbstractCellStyle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.project.excel.utils.ReflectionUtils.getAllFields;
import static org.project.excel.utils.ReflectionUtils.getAnnotation;

public class XlsSheetMetaMapFactory {
    private static XlsSheetMetaMapFactory xlsSheetMetaMapFactory;

    static {
        xlsSheetMetaMapFactory = new XlsSheetMetaMapFactory();
    }

    public static XlsSheetMetaMapFactory getSingletonInstance() {
        return xlsSheetMetaMapFactory;
    }

    public XlsSheetMetaMap create(Class<?> clazz, SXSSFWorkbook workbook) {
        Map<String, String> fieldNameMap = new LinkedHashMap<>();
        Map<String, Integer> cellWidthMap = new LinkedHashMap<>();
        Map<CellFieldRowType, CellStyle> cellStyleMap = new LinkedHashMap<>();

        for (Field field : getAllFields(clazz)) {
            Annotation annotation = getAnnotation(field, ExcelColumn.class);

            if (annotation != null) {
                ExcelColumn excelColumn = (ExcelColumn) annotation;

                putFieldName(field.getName(), excelColumn, fieldNameMap);
                putCellWidth(field.getName(), excelColumn, cellWidthMap);
                putCellStyle(field.getName(), excelColumn, cellStyleMap, workbook);
            }
        }

        return new XlsSheetMetaMap(fieldNameMap, cellStyleMap, cellWidthMap);
    }

    private void putCellStyle(String fieldName, ExcelColumn excelColumn, Map<CellFieldRowType, CellStyle> cellStyleMap,
                              SXSSFWorkbook workbook) {
        putHeaderCellStyle(fieldName, excelColumn.headerStyle(), CellRowType.HEADER, cellStyleMap, workbook);
        putHeaderCellStyle(fieldName, excelColumn.bodyStyle(), CellRowType.BODY, cellStyleMap, workbook);
    }

    private void putHeaderCellStyle(String fieldName,
                                    ExcelCellStyle excelCellStyle,
                                    CellRowType cellRowType,
                                    Map<CellFieldRowType, CellStyle> cellStyleMap,
                                    SXSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();

        //  Insert Default Font Object
        cellStyle.setFont(workbook.createFont());

        CellFieldRowType fieldRowType = new CellFieldRowType(fieldName, cellRowType);
        cellStyleMap.put(fieldRowType, cellStyle);

        Class<? extends AbstractCellStyle>[] cellStyleClasses = excelCellStyle.cellStyleClasses();

        for (Class<? extends AbstractCellStyle> clazz : cellStyleClasses) {
            try {
                AbstractCellStyle abstractCellStyle = clazz.newInstance();
                abstractCellStyle.apply(cellStyle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        HorizontalAlignment alignment = excelCellStyle.textAlign();
        cellStyle.setAlignment(alignment);

        String dataFormatExp = excelCellStyle.dataFormat();
        try {
            XSSFDataFormat dataFormat = (XSSFDataFormat) workbook.createDataFormat();
            cellStyle.setDataFormat(dataFormat.getFormat(dataFormatExp));
        } catch (Exception e) {
            throw new UnSupportDataFormatException(e);
        }
    }

    private void putCellWidth(String fieldName, ExcelColumn excelColumn, Map<String, Integer> cellWidthMap) {
        int width = excelColumn.columnWidth();
        if (width == 0) {
            cellWidthMap.put(fieldName, 10);
        } else {
            cellWidthMap.put(fieldName, width);
        }
    }

    private void putFieldName(String fieldName, ExcelColumn excelColumn, Map<String, String> fieldNameMap) {
        String headerName = excelColumn.headerName();
        if ("".equals(headerName)) {
            fieldNameMap.put(fieldName, fieldName);
        } else {
            fieldNameMap.put(fieldName, headerName);
        }
    }
}
