package ru.skypro.homework.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.Comment;

@RestController
@RequestMapping("/ads")
@Api(value = "Комментарии")
public class CommentController {

    @Operation(
            summary = "Получение комментариев обЪявления"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long id){
      try {
          Comment comment = new Comment();
          if (comment == null){
              return ResponseEntity.notFound().build();
          }
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    }

    @Operation(
            summary = "Добавление комментария к обЪявлению"
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComments(@PathVariable Long id){
        try {
            Comment comment = new Comment();
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @Operation(
            summary = "Удаление комментария"
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> removeComments(@PathVariable Long adId, @PathVariable Long commentId){
        try {
            Comment comment = new Comment();
            if (adId == null){
                return ResponseEntity.notFound().build();
            }
            if (commentId == null){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @Operation(
            summary = "Обновление комментария"
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> patchComments(@PathVariable Long adId, @PathVariable Long commentId){
        try {
            Comment comment = new Comment();
            if (adId == null){
                return ResponseEntity.notFound().build();
            }
            if (commentId == null){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
