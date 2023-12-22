package springSecurity.login.Controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String login(){
        return "views/index.html";
    }

    @GetMapping("/panelAdmin")
    public String panelAdmin(@RequestParam("id") int id){
        return "views/panelAdmin.html";
    }

    @GetMapping("/panel")
    public String panel(@RequestParam("id") int id){
        return "views/panel.html";
    }

    @GetMapping("/redirect")
    public String redirectToUrl(@RequestParam("id") int id) {
        boolean esAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        if(esAdmin)
            return "redirect:http://localhost:8080/panelAdmin?id=" + id;
        else
            return "redirect:http://localhost:8080/panel?id=" + id;

    }

    @GetMapping("/signIn")
    public String signIn(){
        return "views/signIn.html";
    }


    
}
