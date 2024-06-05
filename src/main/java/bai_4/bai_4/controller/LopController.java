package bai_4.bai_4.controller;

import bai_4.bai_4.entity.Lop;
import bai_4.bai_4.services.LopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lop")
public class LopController {

    @Autowired
    private LopService lopService;

    @GetMapping
    public String showAllLop(Model model) {
        List<Lop> dsLop = lopService.getAll();
        model.addAttribute("dsLop", dsLop);
        return "lop/list";
    }

    @PostMapping("/search")
    public String searchSinhVien(Model model, String search) {
        List<Lop> dsLop = lopService.getAllByTenLop(search);
        model.addAttribute("dsLop", dsLop);
        return "lop/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("lop", new Lop());
        return "lop/add";
    }

    @PostMapping("/add")
    public String addLop(@ModelAttribute("lop") Lop lop) {
        lopService.addLop(lop);
        return "redirect:/lop";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Optional<Lop> lop = lopService.getLopById(id);
        if (lop != null) {
            model.addAttribute("lop", lop.get());
            return "lop/edit";
        }
        return "redirect:/lop";
    }

    @PostMapping("/edit/{id}")
    public String updateLop(@PathVariable("id") Integer id, @ModelAttribute("lop") Lop lopDetails) {
        Optional<Lop> lop = lopService.getLopById(id);
        if (lop.isPresent()) {
            lop.get().setTenLop(lopDetails.getTenLop());
            lopService.updateLop(lop.get());
        }

        return "redirect:/lop";
    }

    @GetMapping("/delete/{id}")
    public String deleteLop(@PathVariable("id") Integer id) {
        Optional<Lop> lop = lopService.getLopById(id);
        if (lop.isPresent()) {
            lopService.deleteLop(lop.get().getMaLop());
        }

        return "redirect:/lop";
    }
}
