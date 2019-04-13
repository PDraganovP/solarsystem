package solarsystem.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solarsystem.domain.entities.StarSystem;
import solarsystem.domain.models.service.StarSystemServiceModel;
import solarsystem.repository.StarSystemRepository;
import solarsystem.services.StarSystemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarSystemServiceImpl implements StarSystemService {
    private StarSystemRepository starSystemRepository;
    private ModelMapper modelMapper;

    @Autowired
    public StarSystemServiceImpl(StarSystemRepository starSystemRepository, ModelMapper modelMapper) {
        this.starSystemRepository = starSystemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StarSystemServiceModel saveStarSystem(StarSystemServiceModel starSystemServiceModel) {
        StarSystem starSystem = this.modelMapper.map(starSystemServiceModel, StarSystem.class);
        StarSystem savedStarSystem = this.starSystemRepository.saveAndFlush(starSystem);
        StarSystemServiceModel mappedStarSystemServiceModel = this.modelMapper
                .map(savedStarSystem, StarSystemServiceModel.class);

        return mappedStarSystemServiceModel;
    }

    @Override
    public void editStarSystem(StarSystemServiceModel starSystemServiceModel) {
        String id = starSystemServiceModel.getId();
        StarSystem starSystem = this.starSystemRepository.getOne(id);
        this.makeAChange(starSystemServiceModel, starSystem);
        this.starSystemRepository.save(starSystem);
    }

    @Override
    public StarSystemServiceModel findById(String id) {
        StarSystem starSystem = this.starSystemRepository.findById(id).orElse(null);
        return starSystem == null ? null : this.modelMapper.map(starSystem, StarSystemServiceModel.class);
    }

    @Override
    public boolean deleteStarSystemById(String id) {
        try {
            this.starSystemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StarSystemServiceModel> findAllOrderedByName() {
        List<StarSystem> starSystems = this.starSystemRepository.findAllOrderByName();
        List<StarSystemServiceModel> starSystemServiceModels = starSystems.stream()
                .map(starSystem -> this.modelMapper
                        .map(starSystem, StarSystemServiceModel.class))
                .collect(Collectors.toList());

        return starSystemServiceModels;

    }

    @Override
    public StarSystemServiceModel findStarSystemById(String id) {
        StarSystem starSystem = this.starSystemRepository.findById(id).orElse(null);
        StarSystemServiceModel mappedStarSystemServiceModel = this.modelMapper
                .map(starSystem, StarSystemServiceModel.class);

        return mappedStarSystemServiceModel;
    }

    private void makeAChange(StarSystemServiceModel starSystemServiceModel, StarSystem starSystem) {
        starSystem.setName(starSystemServiceModel.getName());
        starSystem.setGalaxy(starSystemServiceModel.getGalaxy());
        starSystem.setPlanets(starSystemServiceModel.getPlanets());
        starSystem.setStar(starSystemServiceModel.getStar());
    }
}
