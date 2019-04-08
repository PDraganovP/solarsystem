package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import solarsystem.domain.entities.Planet;
import solarsystem.domain.models.service.StarSystemServiceModel;
import solarsystem.domain.models.view.ShowPlanetViewModel;
import solarsystem.domain.models.view.StarSystemViewModel;
import solarsystem.services.StarSystemService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PlanetRestController {
    private final StarSystemService starSystemService;
    private final ModelMapper modelMapper;

    @Autowired
    public PlanetRestController(StarSystemService starSystemService, ModelMapper modelMapper) {
        this.starSystemService = starSystemService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/show-planets")
    public ResponseEntity<?> showPlanetsTable(@RequestBody String id) {
        String data = id;
        StarSystemServiceModel starSystemServiceModel = this.starSystemService.findById(id);
        StarSystemViewModel mappedStarSystemViewModel = this.modelMapper
                .map(starSystemServiceModel, StarSystemViewModel.class);
        Set<Planet> planets = mappedStarSystemViewModel.getPlanets();
        List<ShowPlanetViewModel> showPlanetViewModelList = planets.stream().map(planet -> this.modelMapper
                .map(planet, ShowPlanetViewModel.class)).collect(Collectors.toList());
        if (data == null || data.equalsIgnoreCase("")) {

            return new ResponseEntity<>(new Exception("Something went wrong!"), HttpStatus.OK);
        }

        return new ResponseEntity<>(showPlanetViewModelList, HttpStatus.OK);
    }

}
