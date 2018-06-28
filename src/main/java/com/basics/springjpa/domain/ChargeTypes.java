package com.basics.springjpa.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ChargeTypes implements Serializable {

	private static final long serialVersionUID = 7675624497214422761L;

	private Long chrgTypeId = 1000l;
	private String chrgCatCd;
	private String chrgTypeCd;
	private String chrgTypeDesc;
	private String status;
	private Date createDate;
	private String createUser;
	private Date lastUdatedate;
	private String lastUpdateUser;
	private String lastUpdateSource;
}
