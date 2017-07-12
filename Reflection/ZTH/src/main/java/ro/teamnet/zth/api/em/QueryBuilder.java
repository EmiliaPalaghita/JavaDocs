package ro.teamnet.zth.api.em;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Emilia.Palaghita on 12-Jul-17.
 */
public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    public String getValueForQuery(Object value) {
        if (value.getClass().getName().equals("java.lang.String")) {
            value = (String) value;
            return "'" + value + "'";
        }
        if (value.getClass().getName().equals("java.util.Date")) {
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('" + dateFormat.format((Date) value) + "','mm-dd-YYYY'";
        }
        return value.toString();
    }

    public QueryBuilder addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    private String createSelectQuery() {
        return null;
    }

    private String createDeleteQuery() {
        return null;
    }

    private String createUpdateQuery() {
        return null;
    }

    private String createInsertQuery() {
        return null;
    }


}
