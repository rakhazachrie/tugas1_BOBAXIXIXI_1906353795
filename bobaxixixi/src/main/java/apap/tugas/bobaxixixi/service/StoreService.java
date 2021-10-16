package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;

import java.util.List;

public interface StoreService {
    void addStore(StoreModel store);
    void updateStore(StoreModel store);
    void deleteStore(StoreModel store);
    List<StoreModel> getStoreList();
    StoreModel getStoreById(Long id);
    String generateCode(StoreModel store);
}
