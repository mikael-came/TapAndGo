package com.tapandgo.services;

import com.tapandgo.data.Station;
import com.tapandgo.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcame on 13/02/17.
 */
@Service
public class StationsService {

    @Autowired
    private StationRepository stationRepository;

    /**
     * Manage the withdrawal of a bike on a station
     *
     * @param stationId
     * @return
     */
    public boolean takeABike(Long stationId) {
        if (stationId == null) {
            return false;
        }

        Station station = stationRepository.findOne(stationId);
        if (station != null) {
            Integer nbBikes = station.getBikesAvailable();
            //Si des velos sont dispo
            if (nbBikes > 0) {
                nbBikes--;
                station.setBikesAvailable(nbBikes);
                stationRepository.save(station);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Manage de deposit of a bike on a station
     *
     * @param stationId
     * @return
     */
    public boolean dropABike(Long stationId) {
        if (stationId == null) {
            return false;
        }
        Station station = stationRepository.findOne(stationId);
        if (station != null) {
            Integer nbBikes = station.getBikesAvailable();
            Integer capacities = station.getBikesCapacities();
            //Si des velos sont dispo
            if (nbBikes < capacities) {
                nbBikes++;
                station.setBikesAvailable(nbBikes);
                stationRepository.save(station);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }


    /**
     * Retrieve the nearest station inside a radius of a given position
     *
     * @param lat
     * @param lng
     * @param radius
     * @return une liste de stations
     */
    public List<Station> getNearestStations(Double lat, Double lng, Integer radius) {
        List<Station> nearestStation = new ArrayList<>();
        if (lat != null && lng != null && radius != null) {
            Point point = new Point(lat, lng);
            Distance distance = new Distance(radius, Metrics.KILOMETERS);
            stationRepository.findByLocationNear(point, distance);
            //TODO PROBLEME D"INDEX avec geonear
            //https://blog.codecentric.de/en/2012/02/spring-data-mongodb-geospatial-queries/
        }

        return nearestStation;
    }

    /**
     * Retrieve all stations From a city
     *
     * @param idCity
     * @return
     */
    public List<Station> getStationsByCity(Long idCity) {
        List<Station> stations = new ArrayList<>();
        if (idCity != null) {

            stations.addAll(stationRepository.findByCity(idCity));

//                   final Pageable pageableRequest = new PageRequest(0, 2);
//                    Query query = new Query();
//                    query.addCriteria(Criteria.where("city.id").is(idCity));
//                    query.with(pageableRequest);
//                return mongoTemplate.find(query, Station.class);
        }

        return stations;
    }


}
