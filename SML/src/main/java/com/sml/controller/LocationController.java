package com.sml.controller;

import com.sml.model.LocationVO;
import com.sml.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @GetMapping("/map")
    public String location() {
        logger.info("Location page accessed");
        return "location/locationMain"; // 뷰 이름
    }

    @PostMapping("/saveSearchKeyword")
    @ResponseBody
    public void saveSearchKeyword(@RequestBody LocationVO locationVO) {
        locationVO.setSearchTime(new Timestamp(System.currentTimeMillis())); // 현재 시간을 설정
        locationService.save(locationVO);
        logger.info("Search keyword saved: " + locationVO.getSearchKeyword());
    }

    @GetMapping("/getRecentSearches")
    @ResponseBody // 이 어노테이션이 JSON 형태로 응답하게 만듭니다.
    public List<LocationVO> getRecentSearches() {
        List<LocationVO> searches = locationService.getRecentSearches();
        if (searches == null || searches.isEmpty()) {
            logger.warn("No recent searches found.");
        } else {
            logger.info("Recent searches retrieved: " + searches);
        }
        return searches;
    }


    
    @GetMapping("/guide")
    public String guide() {
        logger.info("Guide page accessed");
        return "location/guide"; // guide.jsp 페이지로 이동
    }
}
