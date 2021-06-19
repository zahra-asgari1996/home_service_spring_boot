package ir.maktab.service;

import ir.maktab.data.domain.Manager;
import ir.maktab.data.repository.ManagerRepository;
import ir.maktab.dto.ManagerDto;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundManagerException;
import ir.maktab.service.mapper.ManagerMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository repository;
    private final ManagerMapper mapper;
    private final MessageSource messageSource;

    public ManagerServiceImpl(ManagerRepository repository, ManagerMapper mapper, MessageSource messageSource) {
        this.repository = repository;
        this.mapper = mapper;
        this.messageSource = messageSource;
    }

    @Override
    public void saveNewManager(ManagerDto dto) {
        repository.save(mapper.toManager(dto));
    }

    @Override
    public void deleteManager(ManagerDto dto) {
        repository.delete(mapper.toManager(dto));
    }

    @Override
    public void updateManager(ManagerDto dto) {
        repository.save(mapper.toManager(dto));
    }

    @Override
    public List<ManagerDto> fetchAllManagers() {
        return repository.findAll()
                .stream().map(mapper::toManagerDto)
                .collect(Collectors.toList());
    }

    @Override
    public ManagerDto findByUserName(String userName) {
        Optional<Manager> manager = repository.findByUserName(userName);
        if (manager.isPresent()) {
            return mapper.toManagerDto(manager.get());
        }
        return null;
    }

    @Override
    public ManagerDto loginManager(ManagerDto dto) throws NotFoundManagerException, InvalidPassword {
        Optional<Manager> manager = repository.findByUserName(dto.getUserName());
        if (manager.isPresent()) {
            Manager correctManager = manager.get();
            if (correctManager.getPassword().equals(dto.getPassword())) {
                return mapper.toManagerDto(correctManager);
            } else {
                throw new InvalidPassword(messageSource.getMessage("invalid.password",null,new Locale("fa_ir")));
            }
        } else {
            throw new NotFoundManagerException(messageSource.getMessage("not.found.manager",null,new Locale("fa_ir")));
        }
    }
}

