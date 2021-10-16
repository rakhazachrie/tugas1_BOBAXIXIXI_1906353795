package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
import apap.tugas.bobaxixixi.repository.ToppingDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ToppingServiceImpl implements ToppingService{

    @Autowired
    ToppingDb toppingDb;

    @Override
    public List<ToppingModel> getToppingList() {
        return toppingDb.findAll();
    }

    @Override
    public ToppingModel getToppingById(Long id) {
        Optional<ToppingModel> topping = toppingDb.findById(id);
        if (topping.isPresent()) {
            return topping.get();
        }
        return null;
    }

    @Override
    public ToppingModel getToppingByName(String name) {
        Optional<ToppingModel> topping = toppingDb.findByName(name);
        if (topping.isPresent()) {
            return topping.get();
        }
        return null;
    }
}
