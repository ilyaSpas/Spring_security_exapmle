package com.ilsy.Security.Services;

import com.ilsy.Security.Models.Post;
import com.ilsy.Security.Models.User;
import com.ilsy.Security.Repo.PostRepository;
import com.ilsy.Security.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

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
}
