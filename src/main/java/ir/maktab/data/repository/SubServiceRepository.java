package ir.maktab.data.repository;

import ir.maktab.data.domain.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService, Integer> {
    //    void saveNewSubService(SubService subService);
//    void updateSubService(SubService subService);
//    void deleteSubService(SubService subService);
//    SubService getSubService(SubService subService);
//    List<SubService> fetchAllSubServices();
//    void deleteExpertFromSubService(SubService service,Expert expert);
//    void updateExpertInSubService(SubService service,Expert newExpert,Expert oldExpert);
//    void addExpertToSubService(SubService service,Expert expert);
    Optional<SubService> findByName(String name);

    //    void findAllByExperts();
    @Query("select s from SubService as s where s.service.name=:name")
    List<SubService> findByServiceName(@Param("name") String name);
}
