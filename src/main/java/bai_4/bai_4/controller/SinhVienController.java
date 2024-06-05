package bai_4.bai_4.controller;

import bai_4.bai_4.entity.SinhVien;
import bai_4.bai_4.services.LopService;
import bai_4.bai_4.services.MonHocService;
import bai_4.bai_4.services.SinhVienService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhvienService;

    @Autowired
    private LopService lopService;

    @Autowired
    private MonHocService monHocService;

    private static final Logger logger = LoggerFactory.getLogger(SinhVienController.class);

    @GetMapping
    public String showAllSinhVien(Model model) {
        List<SinhVien> dsSv = sinhvienService.getAll();
        model.addAttribute("dsSv", dsSv);
        logger.info("Check list sinhvien >>> " + dsSv);
        return "sinhvien/list";
    }

    @PostMapping("/search")
    public String searchSinhVien(Model model, String search) {
        List<SinhVien> dsSv = sinhvienService.getAllByName(search);
        model.addAttribute("dsSv", dsSv);
        logger.info("Check name >>> " + search);
        return "sinhvien/list";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sinhvien", new SinhVien());
        model.addAttribute("lops", lopService.getAll());
        model.addAttribute("monhocs", monHocService.getAll());
        return "sinhvien/add";
    }

    @PostMapping("/add")
    public String addSinhVien(@Valid @ModelAttribute("sinhvien") SinhVien sinhvien, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("lops", lopService.getAll());
            model.addAttribute("monhocs", monHocService.getAll());
            return "sinhvien/add";
        }
//        SinhVien newSinhVien = new SinhVien();
//        newSinhVien.setNgaySinh(sinhvien.getNgaySinh());
//        newSinhVien.setHoTen(sinhvien.getHoTen());
//        newSinhVien.setEmail(sinhvien.getEmail());
//        newSinhVien.setLop(sinhvien.getLop());
//        Set<MonHoc> monHocs = new HashSet<>();
//
//        for (MonHoc monHoc : sinhvien.getMonHocs()) {
//            Optional<MonHoc> newMh = monHocService.getMonHocById(monHoc.getMaMon());
//            if (newMh.isPresent()) {
//                monHocs.add(newMh.get());
//            }
//        }
//
//        newSinhVien.setMonHocs(monHocs);
        var saved = sinhvienService.addSinhVien(sinhvien);

        logger.info("Check sinh vien add >>> " + saved);


        return "redirect:/sinhvien";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Optional<SinhVien> sinhvien = sinhvienService.getSinhVienById(id);

//        logger.info("Check sinh vien edit >>> " + sinhvien.get());

        if (sinhvien.isPresent()) {
            model.addAttribute("lops", lopService.getAll());
            model.addAttribute("monhocs", monHocService.getAll());
            model.addAttribute("sinhvien", sinhvien.get());
        }
        return "sinhvien/edit";
//        return "redirect:/error";
    }

    @PostMapping("/edit/{id}")
    public String updateSinhVien(Model model, @PathVariable("id") String id, @Valid @ModelAttribute("sinhvien") SinhVien sinhvienDetails, BindingResult bindingResult) {

        SinhVien sinhvien = sinhvienService.getSinhVienById(id).get();
        if (sinhvien != null) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("lops", lopService.getAll());
                model.addAttribute("monhocs", monHocService.getAll());
                return "sinhvien/add";
            }

            sinhvien.setNgaySinh(sinhvienDetails.getNgaySinh());
            sinhvien.setHoTen(sinhvienDetails.getHoTen());
            sinhvien.setEmail(sinhvienDetails.getEmail());
            sinhvien.setLop(sinhvienDetails.getLop());
            sinhvien.setMonHocs(sinhvienDetails.getMonHocs());
//            Set<MonHoc> monHocs = new HashSet<>();
//
//            for (MonHoc monHoc : sinhvienDetails.getMonHocs()) {
//                Optional<MonHoc> newMh = monHocService.getMonHocById(monHoc.getMaMon());
//                if (newMh.isPresent()) {
//                    monHocs.add(newMh.get());
//                }
//            }
//
//            sinhvien.setMonHocs(monHocs);
            var saved = sinhvienService.updateSinhVien(sinhvien);

            logger.info("Check sinh vien add >>> " + saved);

        }

        return "redirect:/sinhvien";

//        return "redirect:/error";
    }

    @GetMapping("/delete/{id}")
    public String deleteSinhVien(@PathVariable("id") String id) {
        Optional<SinhVien> sinhvien = sinhvienService.getSinhVienById(id);
        if (sinhvien.isPresent()) {
            sinhvienService.deleteAllByMssv(sinhvien.get().getMssv());
            sinhvienService.deleteSinhVien(sinhvien.get().getMssv());
        }
        return "redirect:/sinhvien";
    }
}