package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Boba_TeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;

import java.util.List;


public interface StoreBobaTeaService {
    void addStoreBobaTea(StoreBobaTeaModel storeBoba);
    String productCode(StoreBobaTeaModel storeBoba);
    List<StoreBobaTeaModel> getListStoreBoba();
}
