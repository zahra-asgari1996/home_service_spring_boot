package ir.maktab.data.repository;

import ir.maktab.data.domain.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    //    void saveNewExpert(Expert expert);
//    void deleteExpert(Expert expert);
//    void updateExpert(Expert expert);
//    List<Expert> fetchAllExperts();
    Optional<Expert> findByEmail(String email);
}
