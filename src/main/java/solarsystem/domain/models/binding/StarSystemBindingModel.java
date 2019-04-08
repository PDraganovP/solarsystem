package solarsystem.domain.models.binding;

import solarsystem.domain.entities.Galaxy;
import solarsystem.domain.entities.Planet;
import solarsystem.domain.entities.Star;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class StarSystemBindingModel {
    private String id;
    private String name;
    private Star star;
    private Set<Planet> planets;
    private Galaxy galaxy;

    public StarSystemBindingModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 40, message = "Cannot be empty, should be between 3 and 40 symbols.")
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
