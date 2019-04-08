package solarsystem.services;

import org.springframework.stereotype.Service;
import solarsystem.domain.models.service.StarSystemServiceModel;

import java.util.List;

@Service
public interface StarSystemService {

    StarSystemServiceModel saveStarSystem(StarSystemServiceModel starSystemServiceModel);

    void editStarSystem(StarSystemServiceModel starSystemServiceModel);

    StarSystemServiceModel findById(String id);

    boolean deleteStarSystemById(String id);

    List<StarSystemServiceModel> findAllOrderedByName();

    StarSystemServiceModel findStarSystemById(String id);
}
