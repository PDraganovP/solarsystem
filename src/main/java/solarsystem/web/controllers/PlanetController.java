package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.PlanetBindingModel;
import solarsystem.domain.models.service.PlanetServiceModel;
import solarsystem.domain.models.service.StarSystemServiceModel;
import solarsystem.domain.models.view.PlanetViewModel;
import solarsystem.domain.models.view.StarSystemViewModel;
import solarsystem.services.PlanetService;
import solarsystem.services.StarSystemService;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/planets")
public class PlanetController extends BaseController {

    private final PlanetService planetService;
    private final ModelMapper modelMapper;
    private final StarSystemService starSystemService;

    @Autowired
    public PlanetController(PlanetService planetService, ModelMapper modelMapper, StarSystemService starSystemService) {
        this.planetService = planetService;
        this.modelMapper = modelMapper;
        this.starSystemService = starSystemService;
    }

    @GetMapping("/show")
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public ModelAndView show(ModelAndView modelAndView) {
        List<PlanetServiceModel> planetServiceModelList = this.planetService.findAllOrderedByName();
        List<PlanetViewModel> planetViewModelList = planetServiceModelList.stream()
                .map(planetServiceModel -> this.modelMapper.map(planetServiceModel, PlanetViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("planetViewModel", planetViewModelList);

        return super.view("planets/all-planets", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView getAddPlanetPage(@ModelAttribute("planetBindingModel") PlanetBindingModel planetBindingModel,
                                            ModelAndView modelAndView) {

        List<StarSystemViewModel> starSystemsOrderedByName = this.findStarSystemsOrderedByName();
        modelAndView.addObject("starSystemsModels", starSystemsOrderedByName);

        return super.view("planets/add-planet", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addPlanet(ModelAndView modelAndView,
                                  @Valid @ModelAttribute(name = "planetBindingModel") PlanetBindingModel planetBindingModel,
                                  BindingResult bindingResult) {
        String name = planetBindingModel.getName();
        if (bindingResult.hasErrors()) {
            return super.view("planets/add-planet", modelAndView);
        }
        PlanetServiceModel planetServiceModel = this.modelMapper.map(planetBindingModel, PlanetServiceModel.class);
        PlanetServiceModel planetServiceModelId = this.planetService.savePlanet(planetServiceModel);
        planetBindingModel.setId(planetServiceModelId.getId());

        if (planetServiceModelId == null) {
            return super.view("planets/add-planet", modelAndView);
        }

        return super.redirect("/planets/show");
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView getEditPlanetPage(@PathVariable("id") String id,
                                             @ModelAttribute("planetBindingModel") PlanetBindingModel planetBindingModel,
                                             ModelAndView modelAndView) {
        List<StarSystemViewModel> starSystemsOrderedByName = this.findStarSystemsOrderedByName();
        modelAndView.addObject("starSystemsModels", starSystemsOrderedByName);

        PlanetServiceModel planetServiceModel = this.planetService.findById(id);
        planetBindingModel = this.modelMapper.map(planetServiceModel, PlanetBindingModel.class);

        modelAndView.addObject("planetBindingModel", planetBindingModel);

        return super.view("planets/edit-planet", modelAndView);
    }


    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editPlanet(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("galaxyBindingModel") PlanetBindingModel planetBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return super.view("planets/edit-planet", modelAndView);
        }

        planetBindingModel.setId(id);
        PlanetServiceModel planetServiceModel = this.modelMapper.map(planetBindingModel, PlanetServiceModel.class);

        this.planetService.editPlanet(planetServiceModel);

        return super.redirect("/planets/show");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deletePlanet(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.planetService.deletePlanetById(id);

        if (!isDeleted) {

            return super.view("planets/all-planets", modelAndView);
        }

        return super.redirect("/planets/show");
    }

    @GetMapping("/comparePlanets")
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public ModelAndView getComparePlanetsPage(ModelAndView modelAndView) {
        List<PlanetViewModel> planetsOrderedByName = this.findPlanetsOrderedByName();
        modelAndView.addObject("planetsOne", planetsOrderedByName);
        List<PlanetViewModel> planetsOrderedByName2 = this.findPlanetsOrderedByName();
        modelAndView.addObject("planetsTwo", planetsOrderedByName2);

        return super.view("planets/compare-planets", modelAndView);
    }

    private List<PlanetViewModel> findPlanetsOrderedByName() {
        List<PlanetServiceModel> planetServiceModelList = this.planetService.findAllOrderedByName();
        List<PlanetViewModel> planetViewModelList = planetServiceModelList.stream().map(planetServiceModel -> this.modelMapper
                .map(planetServiceModel, PlanetViewModel.class))
                .collect(Collectors.toList());
        return planetViewModelList;

    }

    private List<StarSystemViewModel> findStarSystemsOrderedByName() {
        List<StarSystemServiceModel> starServiceModelList = this.starSystemService.findAllOrderedByName();
        List<StarSystemViewModel> starSystemViewModelList = starServiceModelList
                .stream()
                .map(starSystemServiceModel -> this.modelMapper.map(starSystemServiceModel, StarSystemViewModel.class))
                .collect(Collectors.toList());
        return starSystemViewModelList;

    }
}
