package bai_4.bai_4.repository;

import bai_4.bai_4.entity.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILopRepo extends JpaRepository<Lop, Integer> {
    List<Lop> findAllByTenLopContainsIgnoreCase(String tenLop);
}
