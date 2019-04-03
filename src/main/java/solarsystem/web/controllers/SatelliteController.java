package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.GalaxyBindingModel;
import solarsystem.domain.models.binding.SatelliteBindingModel;
import solarsystem.domain.models.service.GalaxyServiceModel;
import solarsystem.domain.models.service.SatelliteServiceModel;
import solarsystem.domain.models.view.GalaxyViewModel;
import solarsystem.domain.models.view.SatelliteViewModel;
import solarsystem.services.SatelliteService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/satellites")
public class SatelliteController extends BaseController {
    private SatelliteService satelliteService;
    private ModelMapper modelMapper;

    @Autowired
    public SatelliteController(SatelliteService satelliteService, ModelMapper modelMapper) {
        this.satelliteService = satelliteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/show")
    public ModelAndView show(ModelAndView modelAndView) {
        List<SatelliteServiceModel> satelliteServiceModelList = this.satelliteService.findAllOrderedByName();
        List<SatelliteViewModel> satelliteViewModelList = satelliteServiceModelList.stream().map(satelliteServiceModel -> this.modelMapper
                .map(satelliteServiceModel, SatelliteViewModel.class)).collect(Collectors.toList());
        modelAndView.addObject("satelliteViewModel", satelliteViewModelList);

        return this.view("satellites/all-satellites", modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView renderAddSatellitePage(@ModelAttribute("satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                               ModelAndView modelAndView) {
        return this.view("satellites/add-satellite", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addSatellite(ModelAndView modelAndView,
                                  @Valid @ModelAttribute(name = "satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.view("satellites/add-satellite", modelAndView);
        }
        SatelliteServiceModel satelliteServiceModel = this.modelMapper.map(satelliteBindingModel, SatelliteServiceModel.class);
        SatelliteServiceModel satelliteServiceModelWithId = this.satelliteService.saveSatellite(satelliteServiceModel);
        satelliteBindingModel.setId(satelliteServiceModelWithId.getId());

        if (satelliteServiceModelWithId == null) {
            return this.view("satellites/add-satellite", modelAndView);
        }
        //   return this.view("galaxies/all-galaxies", modelAndView);
        return this.redirect("/satellites/show");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView renderEditSatellitePage(@PathVariable("id") String id,
                                             @ModelAttribute("satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                             ModelAndView modelAndView) {

        SatelliteServiceModel satelliteServiceModel = this.satelliteService.findById(id);
        satelliteBindingModel = this.modelMapper.map(satelliteServiceModel, SatelliteBindingModel.class);

        modelAndView.addObject("satelliteBindingModel", satelliteBindingModel);

        return this.view("satellites/edit-satellite", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editGalaxy(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("satelliteBindingModel") SatelliteBindingModel satelliteBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return this.view("satellites/edit-satellite", modelAndView);
        }

        satelliteBindingModel.setId(id);
        SatelliteServiceModel satelliteServiceModel = this.modelMapper.map(satelliteBindingModel, SatelliteServiceModel.class);

        this.satelliteService.editSatellite(satelliteServiceModel);
        /*List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> GalaxyViewModelList = galaxyServiceModelList.stream().map(galaxy -> this.modelMapper
                .map(galaxy, GalaxyViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("galaxyViewModel",GalaxyViewModelList);*/
        // return this.view("galaxies/all-galaxies");
        return this.redirect("/satellites/show");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteGalaxy(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.satelliteService.deleteSatelliteById(id);

        if (!isDeleted) {
            //Error message: something went wrong!
            return this.view("satellites/all-satellites", modelAndView);
        }

        // return this.redirect("galaxies/all");
        //  return this.view("galaxies/all-galaxies",modelAndView);
        return this.redirect("/satellites/show");
    }

}
