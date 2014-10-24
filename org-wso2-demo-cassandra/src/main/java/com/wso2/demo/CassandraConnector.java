package com.wso2.demo;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraConnector {

	public static Cluster cluster;
	public static Session session;
	
	public static Cluster connect(String node){
		return Cluster.builder().addContactPoints(node).build();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cluster = connect("192.168.92.1");
		session = cluster.connect();
		
		//session.execute("CREATE KEYSPACE demo WITH REPLICATION = "
		//		+ "{'class' : 'SimpleStrategy' , 'replication_factor' : 1};");
		
		session.execute("USE demo;");
		
		
		session.execute("CREATE TABLE IF NOT EXISTS testTable (id varchar PRIMARY KEY , name varchar);");
		
		session.execute("INSERT INTO testTable (id , name ) VALUES ( '1001' , 'Rooney' );");
		session.execute("INSERT INTO testTable (id , name ) VALUES ( '1002' , 'Scholes' );");
		session.execute("INSERT INTO testTable (id , name ) VALUES ( '1003' , 'Falcao' );");
		
		
		
		String query_selectAll="SELECT * FROM testTable;";
		ResultSet result = session.execute(query_selectAll);
		for (Row rows: result){
		    System.out.println(rows.toString());
		} 
		
		session.close();
		cluster.close();
	}
}
