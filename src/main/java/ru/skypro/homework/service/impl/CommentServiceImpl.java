package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final AdService adService;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, AdService adService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.adService = adService;
    }

    @Override
    public List<CommentDTO> getComments(Long adId){
        Ad ad  = adService.find(adId);
        return ad.getComments().stream()
                .map(commentMapper::toCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
     public Comment addComments(CreateOrUpdateCommentDTO createOrUpdateCommentDTO, Long id, String userName){
        User user = userService.find(userName);
        Ad ad = adService.find(id);
        Comment comment = commentMapper.createOrUpdateToComment(createOrUpdateCommentDTO);
        comment.setUser(user);
        comment.setAd(ad);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

   @Override
     public Boolean removeComments(Long adId, Long commentId){
         String ok = "okeRemove";
       return true;
    }

   @Override
     public Comment patchComments(Long adId, Long commentId, CreateOrUpdateCommentDTO comment, String userName){
       Ad ad = adService.find(adId);
       User user = userService.find(userName);

       return new Comment();

   }


}
