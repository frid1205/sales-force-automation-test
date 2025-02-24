package utilization;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import bases.BaseConfig;

public class DB extends BaseConfig{
	public static final String DELETE_LEADLABEL = "delete from card_labels cl where created_by in (select id from users u where u.company_id = (select id from companies c where name like '%Automation%'))";
	public static final String DELETE_TASKCATEGORY = "select * from schedule_tasks st where company_id in (select id from companies c where c.name = 'PT Automation Test Technology')";
	public static final String DELETE_CATEGORY_PRODUCT = "DELETE FROM product_categories pc  WHERE company_id IN (SELECT id FROM companies WHERE name = 'PT Automation Test Technology')";
	public static final String DELETE_ROLES_HAS_PERMISION = "DELETE FROM role_has_permissions rhp WHERE role_id IN (SELECT id FROM roles r WHERE company_id = (SELECT id FROM companies c WHERE name LIKE '%Automation%'))";
    public static final String DELETE_ROLES = "DELETE FROM roles WHERE company_id IN (SELECT id FROM companies WHERE name = 'PT Automation Test Technology')";
    public static final String DELETE_USERS = "DELETE FROM users WHERE company_id IN (SELECT id FROM companies WHERE name = 'PT Automation Test Technology')";
    public static final String DELETE_COMPANY = "DELETE FROM companies WHERE name = 'PT Automation Test Technology'";
    
    public static final String[] ALL_QUERIES = {
    		DELETE_LEADLABEL,
    		DELETE_TASKCATEGORY,
    		DELETE_CATEGORY_PRODUCT,
    		DELETE_ROLES_HAS_PERMISION,
    		DELETE_ROLES,
    		DELETE_USERS,
    		DELETE_COMPANY
    }; 
    
    public static void executeAllQueries() {
        for (String query : ALL_QUERIES) {
        	try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
    		    preparedStatement.executeQuery(); 
    		} catch (SQLException e) {
    		   e.getSQLState();
    		}
        }
    }
}
