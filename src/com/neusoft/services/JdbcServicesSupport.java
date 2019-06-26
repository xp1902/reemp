package com.neusoft.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.system.db.DBUtils;

/**
 *������:���԰������󷽷����� 
 *
 */
public abstract class JdbcServicesSupport 
{
   /**************************************************************
    * 	                       ����Ϊ���Զ���
    **************************************************************/
	//����б�
	private final List<PstmMetaData> pstmList=new ArrayList<>();
	
   /**************************************************************
    * 	                       ����Ϊ��������������ʵ�ַ�ʽ
    **************************************************************/
	
	protected final boolean executeTransaction()throws Exception
	{
		//�������񷵻�ֵ
		boolean tag=false;
		//��������
		DBUtils.beginTranscation();
		try
		{
			//ѭ������б�
			for(PstmMetaData pmd:this.pstmList)
			{
				//��һִ��SQL���
				pmd.executePreparedStatement();
			}
			//�ύ����
			DBUtils.commit();
			
			//�޸ķ���ֵ
			tag=true;
		}
		catch(Exception ex)
		{
			//����ع�
			DBUtils.rollBack();
			ex.printStackTrace();
		}
		finally
		{
			//��������
			DBUtils.endTranscation();
			//�������е� ������
			for(PstmMetaData pmd:this.pstmList)
			{
				pmd.close();
			}
			//ɾ��������ִ���ڼ�ע����������
			this.pstmList.clear();
		}
		return tag;
	}

	/**
	 * ��������ɾ������
	 * <
	 *    delete  from  table where id=?
	 * >
	 * @param sql      ---  sql���
	 * @param idlist   ---  �����б�
	 * @return
	 * @throws Exception
	 */
	protected final void appendBatch(final String sql,
			                            final Object...idlist)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		for(Object id:idlist)
		{
			pstm.setObject(1, id);
			pstm.addBatch();
		}
		
