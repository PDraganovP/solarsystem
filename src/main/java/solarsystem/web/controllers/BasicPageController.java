package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.service.SatelliteServiceModel;
import solarsystem.domain.models.service.StarServiceModel;
import solarsystem.domain.models.view.SatelliteViewModel;
import solarsystem.domain.models.view.StarViewModel;
import solarsystem.services.PlanetService;
import solarsystem.services.SatelliteService;
import solarsystem.services.StarServiceImpl;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BasicPageController extends BaseController {
    private final StarServiceImpl starService;
    private final PlanetService planetService;
    private final ModelMapper modelMapper;
    private final SatelliteService satelliteService;

    public BasicPageController(StarServiceImpl starService, PlanetService planetService, ModelMapper modelMapper, SatelliteService satelliteService) {
        this.starService = starService;
        this.planetService = planetService;
        this.modelMapper = modelMapper;
        this.satelliteService = satelliteService;
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


  // @GetMapping("/compareSatellites")// This code works but it is moved in PlanetController
  // @PageFooter
  // @PageNavbar
  // public ModelAndView getCompareSatellitesPage(ModelAndView modelAndView) {  //*@ModelAttribute("planetViewModel") PlanetViewModel planetViewModel,PlanetViewModel planetViewModelTwo,*//*
  //     List<SatelliteViewModel> satellitesOrderedByName = this.findSatellitesOrderedByName();
  //     modelAndView.addObject("satellitesOne", satellitesOrderedByName);
  //     List<SatelliteViewModel> satellitesOrderedByNameTwo = this.findSatellitesOrderedByName();
  //     modelAndView.addObject("satellitesTwo", satellitesOrderedByNameTwo);
  //     //this.starService.setMass();
  //     return this.view("basicPages/compare-satellites", modelAndView);
  // }
//
  // private List<SatelliteViewModel> findSatellitesOrderedByName() {
  //     List<SatelliteServiceModel> satelliteServiceModelList = this.satelliteService.findAllOrderedByName();
  //     List<SatelliteViewModel> satelliteViewModelList = satelliteServiceModelList
  //             .stream()
  //             .map(satelliteServiceModel -> this.modelMapper
  //                     .map(satelliteServiceModel, SatelliteViewModel.class))
  //             .collect(Collectors.toList());
  //     return satelliteViewModelList;
//
  // }

}
/*public ModelAndView renderAddSatellitePage(@ModelAttribute("satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                               ModelAndView modelAndView) {
        List<PlanetViewModel> planetsOrderedByName = this.findPlanetsOrderedByName();
        modelAndView.addObject("planetsModels", planetsOrderedByName);*/