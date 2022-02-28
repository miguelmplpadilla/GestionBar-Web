package com.controladores;

import com.dao.BarDao;
import com.entity.Bar;
import com.security.MyUserDetailService;
import com.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    BarDao barDao;

    @GetMapping("register")
    public String showRegistrationForm(Model model,
                                       @RequestParam(value = "user", required = false) String user,
                                       @RequestParam(value = "pas", required = false) String pas) {

        List<Bar> usuarios = barDao.getAll();

        model.addAttribute("validation","row g-3 needs-validation");
        model.addAttribute("isGood",true);

        if (user != null && pas != null) {
            if (!user.equals("") && !pas.equals("")) {
                boolean crearUsuario = true;
                for (int i = 0; i < usuarios.size(); i++) {
                    if (usuarios.get(i).getNombre().equals(user)) {
                        model.addAttribute("validado", "row g-3 needs-validation was-validated");
                        model.addAttribute("mensage","Usuario ya existente");
                        model.addAttribute("isGood",false);
                        crearUsuario = false;
                        break;
                    }
                }

                if (crearUsuario) {
                    Bar usuario = new Bar();

                    usuario.setNombre(user);
                    usuario.setContrasena(pas);

                    barDao.create(usuario);

                    return "redirect:login";
                }
            } else {
                model.addAttribute("mensage","Porfavor introduzca el nombre de usuario");
                model.addAttribute("validation", "row g-3 needs-validation was-validated");
            }
        }

        return "register";
    }

    @GetMapping("cerrarSesion")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);

        return "redirect:register";
    }

}
