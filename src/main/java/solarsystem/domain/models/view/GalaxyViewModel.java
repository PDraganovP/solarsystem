package solarsystem.domain.models.view;

import solarsystem.domain.entities.enums.GalaxyType;

public class GalaxyViewModel {
    private String id;
    private String name;
    private GalaxyType galaxyType;

    private Double distanceToNearestGalaxy;

    public GalaxyViewModel() {
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
