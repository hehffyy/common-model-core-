package com.butone.license;

import java.util.Map;

public abstract class LicenseCheck {

	private static LicenseCheck instance = null;

	protected static void regist(LicenseCheck paramLicenseChecker) {
		instance = paramLicenseChecker;
	}

	public static LicenseCheck instance() {
		if (instance == null)
			throw new RuntimeException("许可无效");
		return instance;
	}

	public abstract void init(String paramString);

	public abstract void check();

	public abstract void print();

	public abstract String getLicenseInfo();

	public abstract Map<String, Object> getLicense();

}
