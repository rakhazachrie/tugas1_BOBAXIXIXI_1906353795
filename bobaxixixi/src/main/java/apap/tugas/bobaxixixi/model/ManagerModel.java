package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="manager")
public class ManagerModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="full_name", nullable = false)
    private String full_name;

    @NotNull
    @Column(name = "gender", nullable=false)
    private int gender;

    @NotNull
    @Column(nullable = false)
    private LocalDate date_of_birth;

    @OneToOne(mappedBy = "manager")
    private StoreModel store;


}
