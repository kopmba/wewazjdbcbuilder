package database.config;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface WewazDbState<T> {
	
	public Object getModel();
	
	public void sqlOnStateChange(PreparedStatement ps, int column, String propertyValue) throws SQLException;
	
	public void sqlResultSet(PreparedStatement ps, ResultSet rs, WewazDbState db) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, SecurityException;
	
	public void sqlStatement(Statement stmt, ResultSet rs, WewazDbState db, List<T> list) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, SQLException;
	
	public void onCreate(PreparedStatement ps, WewazDbState db, Object o) throws SecurityException, IllegalArgumentException, IllegalAccessException, SQLException, InvocationTargetException;
	
	public void onReadOne(PreparedStatement ps, WewazDbState db, String param) throws SecurityException, IllegalArgumentException, IllegalAccessException, SQLException, InvocationTargetException, InstantiationException, NoSuchMethodException;
	
	public void onRead(Statement stmt, ResultSet rs, WewazDbState db, List<T> list) throws SQLException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException;
	
	public void onDelete(PreparedStatement ps, WewazDbState db, String param) throws SecurityException, IllegalArgumentException, IllegalAccessException, SQLException, InvocationTargetException;
	
	public void onUpdate(PreparedStatement ps, WewazDbState db, String param) throws SecurityException, IllegalArgumentException, IllegalAccessException, SQLException, InvocationTargetException;

	
}