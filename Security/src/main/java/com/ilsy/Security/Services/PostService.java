package com.ilsy.Security.Services;

import com.ilsy.Security.Models.Comment;
import com.ilsy.Security.Models.Post;
import com.ilsy.Security.Models.User;
import com.ilsy.Security.Repo.CommentRepository;
import com.ilsy.Security.Repo.PostRepository;
import com.ilsy.Security.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public void savePost(Principal principal, Post post){
        post.setUser(getUserByPrincipal(principal));
        postRepository.save(post);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null){
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    public void saveComment(Comment comment, Post post, Principal principal){
        comment.setAuthor(getUserByPrincipal(principal).getName());
        comment.setPost(post);
        commentRepository.save(comment);
    }
}
