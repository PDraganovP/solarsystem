package solarsystem.domain.entities.enums;

public enum PlanetType {
    TERRESTRIAL("Terrestrial"), GAS_GIANT("Gas giant"), PROTOPLANET("Protoplanet");

    private String type;
    PlanetType(String type) {
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
