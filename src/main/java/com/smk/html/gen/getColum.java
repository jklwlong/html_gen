package com.smk.html.gen;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
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
        List<String> columnNames = new ArrayList<>();
        List<String> columnTypes = new ArrayList<>();
        List<String> columnComments = new ArrayList<>();
        Map<String, String> map = readFile(path);
        Connection conn = getConnection(map.get("driverClassName"), map.get("url"), map.get("username"), map.get("password"));
        PreparedStatement pStemt = null;
        ResultSet rs;
        String tableSql = SQL + map.get("tableName");
        try {
            pStemt = conn.prepareStatement(tableSql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
            rs = pStemt.executeQuery("show full columns from " + map.get("tableName"));
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
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
        log.info("列名称---" + columnNames.toString());
        log.info("列类型---" + columnTypes.toString());
        generateTable(columnComments, columnNames, columnTypes, map.get("tableName"));
    }
}
