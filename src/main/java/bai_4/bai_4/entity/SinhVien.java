package bai_4.bai_4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;


@Data
@Entity(name = "SinhVien")
@Table(name = "SinhVien")
@NoArgsConstructor
@AllArgsConstructor
public class SinhVien {

    @Id
    @Column(name = "MSSV", length = 10)
    @Size(min = 10, max = 10, message = "MSSV khong duoc vuot qua 10 ky tu")
    private String mssv;

    @Size(max = 50, message = "Ho ten sinh vien khong duoc vuot qua 50 ky tu")
    @NotNull(message = "Ho ten sinh vien khong duoc de trong")
    @Column(name = "HoTen", length = 50)
    private String hoTen;

    @Past(message = "Ngay sinh khong phai trong qua khu")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Email(message = "Email phai hop le")
    @NotNull(message = "Email khong duoc de trong")
    @Column(name = "Email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "MaLop", referencedColumnName = "MaLop", foreignKey = @ForeignKey(name = "Fk_SINHVIEN_LOP")
    )
    private Lop lop;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SinhVien_MonHoc",
            joinColumns = @JoinColumn(name = "MSSV", referencedColumnName = "mssv"),
            inverseJoinColumns = @JoinColumn(name = "MaMon", referencedColumnName = "maMon")
    )
    private Set<MonHoc> monHocs;
}