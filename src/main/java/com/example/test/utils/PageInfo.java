package com.newnoa.status.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageInfo<T> {
    private int pageNum;

    private long total ;//总条数

    private int pageSize ;

    private List<T> rows ;

    public void setPageInfo(int pageNum ,int pageSize,long total ,List<T> rows){
        this.pageNum = pageNum ;
        this.pageSize = pageSize ;
        this.total = total ;
        this.rows = rows ;
    }

    public void setPageInfo(List<T> rows){
        this.rows = rows ;
    }
}