		this.regPstmObject(pstm);
	}
	
	/**
	 * ��һ״̬������
	 * <
	 *    �ʺ�����SQL���
	 *    update table 
	 *       set col=?
	 *     where id=? 
	 * >
	 * @param sql      ---  sql���
	 * @param newState ---  ��һ�е�Ŀ��״̬
	 * @param idlist   ---  ��������
	 * @return
	 */
	protected final void appendBatch(final String sql,
			                            final Object newState,
			                            final Object...idlist)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		pstm.setObject(1, newState);
		for(Object id:idlist)
		{
			pstm.setObject(2, id);
			pstm.addBatch();
		}
		this.regPstmObject(pstm);
	}

	/**
	 * ��״̬�޸�
	 * <
	 *   update table
	 *     set col1=?,col2=?,col3=?...coln=?
	 *   where id=?
	 *   ����:��һ��Ա���ϵ�ְ����ͬʱ��н
	 *   update ac01 
	 *       set aac111=aac111+?,aac112=? 
	 *     where aac101=?
	 * >
	 * @param sql
	 * @param stateList
	 * @param idlist
	 * @return
	 * @throws Exception
	 */
	protected final void appendBatch(final String sql, final Object stateList[],Object...idlist)throws Exception
	{
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		int index=1;
		for(Object state:stateList)
		{
			pstm.setObject(index++, state);
		}
		for(Object id:idlist)
		{
			pstm.setObject(index, id);
			pstm.addBatch();
		}
		this.regPstmObject(pstm);
	}

	
	/**
	 * ������б�,ע��SQL���
	 * @param pstm
	 */
	private void regPstmObject(PreparedStatement pstm)
	{
		//����Pstm��������
		PstmMetaData pmd=new PstmMetaData(pstm, true);
		//��ӵ�����б�
		this.pstmList.add(pmd);
	}

	
	/**
	 * Ϊ������ӷ�������SQL���
	 * <
	 *   delete from table where id=?
	 *   update table set col1=? where id=?
	 *   update table set col1=?,col2=?,col3=?.... where id=?
	 * >
	 * @param sql      ----  ִ�е�SQL���
	 * @param args     ----  �����б�
	 * @throws Exception
	 */
	protected final void apppendSql(final String sql,final Object...args)throws Exception
	{
		//1.����JDBC�ӿڱ���
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		int index=1;
		for(Object param:args)
		{
			pstm.setObject(index++, param);
		}
		//����pstmBean,��������ִ�з�ʽ
		PstmMetaData pmd=new PstmMetaData(pstm, false);
		//����������뵱ǰ�������б���
		this.pstmList.add(pmd);
	}
	
	

   /**************************************************************
    * 	                       ����Ϊ��һ��������·���
    **************************************************************/
	
	/**
	 * ��״̬�޸�
	 * <
	 *   update table
	 *     set col1=?,col2=?,col3=?...coln=?
	 *     
	 *   where id=?
	 *   ����:��һ��Ա���ϵ�ְ����ͬʱ��н
	 *   update ac01 
	 *       set aac111=aac111+?,aac112=? 
	 *     where aac101=?
	 * >
	 * @param sql
	 * @param stateList
	 * @param idlist
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql, final Object stateList[],Object...idlist)throws Exception
	{
		//1.����JDBC�ӿڱ���
		PreparedStatement pstm=null;
		try
		{
			//����SQL
			pstm=DBUtils.prepareStatement(sql);
			int index=1;
			for(Object state:stateList)  //Ϊset�б���ֶθ�ֵ
			{
				pstm.setObject(index++, state);
			}
			/**
			 * ��set�б�ֵ����ʱ��,
			 * index��ǰֵ����where����������е�����
			 */
			for(Object id:idlist)
			{
				pstm.setObject(index, id);
				pstm.addBatch();
			}
			return this.executeBatchTransaction(pstm);
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
	
	/**
	 * ��������ɾ������
	 * <
	 *    delete  from  table where id=?
	 * >
	 * @param sql      ---  sql���
	 * @param idlist   ---  �����б�
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,
			                            final Object...idlist)throws Exception
	{
		PreparedStatement pstm=null;
		try
		{
			pstm=DBUtils.prepareStatement(sql);
			for(Object id:idlist)
			{
				//������ֵ
				pstm.setObject(1, id);
				pstm.addBatch();
			}
			
			return this.executeBatchTransaction(pstm);
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
	
	
	/**
	 * ��һ״̬������
	 * <
	 *    �ʺ�����SQL���
	 *    update table 
	 *       set col=?
	 *     where id=? 
	 * >
	 * @param sql      ---  sql���
	 * @param newState ---  ��һ�е�Ŀ��״̬
	 * @param idlist   ---  ��������
	 * @return
	 */
	protected final boolean batchUpdate(final String sql,
			                            final Object newState,
			                            final Object...idlist)throws Exception
	{
		//1.����JDBC�ӿڱ���
		PreparedStatement pstm=null;
		try
		{
			//����SQL���
			pstm=DBUtils.prepareStatement(sql);
			//������ֵ---set�б������ֵ
			pstm.setObject(1, newState);
			//ѭ����������
			for(Object id:idlist)
			{
				//�����и�ֵ
				pstm.setObject(2, id);
				//��׼���õ�SQL�����뻺����
				pstm.addBatch();
			}
			return this.executeBatchTransaction(pstm);
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
	
	/**
	 * ��һ������������ִ�з���
	 * @param pstm
	 * @return
	 * @throws Exception
	 */
	private boolean executeBatchTransaction(PreparedStatement pstm)throws Exception
	{
		//1.�������񷵻�ֵ
		boolean tag=false;
		//2.��������
		DBUtils.beginTranscation();
		try
		{
			//3.ִ��������
			pstm.executeBatch();
			//4.�ύ����
			DBUtils.commit();
			//5.�޸ķ���ֵ
			tag=true;
		}
		catch(Exception ex)
		{
			//�ع�����
			DBUtils.rollBack();
			ex.printStackTrace();
		}
		finally
		{
			//��������
			DBUtils.endTranscation();
		}
        return tag;   
	}

	
  /**************************************************************
   * 	                       ����Ϊ��ѯ����
   **************************************************************/
	protected final List<Map<String, String>> queryForList(final String sql ) throws Exception{
		return this.queryForList(sql, null);
	}
	
	protected final List<Map<String,String>> queryForList(final String sql, final Object...args )throws Exception
	{
		//����JDBC�ӿڱ���
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//����SQL
			pstm=DBUtils.prepareStatement(sql.toString());
			int index = 0;
			if (args != null) {
				for (Object param : args) {
					pstm.setObject(++index, param);
				} 
			}
			//ִ��SQL
			rs=pstm.executeQuery();
			//��ȡ�������������
			ResultSetMetaData rsmd=rs.getMetaData();
			//�����ѯ������
			int count=rsmd.getColumnCount();
			//���㰲ȫ�ĳ�ʼ����
			int initSize=((int)(count/0.75))+1;
			
			//����װ�ز�ѯ�����List��������
			List<Map<String,String>> rows=new ArrayList<>();
			//����װ�ص�ǰ�����ݵ�Map��������
			Map<String,String> ins=null;
			
			//ѭ������rsָ��Ĺ���
			while(rs.next())
			{
				//ʵ������ǰ����������
				ins=new HashMap<>(initSize);
				//ѭ����ǰ��������
				for(int i=1;i<=count;i++)
				{
					//�м�ӳ��
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				//����ǰ�����ݷ���List
				rows.add(ins);
			}
			return rows;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	
	
   protected final Map<String,String> queryForMap(final String sql,final Object...args)throws Exception
   {
		//����JDBC�ӿڱ���
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//����SQL
			pstm=DBUtils.prepareStatement(sql);
			//������ֵ
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			
			//ִ��SQL--ͨ��������ִ��SQL���,Ȼ���ɽ����������ܲ�ѯ���
			rs=pstm.executeQuery();
			//����װ�����ݵ���������
			Map<String,String> ins=null;
			//�ж��Ƿ���ڲ�ѯ���
			if(rs.next())
			{
				//��ȡ�������������
				ResultSetMetaData rsmd=rs.getMetaData();
				//�����ѯ������
				int count=rsmd.getColumnCount();
				//���㰲ȫ�ĳ�ʼ����
				int initSize=((int)(count/0.75))+1;
				//ʵ��������
				ins=new HashMap<>(initSize);
				//ѭ��������
				for(int i=1;i<=count;i++)
				{
					//����м�ӳ��
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
			}
			//��������
			return ins;
		}
		finally
		{
	         DBUtils.close(rs);
	         DBUtils.close(pstm);
		}
   }	

   
   /**************************************************************
    * 	                       ����Ϊ��һ���������·���
    **************************************************************/

	/**
	 * ��һ�����ݸ���ͨ�÷���
	 * @param sql   --  ��Ҫִ�е�SQL���
	 * @param args  --  SQL���Ĳ�������
	 * @return
	 * @throws Exception
	 */
	protected final int executeUpdate(final String sql,final Object...args)throws Exception
	{
		//1.����JDBC�ӿڱ���
		PreparedStatement pstm=null;
		try
		{
			//3.����SQL
			pstm=DBUtils.prepareStatement(sql);
			//4.������ֵ
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			//5.ִ��SQL���
			return pstm.executeUpdate();
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
}
