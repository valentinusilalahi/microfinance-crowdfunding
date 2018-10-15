package com.silalahi.valentinus.fintech.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@Entity
@Data
@Builder
@AllArgsConstructor(access = PACKAGE)
@NoArgsConstructor
public class FundProvider {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull
	@NotEmpty
	private String salutation;

	@NotNull
	@NotEmpty
	private String fullname;

	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotNull
	@NotEmpty
	private String phone;

	@NotNull
	@NotEmpty
	private String password;

	private Date createDate;

}
