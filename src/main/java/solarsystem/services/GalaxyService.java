package solarsystem.services;

import org.springframework.stereotype.Service;
import solarsystem.domain.models.service.GalaxyServiceModel;

import java.util.List;

@Service
public interface GalaxyService {

    GalaxyServiceModel saveGalaxy(GalaxyServiceModel galaxyServiceModel);

    void editGalaxy(GalaxyServiceModel galaxyServiceModel);

    GalaxyServiceModel findById(String id);

    boolean deleteGalaxyById(String id);

    List<GalaxyServiceModel> findAllOrderedByName();
}
