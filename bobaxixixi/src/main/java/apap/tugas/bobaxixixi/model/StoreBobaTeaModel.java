package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name= "store_bobatea")
public class StoreBobaTeaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private StoreModel store;

    @ManyToOne
    @JoinColumn(name = "id_boba")
    private Boba_TeaModel boba_Tea;

    @NotNull
    @Size(max=12)
    @Column(name="production_code", nullable = false, unique = true)
    private String production_code;

}
