package solarsystem.domain.models.view;

import solarsystem.domain.entities.enums.PlanetType;

public class ShowPlanetViewModel {
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

    public ShowPlanetViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlanetType getPlanetType() {
        return planetType;
    }

    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType;
    }

    public Boolean getThereMagneticField() {
        return isThereMagneticField;
    }

    public void setThereMagneticField(Boolean thereMagneticField) {
        isThereMagneticField = thereMagneticField;
    }

    public Boolean getThereAtmosphere() {
        return isThereAtmosphere;
    }

    public void setThereAtmosphere(Boolean thereAtmosphere) {
        isThereAtmosphere = thereAtmosphere;
    }

    public Boolean getThereLife() {
        return isThereLife;
    }

    public void setThereLife(Boolean thereLife) {
        isThereLife = thereLife;
    }

    public Double getDistanceToStarSystem() {
        return distanceToStarSystem;
    }

    public void setDistanceToStarSystem(Double distanceToStarSystem) {
        this.distanceToStarSystem = distanceToStarSystem;
    }

    public Boolean getThereRing() {
        return isThereRing;
    }

    public void setThereRing(Boolean thereRing) {
        isThereRing = thereRing;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }
}
