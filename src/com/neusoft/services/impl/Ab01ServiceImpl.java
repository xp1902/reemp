package com.neusoft.services.impl;

import java.util.Map;

import com.neusoft.services.JdbcServicesSupport;
import com.neusoft.system.tools.Tools;

public class Ab01ServiceImpl extends JdbcServicesSupport{
	public Map<String, Object> dto;
	public Ab01ServiceImpl(Map<String, Object> dto) {
		// TODO Auto-generated constructor stub
		this.dto = dto;
		System.out.print(this.dto);
	}

	public boolean addEmp() throws Exception{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ab01(aab102,aab103,aab104,aab105,aab106,")
		.append("                 aab107,aab108,aab109,aab110,aab111,")
		.append("                 aab112,aab113)")
		.append("          values(?,?,?,?,?,")
		.append("                 ?,?,?,?,?,")
		.append("                 ?,?)")
		;
		
		Object args[] = {
				this.dto.get("aab102"),
    			this.dto.get("aab103"),
    			this.dto.get("aab104"),
    			this.dto.get("aab105"),
    			this.dto.get("aab106"),
    			this.dto.get("aab107"),
    			this.dto.get("aab108"),
    			this.dto.get("aab109"),
    			this.dto.get("aab110"),
				Tools.joinArray(this.dto.get("aab111")),
				Tools.joinArray(this.dto.get("aab112")),
				this.dto.get("aab113")
		};
		return this.executeUpdate(sb.toString(), args) > 0;
	}

}
