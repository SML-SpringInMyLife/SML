package com.sml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sml.mapper.LocationMapper;
import com.sml.model.LocationVO;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public void save(LocationVO location) {
        locationMapper.save(location);
    }

    @Override
    public List<LocationVO> getRecentSearches() {
        return locationMapper.getRecentSearches();
    }
}
