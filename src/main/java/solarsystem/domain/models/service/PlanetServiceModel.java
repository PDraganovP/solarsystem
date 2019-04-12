package solarsystem.domain.models.service;

import solarsystem.domain.entities.Satellite;
import solarsystem.domain.entities.StarSystem;
import solarsystem.domain.entities.enums.PlanetType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class PlanetServiceModel {
    private String id;
    private String name;
    private PlanetType planetType;
    private Boolean isThereMagneticField;
    private Boolean isThereAtmosphere;
    private Boolean isThereLife;
    private Double distanceToStarSystem;
    private Boolean isThereRing;
    private Double age;
    private Double temperature;
    private Double radius;
    private Double square;
    private Double density;
    private Double mass;
    private StarSystem starSystem;
    private Set<Satellite> satellites;

    public PlanetServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 30, message = "Cannot be empty, should be between 3 and 30 symbols.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Cannot be null.")
    public PlanetType getPlanetType() {
        return planetType;
    }


    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType;
    }

    @NotNull(message = "Cannot be null.")
    public Boolean getThereMagneticField() {
        return isThereMagneticField;
    }

    public void setThereMagneticField(Boolean thereMagneticField) {
        isThereMagneticField = thereMagneticField;
    }

    @NotNull(message = "Cannot be null.")
    public Boolean getThereAtmosphere() {
        return isThereAtmosphere;
    }

    public void setThereAtmosphere(Boolean thereAtmosphere) {
        isThereAtmosphere = thereAtmosphere;
    }

    @NotNull(message = "Cannot be null.")
    public Boolean getThereLife() {
        return isThereLife;
    }

    public void setThereLife(Boolean thereLife) {
        isThereLife = thereLife;
    }

    @Min(1)
    @Max(10000000)
    public Double getDistanceToStarSystem() {
        return distanceToStarSystem;
    }

    public void setDistanceToStarSystem(Double distanceToStarSystem) {
        this.distanceToStarSystem = distanceToStarSystem;
    }

    @NotNull(message = "Cannot be null.")
    public Boolean getThereRing() {
        return isThereRing;
    }

    public void setThereRing(Boolean thereRing) {
        isThereRing = thereRing;
    }

    @Max(100000)
    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    @Max(1000000)
    public Double getTemperature() {
        return temperature;
    }


    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Max(value = 125,message = "Radius can not be bigger than 125")
    @Min(value = 0,message = "Radius can not be negative")
    @NotNull(message = "Cannot be null.")
    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Max(1000000)
    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    @Max(100000)
    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    @Max(1000000)
    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public StarSystem getStarSystem() {
        return starSystem;
    }

    public void setStarSystem(StarSystem starSystem) {
        this.starSystem = starSystem;
    }

    public Set<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(Set<Satellite> satellites) {
        this.satellites = satellites;
    }
}
