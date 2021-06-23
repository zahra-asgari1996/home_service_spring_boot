package ir.maktab.data.dbInit;

import ir.maktab.data.domain.Manager;
import ir.maktab.data.repository.ManagerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DataBaseInitializer {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public DataBaseInitializer(ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }
//    @PostConstruct
//    public void  addManager(){
//        Manager manager=new Manager();
//        manager.setUserName("zahra");
//        manager.setPassword(passwordEncoder.encode("Za1996@#"));
//        managerRepository.save(manager);
//
//    }
//
//    @PreDestroy
//    public void deleteManagers(){
//        managerRepository.deleteAll();
//    }
}
