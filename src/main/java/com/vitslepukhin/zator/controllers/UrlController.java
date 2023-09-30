package com.vitslepukhin.zator.controllers;

import com.vitslepukhin.zator.domain.Url;
import com.vitslepukhin.zator.exceptions.AlreadyExistException;
import com.vitslepukhin.zator.exceptions.IncorrectUrlException;
import com.vitslepukhin.zator.exceptions.NotFoundException;
import com.vitslepukhin.zator.services.interfaces.UrlCheckService;
import com.vitslepukhin.zator.services.interfaces.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes(names = {UrlController.ERROR, UrlController.SUCCESS})
public class UrlController {
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";

    private final UrlService urlService;
    private final UrlCheckService urlCheckService;

    @GetMapping("/urls")
    public String list(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            Model model
    ) {
        Page<Url> urlsPage = urlService.getPaginated(page, size);
        List<Url> urls = urlsPage.getContent();
        int currentPage = urlsPage.getNumber();
        int totalPages = urlsPage.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);
        model.addAttribute("urls", urls);

        return "urls";
    }

    @PostMapping("/urls")
    public String create(String urlName, Model model) {
        try {
            urlService.save(urlName);
            model.addAttribute(UrlController.SUCCESS, "Страница успешно добавлена");
        } catch (AlreadyExistException ignored) {
            model.addAttribute(UrlController.ERROR, "Страница уже существует");
            return "redirect:/";
        } catch (IncorrectUrlException ignored) {
            model.addAttribute(UrlController.ERROR, "Некорректная страница");
            return "redirect:/";
        }

        return "redirect:/urls";
    }

    @GetMapping("/urls/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Url url;
        try {
            url = urlService.getById(id);
        } catch (NotFoundException ignored) {
            model.addAttribute(UrlController.ERROR, "Страница не существует");
            return "redirect:urls";
        }

        model.addAttribute("url", url);

        return "show";
    }

    @PostMapping("/urls/{id}/checks")
    public String check(@PathVariable("id") int id, Model model) {
        Url url;
        try {
            url = urlService.getById(id);
        } catch (NotFoundException ignored) {
            model.addAttribute(UrlController.ERROR, "Страница не существует");
            return "redirect:/urls";
        }
        urlCheckService.checkUrl(url);

        return "redirect:/urls/{id}";
    }

}
