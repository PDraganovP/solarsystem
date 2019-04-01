package solarsystem.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "star_systems")
public class StarSystem extends BaseEntity {
    private String name;
    private Star star;
    private Set<Planet> planets;
    private Galaxy galaxy;

    public StarSystem() {
    }

    @Column(name = "star_system_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "starSystem")
    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    @OneToMany(mappedBy = "starSystem")
    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }

    @ManyToOne
    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }
}
