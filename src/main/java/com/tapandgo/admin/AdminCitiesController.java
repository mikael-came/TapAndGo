package com.tapandgo.admin;

import com.tapandgo.data.City;
import com.tapandgo.data.Station;
import com.tapandgo.repository.CityRepository;
import com.tapandgo.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * The Administation webapp Controller
 * <p>
 * Created by mcame on 14/02/17.
 */
@Controller
@RequestMapping("/admin")
public class AdminCitiesController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StationRepository stationRepository;

    @RequestMapping
    @Secured("ROLE_ADMIN")
    public String getAdmin(Model model) {

        List<City> cities = cityRepository.findAll();
        //init of list of cities
        model.addAttribute("cities", cities);

        //Form init
        City newCity = new City();
        newCity.setId(Long.valueOf(cities.size() + 1));
        model.addAttribute("city", newCity);
        return "/admin/cities";
    }


    @RequestMapping(value = "/cities", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String citySubmit(@ModelAttribute City city, Model model) {

        cityRepository.save(city);


        // Redirect view to Get :/admin For refresh action
        return "redirect:/admin";

    }

    @RequestMapping(value = "/cities/{idCity}")
    @Secured("ROLE_ADMIN")
    public String getCity(@PathVariable Long idCity, Model model) {

        City city = cityRepository.findOne(idCity);
        model.addAttribute("city", city);

        // retrieve stations by city selected
        List<Station> stations = stationRepository.findByCity(idCity);
        model.addAttribute("stations", stations);
        return "admin/city";
    }


    //TODO StationSubmit


}
