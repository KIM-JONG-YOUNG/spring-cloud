package edu.jong.spring.role.enums;

import edu.jong.spring.common.enums.CodeEnum;

public enum APIMethod implements CodeEnum<String> {

	ALL,
	GET,
	PUT,
	DELETE,
	POST;

	@Override
	public String getCode() {
		return this.name();
	}

}
