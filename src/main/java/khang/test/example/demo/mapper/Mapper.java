package khang.test.example.demo.mapper;
import khang.test.example.demo.entity.GiangVien;
import khang.test.example.demo.entity.SinhVien;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@org.mapstruct.Mapper(componentModel="spring")
public interface Mapper {

    void capNhatSV(@MappingTarget SinhVien sinhVien, SinhVien newSinhVien);

    void capNhatGV(@MappingTarget GiangVien giangVien, GiangVien newGiangVien);
}
