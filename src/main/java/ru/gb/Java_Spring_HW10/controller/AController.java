package ru.gb.Java_Spring_HW10.controller;

import ru.gb.Java_Spring_HW10.model.Act;
import ru.gb.Java_Spring_HW10.service.AService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor

public class AController {
    private final AService aService;
    private final Counter addActCounter = Metrics.counter("add_act_count");
    private final Timer findAllActsTimer = Metrics.timer("find_acts_timer");

    @GetMapping("/acts")
    public String findAll(Model model) {
        findAllActsTimer.baseTimeUnit();
        List<Act> acts = aService.getAllActs();
        model.addAttribute("acts", acts);
        return "list";
    }

    @GetMapping("/create")
    public String createActForm(Act ignoredAct) {
        addActCounter.increment();
        return "create";
    }

    @PostMapping("/create")
    public String createAct(Act act) {
        aService.createAct(act);
        return "redirect:/acts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAct(@PathVariable("id") Long id) {
        aService.deleteAct(id);
        return "redirect:/acts";
    }

    @GetMapping("/update/{id}")
    public String getOneAct(@PathVariable("id") Long id, Model model) {
        Act act = aService.getActById(id);
        model.addAttribute("act", act);
        return "update";
    }

    @PostMapping("/update")
    public String updateAct(Act act) {
        aService.updateAct(act);
        return "redirect:/acts";
    }

    @GetMapping("/find-acts-by-period")
    public String findActsByPeriod(Model model) {
        List<Act> acts = aService.findActByReportingPeriod("February");
        model.addAttribute("acts", acts);
        return "list";
    }
}
