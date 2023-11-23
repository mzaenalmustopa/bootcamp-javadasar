package com.bootcamp.webhtml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/html")
public class HtmlController {

    @GetMapping("/style")
    public ModelAndView style(){
        return new ModelAndView("pages/style/backgroundcolor");
    }

    @GetMapping("/style2")
    public ModelAndView style2(){
        return new ModelAndView("pages/style/backgroundcolor2");
    }

    @GetMapping("/style3")
    public ModelAndView style3(){
        return new ModelAndView("pages/style/textcolor");
    }

    @GetMapping("/style4")
    public ModelAndView style4(){
        return new ModelAndView("pages/style/fonts");
    }

    @GetMapping("/style5")
    public ModelAndView style5(){
        return new ModelAndView("pages/style/fontsize");
    }

    @GetMapping("/style6")
    public ModelAndView style6(){
        return new ModelAndView("pages/style/textaligment");
    }
}
