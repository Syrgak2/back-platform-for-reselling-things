package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    private final UserService userService;



    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @Operation(
            summary = "Получение комментариев обЪявления",
            tags = "Комментарии"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(@PathVariable Long id){
      try {
          CommentsDTO commentsDTO = new CommentsDTO(commentService.getComments(id));
          return ResponseEntity.ok(commentsDTO);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    }

    @Operation(
            summary = "Добавление комментария к обЪявлению",
            tags = "Комментарии"
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComments(@PathVariable Long id,
                                         @RequestBody CreateOrUpdateCommentDTO comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            Comment savedComment = commentService.addComments(comment, id, userName);
            return ResponseEntity.ok(commentMapper.toCommentDTO(savedComment));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @Operation(
            summary = "Удаление комментария",
            tags = "Комментарии"
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasRole( 'ADMIN' )" )
    public ResponseEntity<?> removeComments(@PathVariable Long adId,
                                            @PathVariable Long commentId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!authentication.getName().equals(userService.findByCommentId(commentId).getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        boolean excepted = commentService.removeComments(adId, commentId);
        if (!excepted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "Обновление комментария",
            tags = "Комментарии"
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasRole( 'ADMIN' ) or @commentServiceImpl.findAdByCommentId(commentId).author.userName.equals(authentication.name)")
    public ResponseEntity<CommentDTO> patchComments(@PathVariable Long adId,
                                           @PathVariable Long commentId,
                                           @RequestBody CreateOrUpdateCommentDTO comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            if (adId == null) {
                return ResponseEntity.notFound().build();
            }
            if (commentId == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(commentMapper.toCommentDTO(commentService.patchComments(adId, commentId, comment, userName)));

//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException notFoundException) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
