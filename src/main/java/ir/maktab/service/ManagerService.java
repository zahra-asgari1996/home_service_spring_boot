package ir.maktab.service;

import ir.maktab.dto.ManagerDto;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundManagerException;

import java.util.List;

public interface ManagerService {
    void saveNewManager(ManagerDto dto);

    void deleteManager(ManagerDto dto);

    void updateManager(ManagerDto dto);

    List<ManagerDto> fetchAllManagers();

    ManagerDto findByUserName(String userName);

    ManagerDto loginManager(ManagerDto dto) throws NotFoundManagerException, InvalidPassword;
}
