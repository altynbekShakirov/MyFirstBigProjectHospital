package hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

/**
 * The golden boy
 */
@Entity
@Table(name = "departments")
@NoArgsConstructor
@Getter
@Setter
public class Department {//Бөлүм
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_id_gen")
    @SequenceGenerator(name = "department_id_gen",sequenceName = "department_id_seq",allocationSize = 1)
    private Long id ;
    private String name ;
    @ManyToMany(mappedBy = "departments",cascade = ALL,fetch = FetchType.LAZY)
    private List<Doctor>doctors;
    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private Hospital hospital;
}
