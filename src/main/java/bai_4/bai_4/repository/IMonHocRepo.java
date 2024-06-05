package bai_4.bai_4.repository;

import bai_4.bai_4.entity.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMonHocRepo extends JpaRepository<MonHoc, String> {

//    @Query(value = "SELECT mh.ma_mon, mh.ten_mon_hoc FROM sinh_vien sv LEFT JOIN sinh_vien_mon_hoc svmh ON sv.mssv = svmh.mssv LEFT JOIN mon_hoc mh ON mh.ma_mon = svmh.ma_mon WHERE sv.mssv = :id", nativeQuery = true)
//    List<MonHoc> findAllBySinhVien(@Param("id") String id);


    List<MonHoc> findAlByTenMonHocContainsIgnoreCase(String tenMonHoc);
}
