package ir.maktab.service;

import ir.maktab.data.domain.Comments;
import ir.maktab.data.repository.CommentsRepository;
import ir.maktab.dto.CommentDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.exception.NotFoundExpertException;
import ir.maktab.service.exception.NotFoundOrderException;
import ir.maktab.service.exception.NotFoundRateForThisExpert;
import ir.maktab.service.mapper.CommentMapper;
import ir.maktab.service.mapper.ExpertMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;
    private final ExpertService expertService;
    private final OrderService orderService;
    private final ExpertMapper expertMapper;
    private final MessageSource messageSource;

    public CommentServiceImpl(CommentsRepository commentsRepository, CommentMapper commentMapper,
                              ExpertService expertService, OrderService orderService, ExpertMapper expertMapper, MessageSource messageSource) {
        this.commentsRepository = commentsRepository;
        this.commentMapper = commentMapper;
        this.expertService = expertService;
        this.orderService = orderService;
        this.expertMapper = expertMapper;
        this.messageSource = messageSource;
    }

    @Override
    public void saveNewComment(CommentDto dto) throws NotFoundOrderException {
        OrderDto orderDto = orderService.findById(dto.getOrderDto().getId());
        ExpertDto expert = orderDto.getExpert();
        double v = (expert.getRate() + dto.getRate()) / 2;
        expert.setRate(v);
        dto.setExpert(expert);
        expertService.updateExpert(expert);
        dto.setCustomer(orderDto.getCustomer());
        dto.setOrderDto(orderDto);
        commentsRepository.save(commentMapper.toComment(dto));
    }

    @Override
    public void deleteComment(CommentDto dto) {
        commentsRepository.delete(commentMapper.toComment(dto));

    }

    @Override
    public void updateComment(CommentDto dto) {
        commentsRepository.save(commentMapper.toComment(dto));

    }

    @Override
    public List<CommentDto> fetchAllComments() {
        return
                commentsRepository.findAll()
                        .stream()
                        .map(commentMapper::toCommentDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> findByExpert(ExpertDto expertDto) throws NotFoundExpertException, NotFoundRateForThisExpert {
        ExpertDto byEmail = expertService.findByEmail(expertDto.getEmail());
        List<Comments> comments = commentsRepository.findByExpert(expertMapper.toExpert(byEmail));
        if (comments.size()==0){
            throw new NotFoundRateForThisExpert(messageSource.getMessage("not.found.rate",null,new Locale("en_us")));
        }
        return comments.stream().map(commentMapper::toCommentDto).collect(Collectors.toList());
    }
}
