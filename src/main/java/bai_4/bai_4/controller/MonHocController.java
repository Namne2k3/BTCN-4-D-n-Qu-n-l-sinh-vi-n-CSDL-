package bai_4.bai_4.controller;

import bai_4.bai_4.entity.MonHoc;
import bai_4.bai_4.services.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/monhoc")
public class MonHocController {

    @Autowired
    private MonHocService monHocService;

    @GetMapping
    public String showAllMonHoc(Model model) {
        List<MonHoc> dsMonhoc = monHocService.getAll();
        model.addAttribute("dsMonhoc", dsMonhoc);
        return "monhoc/list";
    }

    @PostMapping("/search")
    public String searchSinhVien(Model model, String search) {
        List<MonHoc> dsMonhoc = monHocService.getAllByTenMonHoc(search);
        model.addAttribute("dsMonhoc", dsMonhoc);
        return "monhoc/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("monhoc", new MonHoc());
        return "monhoc/add";
    }

    @PostMapping("/add")
    public String addMonHoc(@ModelAttribute("monhoc") MonHoc monHoc) {
        monHocService.addMonHoc(monHoc);
        return "redirect:/monhoc";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Optional<MonHoc> monhoc = monHocService.getMonHocById(id);
        if (monhoc != null) {
            model.addAttribute("monhoc", monhoc.get());
            return "monhoc/edit";
        }
        return "redirect:/monhoc";
    }

    @PostMapping("/edit/{id}")
    public String updateMonHoc(@PathVariable("id") String id, @ModelAttribute("monhoc") MonHoc monhocDetails) {
        Optional<MonHoc> monhoc = monHocService.getMonHocById(id);
        if (monhoc.isPresent()) {
            monhoc.get().setTenMonHoc(monhocDetails.getTenMonHoc());
            monHocService.updateMonHoc(monhoc.get());
        }

        return "redirect:/monhoc";
    }

    @GetMapping("/delete/{id}")
    public String deleteLop(@PathVariable("id") String id) {
        Optional<MonHoc> monhoc = monHocService.getMonHocById(id);
        if (monhoc.isPresent()) {
            monHocService.deleteMonHoc(monhoc.get().getMaMon());
        }

        return "redirect:/monhoc";
    }
}
