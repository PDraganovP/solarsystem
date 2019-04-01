package solarsystem.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "satellites")
public class Satellite extends SpaceObject {

    private String name;
    private Planet planet;
    private Double distanceToThePlanet;

    public Satellite() {
    }

    @Column(name = "satellite_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    @Column(name = "distance_to_the_planet")
    public Double getDistanceToThePlanet() {
        return distanceToThePlanet;
    }

    public void setDistanceToThePlanet(Double distanceToThePlanet) {
        this.distanceToThePlanet = distanceToThePlanet;
    }
}
