package com.frame.springmvc.ssmbuild.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: railgun
 * 2021/6/19 10:12
 * PS:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    private Integer bookId;
    private String bookName;
    private Integer bookCounts;
    private String detail;

    public Books(Map<String, Object> map) {
        this.bookId = (Integer) map.get("book_id");
        this.bookName = (String) map.get("book_name");
        this.bookCounts = (Integer) map.get("book_counts");
        this.detail = (String) map.get("detail");
    }

}
