package bai_4.bai_4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Entity(name = "Lop")
@Table(name = "Lop")
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLop")
    private Integer maLop;

    @Size(min = 1, max = 7, message = "Ten lop phai duoi 7 ky tu")
    @NotNull(message = "Ten lop khong duoc de trong")
    @Column(name = "TenLop", length = 7)
    private String tenLop;

    @OneToMany(mappedBy = "lop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SinhVien> sinhviens;
}