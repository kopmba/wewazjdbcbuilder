package database.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import database.config.WewazDbConnection;
import database.config.WewazDbState;


public class DaoFactory<T> {
	
	public PreparedStatement getStatement(String q) throws SQLException {
		Connection c = WewazDbConnection.getConnection();
		
		PreparedStatement ps = c.prepareStatement(q);
		return ps;
	}
	
	public void requestWorkflow(String query, WewazDbState entity, String status, Connection c, PreparedStatement ps) throws SQLException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if(c == null) {
			c = WewazDbConnection.getConnection();
		}
		
		entity.onCreate(ps, entity, status);
	}
	
	public void requestCreateFactory(String query, WewazDbState entity, Connection c, PreparedStatement ps) throws SQLException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		if(c == null) {
			c = WewazDbConnection.getConnection();
		}
		
		if(ps == null) {
			ps = c.prepareStatement(query);
		}
		
		entity.onCreate(ps, entity, null);
	}
	
	public void requestUpdateFactory(String query, String rparam, WewazDbState entity, Connection c, PreparedStatement ps) throws SQLException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		//Connection c = WewazDbConnection.getConnection();
		if(c == null) {
			c = WewazDbConnection.getConnection();
		}
		
		if(ps == null) {
			ps = c.prepareStatement(query);
		}
		
		entity.onUpdate(ps, entity, rparam);
	}
	
	public Object requestFindOneFactory(String query, String param, WewazDbState entity, Connection c, PreparedStatement ps) throws SQLException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		
		if(c == null) {
			c = WewazDbConnection.getConnection();
		}
		
		if(ps == null) {
			ps = c.prepareStatement(query);
		}
		
		entity.onReadOne(ps, entity, param);
		
		return entity;
		
	}
	
	public Collection<T> requestFindFactory(String query, List<T> list, WewazDbState entity, Connection c) throws SQLException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		
		if(c == null) {
			c = WewazDbConnection.getConnection();
		}
		
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery(query);
			entity.onRead(stmt, rs, entity, list);
		}
		
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) { }
				
				rs = null;
			}
			
			if(rs != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) { }
				
				stmt = null;
			}
			
		}
		
		return list;
		
	}
	
	public void requestDeleteFactory(String query, String rparam, WewazDbState entity, Connection c, PreparedStatement ps) throws SQLException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		if(c == null) {
			c = WewazDbConnection.getConnection();
		}
		
		if(ps == null) {
			ps = c.prepareStatement(query);
		}
		
		entity.onDelete(ps, entity, rparam);
	}
}
