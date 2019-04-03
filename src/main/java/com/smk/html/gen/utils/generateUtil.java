package com.smk.html.gen.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

/**
 * @author liuwl
 * @date 2019/4/2 18:00
 */
@Slf4j
public class generateUtil {
    public static void generateTable(List<String> columnComments, List<String> columnNames, List<String> columnTypes, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        sb.append("\r\n").append("\t");
        sb.append("<thread>");
        for (String comment : columnComments) {
            sb.append("\r\n").append("\t\t");
            sb.append("<td>");
            sb.append(comment);
            sb.append("</td>");
        }
        sb.append("\r\n").append("\t");
        sb.append("</thread>");
        sb.append("\r\n").append("\t").append("\t");
        sb.append("<tr>");
        for (String name : columnNames) {
            sb.append("\r\n").append("\t").append("\t").append("\t");
            sb.append("<td>");
            sb.append(name);
            sb.append("</td>");
        }
        sb.append("\r\n").append("\t").append("\t");
        sb.append("</tr>");
        sb.append("\r\n");
        sb.append("</table>");
        try {
            File file = new File(tableName + ".html");
            FileUtils.write(file, sb.toString(), "UTF-8");
            log.info("写入文件完成--SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
