package com.neusoft.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.system.db.DBUtils;

public class JdbcServicesSupport {
	
	//”Ôæ‰¡–±Ì
	private final List<PstmMetaData> pstmList=new ArrayList<>();
		
	
	
	protected final int executeUpdate(final String sql, final Object... args) throws Exception
	{
		PreparedStatement st = null;
		try {
			st = DBUtils.prepareStatement(sql);
			int index = 0;
			for(Object param: args) {
				st.setObject(++index, param);
			}
			return st.executeUpdate();
		}
		catch(Exception e) {
			DBUtils.close(st);
		}
		return 0;
	}
	
	private boolean executeBatchTranscation(PreparedStatement ps) throws Exception{
		boolean tag = false;
		try {
			DBUtils.beginTranscation();
			ps.executeBatch();
			DBUtils.commit();
			tag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DBUtils.rollBack();
			e.printStackTrace();
		}
		finally {
			DBUtils.endTranscation();
		}
		return tag;	
	}
	
	protected List<Map<String, String>> queryForList(final String sql) throws Exception{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBUtils.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Map<String, String>> list = new ArrayList<>();
			Map<String, String> ins = null;
			while(rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int size = ((int) (rsmd.getColumnCount() / 0.75 + 1));
				ins = new HashMap<>(size);
				for(int i = 1; i < rsmd.getColumnCount(); i ++) {
					ins.put(rsmd.getColumnLabel(i), rs.getString(i));
				}
				list.add(ins);
			}
			return list;
		}finally {
			DBUtils.close(ps);
			DBUtils.close(rs);
		}
	}
	protected Map<String, String> queryForMap(final String sql, final Object...arg) throws Exception {
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		Map<String, String> ins = null;
		try {
			ps = DBUtils.prepareStatement(sql);
			int index = 0;
			for(Object param: arg) {
				ps.setObject(++index, arg[index]);
				
			}
			rs = ps.executeQuery();
			if(rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				int size = ((int)(count / 0.75 + 1));
				ins = new HashMap<>(size);
				for(int i = 1; i <= count; i ++) {
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				
			}
			return ins;
		}
		finally {
			DBUtils.close(ps);
			DBUtils.close(rs);
		}
	}
	
	
	
}
