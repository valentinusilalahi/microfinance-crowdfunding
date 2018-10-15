package com.silalahi.valentinus.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "application_info")
@EntityListeners({ AuditingEntityListener.class })
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationInfo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@CreatedDate
	private Long createdDate;

	@CreatedBy
	private String createdBy;
	@LastModifiedDate
	private Long lastModifiedDate;
	@LastModifiedBy
	private String lastModifiedBy;

	private String name;
	private String value;

}
