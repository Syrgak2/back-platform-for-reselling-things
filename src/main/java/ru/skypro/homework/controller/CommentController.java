package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads")
public class CommentController {

    private CommentService commentService;

    @Operation(
            summary = "Получение комментариев обЪявления",
            tags = "Комментарии"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable(required = false, name = "id обЪявления") Long id){
      try {
          CommentDTO commentDTO = new CommentDTO();
          if (commentDTO == null){
              return ResponseEntity.notFound().build();
          }
          return ResponseEntity.ok(commentService.getComments(id).getBody());
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    }

    @Operation(
            summary = "Добавление комментария к обЪявлению",
            tags = "Комментарии"
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComments(@PathVariable(required = false, name = "id обЪявления") Long id,
                                         @RequestBody CreateOrUpdateCommentDTO comment){
        try {
            return ResponseEntity.ok(commentService.addComments(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @Operation(
            summary = "Удаление комментария",
            tags = "Комментарии"
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasRole( 'ADMIN' ) or @adServiceImpl.findAdById(id).author.userName.equals(authentication.name)")
    public ResponseEntity<?> removeComments(@PathVariable(name = "id обЪявления") Long adId,
                                            @PathVariable(name = "id комментария") Long commentId){
        try {
            CommentDTO commentDTO = new CommentDTO();
            if (adId == null){
                return ResponseEntity.notFound().build();
            }
            if (commentId == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(commentService.removeComments(adId, commentId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @Operation(
            summary = "Обновление комментария",
            tags = "Комментарии"
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasRole( 'ADMIN' ) or @adServiceImpl.findAdById(id).author.userName.equals(authentication.name)")
    public ResponseEntity<?> patchComments(@PathVariable(name = "id обЪявления") Long adId,
                                           @PathVariable(name = "id комментария") Long commentId,
                                           @RequestBody CreateOrUpdateCommentDTO comment){
        try {
            if (adId == null){
                return ResponseEntity.notFound().build();
            }
            if (commentId == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(commentService.patchComments(adId, commentId, comment));
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
