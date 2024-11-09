package khang.test.example.demo.controller;

import khang.test.example.demo.entity.Accounts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
@Controller
public class WebpageController {

    Accounts accounts;

    @GetMapping("/quanly/upload/sinhvien")
    public String ThemSV(Model model, Authentication authentication){
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        { UserDetails userDetails = (UserDetails)
                authentication.getPrincipal(); model.addAttribute("username", userDetails.getUsername()); }
        return "quanly_html/sinhvien-uploader";
    }
    @GetMapping("/quanly/upload/giangvien")
    public String ThemGV(Model model, Authentication authentication){
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        { UserDetails userDetails = (UserDetails)
                authentication.getPrincipal(); model.addAttribute("username", userDetails.getUsername()); }
        return "quanly_html/giangvien-uploader";
    }
    @GetMapping("/quanly/upload/detai")
    public String ThemDT(Model model, Authentication authentication){
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        { UserDetails userDetails = (UserDetails)
                authentication.getPrincipal(); model.addAttribute("username", userDetails.getUsername()); }
        return "quanly_html/detai-uploader";
    }
    @GetMapping("/quanly/quanly-sinhvien")
    public String QuanlySV(Model model, Authentication authentication){
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        { UserDetails userDetails = (UserDetails)
                authentication.getPrincipal(); model.addAttribute("username", userDetails.getUsername()); }
        return "quanly_html/quanly-sinhvien";
    }
    @GetMapping("/quanly/quanly-giangvien")
    public String QuanlyGV(Model model, Authentication authentication){
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        { UserDetails userDetails = (UserDetails)
                authentication.getPrincipal(); model.addAttribute("username", userDetails.getUsername()); }
        return "quanly_html/quanly-giangvien";
    }
    @GetMapping("quanly/quanly-detai")
    public String qlDetai(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        { UserDetails userDetails = (UserDetails)
                authentication.getPrincipal(); model.addAttribute("username", userDetails.getUsername()); }
        return "quanly_html/qldetai.html";
    }
    @GetMapping("quanly/quanly-khoa")
    public String qlKhoa(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        { UserDetails userDetails = (UserDetails)
                authentication.getPrincipal(); model.addAttribute("username", userDetails.getUsername()); }
        return "quanly_html/quanly-khoa.html";
    }

    @GetMapping("/quanly")
    public String home(Model model, Authentication authentication){
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        { UserDetails userDetails = (UserDetails)
                authentication.getPrincipal(); model.addAttribute("username", userDetails.getUsername()); }
        return "quanly_html/trangchu";
    }
    @GetMapping("/login")
    public String Login(){
        return "login_html/dangnhap";
    }
    @GetMapping("/logout")
    public String Logout(){
        return "login_html/dangnhap";
    }

}
