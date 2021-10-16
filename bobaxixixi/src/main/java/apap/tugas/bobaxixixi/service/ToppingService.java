package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.ToppingModel;

import java.util.List;

public interface ToppingService {
    List<ToppingModel> getToppingList();
    ToppingModel getToppingById(Long id);
    ToppingModel getToppingByName(String name);
}
