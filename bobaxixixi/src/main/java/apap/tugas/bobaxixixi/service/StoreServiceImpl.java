package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.StoreDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreDb storeDb;

    @Override
    public void addStore(StoreModel store) {
        storeDb.save(store);
    }

    @Override
    public void updateStore(StoreModel store) {
        storeDb.save(store);
    }

    @Override
    public void deleteStore(StoreModel store) { storeDb.delete(store); }

    @Override
    public List<StoreModel> getStoreList() {
        return storeDb.findAll();
    }

    @Override
    public StoreModel getStoreById(Long id) {
        Optional<StoreModel> store = storeDb.findById(id);
        if (store.isPresent()) {
            return store.get();
        }
        return null;
    }
    @Override
    public String generateCode(StoreModel store) {
        String temp = "SC";
        //Reverse name
        char ch[] = store.getName().toCharArray();
        String rev = "";
        for (int i = ch.length - 1; i >= 0; i--) {
            rev += ch[i];
        }
        String temp2 = rev.substring(0,3);

        //Get hhhh
        int a = store.getOpen_hour().getHour();
        String open = String.format("%02d", a);
        int tutup = (int) Math.floor(store.getClose_hour().getHour() / 10);
        String close = String.valueOf(tutup);

        //Generate 2 last random string
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int n = 2;
        for(int i = 0; i < n; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String strRandom = sb.toString();
        String code = temp + temp2 + open + close + strRandom;

        return code.toUpperCase();
    }
}
