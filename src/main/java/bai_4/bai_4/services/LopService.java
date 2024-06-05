package bai_4.bai_4.services;

import bai_4.bai_4.entity.Lop;
import bai_4.bai_4.repository.ILopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LopService {
    @Autowired
    private ILopRepo repo;

    public List<Lop> getAll() {
        return repo.findAll();
    }

    public List<Lop> getAllByTenLop(String tenLop) {
        return repo.findAllByTenLopContainsIgnoreCase(tenLop);
    }

    public Optional<Lop> getLopById(Integer id) {
        return repo.findById(id);
    }

    public void addLop(Lop lop) {
        repo.save(lop);
    }

    public void deleteLop(Integer id) {
        repo.deleteById(id);
    }

    public void updateLop(Lop lop) {
        repo.save(lop);
    }
}
