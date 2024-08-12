package com.sml.mapper;

import com.sml.model.LocationVO;
import java.util.List;

public interface LocationMapper {
    void save(LocationVO location);
    List<LocationVO> getRecentSearches();
}
