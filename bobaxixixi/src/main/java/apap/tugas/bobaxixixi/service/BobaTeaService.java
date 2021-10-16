package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Boba_TeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;

import java.util.List;

public interface BobaTeaService {
    void addBobaTea(Boba_TeaModel bobaTea);
    void updateBobaTea(Boba_TeaModel bobaTea);
    void deleteBobaTea(Boba_TeaModel bobaTea);
    List<Boba_TeaModel> getBobaTeaList();
    Boba_TeaModel getBobaTeaById(Long id);
    Boba_TeaModel getBobaTeaByName(String name);
    Boba_TeaModel getBobaTeaByNameAndTopping(String name, ToppingModel topping);

}
