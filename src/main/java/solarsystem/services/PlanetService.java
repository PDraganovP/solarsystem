package solarsystem.services;

import org.springframework.stereotype.Service;
import solarsystem.domain.entities.Planet;
import solarsystem.domain.models.service.PlanetServiceModel;

import java.util.List;

@Service
public interface PlanetService {

    PlanetServiceModel savePlanet(PlanetServiceModel planetServiceModel);

    void editPlanet(PlanetServiceModel planetServiceModel);

    PlanetServiceModel findById(String id);

    boolean deletePlanetById(String id);

    List<PlanetServiceModel> findAllOrderedByName();

}
