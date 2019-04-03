package solarsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicPageController {
    @GetMapping("/")
    public String getIndexPage() {
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
    }
}
