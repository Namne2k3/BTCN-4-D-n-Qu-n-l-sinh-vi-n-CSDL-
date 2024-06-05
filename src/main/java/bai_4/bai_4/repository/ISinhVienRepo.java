package bai_4.bai_4.repository;

import bai_4.bai_4.entity.SinhVien;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISinhVienRepo extends JpaRepository<SinhVien, String> {

    List<SinhVien> findSinhViensByHoTenContainsIgnoreCase(String hoTen);
//
//    @Query(value = "SELECT * from sinh_vien where mssv = :id", nativeQuery = true)
//    Optional<SinhVien> findById(@Param("id") String id);


//    @Query(value = "SELECT sv.*, mh.* FROM sinh_vien sv LEFT JOIN sinh_vien_mon_hoc svmh ON sv.mssv = svmh.mssv LEFT JOIN mon_hoc mh ON mh.ma_mon = svmh.ma_mon", nativeQuery = true)
//    List<SinhVien> getAll();

    @Transactional
    void deleteAllByMssv(String mssv);
}