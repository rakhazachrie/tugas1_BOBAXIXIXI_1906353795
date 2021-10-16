package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.ManagerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDb managerDb;

    @Override
    public List<ManagerModel> getManagerList() {
        return managerDb.findAll();
    }

    @Override
    public ManagerModel getManagerById(Long id) {
        Optional<ManagerModel> manager = managerDb.findById(id);
        if (manager.isPresent()) {
            return manager.get();
        }
        return null;
    }
}
