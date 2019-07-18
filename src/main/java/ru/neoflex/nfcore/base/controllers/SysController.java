package ru.neoflex.nfcore.base.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController()
@RequestMapping("/system")
public class SysController {

    @RequestMapping(value="/user", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Principal getUser(Principal principal) {
        return principal;
    }
}
