package khang.test.example.demo.Service;

import khang.test.example.demo.entity.ThongBao;
import khang.test.example.demo.repository.admin_repository.ThongBaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongBaoService {
    @Autowired
    ThongBaoRepository thongBaoRepo;

    public List<ThongBao> findAll(){
        return thongBaoRepo.findAll();
    }

    public void xoaTbao(Integer idTbao){
        thongBaoRepo.deleteByIdTbao(idTbao);
    }

}
