package khang.test.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class QuanLyController {
    @GetMapping("/quanly")
    public String quanly() {
        return "quanly_html/thongbao.html";
    }

    @GetMapping("/qldetai")
    public String qlDetai() {
        return "quanly_html/qldetai.html";
    }
    @GetMapping("/phanconggv")
    public String phanconggv() {
        return "quanly_html/phanconggv.html";
    }
    @GetMapping("/sua_phanconggv")
    public String sua_phanconggv() {
        return "quanly_html/sua_phanconggv.html";
    }
}