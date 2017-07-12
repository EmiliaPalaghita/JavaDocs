package ro.teamnet.zth.api.em;

/**
 * Created by Emilia.Palaghita on 12-Jul-17.
 */
public class Condition {
    private String columnName;
    private Object value;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
