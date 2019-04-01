package solarsystem.domain.entities;

import solarsystem.domain.entities.enums.GalaxyType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "galaxies")
public class Galaxy extends BaseEntity {
    private String name;
    private GalaxyType galaxyType;
    private Set<StarSystem> starSystems;
    private Double distanceToNearestGalaxy;

    public Galaxy() {
    }

    @Column(name = "galaxy_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "galaxy_type")
    public GalaxyType getGalaxyType() {
        return galaxyType;
    }

    public void setGalaxyType(GalaxyType galaxyType) {
        this.galaxyType = galaxyType;
    }

    @OneToMany(mappedBy = "galaxy")
    public Set<StarSystem> getStarSystems() {
        return starSystems;
    }

    public void setStarSystems(Set<StarSystem> starSystems) {
        this.starSystems = starSystems;
    }

    @Column(name = "distance_to_the_neareast_galaxy")
    public Double getDistanceToNearestGalaxy() {
        return distanceToNearestGalaxy;
    }

    public void setDistanceToNearestGalaxy(Double distanceToNearestGalaxy) {
        this.distanceToNearestGalaxy = distanceToNearestGalaxy;
    }
}
