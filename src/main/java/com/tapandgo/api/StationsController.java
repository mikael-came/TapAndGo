package com.tapandgo.api;

import com.tapandgo.data.Station;
import com.tapandgo.repository.CityRepository;
import com.tapandgo.repository.StationRepository;
import com.tapandgo.services.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the Controller For the API Of the bike station ressource
 * Created by mcame on 13/02/17.
 */
@RestController
@RequestMapping("/api/stations")
@Secured("ROLE_USER")
public class StationsController {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StationsService stationsService;

    //TODO should This call be managed by station Controlller like : /api/cities/{cityid}/stations
    @RequestMapping("/{cityId}")
    public List<Station> getAllStationsOfCity(@PathVariable Long cityId,
                                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {


        return stationsService.getStationsByCity(cityId);

    }


    @RequestMapping("/near")
    public List<Station> getNearestStations(@RequestParam(value = "lat", required = true) Double lat,
                                            @RequestParam(value = "lng", required = true) Double lng,
                                            @RequestParam(value = "radius", required = true) Integer radius
    ) {
        return stationsService.getStationsByCity(1l);
        //TODO MAKE IT WORK
//        return stationsService.getNearestStations(lat, lng, radius);
    }

    @RequestMapping(value = "/{stationId}/take", method = RequestMethod.POST)
    public boolean takeBike(@PathVariable Long stationId) {
        return stationsService.takeABike(stationId);
    }

    @RequestMapping(value = "/{stationId}/drop", method = RequestMethod.POST)
    public boolean dropBike(@PathVariable Long stationId) {
        return stationsService.dropABike(stationId);
    }
}
