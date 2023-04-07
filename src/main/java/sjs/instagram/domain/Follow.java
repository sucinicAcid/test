package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "FOLLOW")
@Getter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FOLLOW_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FROM")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "TO")
    private User toUser;
}
