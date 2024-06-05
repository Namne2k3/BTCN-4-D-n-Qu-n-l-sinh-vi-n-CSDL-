package bai_4.bai_4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Table(name = "MonHoc")
@Entity(name = "MonHoc")
public class MonHoc {
    @Id
    @Column(name = "MaMon", length = 10)
    @Size(min = 1, max = 10, message = "Ma mon hoc phai tu 1 den 10 ky tu ")
    private String maMon;


    @Size(min = 5, max = 50, message = "Ten mon phai tu 5 den 50 ky tu")
    @Column(name = "TenMonHoc", length = 50)
    private String tenMonHoc;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SinhVien_MonHoc",
            joinColumns = @JoinColumn(name = "MaMon", referencedColumnName = "maMon"),
            inverseJoinColumns = @JoinColumn(name = "MSSV", referencedColumnName = "mssv")
    )
    private Set<SinhVien> sinhViens;
}