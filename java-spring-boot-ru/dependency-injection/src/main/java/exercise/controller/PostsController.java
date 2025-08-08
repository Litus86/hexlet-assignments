package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

import exercise.model.Post;
import exercise.repository.PostRepository;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path ="")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index (){
        return  postRepository.findAll();
    }

    @GetMapping(path ="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post show (@PathVariable long id){
        return  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable long id , @RequestBody Post post){
        var maybe  = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        maybe.setBody(post.getBody());
        maybe.setTitle(post.getTitle());
        return postRepository.save(maybe);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete (@PathVariable long id ){
       postRepository.deleteById(id);
       commentRepository.deleteByPostId(id);
    }

}
// END
