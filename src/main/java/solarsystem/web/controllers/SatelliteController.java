package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.SatelliteBindingModel;
import solarsystem.domain.models.service.PlanetServiceModel;
import solarsystem.domain.models.service.SatelliteServiceModel;
import solarsystem.domain.models.view.PlanetViewModel;
import solarsystem.domain.models.view.SatelliteViewModel;
import solarsystem.services.PlanetService;
import solarsystem.services.SatelliteService;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/satellites")
public class SatelliteController extends BaseController {
    private final SatelliteService satelliteService;
    private final ModelMapper modelMapper;
    private final PlanetService planetService;

    @Autowired
    public SatelliteController(SatelliteService satelliteService, ModelMapper modelMapper, PlanetService planetService) {
        this.satelliteService = satelliteService;
        this.modelMapper = modelMapper;
        this.planetService = planetService;
    }

    @GetMapping("/show")
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public ModelAndView show(ModelAndView modelAndView) {
        List<SatelliteServiceModel> satelliteServiceModelList = this.satelliteService.findAllOrderedByName();
        List<SatelliteViewModel> satelliteViewModelList = satelliteServiceModelList.stream().map(satelliteServiceModel -> this.modelMapper
                .map(satelliteServiceModel, SatelliteViewModel.class)).collect(Collectors.toList());
        modelAndView.addObject("satelliteViewModel", satelliteViewModelList);

        return super.view("satellites/all-satellites", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView getAddSatellitePage(@ModelAttribute("satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                               ModelAndView modelAndView) {
        List<PlanetViewModel> planetsOrderedByName = this.findPlanetsOrderedByName();
        modelAndView.addObject("planetsModels", planetsOrderedByName);

        return super.view("satellites/add-satellite", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addSatellite(ModelAndView modelAndView,
                                     @Valid @ModelAttribute(name = "satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.view("satellites/add-satellite", modelAndView);
        }
        SatelliteServiceModel satelliteServiceModel = this.modelMapper.map(satelliteBindingModel, SatelliteServiceModel.class);
        SatelliteServiceModel satelliteServiceModelWithId = this.satelliteService.saveSatellite(satelliteServiceModel);
        satelliteBindingModel.setId(satelliteServiceModelWithId.getId());

        if (satelliteServiceModelWithId == null) {
            return super.view("satellites/add-satellite", modelAndView);
        }

        return super.redirect("/satellites/show");
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView getEditSatellitePage(@PathVariable("id") String id,
                                                @ModelAttribute("satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                                ModelAndView modelAndView) {
        List<PlanetViewModel> planetsOrderedByName = this.findPlanetsOrderedByName();
        modelAndView.addObject("planetsModels", planetsOrderedByName);

        SatelliteServiceModel satelliteServiceModel = this.satelliteService.findById(id);
        satelliteBindingModel = this.modelMapper.map(satelliteServiceModel, SatelliteBindingModel.class);

        modelAndView.addObject("satelliteBindingModel", satelliteBindingModel);

        return super.view("satellites/edit-satellite", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editGalaxy(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return super.view("satellites/edit-satellite", modelAndView);
        }

        satelliteBindingModel.setId(id);
        SatelliteServiceModel satelliteServiceModel = this.modelMapper.map(satelliteBindingModel, SatelliteServiceModel.class);

        this.satelliteService.editSatellite(satelliteServiceModel);

        return super.redirect("/satellites/show");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteGalaxy(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.satelliteService.deleteSatelliteById(id);

        if (!isDeleted) {

            return super.view("satellites/all-satellites", modelAndView);
        }

        return super.redirect("/satellites/show");
    }

    @GetMapping("/compareSatellites")
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public ModelAndView getCompareSatellitesPage(ModelAndView modelAndView) {
        List<SatelliteViewModel> satellitesOrderedByName = this.findSatellitesOrderedByName();
        modelAndView.addObject("satellitesOne", satellitesOrderedByName);
        List<SatelliteViewModel> satellitesOrderedByNameTwo = this.findSatellitesOrderedByName();
        modelAndView.addObject("satellitesTwo", satellitesOrderedByNameTwo);

        return super.view("satellites/compare-satellites", modelAndView);
    }

    private List<SatelliteViewModel> findSatellitesOrderedByName() {
        List<SatelliteServiceModel> satelliteServiceModelList = this.satelliteService.findAllOrderedByName();
        List<SatelliteViewModel> satelliteViewModelList = satelliteServiceModelList
                .stream()
                .map(satelliteServiceModel -> this.modelMapper
                        .map(satelliteServiceModel, SatelliteViewModel.class))
                .collect(Collectors.toList());
        return satelliteViewModelList;

    }

    private List<PlanetViewModel> findPlanetsOrderedByName() {
        List<PlanetServiceModel> planetServiceModelList = this.planetService.findAllOrderedByName();
        List<PlanetViewModel> planetViewModelList = planetServiceModelList.stream().map(planetServiceModel -> this.modelMapper
                .map(planetServiceModel, PlanetViewModel.class))
                .collect(Collectors.toList());
        return planetViewModelList;

    }

}
