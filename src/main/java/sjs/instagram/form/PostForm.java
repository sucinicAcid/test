package sjs.instagram.form;


import lombok.Getter;
import lombok.Setter;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;

@Getter
@Setter
public class PostForm {

    private String title;
    private String content;
}
