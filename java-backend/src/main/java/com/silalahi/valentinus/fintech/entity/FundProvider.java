package com.silalahi.valentinus.fintech.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "fund_provider")
@EntityListeners({AuditingEntityListener.class})
@DiscriminatorValue("FUND_PROVIDER")
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FundProvider extends User {

    private String name;
    private String phone;

}


