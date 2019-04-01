package solarsystem.domain.entities.enums;

public enum GalaxyType {
    SPIRAL("spiral"), ELLIPTICAL("elliptical"), IRREGULAR("irregular");
    private String type;

    GalaxyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}



