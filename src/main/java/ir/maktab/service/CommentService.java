package ir.maktab.service;

import ir.maktab.dto.CommentDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.exception.NotFoundOrderException;
import ir.maktab.service.exception.NotFoundRateForThisExpert;

import java.util.List;

public interface CommentService {
    void saveNewComment(CommentDto dto) throws NotFoundOrderException;

    void deleteComment(CommentDto dto);

    void updateComment(CommentDto dto);

    List<CommentDto> fetchAllComments();
    List<CommentDto> findByExpert(ExpertDto expertDto) throws NotFoundExpertException, NotFoundRateForThisExpert;

}
