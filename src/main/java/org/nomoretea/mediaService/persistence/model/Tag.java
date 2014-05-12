package org.nomoretea.mediaService.persistence.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Long id;

	@Column(name = "tag_name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JoinColumn(referencedColumnName = "post_id", name = "post_id", updatable = false)
	private List<Post> posts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
