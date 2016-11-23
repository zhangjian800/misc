package com.dolphin.webapp.sms.app;

import org.apache.struts.upload.FormFile;

import com.dolphin.webapp.common.web.struts.GenericForm;

public class UploadForm extends GenericForm {
	
	private FormFile file;
	 
	public FormFile getFile() {
		return file;
	}
 
	public void setFile(FormFile file) {
		this.file = file;
	}

}
