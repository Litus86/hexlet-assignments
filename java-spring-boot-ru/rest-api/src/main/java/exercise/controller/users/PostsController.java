package exercise.controller.users;

import java.util.List;

import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController{
    @Setter
    private static  List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> index (@PathVariable ("id") int userID ){
        var result = posts.stream()
                .filter(p->p.getUserId()==userID)
                .toList();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create (@RequestBody Post data, @PathVariable("id") int userID ){
      /* Post post = null;
       post.setUserId(userID);
       post.setSlug(data.getSlug());
       post.setTitle(data.getTitle());
       post.setBody(data.getBody());*/
        System.out.println("received id: " + userID);
       data.setUserId(userID);
       posts.add(data);
       return ResponseEntity.status(HttpStatus.CREATED) .body(data);
    }

}

// END
