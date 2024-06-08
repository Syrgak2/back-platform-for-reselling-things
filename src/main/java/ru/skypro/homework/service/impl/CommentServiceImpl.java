package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
   @Override
    public ResponseEntity<?> getComments(Long id){
      String ok = "okeGet";
        return ResponseEntity.ok(ok + id);
    }

    @Override
     public ResponseEntity<?> addComments(Long id){
        String ok = "okeAdd";
        return ResponseEntity.ok(ok + id);
    }

   @Override
     public ResponseEntity<?> removeComments(Long adId, Long commentId){
         String ok = "okeRemove";
         return ResponseEntity.ok(ok + adId + commentId);
    }

   @Override
     public ResponseEntity<?> patchComments(Long adId, Long commentId, CreateOrUpdateComment comment){
       String ok = "okeRemove";
       return ResponseEntity.ok(ok + adId + commentId + comment);

   }


}
