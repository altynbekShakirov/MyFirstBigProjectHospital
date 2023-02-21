package hospital.repostitory.repositoryImpl;

import hospital.model.Hospital;
import hospital.repostitory.HospitalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The golden boy
 */
@Repository
@Controller
@Transactional

public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public HospitalRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Hospital save(Hospital hospital) {
        entityManager.persist(hospital);
        return hospital;
    }

    @Override
    public List<Hospital> getAll() {
        return entityManager.createQuery("select l from Hospital l",Hospital.class).getResultList();
    }

    @Override
    public Hospital getById(Long id) {
        return entityManager.find(Hospital.class,id);
    }

    @Override
    public void update(Long id, Hospital newHospital) {

       entityManager.createQuery("update Hospital set name=:n, address =:a where id=:id")
               .setParameter("n",newHospital.getName())
               .setParameter("a",newHospital.getAddress()).
               setParameter( "id", id)
               .executeUpdate();

    }

    @Override
    public void deleteById(Long id) {
        Hospital hospital = entityManager.find(Hospital.class, id);
        entityManager.remove(hospital);
    }
}
