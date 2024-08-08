package com.sml.service;

import com.sml.model.LocationVO;

import java.util.List;

public interface LocationService {
    void save(LocationVO locationVO);
    List<LocationVO> getRecentSearches();
}
