package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.GalaxyBindingModel;
import solarsystem.domain.models.binding.StarSystemBindingModel;
import solarsystem.domain.models.service.GalaxyServiceModel;
import solarsystem.domain.models.service.PlanetServiceModel;
import solarsystem.domain.models.service.StarServiceModel;
import solarsystem.domain.models.service.StarSystemServiceModel;
import solarsystem.domain.models.view.GalaxyViewModel;
import solarsystem.domain.models.view.PlanetViewModel;
import solarsystem.domain.models.view.StarSystemViewModel;
import solarsystem.domain.models.view.StarViewModel;
import solarsystem.services.GalaxyService;
import solarsystem.services.PlanetService;
import solarsystem.services.StarService;
import solarsystem.services.StarSystemService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/starSystems")
public class StarSystemController extends BaseController {
    private StarSystemService starSystemService;
    private ModelMapper modelMapper;
    private PlanetService planetService;
    private GalaxyService galaxyService;
    private StarService starService;


    @Autowired
    public StarSystemController(StarSystemService starSystemService, ModelMapper modelMapper, PlanetService planetService, GalaxyService galaxyService, StarService starService) {
        this.starSystemService = starSystemService;
        this.modelMapper = modelMapper;
        this.planetService = planetService;
        this.galaxyService = galaxyService;
        this.starService = starService;
    }

    @GetMapping("/show")
    public ModelAndView show(ModelAndView modelAndView) {
        List<StarSystemServiceModel> starSystemServiceModels = this.starSystemService.findAllOrderedByName();
        List<StarSystemViewModel> starSystemViewModelList = starSystemServiceModels.stream().map(starSystemServiceModel -> this.modelMapper
                .map(starSystemServiceModel, StarSystemViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("starSystemViewModel", starSystemViewModelList);

        return this.view("starSystems/all-starSystems", modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView renderAddStarSystemPage(@ModelAttribute("starSystemBindingModel") StarSystemBindingModel starSystemBindingModel,
                                                ModelAndView modelAndView) {
        List<PlanetViewModel> planetsOrderedByName = this.findPlanetsOrderedByName();
        List<GalaxyViewModel> galaxiesOrderedByName = this.findGalaxiesOrderedByName();
        List<StarViewModel> starsOrderedByName = this.findStarsOrderedByName();
        modelAndView.addObject("planetsModels", planetsOrderedByName);
        modelAndView.addObject("galaxiesModels", galaxiesOrderedByName);
        modelAndView.addObject("starsModels", starsOrderedByName);


        return this.view("starSystems/add-starSystem", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addStarSystem(ModelAndView modelAndView,
                                  @Valid @ModelAttribute(name = "starSystemBindingModel") StarSystemBindingModel starSystemBindingModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.view("starSystems/add-starSystem", modelAndView);
        }
        StarSystemServiceModel starSystemServiceModel = this.modelMapper.map(starSystemBindingModel, StarSystemServiceModel.class);
        StarSystemServiceModel starSystemServiceModelWithId = this.starSystemService.saveStarSystem(starSystemServiceModel);
        starSystemBindingModel.setId(starSystemServiceModelWithId.getId());

        if (starSystemServiceModelWithId == null) {
            return this.view("starSystems/add-starSystem", modelAndView);
        }
        //   return this.view("galaxies/all-galaxies", modelAndView);
        return this.redirect("/starSystems/show");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView renderEditStarSystemPage(@PathVariable("id") String id,
                                             @ModelAttribute("starSystemBindingModel") StarSystemBindingModel starSystemBindingModel,
                                             ModelAndView modelAndView) {

        List<PlanetViewModel> planetsOrderedByName = this.findPlanetsOrderedByName();
        List<GalaxyViewModel> galaxiesOrderedByName = this.findGalaxiesOrderedByName();
        List<StarViewModel> starsOrderedByName = this.findStarsOrderedByName();
        modelAndView.addObject("planetsModels", planetsOrderedByName);
        modelAndView.addObject("galaxiesModels", galaxiesOrderedByName);
        modelAndView.addObject("starsModels", starsOrderedByName);

        StarSystemServiceModel starSystemServiceModel = this.starSystemService.findById(id);
        starSystemBindingModel = this.modelMapper.map(starSystemServiceModel, StarSystemBindingModel.class);

        modelAndView.addObject("starSystemBindingModel", starSystemBindingModel);

        return this.view("starSystems/edit-starSystem", modelAndView);
    }


    @PostMapping("/edit/{id}")
    public ModelAndView editStarSystem(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("starSystemBindingModel") StarSystemBindingModel starSystemBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return this.view("starSystem/edit-starSystem", modelAndView);
        }

        starSystemBindingModel.setId(id);
        StarSystemServiceModel starSystemServiceModel = this.modelMapper.map(starSystemBindingModel, StarSystemServiceModel.class);

        this.starSystemService.editStarSystem(starSystemServiceModel);
        /*List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> GalaxyViewModelList = galaxyServiceModelList.stream().map(galaxy -> this.modelMapper
                .map(galaxy, GalaxyViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("galaxyViewModel",GalaxyViewModelList);*/
        // return this.view("galaxies/all-galaxies");
        return this.redirect("/starSystems/show");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteStarSystem(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.starSystemService.deleteStarSystemById(id);

        if (!isDeleted) {
            //Error message: something went wrong!
            return this.view("starSystems/all-starSystems", modelAndView);
        }

        // return this.redirect("galaxies/all");
        //  return this.view("galaxies/all-galaxies",modelAndView);
        return this.redirect("/starSystems/show");
    }


    private List<PlanetViewModel> findPlanetsOrderedByName() {
        List<PlanetServiceModel> planetServiceModelList = this.planetService.findAllOrderedByName();
        List<PlanetViewModel> planetViewModelList = planetServiceModelList.stream().map(planetServiceModel -> this.modelMapper
                .map(planetServiceModel, PlanetViewModel.class))
                .collect(Collectors.toList());
        return planetViewModelList;

    }
    private List<GalaxyViewModel> findGalaxiesOrderedByName() {
        List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> galaxyViewModelList = galaxyServiceModelList
                .stream()
                .map(galaxyServiceModel ->  this.modelMapper
                .map(galaxyServiceModel, GalaxyViewModel.class))
                .collect(Collectors.toList());
        return galaxyViewModelList;

    }
    private List<StarViewModel> findStarsOrderedByName() {
        List<StarServiceModel> starServiceModelList = this.starService.findAllOrderedByName();
        List<StarViewModel> starViewModelList = starServiceModelList
                .stream()
                .map(starServiceModel ->   this.modelMapper
                        .map(starServiceModel, StarViewModel.class))
                .collect(Collectors.toList());
        return starViewModelList;

    }
}
