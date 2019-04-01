package solarsystem.domain.entities.enums;

public enum PlanetType {
    TERRESTRIAL("terrestrial"), GAS_GIANT("gas giant"), PROTOPLANET("protoplanet");

    private String type;
    PlanetType(String type) {
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
