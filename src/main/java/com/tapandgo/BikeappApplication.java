package com.tapandgo;

import com.tapandgo.data.City;
import com.tapandgo.data.Station;
import com.tapandgo.repository.CityRepository;
import com.tapandgo.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableWebSecurity
public class BikeappApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BikeappApplication.class, args);

    }

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StationRepository stationRepository;

    @Override
    public void run(String... args) throws Exception {


        stationRepository.deleteAll();
        cityRepository.deleteAll();

        List<Station> stations = initDbStations();
        initDBCities(stations);
    }


    /**
     * script d'initialisation des stations
     *
     * @return
     */
    private List<Station> initDbStations() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(1L, new Date(), new Date(), "Chateau", null, null, 47.213603d, -1.548191d, 10, 10));
        stations.add(new Station(2L, new Date(), new Date(), "Cathédrale", null, null, 47.213603d, -1.548191d, 20, 20));
        stations.add(new Station(3L, new Date(), new Date(), "Médiathèque", null, null, 47.213603d, -1.548191d, 10, 10));
        stations.add(new Station(4L, new Date(), new Date(), "Commerces", null, null, 48.119218d, -1.680045d, 20, 20));
        stations.add(new Station(5L, new Date(), new Date(), "Gare sncf", null, null, 48.119218d, -1.680045d, 10, 10));
        stations.add(new Station(6L, new Date(), new Date(), "Nefs", null, null, 48.119218d, -1.680045d, 20, 20));

        stationRepository.save(stations);
        return stations;
    }

    /**
     * script d'initialisation des villes
     *
     * @return
     */
    private List<City> initDBCities(List<Station> stations) {
        cityRepository.deleteAll();
        List<City> villes = new ArrayList<>();
        City nantes = new City(1L, "Nantes", 47.213603d, -1.548191d);
        nantes.setStations(stations);

        stations.forEach((s) -> {
            s.setCity(nantes);
            stationRepository.save(s);
        });

        City rennes = new City(2L, "Rennes", 48.119218d, -1.680045d);
        City laRoche = new City(3L, "La roche sur Yon", 46.670568d, -1.426667d);
        villes.add(nantes);
        villes.add(rennes);
        villes.add(laRoche);
        cityRepository.save(villes);
        return villes;

    }
}
