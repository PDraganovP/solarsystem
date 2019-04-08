package solarsystem.domain.entities.enums;

public enum GalaxyType {
    SPIRAL("Spiral"), ELLIPTICAL("Elliptical"), IRREGULAR("Irregular");
    private String type;

    GalaxyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}



