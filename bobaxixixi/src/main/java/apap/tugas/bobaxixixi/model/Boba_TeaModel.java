package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name= "boba_tea")
public class Boba_TeaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "price", nullable=false)
    private int price;

    @NotNull
    @Column(name = "size", nullable=false)
    private int size;

    @NotNull
    @Column(name = "ice_level", nullable=false)
    private int ice_level;

    @NotNull
    @Column(name = "sugar_level", nullable=false)
    private int sugar_level;

    //Relation with Topping
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_topping", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToppingModel topping;

    //ManyToMany
    @OneToMany(mappedBy = "boba_Tea")
    List<StoreBobaTeaModel> storeBobatea;
//    @ManyToMany(mappedBy = "listBoba")
//    List<StoreModel> listStore;

}
