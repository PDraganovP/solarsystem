package solarsystem.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    @PageNavbar
    @PageFooter
    public ModelAndView index() {
        return super.view("home/index");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    @PageNavbar
    @PageFooter
    public ModelAndView home() {
        return super.view("home/home");
    }
}
