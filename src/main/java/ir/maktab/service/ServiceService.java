package ir.maktab.service;

import ir.maktab.dto.ServiceDto;
import ir.maktab.service.exception.DuplicatedDataException;

import java.util.List;

public interface ServiceService {
    void saveNewService(ServiceDto dto) throws DuplicatedDataException;

    ServiceDto getService(ServiceDto dto);

    void deleteService(ServiceDto dto);

    void updateService(ServiceDto dto);

    List<ServiceDto> fetchAllServices();
}
