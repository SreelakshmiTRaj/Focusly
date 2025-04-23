package com.example.Focusly.resource;

import jakarta.persistence.*;

@Entity
public class Resource {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private String type; // video, pdf, external_link
    private String url; // could be YouTube link or Drive link

    private String topic;

    private boolean locked;

	public Resource() {
		super();
	}

	public Resource(Long id, String title, String description, String type, String url, String topic, boolean locked) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.url = url;
		this.topic = topic;
		this.locked = locked;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}

