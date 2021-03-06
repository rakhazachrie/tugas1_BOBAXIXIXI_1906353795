package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="store")
public class StoreModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Size(max=255)
    @Column(name="address", nullable = false)
    private String address;

    @NotNull
    @Size(max=10)
    @Column(name="store_code", nullable = false, unique = true)
    private String store_code;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime open_hour;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime close_hour;

    //Relation with Manager
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_manager", referencedColumnName = "id")
    private ManagerModel manager;

    //ManyToMany
    @OneToMany(mappedBy = "store")
    List<StoreBobaTeaModel> storeBobatea;

}
