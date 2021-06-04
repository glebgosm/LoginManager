package org.gleb.login_manager.web;

import org.gleb.login_manager.dto.UserDTO;
import org.gleb.login_manager.model.User;
import org.gleb.login_manager.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
public class WebsiteController {

    @PostConstruct
    public void createAdminUser() {
        userDAO.createUser("admin", "admin");
    }

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("admin");
        model.addAttribute("userDTO", userDTO);
        return "login.html";
    }

    @PostMapping("/login")
    public String login(Model model, UserDTO userDTO) {
        User user = userDAO.getUserByName(userDTO.getUserName());
        if (user == null) {
            model.addAttribute("noSuchUser", Boolean.TRUE);
            return "login.html";
        }
        if (userDTO.getPassword() == null || !userDTO.getPassword().equals(user.getPassword())) {
            model.addAttribute("passwordIsValid",Boolean.FALSE);
            return "login.html";
        }
        model.addAttribute("userName", userDTO.getUserName());
        model.addAttribute("users", userDAO.getUsers());
        return "users.html";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register.html";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @ModelAttribute("userDTO") @Valid UserDTO userDTO,
                           BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            //System.out.println(bindingResult.getFieldError("password").toString());
            model.addAttribute("passwordIsAcceptable",Boolean.FALSE);
            return "register.html";
        }
        userDAO.createUser(userDTO.getUserName(), userDTO.getPassword());
        return "redirect:/login";
    }

    @GetMapping("/login/{userName}")
    public String loginUser(Model model, @PathVariable String userName) throws Exception {
        User user = userDAO.getUserByName(userName);
        if (user == null) throw new Exception("No such user.");
        model.addAttribute("userName", userName);
        model.addAttribute("userDTO", new UserDTO());
        return "simple_login.html";
    }

    @PostMapping("/login/{userName}")
    public String loginUser(Model model, UserDTO userDTO, @PathVariable String userName) {
        User user = userDAO.getUserByName(userName);
        if (userDTO.getPassword() == null || !userDTO.getPassword().equals(user.getPassword())) {
            model.addAttribute("passwordIsValid", Boolean.FALSE);
            return "simple_login.html";
        }
        model.addAttribute("userName", userDTO.getUserName());
        model.addAttribute("users", userDAO.getUsers());
        return "users.html";
    }

}



























