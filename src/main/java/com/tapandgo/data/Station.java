package com.tapandgo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

/**
 *  This is Modele of a Bike station
 * Created by mcame on 13/02/17.
 */
public class Station {

    @Id
    private Long id;
    private Date creationDate;
    private Date lastUpdate;

    private String name;
    private String address;
    private String description;
    private Double latitude;
    private Double longitude;
    private Integer bikesCapacities;
    private Integer bikesAvailable;
    @JsonIgnore
    private Point location;

    @DBRef
    @JsonIgnore
    private City city;

    public Station(Long id, Date creationDate, Date lastUpdate, String name, String address, String description, Double latitude, Double longitude, Integer bikesCapacities, Integer bikesAvailable) {
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.name = name;
        this.address = address;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bikesCapacities = bikesCapacities;
        this.bikesAvailable = bikesAvailable;

        // For Mongo indexing location
        if (latitude != null && longitude != null) {
            this.location = new Point(latitude, longitude);
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getBikesCapacities() {
        return bikesCapacities;
    }

    public void setBikesCapacities(Integer bikesCapacities) {
        this.bikesCapacities = bikesCapacities;
    }

    public Integer getBikesAvailable() {
        return bikesAvailable;
    }

    public void setBikesAvailable(Integer bikesAvailable) {
        this.bikesAvailable = bikesAvailable;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @JsonIgnore
    public Point getLocation() {
        return this.location;

    }
}
