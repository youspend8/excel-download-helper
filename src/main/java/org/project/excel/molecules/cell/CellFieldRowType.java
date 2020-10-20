package org.project.excel.molecules.cell;

import lombok.ToString;
import org.project.excel.constants.CellRowType;

import java.util.Objects;

@ToString
public class CellFieldRowType {
    private String fieldName;
    private CellRowType cellRowType;

    public CellFieldRowType(String fieldName, CellRowType cellRowType) {
        this.fieldName = fieldName;
        this.cellRowType = cellRowType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        CellFieldRowType that = (CellFieldRowType) o;

        return Objects.equals(fieldName, that.fieldName) &&
                cellRowType == that.cellRowType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, cellRowType);
    }
}
