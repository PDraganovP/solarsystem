package solarsystem.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.domain.models.binding.GalaxyBindingModel;
import solarsystem.domain.models.binding.StarBindingModel;
import solarsystem.domain.models.service.GalaxyServiceModel;
import solarsystem.domain.models.service.StarServiceModel;
import solarsystem.domain.models.view.GalaxyViewModel;
import solarsystem.domain.models.view.StarViewModel;
import solarsystem.services.StarService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("stars")
public class StarController extends BaseController {
    private StarService starService;
    private ModelMapper modelMapper;

    @Autowired
    public StarController(StarService starService, ModelMapper modelMapper) {
        this.starService = starService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/show")
    public ModelAndView show(ModelAndView modelAndView){
        List<StarServiceModel> starServiceModelList = this.starService.findAllOrderedByName();
        List<StarViewModel> starViewModelList = starServiceModelList.stream().map(starServiceModel -> this.modelMapper
                .map(starServiceModel, StarViewModel.class)).collect(Collectors.toList());
        modelAndView.addObject("starViewModel", starViewModelList);

        return this.view("stars/all-stars",modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView renderAddStarPage(@ModelAttribute("starBindingModel") StarBindingModel starBindingModel,
                                            ModelAndView modelAndView) {
        return this.view("stars/add-star", modelAndView);
    }

    @PostMapping("/add")
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
        //   return this.view("galaxies/all-galaxies", modelAndView);
        return this.redirect("/stars/show");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView renderEditStarPage(@PathVariable("id") String id,
                                             @ModelAttribute("starBindingModel") StarBindingModel starBindingModel,
                                             ModelAndView modelAndView) {

        StarServiceModel starServiceModel = this.starService.findById(id);
        starBindingModel = this.modelMapper.map(starServiceModel, StarBindingModel.class);

        modelAndView.addObject("starBindingModel", starBindingModel);

        return this.view("stars/edit-star", modelAndView);
    }
    @PostMapping("/edit/{id}")
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
        /*List<GalaxyServiceModel> galaxyServiceModelList = this.galaxyService.findAllOrderedByName();
        List<GalaxyViewModel> GalaxyViewModelList = galaxyServiceModelList.stream().map(galaxy -> this.modelMapper
                .map(galaxy, GalaxyViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("galaxyViewModel",GalaxyViewModelList);*/
        // return this.view("galaxies/all-galaxies");
        return this.redirect("/stars/show");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteStar(@PathVariable("id") String id, ModelAndView modelAndView) {
        boolean isDeleted = this.starService.deleteStarById(id);

        if (!isDeleted) {
            //Error message: something went wrong!
            return this.view("stars/all-stars", modelAndView);
        }

        // return this.redirect("galaxies/all");
        //  return this.view("galaxies/all-galaxies",modelAndView);
        return this.redirect("/stars/show");
    }

}

