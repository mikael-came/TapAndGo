package com.tapandgo.repository;

import com.tapandgo.data.Station;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Mongo DAO For Stations Collection
 * Created by mcame on 13/02/17.
 */
public interface StationRepository extends MongoRepository<Station, Long> {

    @Query("{'city' :{'$ref' : 'city' , '$id' : ?0}}")
    List<Station> findByCity(Long idCity);

    List<Station> findByLocationNear(Point location, Distance distance);


}

