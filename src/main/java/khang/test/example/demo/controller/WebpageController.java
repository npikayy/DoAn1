package khang.test.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebpageController {
    @GetMapping("/quanly")
    public String admin(){
        return "quanly_html/trangchu";
    }
    @GetMapping("/quanly/upload/sinhvien")
    public String ThemSV(){
        return "quanly_html/sinhvien-uploader";
    }
    @GetMapping("/quanly/upload/giangvien")
    public String ThemGV(){
        return "quanly_html/giangvien-uploader";
    }
    @GetMapping("/quanly/upload/detai")
    public String ThemDT(){
        return "quanly_html/detai-uploader";
    }
    @GetMapping("/quanly/quanly-sinhvien")
    public String QuanlySV(){
        return "quanly_html/quanly-sinhvien";
    }
    @GetMapping("/quanly/quanly-giangvien")
    public String QuanlyGV(){
        return "quanly_html/quanly-giangvien";
    }
    @GetMapping("/login")
    public String Login(){
        return "login_html/dangnhap";
    }
    @GetMapping("quanly/quanly-detai")
    public String qlDetai() {
        return "quanly_html/qldetai.html";
    }

}
