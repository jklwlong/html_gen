package com.smk.html.gen.utils;

import com.smk.html.gen.AllTable;
import com.smk.html.gen.TableDomain;
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
    public static void generateTable(List<AllTable> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("<style type=\"text/css\"> \n" +
                "\n" +
                "\n" +
                "body { \n" +
                "font: normal 11px auto \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; \n" +
                "color: #4f6b72; \n" +
                "background: #E6EAE9; \n" +
                "} \n" +
                "\n" +
                "a { \n" +
                "color: #c75f3e; \n" +
                "} \n" +
                "\n" +
                "#mytable { \n" +
                "width: 700px; \n" +
                "padding: 0; \n" +
                "margin: 0; \n" +
                "} \n" +
                "\n" +
                "caption { \n" +
                "padding: 0 0 5px 0; \n" +
                "width: 700px; \n" +
                "font: italic 11px \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; \n" +
                "text-align: right; \n" +
                "} \n" +
                "\n" +
                "th { \n" +
                "font: bold 11px \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; \n" +
                "color: #4f6b72; \n" +
                "border-right: 1px solid #C1DAD7; \n" +
                "border-bottom: 1px solid #C1DAD7; \n" +
                "border-top: 1px solid #C1DAD7; \n" +
                "letter-spacing: 2px; \n" +
                "text-transform: uppercase; \n" +
                "text-align: left; \n" +
                "padding: 6px 6px 6px 12px; \n" +
                "background: #CAE8EA  no-repeat; \n" +
                "} \n" +
                "\n" +
                "th.nobg { \n" +
                "border-top: 0; \n" +
                "border-left: 0; \n" +
                "border-right: 1px solid #C1DAD7; \n" +
                "background: none; \n" +
                "} \n" +
                "\n" +
                "td { \n" +
                "border-right: 1px solid #C1DAD7; \n" +
                "border-bottom: 1px solid #C1DAD7; \n" +
                "background: #fff; \n" +
                "font-size:11px; \n" +
                "padding: 6px 6px 6px 12px; \n" +
                "color: #4f6b72; \n" +
                "} \n" +
                "\n" +
                "\n" +
                "td.alt { \n" +
                "background: #F5FAFA; \n" +
                "color: #797268; \n" +
                "} \n" +
                "\n" +
                "th.spec { \n" +
                "border-left: 1px solid #C1DAD7; \n" +
                "border-top: 0; \n" +
                "background: #fff no-repeat; \n" +
                "font: bold 10px \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; \n" +
                "} \n" +
                "\n" +
                "th.specalt { \n" +
                "border-left: 1px solid #C1DAD7; \n" +
                "border-top: 0; \n" +
                "background: #f5fafa no-repeat; \n" +
                "font: bold 10px \"Trebuchet MS\", Verdana, Arial, Helvetica, sans-serif; \n" +
                "color: #797268; \n" +
                "} \n" +
                "/*---------for IE 5.x bug*/ \n" +
                "html>body td{ font-size:18px;} \n" +
                "body,td,th { \n" +
                "font-family: 微软雅黑, Arial; \n" +
                "font-size: 14px; \n" +
                "} \n" +
                "</style>");
        sb.append("\r\n").append("\t");

        for (AllTable table : list) {
            sb.append("<table id='mytable' cellspacing='0'");
            sb.append("\r\n").append("\t");
            sb.append("<tr>");
            sb.append("\r\n").append("\t\t");
            sb.append("<th colspan='4'>");
            sb.append(table.getTableName());
            sb.append("</th>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("\r\n").append("\t\t");
            sb.append("<th>");
            sb.append("字段名");
            sb.append("</th>");
            sb.append("<th>");
            sb.append("类型");
            sb.append("</th>");
            sb.append("<th>");
            sb.append("长度");
            sb.append("</th>");
            sb.append("<th>");
            sb.append("描述");
            sb.append("</th>");
            sb.append("</tr>");
            for (TableDomain t : table.getTableDomainList()) {
                sb.append("<tr>");
                sb.append("\r\n").append("\t\t");
                sb.append("<td>");
                sb.append(t.getField());
                sb.append("</td>");
                sb.append("<td>");
                sb.append(t.getType());
                sb.append("</td>");
                sb.append("<td>");
                sb.append(t.getSize());
                sb.append("</td>");
                sb.append("<td>");
                sb.append(t.getDesc());
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("</table>");
        }
        try {
            File file = new File("table" + ".html");
            FileUtils.write(file, sb.toString(), "UTF-8");
            log.info("写入文件完成--SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
