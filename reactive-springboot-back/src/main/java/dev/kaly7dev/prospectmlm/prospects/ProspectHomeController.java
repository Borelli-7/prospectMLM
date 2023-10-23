package dev.kaly7dev.prospectmlm.prospects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProspectHomeController {

    @GetMapping("/prospect/")
    public String react() {
        return "index";
    }
}
