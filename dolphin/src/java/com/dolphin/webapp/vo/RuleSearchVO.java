package com.dolphin.webapp.vo;

import com.dolphin.common.vo.DOLValueObject;

public class RuleSearchVO implements DOLValueObject{
		
		private String rulecode;
		private String province;
		private String city;
		private String apptype;
		
		private String productcode;
		private String version;
		private String pid;
		private long gapdays;
		private String status;
		public String getRulecode() {
			return rulecode;
		}
		public void setRulecode(String rulecode) {
			this.rulecode = rulecode;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getProductcode() {
			return productcode;
		}
		public void setProductcode(String productcode) {
			this.productcode = productcode;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public long getGapdays() {
			return gapdays;
		}
		public void setGapdays(long gapdays) {
			this.gapdays = gapdays;
		}
		public String getApptype() {
			return apptype;
		}
		public void setApptype(String apptype) {
			this.apptype = apptype;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
}
