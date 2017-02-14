package com.tapandgo.repository;

import com.tapandgo.data.City;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongo DAO For Cities Collection
 * Created by mcame on 13/02/17.
 */
public interface CityRepository extends MongoRepository<City, Long> {


}

