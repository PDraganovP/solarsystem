package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.StarBindingModel;
import solarsystem.domain.models.service.StarServiceModel;
import solarsystem.domain.models.service.StarSystemServiceModel;
import solarsystem.domain.models.view.StarSystemViewModel;
import solarsystem.domain.models.view.StarViewModel;
import solarsystem.services.StarService;
import solarsystem.services.StarSystemService;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/stars")
public class StarController extends BaseController {
    private StarService starService;
    private ModelMapper modelMapper;
    private StarSystemService starSystemService;

    @Autowired
    public StarController(StarService starService, ModelMapper modelMapper, StarSystemService starSystemService) {
        this.starService = starService;
        this.modelMapper = modelMapper;
        this.starSystemService = starSystemService;
    }

    @GetMapping("/show")
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public ModelAndView show(ModelAndView modelAndView) {
        List<StarServiceModel> starServiceModelList = this.starService.findAllOrderedByName();
        List<StarViewModel> starViewModelList = starServiceModelList.stream().map(starServiceModel -> this.modelMapper
                .map(starServiceModel, StarViewModel.class)).collect(Collectors.toList());
        modelAndView.addObject("starViewModel", starViewModelList);

        return this.view("stars/all-stars", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView renderAddStarPage(@ModelAttribute("starBindingModel") StarBindingModel starBindingModel,
                                          ModelAndView modelAndView) {
        List<StarSystemViewModel> starSystemsOrderedByName = this.findStarSystemsOrderedByName()
                .stream()
                .filter(starSystemViewModel -> starSystemViewModel.getStar() == null)
                .collect(Collectors.toList()); //This because one to one relation
        modelAndView.addObject("starSystemsModels", starSystemsOrderedByName);
        return this.view("stars/add-star", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addStar(ModelAndView modelAndView,
                                @Valid @ModelAttribute(name = "starBindingModel") StarBindingModel starBindingModel,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.view("stars/add-star", modelAndView);
        }
        StarServiceModel starServiceModel = this.modelMapper.map(starBindingModel, StarServiceModel.class);
        StarServiceModel starServiceModelWithId = this.starService.saveStar(starServiceModel);
        starBindingModel.setId(starServiceModelWithId.getId());

        if (starServiceModelWithId == null) {
            return this.view("stars/add-star", modelAndView);
        }
        return this.redirect("/stars/show");
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageFooter
    @PageNavbar
    public ModelAndView renderEditStarPage(@PathVariable("id") String id,
                                           @ModelAttribute("starBindingModel") StarBindingModel starBindingModel,
                                           ModelAndView modelAndView) {

        List<StarSystemViewModel> starSystemsOrderedByName = this.findStarSystemsOrderedByName()
                .stream()
                .filter(starSystemViewModel -> starSystemViewModel.getStar() == null)
                .collect(Collectors.toList());
        ;
        modelAndView.addObject("starSystemsModels", starSystemsOrderedByName);

        StarServiceModel starServiceModel = this.starService.findById(id);
        starBindingModel = this.modelMapper.map(starServiceModel, StarBindingModel.class);

        modelAndView.addObject("starBindingModel", starBindingModel);

        return this.view("stars/edit-star", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editGalaxy(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("starBindingModel") StarBindingModel starBindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return this.view("stars/edit-star", modelAndView);
        }

        starBindingModel.setId(id);
        StarServiceModel starServiceModel = this.modelMapper.map(starBindingModel, StarServiceModel.class);

        this.starService.editStar(starServiceModel);

        return this.redirect("/stars/show");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteStar(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.starService.deleteStarById(id);

        if (!isDeleted) {
            return this.view("stars/all-stars", modelAndView);
        }

        return this.redirect("/stars/show");
    }

    @GetMapping("/compareStars")
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public ModelAndView getCompareStarsPage(ModelAndView modelAndView) {
        List<StarViewModel> starsOrderedByName = this.findStarsOrderedByName();
        modelAndView.addObject("starsOne", starsOrderedByName);
        List<StarViewModel> starsOrderedByNameTwo = this.findStarsOrderedByName();
        modelAndView.addObject("starsTwo", starsOrderedByNameTwo);

        return this.view("stars/compare-stars", modelAndView);
    }

    private List<StarSystemViewModel> findStarSystemsOrderedByName() {
        List<StarSystemServiceModel> starServiceModelList = this.starSystemService.findAllOrderedByName();
        List<StarSystemViewModel> starSystemViewModelList = starServiceModelList
                .stream()
                .map(starSystemServiceModel -> this.modelMapper.map(starSystemServiceModel, StarSystemViewModel.class))
                .collect(Collectors.toList());
        return starSystemViewModelList;

    }

    private List<StarViewModel> findStarsOrderedByName() {
        List<StarServiceModel> starServiceModelList = this.starService.findAllOrderedByName();
        List<StarViewModel> starViewModelList = starServiceModelList
                .stream()
                .map(starServiceModel -> this.modelMapper
                        .map(starServiceModel, StarViewModel.class))
                .collect(Collectors.toList());
        return starViewModelList;

    }
}

