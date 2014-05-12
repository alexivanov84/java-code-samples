package org.nomoretea.mediaService.web.dto;

import org.nomoretea.mediaService.persistence.model.Tag;

public class TagDTO {

	private Long id;

	private String name;

	public TagDTO(Tag tag) {
		this.id = tag.getId();
		this.name = tag.getName();
	}

	public TagDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

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
}
