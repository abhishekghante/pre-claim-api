package com.api.preclaimapi.common;

import java.util.ArrayList;
import java.util.List;

public class CustomMethods {

	public static List<String> getUploadType()
	{
		List<String> uploadType = new ArrayList<String>();
		uploadType.add("pdf1");
		uploadType.add("pdf2");
		uploadType.add("pdf3");
		uploadType.add("image");
		uploadType.add("audio");
		uploadType.add("video");
		return uploadType;
	}
}
