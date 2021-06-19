package ir.maktab.service.mapper;

import ir.maktab.data.domain.Manager;
import ir.maktab.dto.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapperImpl implements ManagerMapper {
    @Override
    public Manager toManager(ManagerDto dto) {
        Manager manager = new Manager();
        manager.setId(dto.getId());
        manager.setUserName(dto.getUserName());
        manager.setPassword(dto.getPassword());
        return manager;
    }

    @Override
    public ManagerDto toManagerDto(Manager manager) {
        ManagerDto dto = new ManagerDto();
        dto.setId(manager.getId());
        dto.setUserName(manager.getUserName());
        dto.setPassword(manager.getPassword());
        return dto;
    }
}
