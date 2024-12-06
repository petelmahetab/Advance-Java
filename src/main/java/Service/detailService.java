package Service;

import Dao.detailDao;
import Model.Hospital;

public class detailService {
    detailDao detailDao=new detailDao();
    public void addDetails(Hospital hospital) {
        detailDao.addDetails(hospital);
    }

    public void updateDetails(Hospital hospital) {
        detailDao.updateDetails(hospital);
    }

    public Hospital getDetailById(int id) {
        return detailDao.getDetailsById(id);
    }

//    public void getAllDetailsById() {
//        detailDao.getAllDetailsById();
//    }

    public void deleteDetails(int id) {
    detailDao.deleteDetails(id);
    }
}
