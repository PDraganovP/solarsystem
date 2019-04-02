package solarsystem.services;

import org.springframework.stereotype.Service;
import solarsystem.domain.models.service.SatelliteServiceModel;

import java.util.List;

@Service
public interface SatelliteService {

    SatelliteServiceModel saveSatellite(SatelliteServiceModel satelliteServiceModel);

    void editSatellite(SatelliteServiceModel satelliteServiceModel);

    SatelliteServiceModel findById(String id);

    boolean deleteSatelliteById(String id);

    List<SatelliteServiceModel> findAllOrderedByName();
}
