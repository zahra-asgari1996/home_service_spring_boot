package ir.maktab.service;

import ir.maktab.data.domain.SubService;
import ir.maktab.data.repository.ServiceRepository;
import ir.maktab.data.repository.SubServiceRepository;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.exception.DuplicatedDataException;
import ir.maktab.service.exception.NotFoundServiceException;
import ir.maktab.service.exception.NotFoundSubServiceException;
import ir.maktab.service.mapper.ServiceMapper;
import ir.maktab.service.mapper.SubServiceMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private final SubServiceMapper subServiceMapper;
    private final ServiceRepository service;
    private final ServiceMapper serviceMapper;
    private final MessageSource messageSource;

    public SubServiceServiceImpl(SubServiceRepository subServiceRepository, SubServiceMapper subServiceMapper, ServiceRepository service, ServiceMapper serviceMapper, MessageSource messageSource) {
        this.subServiceRepository = subServiceRepository;
        this.subServiceMapper = subServiceMapper;
        this.service = service;
        this.serviceMapper = serviceMapper;
        this.messageSource = messageSource;
    }


    @Override
    public void saveNewSubService(SubServiceDto subServiceDto) throws DuplicatedDataException, NotFoundServiceException {
        if (subServiceRepository.findByName(subServiceDto.getName()).isPresent()) {
            throw new DuplicatedDataException(messageSource.getMessage("duplicated.data",null,new Locale("fa_ir")));
        }
        if (service.findByName(subServiceDto.getService().getName()) == null) {
            throw new NotFoundServiceException(messageSource.getMessage("not.found.service",null,new Locale("fa_ir")));
        }
        subServiceDto.setService(serviceMapper.convertToServiceDto(service.findByName(subServiceDto.getService().getName())));
        subServiceRepository.save(
                subServiceMapper.convertToSubService(subServiceDto));

    }

    @Override
    public void updateSubService(SubServiceDto dto) {
        subServiceRepository.save(subServiceMapper.convertToSubService(dto));

    }

    @Override
    public void deleteSubService(SubServiceDto dto) {
        subServiceRepository.delete(subServiceMapper.convertToSubService(dto));

    }

    @Override
    public SubServiceDto getSubService(SubServiceDto dto) {
        return null;
    }

    @Override
    public List<SubServiceDto> fetchAllSubServices() {
        return subServiceRepository.findAll()
                .stream().map(i -> subServiceMapper.covertToSubServiceDto(i))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExpertFromSubService(SubServiceDto service, ExpertDto expert) {
        service.getExperts().remove(expert);
        subServiceRepository.save(subServiceMapper.convertToSubService(service));
//        subServiceRepository.
//                deleteExpertFromSubService(
//                        subServiceMapper.convertToSubService(service),mapper.toExpert(expert) );
    }

    @Override
    public void updateExpertInSubService(SubServiceDto service, ExpertDto newExpert, ExpertDto oldExpert) {
        service.getExperts().remove(oldExpert);
        service.getExperts().add(newExpert);
        subServiceRepository.save(subServiceMapper.convertToSubService(service));
//        subServiceRepository.
//                updateExpertInSubService
//                        (subServiceMapper.convertToSubService(service),mapper.toExpert(newExpert),mapper.toExpert(oldExpert) );

    }

    @Override
    public void addExpertToSubService(SubServiceDto service, ExpertDto expert) {
        System.out.println(service.getExperts().size());
        service.getExperts().add(expert);
        System.out.println(service.getExperts().size());
        subServiceRepository.save(subServiceMapper.convertToSubService(service));
//        subServiceRepository.
//                addExpertToSubService
//                        (subServiceMapper.convertToSubService(service),mapper.toExpert(expert) );

    }

    @Override
    public SubServiceDto findByName(String name) throws NotFoundSubServiceException {
        Optional<SubService> subService = subServiceRepository.findByName(name);
        if (subService.isPresent()) {
            return subServiceMapper.covertToSubServiceDto(subService.get());
        }
        throw new NotFoundSubServiceException(messageSource.getMessage("not.found.sub.service",null,new Locale("fa_ir")));
    }

    @Override
    //ist of dto beferst
    public List<String> getSubServicesByServiceName(String service) {
        return subServiceRepository.findByServiceName(service).stream().map(i -> i.getName()).collect(Collectors.toList());

    }
}
