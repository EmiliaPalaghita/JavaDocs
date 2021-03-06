package ro.teamnet.zth.api.em;

/**
 * Created by Emilia.Palaghita on 12-Jul-17.
 */
public class ColumnInfo {
    private String columnName;
    private Class columnType;
    private String dbColumnName;
    private boolean isId;
    private Object value;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class getColumnType() {
        return columnType;
    }

    public void setColumnType(Class columnType) {
        this.columnType = columnType;
    }

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    public boolean isId() {
        return isId;
    }

    public void setId(boolean id) {
        isId = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "columnName='" + columnName + '\'' +
                ", columnType=" + columnType +
                ", dbColumnName='" + dbColumnName + '\'' +
                ", isId=" + isId +
                ", value=" + value +
                "}\n";
    }
}
