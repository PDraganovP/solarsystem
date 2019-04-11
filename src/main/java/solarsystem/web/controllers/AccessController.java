package solarsystem.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import solarsystem.web.annotations.PageFooter;
import solarsystem.web.annotations.PageNavbar;

@Controller
public class AccessController {
    @GetMapping("/unauthorized")
    @PreAuthorize("isAuthenticated()")
    @PageFooter
    @PageNavbar
    public String getUnauthorizedPage() {
        return "unauthorized/unauthorized";
    }
}
