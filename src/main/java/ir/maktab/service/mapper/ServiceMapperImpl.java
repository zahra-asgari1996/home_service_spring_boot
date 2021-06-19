package ir.maktab.service.mapper;

import ir.maktab.data.domain.Service;
import ir.maktab.dto.ServiceDto;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapperImpl implements ServiceMapper {
//    private final SubServiceMapper serviceMapper;

//    public ServiceMapperImpl(SubServiceMapper serviceMapper) {
//        this.serviceMapper = serviceMapper;
//    }

    @Override
    public ServiceDto convertToServiceDto(Service service) {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(service.getId());
        serviceDto.setName(service.getName());
//        serviceDto.setSubServices(service.getSubServices().stream().map(i->serviceMapper.covertToSubServiceDto(i)).collect(Collectors.toList()));
        return serviceDto;
    }

    @Override
    public Service convertToService(ServiceDto serviceDto) {
        Service service = new Service();
        service.setId(serviceDto.getId());
        service.setName(serviceDto.getName());
//        service.setSubServices(serviceDto.getSubServices().stream().map(i->serviceMapper.convertToSubService(i)).collect(Collectors.toList()));
        return service;
    }
}
