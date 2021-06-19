package ir.maktab.service.mapper;

import ir.maktab.data.domain.Comments;
import ir.maktab.dto.CommentDto;

public interface CommentMapper {
    Comments toComment(CommentDto dto);

    CommentDto toCommentDto(Comments comments);
}
