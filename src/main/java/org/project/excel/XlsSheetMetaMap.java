package org.project.excel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.poi.ss.usermodel.CellStyle;
import org.project.excel.constants.CellRowType;
import org.project.excel.molecules.cell.CellFieldRowType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class XlsSheetMetaMap {
    private Map<String, String> fieldNameMap;
    private Map<String, Integer> cellWidthMap;
    private Map<CellFieldRowType, CellStyle> cellStyleMap;

    public XlsSheetMetaMap(Map<String, String> fieldNameMap,
                           Map<CellFieldRowType, CellStyle> cellStyleMap,
                           Map<String, Integer> cellWidthMap) {
        this.fieldNameMap = fieldNameMap;
        this.cellStyleMap = cellStyleMap;
        this.cellWidthMap = cellWidthMap;
    }

    public List<String> getFieldNames() {
        return new ArrayList<>(fieldNameMap.keySet());
    }

    public List<String> getHeaderNames() {
        return new ArrayList<>(this.fieldNameMap.values());
    }

    public String getHeaderName(String fieldName) {
        return this.fieldNameMap.get(fieldName);
    }

    public CellStyle getCellStyle(String fieldName, CellRowType cellRowType) {
        CellFieldRowType cellFieldRowType = new CellFieldRowType(fieldName, cellRowType);
        return cellStyleMap.get(cellFieldRowType);
    }

    public Integer getCellWidth(String fieldName) {
        return this.cellWidthMap.get(fieldName);
    }
}
