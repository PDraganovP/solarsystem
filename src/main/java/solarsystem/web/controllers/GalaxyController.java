package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.GalaxyBindingModel;
import solarsystem.domain.models.service.GalaxyServiceModel;
import solarsystem.domain.models.view.GalaxyViewModel;
import solarsystem.services.GalaxyService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/galaxies")
public class GalaxyController extends BaseController {

    private final GalaxyService galaxyService;
    private final ModelMapper modelMapper;

    @Autowired
    public GalaxyController(GalaxyService galaxyService, ModelMapper modelMapper) {
        this.galaxyService = galaxyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/show")
    public ModelAndView show(ModelAndView modelAndView) {
      /*  final List<VirusShowModel> viruses = virusService.findAll().stream()
                .map(virus -> modelMapper.map(virus, VirusShowModel.class)).collect(Collectors.toList());
        modelAndView.addObject("viruses", viruses);*/
        List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> galaxyViewModelList = galaxyServiceModelList.stream().map(galaxyServiceModel -> this.modelMapper
                .map(galaxyServiceModel, GalaxyViewModel.class)).collect(Collectors.toList());
        modelAndView.addObject("galaxyViewModel", galaxyViewModelList);

        return this.view("galaxies/all-galaxies", modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView renderAddGalaxyPage(@ModelAttribute("galaxyBindingModel") GalaxyBindingModel galaxyBindingModel,
                                            ModelAndView modelAndView) {
        return this.view("galaxies/add-galaxy", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addGalaxy(ModelAndView modelAndView,
                                  @Valid @ModelAttribute(name = "galaxyBindingModel") GalaxyBindingModel galaxyBindingModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.view("galaxies/add-galaxy", modelAndView);
        }
        GalaxyServiceModel galaxyServiceModel = this.modelMapper.map(galaxyBindingModel, GalaxyServiceModel.class);
        GalaxyServiceModel galaxyServiceModelWithId = this.galaxyService.saveGalaxy(galaxyServiceModel);
        galaxyBindingModel.setId(galaxyServiceModelWithId.getId());

        if (galaxyServiceModelWithId == null) {
            return this.view("galaxies/add-galaxy", modelAndView);
        }
        //   return this.view("galaxies/all-galaxies", modelAndView);
        return this.redirect("/galaxies/show");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView renderEditGalaxyPage(@PathVariable("id") String id,
                                             @ModelAttribute("galaxyBindingModel") GalaxyBindingModel galaxyBindingModel,
                                             ModelAndView modelAndView) {

        GalaxyServiceModel galaxyServiceModel = this.galaxyService.findById(id);
        galaxyBindingModel = this.modelMapper.map(galaxyServiceModel, GalaxyBindingModel.class);

        modelAndView.addObject("galaxyBindingModel", galaxyBindingModel);

        return this.view("galaxies/edit-galaxy", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editGalaxy(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("galaxyBindingModel") GalaxyBindingModel galaxyBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return this.view("galaxies/edit-galaxy", modelAndView);
        }

        galaxyBindingModel.setId(id);
        GalaxyServiceModel galaxyServiceModel = this.modelMapper.map(galaxyBindingModel, GalaxyServiceModel.class);

        this.galaxyService.editGalaxy(galaxyServiceModel);
        /*List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> GalaxyViewModelList = galaxyServiceModelList.stream().map(galaxy -> this.modelMapper
                .map(galaxy, GalaxyViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("galaxyViewModel",GalaxyViewModelList);*/
        // return this.view("galaxies/all-galaxies");
        return this.redirect("/galaxies/show");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteGalaxy(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.galaxyService.deleteGalaxyById(id);

        if (!isDeleted) {
            //Error message: something went wrong!
            return this.view("galaxies/all-galaxies", modelAndView);
        }

        // return this.redirect("galaxies/all");
        //  return this.view("galaxies/all-galaxies",modelAndView);
        return this.redirect("/galaxies/show");
    }
}