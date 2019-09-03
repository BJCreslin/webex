package ru.bjcreslin.webex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.webex.repository.domain.region70;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class IndexController {
    List<region70> region70s = new ArrayList<>();

    @GetMapping("")
    public String getIndex(Model model) {

        model.addAttribute("bookslist", region70s);
        return "index";

    }



}
