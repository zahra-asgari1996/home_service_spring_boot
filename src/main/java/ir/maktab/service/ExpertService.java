package ir.maktab.service;

import ir.maktab.dto.*;
import ir.maktab.service.exception.DuplicatedEmailAddressException;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.exception.NotFoundSubServiceException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ExpertService {
    ExpertDto registerExpert(ExpertDto expert,String siteURL) throws DuplicatedEmailAddressException, UnsupportedEncodingException, MessagingException;
    public void sendVerificationEmail(ExpertDto dto, String siteURL) throws UnsupportedEncodingException, MessagingException;
    public boolean verify(String verificationCode);

    void deleteExpert(ExpertDto expert);

    void updateExpert(ExpertDto expert);

    List<ExpertDto> fetchAllExperts();

    ExpertDto findByEmail(String email) throws NotFoundExpertException;

     void addExpertToSubService(SubServiceDto service, ExpertDto expert) throws NotFoundSubServiceException, NotFoundExpertException;

     void addExpertToSubService(SelectFieldForExpertDto dto);

    ExpertDto loginExpert(ExpertDto dto) throws NotFoundExpertException, InvalidPassword;

    void changePassword(ExpertDto dto);

    Double showAvgRate(ExpertDto dto) throws NotFoundExpertException;

    Double getBalance(ExpertDto user);

    void addSubServiceToExpertList(AddSubServiceToExpertDto dto);
}
