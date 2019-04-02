package solarsystem.services;

import org.springframework.stereotype.Service;
import solarsystem.domain.models.service.GalaxyServiceModel;
import solarsystem.domain.models.service.StarServiceModel;

import java.util.List;

@Service
public interface StarService {

    StarServiceModel saveStar(StarServiceModel starServiceModel);

   void editStar(StarServiceModel starServiceModel);

    StarServiceModel findById(String id);

    boolean deleteStarById(String id);

  List<StarServiceModel> findAllOrderedByName();
}
