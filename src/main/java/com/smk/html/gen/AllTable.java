package com.smk.html.gen;

import java.util.List;

/**
 * @author liuwl
 * @since 2020/12/7
 */
public class AllTable {
    private String tableName;
    private List<TableDomain> tableDomainList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableDomain> getTableDomainList() {
        return tableDomainList;
    }

    public void setTableDomainList(List<TableDomain> tableDomainList) {
        this.tableDomainList = tableDomainList;
    }
}
