package com.neusoft.services.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.services.JdbcServicesSupport;
import com.neusoft.system.db.DBUtils;
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

	public List<Map<String, String>> query() throws Exception{
			
	  		Object aab102=this.dto.get("qaab102");     //姓名  模糊查询
	  		Object aab103=this.dto.get("qaab103");     //编号
	  		Object aab105=this.dto.get("qaab105");     //性别
	  		Object aab106=this.dto.get("qaab106");     //民族
	  		Object baab104=this.dto.get("baab104");    //生日B
	  		Object eaab104=this.dto.get("eaab104");    //生日E
			List<Object> args = new ArrayList<>();
	  		
	  		//定义SQL主体
	  		StringBuilder sql=new StringBuilder()
	  				.append("select x.aab101,x.aab102,x.aab103,x.aab104,a.fvalue cnaab105,")
	  				.append("       b.fvalue cnaab106,x.aab108,x.aab109")
	  				.append("  from syscode a,syscode b, ab01 x")
	  				.append(" where x.aab105=a.fcode and a.fname='aab105'")
	  				.append("   and x.aab106=b.fcode and b.fname='aab106'") 
	  				;
	  		if(aab102 != "" && aab102 != null) {
	  			sql.append(" and x.aab102 like ?");
	  			args.add(aab102);
	  		}
	  		
	  		
	  		if(aab103 != "" && aab103 != null) {
	  			sql.append(" and x.aab103 = ?");
	  			args.add(aab103);
	  		}
	  		
	  		if(aab105 != "" && aab105 != null) {
	  			sql.append(" and x.aab105 = ?");
	  			args.add(aab105);
	  		}
	  		if(aab106 != "" && aab106 != null) {
	  			sql.append(" and x.aab106 = ?");
	  			args.add(aab106);
	  			
	  		}
	  		if(baab104 != "" && baab104 != null) {
	  			sql.append(" and x.baab104 >= ?");
	  			args.add(baab104);
	  		}
	  		if(baab104 != "" && baab104 != null) {
	  			sql.append(" and x.eaab104 <= ?");
	  			args.add(eaab104);
	  		}
	  		return this.queryForList(sql.toString(), args);
	}

	public boolean deleteEmp() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ab01 where id = ");
		List<Object> delList = dto.get("dlist");
		
		
		
	}

}
