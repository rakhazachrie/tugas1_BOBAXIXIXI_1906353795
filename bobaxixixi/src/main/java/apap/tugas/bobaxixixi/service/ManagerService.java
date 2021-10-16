package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.ManagerModel;

import java.util.List;

public interface ManagerService {
    List<ManagerModel> getManagerList();
    ManagerModel getManagerById(Long id);
}
