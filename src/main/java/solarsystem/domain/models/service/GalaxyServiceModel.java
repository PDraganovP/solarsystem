package solarsystem.domain.models.service;

import solarsystem.domain.entities.enums.GalaxyType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GalaxyServiceModel {
    private String id;
    private String name;
    private GalaxyType galaxyType;
    private Double distanceToNearestGalaxy;

    public GalaxyServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @NotNull
    @Size(min = 4, max = 40, message = "Cannot be empty, should be between 4 and 40 symbols.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "Cannot be null.")
    public GalaxyType getGalaxyType() {
        return galaxyType;
    }

    public void setGalaxyType(GalaxyType galaxyType) {
        this.galaxyType = galaxyType;
    }


    public Double getDistanceToNearestGalaxy() {
        return distanceToNearestGalaxy;
    }

    public void setDistanceToNearestGalaxy(Double distanceToNearestGalaxy) {
        this.distanceToNearestGalaxy = distanceToNearestGalaxy;
    }
}
