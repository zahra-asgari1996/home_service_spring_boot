package ir.maktab.service.mapper;

import ir.maktab.data.domain.Manager;
import ir.maktab.dto.ManagerDto;

public interface ManagerMapper {
    Manager toManager(ManagerDto dto);

    ManagerDto toManagerDto(Manager manager);
}
