package com.neusoft.system.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class Tools {

	public static Map<String, Object> createDto(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String[]> res = request.getParameterMap();
		int initsize = ((int)(res.size() / 0.75 + 1));
		Set<Entry<String, String[]>> entrySet = res.entrySet();
		String[] value = null;
		Map<String, Object> dto = new HashMap<>(initsize);
		for(Entry<String, String[]> entry: entrySet) {
			value = entry.getValue();
			if (value.length == 1) {
				if (value[0] == null || value[0] == "") {
					dto.put(entry.getKey(), value[0]);
				} 
			}
			else
				dto.put(entry.getKey(), value);
		}
		return dto;
	}

	public static Object joinArray(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
