/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaSetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author SB133380
 */
public class CategoryList {
    // class variables (global)
    public DefaultListModel model;
    public DefaultComboBoxModel model2;
    public Category newcat;
    
    public CategoryList(){
        //get info from database and load variables
        getCategoryInfo();
    }
    public void getCategoryInfo(){
        try
        {
            //initialize database driver
            Class.forName("com.mysql.jdbc.Driver");

            //create database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trivia", "root", "");
            
            //create statement object
            Statement st = conn.createStatement();

            //create result set (executes SQL)
            ResultSet rs = st.executeQuery("SELECT CategoryID FROM Categories ORDER BY CategoryID");

            //loop to load class variables from result set
            model = new DefaultListModel();
            model2 = new DefaultComboBoxModel();
            while(rs.next())
            {
                newcat = new Category(rs.getInt("CategoryID"));
                model.addElement(newcat);
                model2.addElement(newcat);
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
    
    public DefaultListModel getCategoryList()
    {
        getCategoryInfo();
        return model;
    }

    public DefaultComboBoxModel getCategoryCombo()
    {
        getCategoryInfo();
        return model2;
    }
}
