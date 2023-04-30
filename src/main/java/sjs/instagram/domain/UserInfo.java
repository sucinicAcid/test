package sjs.instagram.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Column(name = "NAME")
    private String username;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "INSTAGRAM_ID")
    private String instagram_id;

    @Column(name = "INTRODUCTION")
    private String introduction;
}
