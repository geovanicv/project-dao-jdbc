package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Department dep = new Department();
				dep.setId(resultSet.getInt("DepartmentId"));
				dep.setName(resultSet.getString("DepName"));
				
				Seller obj = new Seller();
				obj.setId(resultSet.getInt("Id"));
				obj.setName(resultSet.getString("Name"));
				obj.setEmail(resultSet.getString("Email"));
				obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
				obj.setBirthDate(resultSet.getDate("BirthDate"));
				obj.setDepartment(dep);
				
				return obj;
			}
			
			return null;
			
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
