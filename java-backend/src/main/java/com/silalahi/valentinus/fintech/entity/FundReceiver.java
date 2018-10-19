package com.silalahi.valentinus.fintech.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fund_receiver")
@EntityListeners({ AuditingEntityListener.class })
@DiscriminatorValue("FUND_RECEIVER")
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FundReceiver extends User {

	private String name;
	private String phone;

}
