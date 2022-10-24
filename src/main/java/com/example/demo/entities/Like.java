package com.example.demo.entities;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="p_like")
@Data
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_id", nullable = false) // to set relation of tables
	@OnDelete(action = OnDeleteAction.CASCADE) // when a user deleted, all posts' will deleted
	@JsonIgnore // for serilization
	Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false) // to set relation of tables
	@OnDelete(action = OnDeleteAction.CASCADE) // when a user deleted, all posts' will deleted
	@JsonIgnore // for serilization
	Users user;
}
