package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.GalaxyBindingModel;
import solarsystem.domain.models.service.GalaxyServiceModel;
import solarsystem.domain.models.view.GalaxyViewModel;
import solarsystem.services.GalaxyService;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

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
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public ModelAndView show(ModelAndView modelAndView) {

        List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> galaxyViewModelList = galaxyServiceModelList.stream().map(galaxyServiceModel -> this.modelMapper
                .map(galaxyServiceModel, GalaxyViewModel.class)).collect(Collectors.toList());
        modelAndView.addObject("galaxyViewModel", galaxyViewModelList);

        return super.view("galaxies/all-galaxies", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView getAddGalaxyPage(@ModelAttribute("galaxyBindingModel") GalaxyBindingModel galaxyBindingModel,
                                            ModelAndView modelAndView) {
        return super.view("galaxies/add-galaxy", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addGalaxy(ModelAndView modelAndView,
                                  @Valid @ModelAttribute(name = "galaxyBindingModel") GalaxyBindingModel galaxyBindingModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.view("galaxies/add-galaxy", modelAndView);
        }
        GalaxyServiceModel galaxyServiceModel = this.modelMapper.map(galaxyBindingModel, GalaxyServiceModel.class);
        GalaxyServiceModel galaxyServiceModelWithId = this.galaxyService.saveGalaxy(galaxyServiceModel);
        galaxyBindingModel.setId(galaxyServiceModelWithId.getId());

        if (galaxyServiceModelWithId == null) {
            return super.view("galaxies/add-galaxy", modelAndView);
        }

        return super.redirect("/galaxies/show");
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView getEditGalaxyPage(@PathVariable("id") String id,
                                             @ModelAttribute("galaxyBindingModel") GalaxyBindingModel galaxyBindingModel,
                                             ModelAndView modelAndView) {

        GalaxyServiceModel galaxyServiceModel = this.galaxyService.findById(id);
        galaxyBindingModel = this.modelMapper.map(galaxyServiceModel, GalaxyBindingModel.class);

        modelAndView.addObject("galaxyBindingModel", galaxyBindingModel);

        return super.view("galaxies/edit-galaxy", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editGalaxy(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("galaxyBindingModel") GalaxyBindingModel galaxyBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return super.view("galaxies/edit-galaxy", modelAndView);
        }

        galaxyBindingModel.setId(id);
        GalaxyServiceModel galaxyServiceModel = this.modelMapper.map(galaxyBindingModel, GalaxyServiceModel.class);

        this.galaxyService.editGalaxy(galaxyServiceModel);

        return super.redirect("/galaxies/show");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteGalaxy(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.galaxyService.deleteGalaxyById(id);

        if (!isDeleted) {

            return super.view("galaxies/all-galaxies", modelAndView);
        }

        return super.redirect("/galaxies/show");
    }
}