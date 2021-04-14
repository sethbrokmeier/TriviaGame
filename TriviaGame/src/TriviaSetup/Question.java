/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaSetup;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

/**
 *
 * @author SB133380
 */
public class Question {
    
    private int intQuestionID;
    private int intCategory;
    private String strQuestionText;
    private String strAnswerText;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
    public Question(int qid){
        intQuestionID = qid;
        
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
        getQuestionInfo(qid);
    }
    
    private void getQuestionInfo(int qid){
        
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
            rs = st.executeQuery("SELECT * FROM Questions WHERE QuestionID = '" + qid + "'");
            //loop to load class variables from result set
            while(rs.next())
            {
                intQuestionID = rs.getInt("QuestionID");
                strQuestionText = rs.getString("QuestionText");
                strAnswerText = rs.getString("AnswerText");
                intCategory = rs.getInt("Category");
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
    //Question ID
    public int getQuestionID(){
        return intQuestionID;
    }
    public void setQuestionID(int setter){
        intQuestionID = setter;
    }
    //Question and Answer Texts
    public String getQuestionText(){
        return strQuestionText;
    }
    public void setQuestionText(String setter){
        strQuestionText = setter;
    }
    public String getAnswerText(){
        return strAnswerText;
    }
    public void setAnswerText(String setter){
        strAnswerText = setter;
    }
    //Category
    public int getCategory(){
        return intCategory;
    }
    public void setCategory(int setter){
        intCategory = setter;
    }
    
    public void updateQuestionInfo(){
        
        try
        {
            //create database connection
            //conn = DriverManager.getConnection("jdbc:sqlserver://10.10.0.26;databaseName=Northwind;user=student;password=student");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trivia", "root", "");

            //create statement object
            st = conn.createStatement();
            
            //create result set (executes SQL)
            st.executeUpdate("UPDATE Questions " +
                    
                    "SET "+
                    "QuestionText = \"" + getQuestionText() + "\", " +
                    "AnswerText = \"" + getAnswerText() + "\", " +
                    "Category = " + getCategory() + " " +
                    "WHERE QuestionID = " + intQuestionID + 
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
    public void insertNewQuestion(){
        try
        {
            //create database connection
            //conn = DriverManager.getConnection("jdbc:sqlserver://10.10.0.26;databaseName=Northwind;user=student;password=student");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trivia", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("INSERT INTO Questions(QuestionText, AnswerText) " +
                    "VALUES('" + strQuestionText  + "', '"+ strAnswerText+ "')" );

            //close stuff
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void deleteQuestion(){
        try
        {
            //create database connection
            //conn = DriverManager.getConnection("jdbc:sqlserver://10.10.0.26;databaseName=Northwind;user=student;password=student");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trivia", "root", "");

            //create statement object
            st = conn.createStatement();

            //create result set (executes SQL)
            st.executeUpdate("DELETE FROM Questions " +
                    "WHERE QuestionID = '" + intQuestionID  + "'" );

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
