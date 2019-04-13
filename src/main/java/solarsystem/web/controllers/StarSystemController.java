package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.StarSystemBindingModel;
import solarsystem.domain.models.service.GalaxyServiceModel;
import solarsystem.domain.models.service.StarSystemServiceModel;
import solarsystem.domain.models.view.GalaxyViewModel;
import solarsystem.domain.models.view.StarSystemViewModel;
import solarsystem.services.GalaxyService;
import solarsystem.services.PlanetService;
import solarsystem.services.StarService;
import solarsystem.services.StarSystemService;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/starSystems")
public class StarSystemController extends BaseController {
    private final StarSystemService starSystemService;
    private final ModelMapper modelMapper;
    private final PlanetService planetService;
    private final GalaxyService galaxyService;
    private final StarService starService;


    @Autowired
    public StarSystemController(StarSystemService starSystemService, ModelMapper modelMapper, PlanetService planetService, GalaxyService galaxyService, StarService starService) {
        this.starSystemService = starSystemService;
        this.modelMapper = modelMapper;
        this.planetService = planetService;
        this.galaxyService = galaxyService;
        this.starService = starService;
    }

    @GetMapping("/show")
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public ModelAndView show(ModelAndView modelAndView) {
        List<StarSystemServiceModel> starSystemServiceModels = this.starSystemService.findAllOrderedByName();
        List<StarSystemViewModel> starSystemViewModelList = starSystemServiceModels.stream().map(starSystemServiceModel -> this.modelMapper
                .map(starSystemServiceModel, StarSystemViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("starSystemViewModel", starSystemViewModelList);

        return super.view("starSystems/all-starSystems", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView getAddStarSystemPage(@ModelAttribute("starSystemBindingModel") StarSystemBindingModel starSystemBindingModel,
                                                ModelAndView modelAndView) {

        List<GalaxyViewModel> galaxiesOrderedByName = this.findGalaxiesOrderedByName();
        modelAndView.addObject("galaxiesModels", galaxiesOrderedByName);


        return super.view("starSystems/add-starSystem", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addStarSystem(ModelAndView modelAndView,
                                      @Valid @ModelAttribute(name = "starSystemBindingModel") StarSystemBindingModel starSystemBindingModel,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.view("starSystems/add-starSystem", modelAndView);
        }
        StarSystemServiceModel starSystemServiceModel = this.modelMapper.map(starSystemBindingModel, StarSystemServiceModel.class);
        StarSystemServiceModel starSystemServiceModelWithId = this.starSystemService.saveStarSystem(starSystemServiceModel);
        starSystemBindingModel.setId(starSystemServiceModelWithId.getId());

        if (starSystemServiceModelWithId == null) {
            return super.view("starSystems/add-starSystem", modelAndView);
        }

        return super.redirect("/starSystems/show");
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView getEditStarSystemPage(@PathVariable("id") String id,
                                                 @ModelAttribute("starSystemBindingModel") StarSystemBindingModel starSystemBindingModel,
                                                 ModelAndView modelAndView) {

        List<GalaxyViewModel> galaxiesOrderedByName = this.findGalaxiesOrderedByName();
        modelAndView.addObject("galaxiesModels", galaxiesOrderedByName);

        StarSystemServiceModel starSystemServiceModel = this.starSystemService.findById(id);
        starSystemBindingModel = this.modelMapper.map(starSystemServiceModel, StarSystemBindingModel.class);

        modelAndView.addObject("starSystemBindingModel", starSystemBindingModel);

        return super.view("starSystems/edit-starSystem", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editStarSystem(@PathVariable("id") String id,
                                       @Valid @ModelAttribute("starSystemBindingModel") StarSystemBindingModel starSystemBindingModel,
                                       BindingResult bindingResult,
                                       ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return super.view("starSystem/edit-starSystem", modelAndView);
        }

        starSystemBindingModel.setId(id);
        StarSystemServiceModel starSystemServiceModel = this.modelMapper.map(starSystemBindingModel, StarSystemServiceModel.class);

        this.starSystemService.editStarSystem(starSystemServiceModel);

        return super.redirect("/starSystems/show");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteStarSystem(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.starSystemService.deleteStarSystemById(id);

        if (!isDeleted) {
            return super.view("starSystems/all-starSystems", modelAndView);
        }

        return super.redirect("/starSystems/show");
    }

    private List<GalaxyViewModel> findGalaxiesOrderedByName() {
        List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> galaxyViewModelList = galaxyServiceModelList
                .stream()
                .map(galaxyServiceModel -> this.modelMapper
                        .map(galaxyServiceModel, GalaxyViewModel.class))
                .collect(Collectors.toList());
        return galaxyViewModelList;

    }

}
