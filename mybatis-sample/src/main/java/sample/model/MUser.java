package sample.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MUser {

	private String userId;
	private String userName;
	private BigDecimal orgId;
	private boolean delFlg;
	private String insDate;
}
