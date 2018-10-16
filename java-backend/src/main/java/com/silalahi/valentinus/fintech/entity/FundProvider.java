package com.silalahi.valentinus.fintech.entity;

import javax.persistence.Column;
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

@Entity
@Table(name = "fund_provider")
@EntityListeners({ AuditingEntityListener.class })
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundProvider {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String salutation;

	private String fullname;

	@Column(unique = true)
	private String email;

	private String phone;

	private String password;

	@CreatedDate
	private Long createDate;

	@CreatedBy
	private String createdBy;

	@LastModifiedDate
	private Long lastModifiedDate;

	@LastModifiedBy
	private String lastModifiedBy;

}
