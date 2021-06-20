package ir.maktab.service.mapper;

import ir.maktab.data.domain.Expert;
import ir.maktab.dto.ExpertDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ExpertMapperImpl implements ExpertMapper {
    private final SubServiceMapper serviceMapper;

    public ExpertMapperImpl(SubServiceMapper serviceMapper) {
        this.serviceMapper = serviceMapper;
    }


    @Override
    public Expert toExpert(ExpertDto dto) {
        Expert expert = new Expert();
        expert.setId(dto.getId());
        expert.setName(dto.getName());
        expert.setLastName(dto.getLastName());
        expert.setEmail(dto.getEmail());
        expert.setPassword(dto.getPassword());
        expert.setRole(dto.getRole());
        expert.setSituation(dto.getSituation());
        expert.setDate(dto.getDate());
        expert.setCredit(dto.getCredit());
        expert.setField(dto.getField());
        expert.setImage(dto.getImage());
        expert.setRate(dto.getRate());
        expert.setServices(dto.getServices().stream().map(i -> serviceMapper.convertToSubService(i)).collect(Collectors.toList()));
        return expert;
    }

    @Override
    public ExpertDto toExpertDto(Expert expert) {
        ExpertDto dto = new ExpertDto();
        dto.setId(expert.getId());
        dto.setName(expert.getName());
        dto.setLastName(expert.getLastName());
        dto.setEmail(expert.getEmail());
        dto.setPassword(expert.getPassword());
        dto.setDate(expert.getDate());
        dto.setField(expert.getField());
        dto.setRole(expert.getRole());
        dto.setSituation(expert.getSituation());
        dto.setCredit(expert.getCredit());
        dto.setImage(expert.getImage());
        dto.setRate(expert.getRate());
        dto.setServices(expert.getServices().stream().map(i -> serviceMapper.covertToSubServiceDto(i)).collect(Collectors.toList()));
        return dto;
    }
}
