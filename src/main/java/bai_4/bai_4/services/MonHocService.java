package bai_4.bai_4.services;

import bai_4.bai_4.entity.MonHoc;
import bai_4.bai_4.repository.IMonHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonHocService {
    @Autowired
    private IMonHocRepo repo;

    public List<MonHoc> getAll() {
        return repo.findAll();
    }

    public Optional<MonHoc> getMonHocById(String id) {
        return repo.findById(id);
    }

    public void addMonHoc(MonHoc monHoc) {
        repo.save(monHoc);
    }

    public void deleteMonHoc(String id) {
        repo.deleteById(id);
    }

    public void updateMonHoc(MonHoc monHoc) {
        repo.save(monHoc);
    }

    public List<MonHoc> getAllByTenMonHoc(String tenMonHoc) {
        return repo.findAlByTenMonHocContainsIgnoreCase(tenMonHoc);
    }
}
