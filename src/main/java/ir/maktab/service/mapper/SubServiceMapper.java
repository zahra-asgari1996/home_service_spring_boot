package ir.maktab.service.mapper;

import ir.maktab.data.domain.SubService;
import ir.maktab.dto.SubServiceDto;

public interface SubServiceMapper {

    SubServiceDto covertToSubServiceDto(SubService subService);

    SubService convertToSubService(SubServiceDto subServiceDto);
}
