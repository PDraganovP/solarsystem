package solarsystem.domain.entities.enums;

public enum SpectralClass {
    O("O"), B("B"), A("A"), F("F"), G("G"), K("K"), M("M");

    private String spectralClassValue;

    SpectralClass(String spectralClassValue) {
        this.spectralClassValue = spectralClassValue;
    }

    public String getSpectralClassValue() {
        return spectralClassValue;
    }
}
