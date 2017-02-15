package com.tapandgo.api;

import com.tapandgo.data.City;
import com.tapandgo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcame on 13/02/17.
 */
@RestController
@RequestMapping("/api/cities")
@Secured("ROLE_USER")
public class CitiesController {
    @Autowired
    private CityRepository cityRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<City> getAllCities(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                   @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {


        //List<City> all = cityRepository.findAll();
        Pageable pageable = new PageRequest(page, limit);
        Page<City> currentPage = cityRepository.findAll(pageable);
        return currentPage.getContent();



    }
}
