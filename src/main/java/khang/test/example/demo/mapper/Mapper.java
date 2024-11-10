package khang.test.example.demo.mapper;

import khang.test.example.demo.entity.Detai;
import khang.test.example.demo.entity.GiangVien;
import khang.test.example.demo.entity.SinhVien;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    SinhVien TaoSVmoi(SinhVien sinhVien);

    GiangVien TaoGVmoi(GiangVien giangVien);

    void capNhatSV(@MappingTarget SinhVien sinhVien, SinhVien newSinhVien);

    void capNhatGV(@MappingTarget GiangVien giangVien, GiangVien newGiangVien);

    void capNhatDT(@MappingTarget Detai detai, Detai newDetai);

    @Mapping(target = "ngayKetthuc", ignore = true)
    void capNhatDT2(@MappingTarget Detai detai, Detai newDetai);
}

