package khang.test.example.demo.mapper;
import khang.test.example.demo.entity.GiangVien;
import khang.test.example.demo.entity.SinhVien;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@org.mapstruct.Mapper(componentModel="spring")
public interface Mapper {
    @Mapping(target = "thongTinTK", ignore = true)
    void capNhatSV(@MappingTarget SinhVien sinhVien, SinhVien newSinhVien);
    @Mapping(target = "thongTinTK", ignore = true)
    void capNhatGV(@MappingTarget GiangVien giangVien, GiangVien newGiangVien);
}
