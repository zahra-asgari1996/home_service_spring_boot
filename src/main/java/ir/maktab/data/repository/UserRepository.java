package ir.maktab.data.repository;

import ir.maktab.data.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {
//    List<Users> fetchAllUsers();
//    void changePassword(Users user);
//    List<Users> findByProperty(SearchCustomerDto dto);
}
