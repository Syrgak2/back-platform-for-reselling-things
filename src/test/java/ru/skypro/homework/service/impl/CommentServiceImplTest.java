package ru.skypro.homework.service.impl;

import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.skypro.homework.constants.Constants.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private AdService adService;
    @Mock
    private UserService userService;

    @InjectMocks
    private CommentServiceImpl commentService;

    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    @Test
    public void testGetComments() {
//        Given
        Comment comment = COMMENT;
        Ad ad = AD;
        comment.setAd(ad);
        ad.setComments(List.of(comment));

        List<CommentDTO> excepted = List.of(commentMapper.toCommentDTO(comment));

//        When
        when(adService.find(anyLong())).thenReturn(AD);
        List<CommentDTO> actual = commentService.getComments(AD.getId());
//        Then
        assertEquals(excepted, actual);
    }

    @Test
    public void testFind() {
//        When
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(COMMENT));
        Comment actual = commentService.find(COMMENT.getId());
//        Then
        assertEquals(COMMENT, actual);
    }

    @Test
    public void testAddComment() {
//        Given
        Comment excepted = COMMENT;
        excepted.setAd(AD);
        excepted.setUser(USER);
        excepted.setCreatedAt(LocalDateTime.now());

//        When
        when(userService.find(USER.getUsername())).thenReturn(USER);
        when(adService.find(AD.getId())).thenReturn(AD);
        when(commentRepository.save(any())).thenReturn(excepted);

        Comment actual = commentService.addComments(commentMapper.toCreateOrUpdateDTO(COMMENT), AD.getId(), USER.getUsername());
//        Then
        assertEquals(excepted, actual);
        verify(userService).find(USER.getUsername());
        verify(adService).find(AD.getId());

    }

    @Test
    public void testRemoveComment() {
//        When
        when(commentRepository.findById(COMMENT.getId())).thenReturn(Optional.of(COMMENT));
        boolean actual = commentService.removeComments(AD.getId(), COMMENT.getId());
//        Then
        assertTrue(actual);
    }


}
