package com.smk.html.gen;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.smk.html.gen.utils.DatabaseUtil.closeConnection;
import static com.smk.html.gen.utils.DatabaseUtil.getConnection;
import static com.smk.html.gen.utils.ReaderUtil.readFile;
import static com.smk.html.gen.utils.generateUtil.generateTable;


/**
 * @author liuwl
 * @date 2019/4/2 10:56
 */
@Slf4j
public class getColum {

    private static final String SQL = "SELECT * FROM ";

    /**
     * 获取表中所有字段名称
     *
     * @return
     */
    public static void getColumn(String path) {
//        List<String> columnNames = new ArrayList<>();
//        List<String> columnTypes = new ArrayList<>();

//        List<Integer> sizes = new ArrayList<>();

        List<AllTable> list = new ArrayList<>();

        List<String> columnComments = new ArrayList<>();
        Map<String, String> map = readFile(path);
        Connection conn = null;
        PreparedStatement pStemt = null;
        ResultSet rs;
        List<String> tables = new ArrayList<>();
        if (map.get("tableName").contains(",")) {
            tables = Arrays.asList(map.get("tableName").split(","));
        } else {
            tables.add(map.get("tableName"));
        }
        for (String table : tables) {
            try {
                columnComments.clear();
                List<TableDomain> tableDomainList = new ArrayList<>();
                String tableSql = SQL + table;
                conn = getConnection(map.get("driverClassName"), map.get("url"), map.get("username"), map.get("password"));
                pStemt = conn.prepareStatement(tableSql);
                ResultSetMetaData rsmd = pStemt.getMetaData();
                int size = rsmd.getColumnCount();
                rs = pStemt.executeQuery("show full columns from " + table);
                while (rs.next()) {
                    columnComments.add(rs.getString("Comment"));
                }
                for (int i = 0; i < size; i++) {
                    TableDomain domain = new TableDomain();
                    domain.setField(rsmd.getColumnName(i + 1));
                    domain.setType(rsmd.getColumnTypeName(i + 1));
                    domain.setSize(rsmd.getColumnDisplaySize(i + 1));
                    domain.setDesc(columnComments.get(i));
                    tableDomainList.add(domain);
                }
                AllTable all = new AllTable();
                all.setTableName(table);
                all.setTableDomainList(tableDomainList);
                list.add(all);
            } catch (SQLException e) {
                log.error("getColumnNames failure", e);
            } finally {
                if (pStemt != null) {
                    try {
                        pStemt.close();
                        closeConnection(conn);
                    } catch (SQLException e) {
                        log.error("getColumnNames close pstem and connection failure", e);
                    }
                }
            }
            log.info("列描述---" + columnComments.toString());
//            log.info("列名称---" + columnNames.toString());
//            log.info("列类型---" + columnTypes.toString());
        }
        generateTable(list);
    }
}
