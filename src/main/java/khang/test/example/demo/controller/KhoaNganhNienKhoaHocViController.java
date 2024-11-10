package khang.test.example.demo.controller;

import jakarta.transaction.Transactional;
import khang.test.example.demo.Service.KhoaNganhNienKhoaHocViService;
import khang.test.example.demo.entity.Khoa;
import khang.test.example.demo.entity.chuyenNganh;
import khang.test.example.demo.entity.hocVi;
import khang.test.example.demo.entity.nienKhoa;
import khang.test.example.demo.response.apiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/taoNKMoi")
    public apiResponse<nienKhoa> taoNKmoi(@RequestBody nienKhoa nienKhoa) {
        return apiResponse.<nienKhoa>builder()
                .Code(1000)
                .result(service.TaoNienKhoamoi(nienKhoa))
                .build();
    }

    @PostMapping("/taoNganhMoi")
    public apiResponse<chuyenNganh> taoNganhmoi(@RequestBody chuyenNganh chuyenNganh) {
        return apiResponse.<chuyenNganh>builder()
                .Code(1000)
                .result(service.TaoNganhmoi(chuyenNganh))
                .build();
    }

    @PostMapping("/taoKhoaMoi")
    public apiResponse<Khoa> taoKhoamoi(@RequestBody Khoa khoa) {
        return apiResponse.<Khoa>builder()
                .Code(1000)
                .result(service.TaoKhoamoi(khoa))
                .build();
    }

    @PostMapping("/taoHocViMoi")
    public apiResponse<hocVi> taoHocVimoi(@RequestBody hocVi HocVi) {
        return apiResponse.<hocVi>builder()
                .Code(1000)
                .result(service.TaoHocVimoi(HocVi))
                .build();
    }

    @Transactional
    @DeleteMapping("/khoa/{tenKhoa}")
    public void xoaKhoa(@PathVariable String tenKhoa) {
        service.XoaKhoa(tenKhoa);
    }

    @Transactional
    @DeleteMapping("/nganh/{chuyenNganh}")
    public void xoaNganh(@PathVariable String chuyenNganh) {
        service.XoaNganh(chuyenNganh);
    }

    @Transactional
    @DeleteMapping("/nienkhoa/{nienKhoa}")
    public void xoaNienKhoa(@PathVariable Integer nienKhoa) {
        service.XoaNienKhoa(nienKhoa);
    }

    @Transactional
    @DeleteMapping("/hocvi/{hocvi}")
    public void xoaHocvi(@PathVariable String hocvi) {
        service.XoaHocVi(hocvi);
    }


}
