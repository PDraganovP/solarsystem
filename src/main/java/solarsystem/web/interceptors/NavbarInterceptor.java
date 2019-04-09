package solarsystem.web.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import solarsystem.web.annotations.PageNavbar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class NavbarInterceptor extends HandlerInterceptorAdapter {
    private static final String NAVBAR_TEXT = "Solar System";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            if (handler instanceof HandlerMethod) {
                PageNavbar methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PageNavbar.class);

                if (methodAnnotation != null) {
                    modelAndView
                            .addObject("navbarText", NAVBAR_TEXT + " " + methodAnnotation.value());  /*+ " - " + methodAnnotation.value())*/
                }
            }
        }
    }
}