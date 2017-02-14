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


        initDBCities();
    }


    /**
     * script d'initialisation des stations
     *
     * @return
     */
    private List<Station> initDbStationsNantes() {
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
    private List<Station> initDbStationsRennes() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(7L, new Date(), new Date(), "Saint Anne", null, null, 48.114476d, -1.679757d, 10, 10));
        stations.add(new Station(8L, new Date(), new Date(), "Les champs Libres", null, null, 48.105135d, -1.674607d, 20, 20));

        stationRepository.save(stations);
        return stations;
    }
    private List<Station> initDbStationsLaRoche() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(9L, new Date(), new Date(), "Place Nap", null, null, 46.670197d, -1.427410d, 10, 10));
        stations.add(new Station(10L, new Date(), new Date(), "Fuzz Yon", null, null, 46.671928d, -1.429730d, 20, 20));

        stationRepository.save(stations);
        return stations;
    }


    /**
     * script d'initialisation des villes
     *
     * @return
     */
    private List<City> initDBCities() {
        List<City> villes = new ArrayList<>();
        City nantes = new City(1L, "Nantes", 47.213603d, -1.548191d);
        List<Station> nantesStations = initDbStationsNantes();
        nantes.setStations(nantesStations);
        nantesStations.forEach((s) -> {
            s.setCity(nantes);
            stationRepository.save(s);
        });


        City rennes = new City(2L, "Rennes", 48.119218d, -1.680045d);
        List<Station> rennesStations = initDbStationsRennes();
        rennes.setStations(rennesStations);
        rennesStations.forEach((s) -> {
            s.setCity(rennes);
            stationRepository.save(s);
        });

        City laRoche = new City(3L, "La roche sur Yon", 46.670568d, -1.426667d);
        List<Station> stations = initDbStationsLaRoche();
        laRoche.setStations(stations);
        stations.forEach((s) -> {
            s.setCity(laRoche);
            stationRepository.save(s);
        });

        villes.add(nantes);
        villes.add(rennes);
        villes.add(laRoche);
        cityRepository.save(villes);
        return villes;

    }
}
