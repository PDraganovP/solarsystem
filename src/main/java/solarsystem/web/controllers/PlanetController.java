package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.PlanetBindingModel;
import solarsystem.domain.models.service.PlanetServiceModel;
import solarsystem.domain.models.service.StarServiceModel;
import solarsystem.domain.models.service.StarSystemServiceModel;
import solarsystem.domain.models.view.PlanetViewModel;
import solarsystem.domain.models.view.StarSystemViewModel;
import solarsystem.domain.models.view.StarViewModel;
import solarsystem.services.PlanetService;
import solarsystem.services.StarSystemService;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/planets")
public class PlanetController extends BaseController {

    private PlanetService planetService;
    private ModelMapper modelMapper;
    private StarSystemService starSystemService;

    @Autowired
    public PlanetController(PlanetService planetService, ModelMapper modelMapper, StarSystemService starSystemService) {
        this.planetService = planetService;
        this.modelMapper = modelMapper;
        this.starSystemService = starSystemService;
    }

    @GetMapping("/show")
    public ModelAndView show(ModelAndView modelAndView) {
        List<PlanetServiceModel> planetServiceModelList = this.planetService.findAllOrderedByName();
        List<PlanetViewModel> planetViewModelList = planetServiceModelList.stream()
                .map(planetServiceModel -> this.modelMapper.map(planetServiceModel, PlanetViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("planetViewModel", planetViewModelList);

        return this.view("planets/all-planets", modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView renderAddPlanetPage(@ModelAttribute("planetBindingModel") PlanetBindingModel planetBindingModel,
                                            ModelAndView modelAndView) {

        List<StarSystemViewModel> starSystemsOrderedByName = this.findStarSystemsOrderedByName();
        modelAndView.addObject("starSystemsModels", starSystemsOrderedByName);

        return this.view("planets/add-planet", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addPlanet(ModelAndView modelAndView,
                                  @Valid @ModelAttribute(name = "planetBindingModel") PlanetBindingModel planetBindingModel,
                                  BindingResult bindingResult) {
        String name = planetBindingModel.getName();
        if (bindingResult.hasErrors()) {
            return this.view("planets/add-planet", modelAndView);
        }
        PlanetServiceModel planetServiceModel = this.modelMapper.map(planetBindingModel, PlanetServiceModel.class);
        PlanetServiceModel planetServiceModelId = this.planetService.savePlanet(planetServiceModel);
        planetBindingModel.setId(planetServiceModelId.getId());

        if (planetServiceModelId == null) {
            return this.view("planets/add-planet", modelAndView);
        }
        //   return this.view("galaxies/all-galaxies", modelAndView);
        return this.redirect("/planets/show");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView renderEditPlanetPage(@PathVariable("id") String id,
                                             @ModelAttribute("planetBindingModel") PlanetBindingModel planetBindingModel,
                                             ModelAndView modelAndView) {

        PlanetServiceModel planetServiceModel = this.planetService.findById(id);
        planetBindingModel = this.modelMapper.map(planetServiceModel, PlanetBindingModel.class);

        modelAndView.addObject("planetBindingModel", planetBindingModel);

        return this.view("planets/edit-planet", modelAndView);
    }


    @PostMapping("/edit/{id}")
    public ModelAndView editPlanet(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("galaxyBindingModel") PlanetBindingModel planetBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return this.view("planets/edit-planet", modelAndView);
        }

        planetBindingModel.setId(id);
        PlanetServiceModel planetServiceModel = this.modelMapper.map(planetBindingModel, PlanetServiceModel.class);

        this.planetService.editPlanet(planetServiceModel);
        /*List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> GalaxyViewModelList = galaxyServiceModelList.stream().map(galaxy -> this.modelMapper
                .map(galaxy, GalaxyViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("galaxyViewModel",GalaxyViewModelList);*/
        // return this.view("galaxies/all-galaxies");
        return this.redirect("/planets/show");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deletePlanet(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.planetService.deletePlanetById(id);

        if (!isDeleted) {
            //Error message: something went wrong!
            return this.view("planets/all-planets", modelAndView);
        }

        // return this.redirect("galaxies/all");
        //  return this.view("galaxies/all-galaxies",modelAndView);
        return this.redirect("/planets/show");
    }
   /* @GetMapping("/planets")
    public String showAllPlanets(Model model) {
        List<Planet> planets = this.planetService.findAll();
        planets.get(0).getMass();
        model.addAttribute("planets",planets);
        return "planets/planets";
    }*/

    private List<StarSystemViewModel> findStarSystemsOrderedByName() {
        List<StarSystemServiceModel> starServiceModelList = this.starSystemService.findAllOrderedByName();
        List<StarSystemViewModel> starSystemViewModelList = starServiceModelList
                .stream()
                .map(starSystemServiceModel ->    this.modelMapper
                        .map(starSystemServiceModel, StarSystemViewModel.class))
                .collect(Collectors.toList());
        return starSystemViewModelList;

    }
}
