package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.Boba_TeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BobaTeaDb extends JpaRepository<Boba_TeaModel, Long> {
    Optional<Boba_TeaModel> findById(Long id);
    Optional<Boba_TeaModel> findByName(String name);
    Optional<Boba_TeaModel> findByNameAndTopping(String name, ToppingModel topping);
}
