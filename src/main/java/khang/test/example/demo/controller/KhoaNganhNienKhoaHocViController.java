package khang.test.example.demo.controller;

import khang.test.example.demo.Service.KhoaNganhNienKhoaHocViService;
import khang.test.example.demo.entity.Khoa;
import khang.test.example.demo.entity.chuyenNganh;
import khang.test.example.demo.entity.hocVi;
import khang.test.example.demo.entity.nienKhoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@RestController
public class KhoaNganhNienKhoaHocViController {
    @Autowired
    KhoaNganhNienKhoaHocViService service;

    @GetMapping("/nienKhoa-list")
    public ResponseEntity<?> layNienKhoa() {
        Map<String, Object> nienkhoa = new LinkedHashMap<String, Object>();
        List<nienKhoa> nienKhoaList = service.findAllNienKhoa();
        if (!nienKhoaList.isEmpty()) {
            nienkhoa.put("status", 1);
            nienkhoa.put("data2", nienKhoaList);
        }
        return new ResponseEntity<>(nienkhoa, HttpStatus.OK);
    }

    @GetMapping("/khoa-list")
    public ResponseEntity<?> layTenKhoa() {
        Map<String, Object> khoa = new LinkedHashMap<String, Object>();
        List<Khoa> KhoaList = service.findAllKhoa();
        if (!KhoaList.isEmpty()) {
            khoa.put("status", 1);
            khoa.put("data1", KhoaList);
        }
        return new ResponseEntity<>(khoa, HttpStatus.OK);
    }

    @GetMapping("/chuyenNganh-list")
    public ResponseEntity<?> layChuyenNganh() {
        Map<String, Object> ChuyenNganh = new LinkedHashMap<String, Object>();
        List<chuyenNganh> ChuyenNganhList = service.findAllChuyenNganh();
        if (!ChuyenNganhList.isEmpty()) {
            ChuyenNganh.put("status", 1);
            ChuyenNganh.put("data3", ChuyenNganhList);
        }
        return new ResponseEntity<>(ChuyenNganh, HttpStatus.OK);
    }

    @GetMapping("/hocVi-list")
    public ResponseEntity<?> layHocVi() {
        Map<String, Object> HocVi = new LinkedHashMap<String, Object>();
        List<hocVi> HocVIList = service.findAllHocVi();
        if (!HocVIList.isEmpty()) {
            HocVi.put("status", 1);
            HocVi.put("data4", HocVIList);
        }
        return new ResponseEntity<>(HocVi, HttpStatus.OK);
    }
}
