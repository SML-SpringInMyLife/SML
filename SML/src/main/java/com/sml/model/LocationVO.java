package com.sml.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LocationVO {
	private String searchKeyword;
    private Timestamp searchTime;

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Timestamp getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Timestamp searchTime) {
        this.searchTime = searchTime;
    }
}
