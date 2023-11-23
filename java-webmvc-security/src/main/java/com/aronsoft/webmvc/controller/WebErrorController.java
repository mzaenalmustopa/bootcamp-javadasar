package com.aronsoft.webmvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/error")
public class WebErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("")
    public ModelAndView handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return new ModelAndView("errors/error-404");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return new ModelAndView("errors/error-500");
            }
        }
        return new ModelAndView("errors/index");
    }

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied(){
        return new ModelAndView("/errors/accessDenied.html");
    }

    @GetMapping("/authenticationFailure")
    public ModelAndView authenticationFailure(){
        return new ModelAndView("/errors/authenticationFailure.html");
    }
}
