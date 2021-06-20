package ir.maktab.service.mapper;

import ir.maktab.data.domain.Address;
import ir.maktab.dto.AddressDto;

public interface AddressMapper {
    AddressDto toAddressDto(Address address);
    Address toAddress(AddressDto addressDto);
}
