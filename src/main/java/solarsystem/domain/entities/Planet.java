package solarsystem.domain.entities;

import solarsystem.domain.entities.enums.PlanetType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet extends SpaceObject {

    private String name;

    private PlanetType planetType;

    private Boolean isThereMagneticFeild;

    private Boolean isThereAtmosphere;

    private Boolean isThereLife;

    private Double distanceToStarSystem;

    private Boolean isThereRing;
    /*(targetEntity = StarSystem.class, cascade = CascadeType.ALL)*/
    private StarSystem starSystem;

    private Set<Satellite> satellites;

    public Planet() {
    }

    @Column(name = "planet_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "planet_type")
    public PlanetType getPlanetType() {
        return planetType;
    }

    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType;
    }

    @Column(name = "is_there_magnetic_field")
    public Boolean getThereMagneticFeild() {
        return isThereMagneticFeild;
    }

    public void setThereMagneticFeild(Boolean thereMagneticFeild) {
        isThereMagneticFeild = thereMagneticFeild;
    }

    @Column(name = "is_there_atmosphere")
    public Boolean getThereAtmosphere() {
        return isThereAtmosphere;
    }

    public void setThereAtmosphere(Boolean thereAtmosphere) {
        isThereAtmosphere = thereAtmosphere;
    }

    @Column(name = "is_there_life")
    public Boolean getThereLife() {
        return isThereLife;
    }

    public void setThereLife(Boolean thereLife) {
        isThereLife = thereLife;
    }

    @Column(name = "distance_to_star_system")
    public Double getDistanceToStarSystem() {
        return distanceToStarSystem;
    }

    public void setDistanceToStarSystem(Double distanceToStarSystem) {
        this.distanceToStarSystem = distanceToStarSystem;
    }

    @Column(name = "iS_there_ring")
    public Boolean getThereRing() {
        return isThereRing;
    }

    public void setThereRing(Boolean thereRing) {
        isThereRing = thereRing;
    }

    @ManyToOne
    public StarSystem getStarSystem() {
        return starSystem;
    }

    public void setStarSystem(StarSystem starSystem) {
        this.starSystem = starSystem;
    }

    @OneToMany(mappedBy = "planet")
    public Set<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(Set<Satellite> satellites) {
        this.satellites = satellites;
    }

}

