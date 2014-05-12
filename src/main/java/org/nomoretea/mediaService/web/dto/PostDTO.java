package org.nomoretea.mediaService.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.nomoretea.mediaService.persistence.model.Post;

import javax.validation.constraints.Size;
import java.util.List;

public class PostDTO {

	private Long id;

	@NotEmpty
	@Size(min = 2, max = 140)
	private String title;

	private String description;

	@NotEmpty
	@Size(min = 1, max = 160)
	private String content;

	private List<TagDTO> tags;

	@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
	private UserDTO user;

	public PostDTO() {
	}

	public PostDTO(Post post) {

		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.description = post.getDescription();

	}

	public PostDTO(String title, String description, String content, UserDTO user) {
		this.title = title;
		this.description = description;
		this.content = content;
		this.user = user;
	}

	public PostDTO(Long id, String title, String description, String content, UserDTO user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.user = user;
	}

	public PostDTO(Long id, String title, String description, String content) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
