package peaksoft.service.serviceimpl;


import peaksoft.model.Hospital;
import peaksoft.repostitory.HospitalRepository;
import peaksoft.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The golden boy
 */
@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalRepository.getAll();
    }

    @Override
    public Hospital getById(Long id) {
        return hospitalRepository.getById(id);
    }

    @Override
    public void update(Long id, Hospital newHospital) {
        hospitalRepository.update(id,newHospital);

    }

    @Override
    public void deleteById(Long id) {
        hospitalRepository.deleteById(id);

    }
}
