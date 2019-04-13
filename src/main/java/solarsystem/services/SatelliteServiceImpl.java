package solarsystem.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solarsystem.domain.entities.Satellite;
import solarsystem.domain.models.service.SatelliteServiceModel;
import solarsystem.repository.SatelliteRepository;
import solarsystem.services.SatelliteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SatelliteServiceImpl implements SatelliteService {
    private SatelliteRepository satelliteRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SatelliteServiceImpl(SatelliteRepository satelliteRepository, ModelMapper modelMapper) {
        this.satelliteRepository = satelliteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SatelliteServiceModel saveSatellite(SatelliteServiceModel satelliteServiceModel) {
        Satellite satellite = this.modelMapper.map(satelliteServiceModel, Satellite.class);
        Satellite savedSatellite = this.satelliteRepository.saveAndFlush(satellite);
        SatelliteServiceModel mappedSatelliteServiceModel = this.modelMapper
                .map(savedSatellite, SatelliteServiceModel.class);

        return mappedSatelliteServiceModel;
    }

    @Override
    public void editSatellite(SatelliteServiceModel satelliteServiceModel) {
        String id = satelliteServiceModel.getId();
        Satellite satellite = this.satelliteRepository.getOne(id);
        this.makeAChange(satelliteServiceModel, satellite);
        this.satelliteRepository.save(satellite);
    }

    @Override
    public SatelliteServiceModel findById(String id) {
        Satellite satellite = this.satelliteRepository.findById(id).orElse(null);
        return satellite == null ? null : this.modelMapper.map(satellite, SatelliteServiceModel.class);
    }

    @Override
    public boolean deleteSatelliteById(String id) {
        try {
            this.satelliteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SatelliteServiceModel> findAllOrderedByName() {
        List<Satellite> satellites = this.satelliteRepository.findAllOrderByName();
        List<SatelliteServiceModel> satelliteServiceModels = satellites.stream()
                .map(satellite -> this.modelMapper
                        .map(satellite, SatelliteServiceModel.class))
                .collect(Collectors.toList());

        return satelliteServiceModels;

    }

    private void makeAChange(SatelliteServiceModel satelliteServiceModel, Satellite galaxy) {
        galaxy.setName(satelliteServiceModel.getName());
        galaxy.setDistanceToThePlanet(satelliteServiceModel.getDistanceToThePlanet());
        galaxy.setPlanet(satelliteServiceModel.getPlanet());
        galaxy.setAge(satelliteServiceModel.getAge());
        galaxy.setDensity(satelliteServiceModel.getDensity());
        galaxy.setMass(satelliteServiceModel.getMass());
        galaxy.setRadius(satelliteServiceModel.getRadius());
        galaxy.setSquare(satelliteServiceModel.getSquare());
        galaxy.setTemperature(satelliteServiceModel.getTemperature());

    }
}
