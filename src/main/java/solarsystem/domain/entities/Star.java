package solarsystem.domain.entities;

import solarsystem.domain.entities.enums.SpectralClass;

import javax.persistence.*;

@Entity
@Table(name = "stars")
public class Star extends SpaceObject {
    private String name;
    private SpectralClass spectralClass;
    private Double luminosity;
    private StarSystem starSystem;

    public Star() {
    }

    @Column(name = "star_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "spectral_class")
    public SpectralClass getSpectralClass() {
        return spectralClass;
    }

    public void setSpectralClass(SpectralClass spectralClass) {
        this.spectralClass = spectralClass;
    }

    @Column(name = "luminosity")
    public Double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(Double luminosity) {
        this.luminosity = luminosity;
    }

    @OneToOne
    public StarSystem getStarSystem() {
        return starSystem;
    }

    public void setStarSystem(StarSystem starSystem) {
        this.starSystem = starSystem;
    }
}
