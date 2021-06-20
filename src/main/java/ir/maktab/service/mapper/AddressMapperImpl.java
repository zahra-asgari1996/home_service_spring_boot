package ir.maktab.service.mapper;

import ir.maktab.data.domain.Address;
import ir.maktab.dto.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper{
    @Override
    public AddressDto toAddressDto(Address address) {
        AddressDto addressDto=new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setAlley(address.getAlley());
        addressDto.setPlaque(address.getPlaque());
        return addressDto;
    }

    @Override
    public Address toAddress(AddressDto addressDto) {
        Address address=new Address();
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setAlley(addressDto.getAlley());
        address.setPlaque(addressDto.getPlaque());
        return address;
    }
}
