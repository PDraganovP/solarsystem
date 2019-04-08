package solarsystem.domain.models.view;

import solarsystem.domain.entities.Galaxy;
import solarsystem.domain.entities.Planet;
import solarsystem.domain.entities.Star;

import java.util.Set;

public class StarSystemViewModel {
    private String id;
    private String name;
    private Star star;
    private Set<Planet> planets;
    private Galaxy galaxy;

    public StarSystemViewModel() {
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

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }
}
