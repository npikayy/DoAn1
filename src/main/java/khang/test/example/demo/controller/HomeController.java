package khang.test.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping()
    public String home(){
        return "login";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin_html/admin_page";
    }
    @GetMapping("/admin/upload/sinhvien")
    public String ThemSV(){
        return "admin_html/sinhvien-uploader";
    }
    @GetMapping("/admin/upload/giangvien")
    public String ThemGV(){
        return "admin_html/giangvien-uploader";
    }
    @GetMapping("/admin/quanly-sinhvien/")
    public String QuanlySV(){
        return "admin_html/quanly-sinhvien";
    }
    @GetMapping("/admin/quanly-giangvien/")
    public String QuanlyGV(){
        return "admin_html/quanly-giangvien";
    }
}
