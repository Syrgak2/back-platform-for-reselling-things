package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
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
          Comment comment = new Comment();
          if (comment == null){
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
                                         @RequestBody CreateOrUpdateComment comment){
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
    public ResponseEntity<?> removeComments(@PathVariable(required = false, name = "id обЪявления") Long adId,
                                            @PathVariable(required = false, name = "id комментария") Long commentId){
        try {
            Comment comment = new Comment();
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
    public ResponseEntity<?> patchComments(@PathVariable(required = false, name = "id обЪявления") Long adId,
                                           @PathVariable(required = false, name = "id комментария") Long commentId,
                                           @RequestBody CreateOrUpdateComment comment){
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
