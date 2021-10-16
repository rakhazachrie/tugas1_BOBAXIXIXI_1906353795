package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Boba_TeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.StoreBobaTeaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StoreBobaTeaServiceImpl implements StoreBobaTeaService{

    @Autowired
    StoreBobaTeaDb storeBobaTeaDb;

    @Override
    public void addStoreBobaTea(StoreBobaTeaModel storeBoba) { storeBobaTeaDb.save(storeBoba); }

    @Override
    public List<StoreBobaTeaModel> getListStoreBoba() {
        return storeBobaTeaDb.findAll();
    }

    @Override
    public String productCode(StoreBobaTeaModel storeBoba){
        String code = "PC";
        StoreModel store = storeBoba.getStore();
        Boba_TeaModel boba = storeBoba.getBoba_Tea();
        long idStore = store.getId();
        code += String.format("%03d", idStore);
        if (boba.getTopping() == null){
            code += "0";
        }
        else{
            code += "1";
        }
        long idBoba = boba.getId();
        code += String.format("%03d", idBoba);
        return code.toUpperCase();
    }
}
