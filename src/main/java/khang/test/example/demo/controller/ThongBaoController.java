package khang.test.example.demo.controller;

import khang.test.example.demo.Service.ThongBaoService;
import khang.test.example.demo.entity.SinhVien;
import khang.test.example.demo.entity.ThongBao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ThongBaoController {
    @Autowired
    ThongBaoService thongBaoService;

    @GetMapping("/thongbao-list")
    public ResponseEntity<?> layThongbao() {
        Map<String, Object> respStu = new LinkedHashMap<String, Object>();
        List<ThongBao> tbaoList = thongBaoService.findAll();
        if (!tbaoList.isEmpty()) {
            respStu.put("status", 1);
            respStu.put("data", tbaoList);
            return new ResponseEntity<>(respStu, HttpStatus.OK);
        } else {
            respStu.clear();
            respStu.put("status", 0);
            respStu.put("message", "Data is not found");
            return new ResponseEntity<>(respStu, HttpStatus.NOT_FOUND);
        }
    }
}
