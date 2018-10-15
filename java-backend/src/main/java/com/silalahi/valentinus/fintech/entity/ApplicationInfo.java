package com.silalahi.valentinus.fintech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@Data
@Entity
@Builder
@AllArgsConstructor(access = PACKAGE)
@NoArgsConstructor
public class ApplicationInfo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String value;

}
