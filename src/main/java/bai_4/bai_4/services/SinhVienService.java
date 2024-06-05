package bai_4.bai_4.services;

import bai_4.bai_4.entity.SinhVien;
import bai_4.bai_4.repository.ISinhVienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SinhVienService {

    @Autowired
    private ISinhVienRepo repo;


    public List<SinhVien> getAll() {
        List<SinhVien> dssv = repo.findAll();
//        dssv.forEach(sv -> {
//            List<MonHoc> dsmh = monHocRepo.findAllBySinhVien(sv.getMssv());
//            Set<MonHoc> monHocSet = new HashSet<>(dsmh); // Chuyển đổi danh sách thành một tập hợp
//            sv.setMonHocs(monHocSet);
//        });
        return dssv;

    }

    public Optional<SinhVien> getSinhVienById(String id) {
        return repo.findById(id);
    }

    public SinhVien addSinhVien(SinhVien sinhVien) {
        return repo.save(sinhVien);
    }

    public void deleteSinhVien(String id) {
        repo.deleteById(id);
    }

    public SinhVien updateSinhVien(SinhVien sinhVien) {
        return repo.save(sinhVien);
    }

    public void deleteAllByMssv(String id) {
        repo.deleteAllByMssv(id);
    }

    public List<SinhVien> getAllByName(String name) {
        return repo.findSinhViensByHoTenContainsIgnoreCase(name);
    }
}
