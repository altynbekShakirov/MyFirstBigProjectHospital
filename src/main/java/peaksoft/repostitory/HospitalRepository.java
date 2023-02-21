package hospital.repostitory;

import hospital.model.Hospital;

import java.util.List;

/**
 * The golden boy
 */
public interface HospitalRepository {
    Hospital save(Hospital hospital);
    List<Hospital> getAll();
    Hospital getById( Long id );
    void  update(Long id ,Hospital newHospital);
    void  deleteById(Long id);


}
