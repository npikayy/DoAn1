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
    @GetMapping("/admin/upload/student")
    public String uploadSV(){
        return "admin_html/upload_sinhvien";
    }
    @GetMapping("/admin/upload/teacher")
    public String uploadGV(){
        return "admin_html/upload_teacher";
    }
    @GetMapping("/admin/upload/user")
    public String uploadUser(){
        return "admin_html/upload_user";
    }
}
