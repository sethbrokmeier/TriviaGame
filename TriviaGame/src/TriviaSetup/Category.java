/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaSetup;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author SB133380
 */
public class Category {
    
    private int intCategoryID;
    private String strDescription;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
    public Category(int cid){
        intCategoryID = cid;
        
        // initialize database objects
        try
        {
            //initialize database driver
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }

        // call method to get customer info from database
        // and load class variables
        getCategoryInfo(cid);
    }
    
    private void getCategoryInfo(int cid){
        
        try
        {
            //create database connection
//            conn = DriverManager.getConnection(
//                    "jdbc:sqlserver://10.10.0.26;databaseName=Northwind;user=student;password=student");
//            Connection conn = DriverManager.getConnection("jdbc:odbc:Northwind", "Admin", "");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trivia", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            rs = st.executeQuery("SELECT * FROM Categories WHERE CategoryID = '" + cid + "'");
            //loop to load class variables from result set
            while(rs.next())
            {
                intCategoryID = rs.getInt("CategoryID");
                strDescription = rs.getString("Description");
                
            }
        //close stuff
            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    //Category ID
    public int getCategoryID(){
        return intCategoryID;
    }
    public void setCategoryID(int setter){
        intCategoryID = setter;
    }
    //Description
    public String getDescription(){
        return strDescription;
    }
    public void setDescription(String setter){
        strDescription = setter;
    }
    
    public void updateCategoryInfo(){
        
        try
        {
            //create database connection
            //conn = DriverManager.getConnection("jdbc:sqlserver://10.10.0.26;databaseName=Northwind;user=student;password=student");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Text", "root", "");

            //create statement object
            st = conn.createStatement();
            
            //create result set (executes SQL)
            st.executeUpdate("UPDATE Categories " +
                    
                    "SET "+
                    "Description = \"" + getDescription() + "\" " +
                    "WHERE CategoryID = " + intCategoryID + 
                    "");

            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
    }
    public void insertNewCategories(){
        try
        {
            //create database connection
            //conn = DriverManager.getConnection("jdbc:sqlserver://10.10.0.26;databaseName=Northwind;user=student;password=student");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trivia", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("INSERT INTO Categories(CategoriesID) " +
                    "VALUES('" + intCategoryID  + "')" );

            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void deleteCategories(){
        try
        {
            //create database connection
            //conn = DriverManager.getConnection("jdbc:sqlserver://10.10.0.26;databaseName=Northwind;user=student;password=student");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trivia", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("DELETE FROM Categories " +
                    "WHERE CategoryID = '" + intCategoryID  + "'" );

            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
