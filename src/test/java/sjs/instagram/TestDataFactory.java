package sjs.instagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sjs.instagram.domain.*;
import sjs.instagram.repository.*;

@Component
public class TestDataFactory {
    @Autowired CommentRepository commentRepository;
    @Autowired CommentLikeRepository commentLikeRepository;
    @Autowired FollowRepository followRepository;
    @Autowired PostRepository postRepository;
    @Autowired PostLikeRepository postLikeRepository;
    @Autowired ReplyRepository replyRepository;
    @Autowired ReplyLikeRepository replyLikeRepository;
    @Autowired StoryRepository storyRepository;
    @Autowired StoryLikeRepository storyLikeRepository;
    @Autowired StoryViewerRepository storyViewerRepository;
    @Autowired UserRepository userRepository;

    public User createUser() {
        UserInfo userInfo = new UserInfo("username", "photo", "instagram_id", "introduction");
        User user = new User(userInfo);
        userRepository.save(user);
        return user;
    }

    public Follow createFollow() {
        Follow follow = new Follow(createUser(), createUser());
        followRepository.save(follow);
        return follow;
    }

    public Post createPost() {
        Post post = new Post("title", "content", createUser());
        postRepository.save(post);
        return post;
    }

    public PostLike createPostLike() {
        PostLike postLike = new PostLike(createPost(), createUser());
        postLikeRepository.save(postLike);
        return postLike;
    }

    public Comment createComment() {
        Comment comment = new Comment("content", createUser(), createPost());
        commentRepository.save(comment);
        return comment;
    }

    public CommentLike createCommentLike() {
        CommentLike commentLike = new CommentLike(createComment(), createUser());
        commentLikeRepository.save(commentLike);
        return commentLike;
    }

    public Reply createReply() {
        Reply reply = new Reply("content", createUser(), createComment());
        replyRepository.save(reply);
        return reply;
    }

    public ReplyLike createReplyLike() {
        ReplyLike replyLike = new ReplyLike(createReply(), createUser());
        replyLikeRepository.save(replyLike);
        return replyLike;
    }

    public Story createStory() {
        Story story = new Story("photo", createUser());
        storyRepository.save(story);
        return story;
    }

    public StoryLike createStoryLike() {
        StoryLike storyLike = new StoryLike(createStory(), createUser());
        storyLikeRepository.save(storyLike);
        return storyLike;
    }

    public StoryViewer createStoryViewer() {
        StoryViewer storyViewer = new StoryViewer(createUser(), createStory());
        storyViewerRepository.save(storyViewer);
        return storyViewer;
    }
}