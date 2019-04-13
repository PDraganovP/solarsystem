package solarsystem.domain.models.binding;

import solarsystem.domain.entities.StarSystem;
import solarsystem.domain.entities.enums.SpectralClass;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StarBindingModel {
    private String id;
    private String name;
    private SpectralClass spectralClass;
    private Double luminosity;
    private StarSystem starSystem;
    private Double age;
    private Double temperature;
    private Double radius;
    private Double square;
    private Double density;
    private Double mass;

    public StarBindingModel() {
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

    @NotNull(message = "Cannot be null.")
    public SpectralClass getSpectralClass() {
        return spectralClass;
    }

    public void setSpectralClass(SpectralClass spectralClass) {
        this.spectralClass = spectralClass;
    }

    @Max(value = 100000,message = "Field can not be bigger than 100000")
    @Min(value = 0,message = "Field can not be negative")
    public Double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(Double luminosity) {
        this.luminosity = luminosity;
    }

    public StarSystem getStarSystem() {
        return starSystem;
    }

    public void setStarSystem(StarSystem starSystem) {
        this.starSystem = starSystem;
    }

    @Max(value = 100000,message = "Field can not be bigger than 100000")
    @Min(value = 0,message = "Field can not be negative")
    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    @Max(value = 100000,message = "Field can not be bigger than 100000")
    @Min(value = 0,message = "Field can not be negative")
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Max(value = 100000,message = "Field can not be bigger than 100000")
    @Min(value = 0,message = "Field can not be negative")
    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Max(value = 100000,message = "Field can not be bigger than 100000")
    @Min(value = 0,message = "Field can not be negative")
    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    @Max(value = 100000,message = "Field can not be bigger than 100000")
    @Min(value = 0,message = "Field can not be negative")
    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    @Max(value = 100000,message = "Field can not be bigger than 100000")
    @Min(value = 0,message = "Field can not be negative")
    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }
}
