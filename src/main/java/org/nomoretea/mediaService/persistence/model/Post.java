package org.nomoretea.mediaService.persistence.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.nomoretea.mediaService.web.dto.PostDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "posts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	@Column(name = "post_title")
	private String title;

	@Column(name = "post_description")
	private String description;

	@Column(name = "post_content")
	private String content;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "posts")
	private List<Tag> tags;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JoinColumn(referencedColumnName = "user_id", name = "user_id")
	private User user;

	public Post() {
	}

	public Post(PostDTO post) {
		this.title = post.getTitle();
		this.description = post.getDescription();
		this.content = post.getContent();
	}

	public Post(String title, String description, String content, User user) {
		this.title = title;
		this.description = description;
		this.content = content;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
