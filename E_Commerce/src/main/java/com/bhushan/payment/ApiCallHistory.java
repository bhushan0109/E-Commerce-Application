package com.bhushan.payment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="api_call_history")
public class ApiCallHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="api_id")
	private Integer apiId;

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String request;

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String response;

	private String url;

	@Column(name="user_id")
	private Long userId;
	
	private Date createdDate;

	private Boolean isActive;
	
	private String ipAddress;
	

	public ApiCallHistory(Integer apiId, String request, String response, String url, Long userId, String ipAddress) {
		super();
		this.apiId = apiId;
		this.request = request;
		this.response = response;
		this.url = url;
		this.userId = userId;
		this.setIsActive(true);
		this.setCreatedDate(new Date());
		this.setIpAddress(ipAddress);
	}
}
