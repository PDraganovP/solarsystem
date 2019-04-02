package solarsystem.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solarsystem.domain.entities.Planet;
import solarsystem.domain.models.service.PlanetServiceModel;
import solarsystem.repositories.PlanetRepository;
import solarsystem.services.PlanetService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService {
    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PlanetServiceImpl(PlanetRepository planetRepository, ModelMapper modelMapper) {
        this.planetRepository = planetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PlanetServiceModel savePlanet(PlanetServiceModel planetServiceModel) {
        Planet planet = this.modelMapper.map(planetServiceModel, Planet.class);
        Planet savedPlanet = this.planetRepository.saveAndFlush(planet);
        PlanetServiceModel mappedPlanetServiceModel = this.modelMapper
                .map(savedPlanet, PlanetServiceModel.class);

        return mappedPlanetServiceModel;
    }

    @Override
    public void editPlanet(PlanetServiceModel planetServiceModel) {
        String id = planetServiceModel.getId();
        Planet planet = this.planetRepository.getOne(id);
        this.makeAChange(planetServiceModel, planet);
        this.planetRepository.save(planet);
    }

    @Override
    public PlanetServiceModel findById(String id) {
        Planet planet = this.planetRepository.findById(id).orElse(null);
        return planet == null ? null : this.modelMapper.map(planet, PlanetServiceModel.class);
    }

    @Override
    public boolean deletePlanetById(String id) {
        try {
            this.planetRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PlanetServiceModel> findAllOrderedByName() {
        List<Planet>planets=this.planetRepository.findAllOrderByName();
        List<PlanetServiceModel> planetServiceModels=planets.stream()
                .map(planet -> this.modelMapper
                .map(planet,PlanetServiceModel.class))
                .collect(Collectors.toList());

        return planetServiceModels;
    }

    private void makeAChange(PlanetServiceModel planetServiceModel, Planet planet) {
        planet.setName(planetServiceModel.getName());
        planet.setDistanceToStarSystem(planetServiceModel.getDistanceToStarSystem());
        planet.setPlanetType(planetServiceModel.getPlanetType());
        planet.setStarSystem(planetServiceModel.getStarSystem());
        planet.setSatellites(planetServiceModel.getSatellites());
        planet.setThereAtmosphere(planetServiceModel.getThereAtmosphere());
        planet.setThereLife(planetServiceModel.getThereLife());
        planet.setThereMagneticField(planetServiceModel.getThereMagneticField());
        planet.setThereRing(planetServiceModel.getThereRing());
        planet.setAge(planetServiceModel.getAge());
        planet.setDensity(planetServiceModel.getDensity());
        planet.setMass(planetServiceModel.getMass());
        planet.setRadius(planetServiceModel.getRadius());
        planet.setSquare(planetServiceModel.getSquare());
        planet.setTemperature(planetServiceModel.getTemperature());
    }
}
