package ru.gb.Java_Spring_HW10.service;

import ru.gb.Java_Spring_HW10.model.Act;

import java.util.List;

public interface AService {
    List<Act> getAllActs();
    Act createAct(Act note);
    Act getActById(Long id);
    Act updateAct(Act act);
    void deleteAct(Long id);
    List<Act> findActByReportingPeriod(String reportingPeriod);
}
