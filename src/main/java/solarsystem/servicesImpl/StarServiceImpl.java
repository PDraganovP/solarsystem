package solarsystem.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solarsystem.domain.entities.Star;
import solarsystem.domain.models.service.StarServiceModel;
import solarsystem.repositories.StarRepository;
import solarsystem.services.StarService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {
    private StarRepository starRepository;
    private ModelMapper modelMapper;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, ModelMapper modelMapper) {
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StarServiceModel saveStar(StarServiceModel starServiceModel) {
        Star star = this.modelMapper.map(starServiceModel, Star.class);
        Star savedStar = this.starRepository.saveAndFlush(star);
        StarServiceModel mappedStarServiceModel = this.modelMapper
                .map(savedStar, StarServiceModel.class);

        return mappedStarServiceModel;
    }

    @Override
    public void editStar(StarServiceModel starServiceModel) {
        String id = starServiceModel.getId();
        Star star = this.starRepository.getOne(id);
        this.makeAChange(starServiceModel, star);
        this.starRepository.save(star);

    }

    @Override
    public StarServiceModel findById(String id) {
        Star star = this.starRepository.findById(id).orElse(null);
        return star == null ? null : this.modelMapper.map(star, StarServiceModel.class);
    }

    @Override
    public boolean deleteStarById(String id) {
        try {
            this.starRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StarServiceModel> findAllOrderedByName() {
        List<Star> stars = this.starRepository.findAllOrderByName();
        List<StarServiceModel> starServiceModels = stars.stream()
                .map(star -> this.modelMapper
                        .map(star, StarServiceModel.class))
                .collect(Collectors.toList());

        return starServiceModels;
    }

    private void makeAChange(StarServiceModel starServiceModel, Star star) {
        star.setName(starServiceModel.getName());
        star.setLuminosity(starServiceModel.getLuminosity());
        star.setSpectralClass(starServiceModel.getSpectralClass());
        star.setStarSystem(starServiceModel.getStarSystem());
        star.setAge(starServiceModel.getAge());
        star.setDensity(starServiceModel.getDensity());
        star.setMass(starServiceModel.getMass());
        star.setRadius(starServiceModel.getRadius());
        star.setSquare(starServiceModel.getSquare());
        star.setTemperature(starServiceModel.getTemperature());

    }
}
