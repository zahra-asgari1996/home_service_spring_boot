package ir.maktab.service.mapper;

import ir.maktab.data.domain.Expert;
import ir.maktab.dto.ExpertDto;

public interface ExpertMapper {
    Expert toExpert(ExpertDto dto);

    ExpertDto toExpertDto(Expert expert);
}
