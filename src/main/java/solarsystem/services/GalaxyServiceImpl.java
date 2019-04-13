package solarsystem.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solarsystem.domain.entities.Galaxy;
import solarsystem.domain.models.service.GalaxyServiceModel;
import solarsystem.repository.GalaxyRepository;
import solarsystem.services.GalaxyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalaxyServiceImpl implements GalaxyService {
    private final GalaxyRepository galaxyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GalaxyServiceImpl(GalaxyRepository galaxyRepository, ModelMapper modelMapper) {
        this.galaxyRepository = galaxyRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public GalaxyServiceModel saveGalaxy(GalaxyServiceModel galaxyServiceModel) {
        Galaxy galaxy = this.modelMapper.map(galaxyServiceModel, Galaxy.class);
        Galaxy savedGalaxy = this.galaxyRepository.saveAndFlush(galaxy);
        GalaxyServiceModel mappedGalaxyServiceModel = this.modelMapper
                .map(savedGalaxy, GalaxyServiceModel.class);

        return mappedGalaxyServiceModel;
    }

    @Override
    public void editGalaxy(GalaxyServiceModel galaxyServiceModel) {
        String id = galaxyServiceModel.getId();
        Galaxy galaxy = this.galaxyRepository.getOne(id);
        this.makeAChange(galaxyServiceModel, galaxy);
        this.galaxyRepository.save(galaxy);

    }

    @Override
    public GalaxyServiceModel findById(String id) {
        Galaxy galaxy = this.galaxyRepository.findById(id).orElse(null);
        return galaxy == null ? null : this.modelMapper.map(galaxy, GalaxyServiceModel.class);
    }

    @Override
    public boolean deleteGalaxyById(String id) {
        try {
            this.galaxyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<GalaxyServiceModel> findAllOrderedByName() {
        List<Galaxy> galaxies = this.galaxyRepository.findAllOrderByName();
        List<GalaxyServiceModel> galaxyServiceModels = galaxies.stream()
                .map(galaxy -> this.modelMapper
                        .map(galaxy, GalaxyServiceModel.class))
                .collect(Collectors.toList());

        return galaxyServiceModels;

    }

    private void makeAChange(GalaxyServiceModel galaxyServiceModel, Galaxy galaxy) {
        galaxy.setName(galaxyServiceModel.getName());
        galaxy.setDistanceToNearestGalaxy(galaxyServiceModel.getDistanceToNearestGalaxy());
        galaxy.setGalaxyType(galaxyServiceModel.getGalaxyType());
    }
}
