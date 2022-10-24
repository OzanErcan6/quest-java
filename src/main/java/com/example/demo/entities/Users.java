package com.example.demo.entities;
import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String userName;
	String password;
}
