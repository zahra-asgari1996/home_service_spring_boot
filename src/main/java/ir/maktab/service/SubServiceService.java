package ir.maktab.service;

import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.exception.DuplicatedDataException;
import ir.maktab.service.exception.NotFoundServiceException;
import ir.maktab.service.exception.NotFoundSubServiceException;

import java.util.List;

public interface SubServiceService {

    void saveNewSubService(SubServiceDto dto) throws DuplicatedDataException, NotFoundServiceException;

    void updateSubService(SubServiceDto dto);

    void deleteSubService(SubServiceDto dto);

    SubServiceDto getSubService(SubServiceDto dto);

    List<SubServiceDto> fetchAllSubServices();

    void deleteExpertFromSubService(SubServiceDto service, ExpertDto expert);

    void updateExpertInSubService(SubServiceDto service, ExpertDto newExpert, ExpertDto oldExpert);

    void addExpertToSubService(SubServiceDto service, ExpertDto expert);

    SubServiceDto findByName(String name) throws NotFoundSubServiceException;

    List<String> getSubServicesByServiceName(String service);
}
