package ru.gb.Java_Spring_HW10.service.imp;

import ru.gb.Java_Spring_HW10.model.Act;
import ru.gb.Java_Spring_HW10.repository.ARepository;
import ru.gb.Java_Spring_HW10.service.AService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceImp implements AService {
    private final ARepository aRepository;

    @Override
    public List<Act> getAllActs() {
        return aRepository.findAll();
    }

    @Override
    public Act createAct(Act act) {
        return aRepository.save(act);
    }

    @Override
    public Act getActById(Long id) {
        return aRepository.findById(id).orElseThrow(null);
    }

    @Override
    public Act updateAct(Act act) {
        return aRepository.save(act);
    }

    @Override
    public void deleteAct(Long id) {
        Act actById = getActById(id);
        aRepository.delete(actById);
    }

    @Override
    public List<Act> findActByReportingPeriod(String reportingPeriod){
        return aRepository.findActByReportingPeriod(reportingPeriod);
    }

}
