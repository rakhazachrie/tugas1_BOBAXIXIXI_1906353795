package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Boba_TeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
import apap.tugas.bobaxixixi.repository.BobaTeaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BobaTeaServiceImpl implements BobaTeaService{

    @Autowired
    BobaTeaDb bobaTeaDb;

    @Override
    public void addBobaTea(Boba_TeaModel bobaTea) {
        bobaTeaDb.save(bobaTea);
    }

    @Override
    public void updateBobaTea(Boba_TeaModel bobaTea) {
        bobaTeaDb.save(bobaTea);
    }

    @Override
    public void deleteBobaTea(Boba_TeaModel bobaTea) { bobaTeaDb.delete(bobaTea); }

    @Override
    public List<Boba_TeaModel> getBobaTeaList() { return bobaTeaDb.findAll(); }

    @Override
    public Boba_TeaModel getBobaTeaById(Long id) {
        Optional<Boba_TeaModel> bobaTea = bobaTeaDb.findById(id);
        if (bobaTea.isPresent()) {
            return bobaTea.get();
        }
        return null;
    }

    @Override
    public Boba_TeaModel getBobaTeaByName(String name) {
        Optional<Boba_TeaModel> bobaTea = bobaTeaDb.findByName(name);
        if (bobaTea.isPresent()) {
            return bobaTea.get();
        }
        return null;
    }

    @Override
    public Boba_TeaModel getBobaTeaByNameAndTopping(String name, ToppingModel topping){
        Optional<Boba_TeaModel> bobaTea = bobaTeaDb.findByNameAndTopping(name, topping);
        if (bobaTea.isPresent()) {
            return bobaTea.get();
        }
        return null;
    }
}
