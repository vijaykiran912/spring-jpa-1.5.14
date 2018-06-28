package com.basics.springjpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "charge_types")
public class ChargeTypesEntity {

	@Id
	@Column(name = "chrg_type_id")
	private Long chrgTypeId;

	@Column(name = "chrg_cat_cd")
	private String chrgCatCd;

	@Column(name = "chrg_type_cd")
	private String chrgTypeCd;

	@Column(name = "chrg_type_desc")
	private String chrgTypeDesc;

	@Column(name = "status")
	private String status;

	@Column(name = "crt_dt")
	private Date createDate;

	@Column(name = "crt_user")
	private String createUser;

	@Column(name = "last_upd_dt")
	private Date lastUdatedate;

	@Column(name = "last_upd_user")
	private String lastUpdateUser;

	@Column(name = "last_upd_source")
	private String lastUpdateSource;

}