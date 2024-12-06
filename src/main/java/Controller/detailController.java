package Controller;

import Model.Hospital;
import Service.detailService;

public class detailController {
    detailService detailService=new detailService();
    public void addDetails(Hospital hospital) {
        detailService.addDetails(hospital);
    }

//    public  void updateDetails(Hospital hospital){
//        detailService.updateDetails(hospital);
//
//    }

    public Hospital getDetailById(int id){
        return  detailService.getDetailById(id);

    }

//    public List<Hospital> getAllDetailsById(Hospital hospital){
//        detailService.getAllDetailsById();
//
//    }
    public void deleteDetails(int id){
        detailService.deleteDetails(id);
    }



    public void updateDetails(Hospital hospital) {
        detailService.updateDetails(hospital);
    }
}
