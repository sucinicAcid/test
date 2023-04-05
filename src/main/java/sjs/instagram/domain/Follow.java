package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;


}
