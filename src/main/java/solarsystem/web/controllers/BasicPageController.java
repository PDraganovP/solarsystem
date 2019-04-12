package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.service.PlanetServiceModel;
import solarsystem.domain.models.service.StarServiceModel;
import solarsystem.domain.models.view.PlanetViewModel;
import solarsystem.domain.models.view.StarViewModel;
import solarsystem.services.PlanetService;
import solarsystem.servicesImpl.StarServiceImpl;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BasicPageController extends BaseController {
    private final StarServiceImpl starService;
    private final PlanetService planetService;
    private final ModelMapper modelMapper;

    public BasicPageController(StarServiceImpl starService, PlanetService planetService, ModelMapper modelMapper) {
        this.starService = starService;
        this.planetService = planetService;
        this.modelMapper = modelMapper;
    }
/*
    @GetMapping("/")
    public String getIndexPage() {
       //this.starService.setMass();
        return "basicPages/index";
    }
    @GetMapping("/earth")
    public String getEarthPage() {
        return "basicPages/earth";
    }
    @GetMapping("/mars")
    public String getMarsPage() {
        return "basicPages/mars";
    }
    @GetMapping("/moon")
    public String getMoonPage() {
        return "basicPages/moon";
    }
    @GetMapping("/sun")
    public String getSunPage() {
        return "basicPages/sun";
    }}*/

    /*@GetMapping("/comparePlanets")// This code works but it is moved in PlanetController
    @PageFooter
    @PageNavbar
    public ModelAndView getComparePlanetsPage(ModelAndView modelAndView) {  *//*@ModelAttribute("planetViewModel") PlanetViewModel planetViewModel,PlanetViewModel planetViewModelTwo,*//*
        List<PlanetViewModel> planetsOrderedByName = this.findPlanetsOrderedByName();
        modelAndView.addObject("planetsOne", planetsOrderedByName);
        List<PlanetViewModel> planetsOrderedByName2 = this.findPlanetsOrderedByName();
        modelAndView.addObject("planetsTwo", planetsOrderedByName2);
        //this.starService.setMass();
        return this.view("basicPages/comparePlanets", modelAndView);
    }

    private List<PlanetViewModel> findPlanetsOrderedByName() {
        List<PlanetServiceModel> planetServiceModelList = this.planetService.findAllOrderedByName();
        List<PlanetViewModel> planetViewModelList = planetServiceModelList.stream().map(planetServiceModel -> this.modelMapper
                .map(planetServiceModel, PlanetViewModel.class))
                .collect(Collectors.toList());
        return planetViewModelList;

    }*/


//    @GetMapping("/compareStars")// This code works but it is moved in PlanetController
//    @PageFooter
//    @PageNavbar
//    public ModelAndView getCompareStarsPage(ModelAndView modelAndView) {  //*@ModelAttribute("planetViewModel") PlanetViewModel planetViewModel,PlanetViewModel planetViewModelTwo,*//*
//        List<StarViewModel> starsOrderedByName = this.findStarsOrderedByName();
//        modelAndView.addObject("starsOne", starsOrderedByName);
//        List<StarViewModel> starsOrderedByNameTwo = this.findStarsOrderedByName();
//        modelAndView.addObject("starsTwo", starsOrderedByNameTwo);
//        //this.starService.setMass();
//        return this.view("basicPages/compareStars", modelAndView);
//    }

//    private List<StarViewModel> findStarsOrderedByName() {
//        List<StarServiceModel> starServiceModelList = this.starService.findAllOrderedByName();
//        List<StarViewModel> starViewModelList = starServiceModelList
//                .stream()
//                .map(starServiceModel -> this.modelMapper
//                        .map(starServiceModel, StarViewModel.class))
//                .collect(Collectors.toList());
//        return starViewModelList;

//    }

}
/*public ModelAndView renderAddSatellitePage(@ModelAttribute("satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                               ModelAndView modelAndView) {
        List<PlanetViewModel> planetsOrderedByName = this.findPlanetsOrderedByName();
        modelAndView.addObject("planetsModels", planetsOrderedByName);*/