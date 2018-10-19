package com.silalahi.valentinus.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application_client")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationClient {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String name;
	
	@CreatedDate
	private Long createdDate;

	@CreatedBy
	private String createdBy;

	@LastModifiedDate
	private Long lastModifiedDate;

	@LastModifiedBy
	private String lastModifiedBy;

}
