package utilization;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import bases.BaseConfig;

public class DB extends BaseConfig{
	
	public static final String DELETE_EXPENSESATTACHMENT = "delete from expenses_files where expenses_id in (select id from expenses where schedule_id in (select id FROM schedules s WHERE user_id in (SELECT id FROM users WHERE company_id in (SELECT id FROM companies c WHERE c.name like '%Automation%'))))";
	public static final String DELETE_EXPENSES = "delete from expenses where schedule_id in (select id FROM schedules s WHERE user_id in (SELECT id FROM users WHERE company_id in (SELECT id FROM companies c WHERE c.name like '%Automation%')));";
	public static final String DELETE_TRANSACTIONDETAIL = "delete from transaction_details td where transaction_id in (select id from transactions t where customer_id in (select id from customers c where company_id in (select id from companies c where c.name like '%Automation%')))";
	public static final String DELETE_TRANSACTIONS = "delete from transactions t where customer_id in (select id from customers c where company_id in (select id from companies c where c.name like '%Automation%'))";
	public static final String DELETE_TRACKING_ATTENDACE = "delete from tracking_attendance ta where user_id in(select id from users u where company_id = (select id from companies where name like '%Automation%'))";
	public static final String DELETE_TRACKING = "delete from tracking t where user_id in(select id from users u where company_id = (select id from companies where name like '%Automation%'))";
	public static final String DELETE_ATTENDACE = "delete from attendances a where user_id in (select id from users u where company_id = (select id from companies c where c.name like '%Automation%'))";
	public static final String DELETE_CARD_COMMENT = "delete from card_comments cc where card_id in (select id from cards c where bucket_id in (select id from buckets b where created_by in (select id from users u where company_id in (select id from companies c where c.name like '%Automation%'))))";
	public static final String DELETE_CARD_DETAIL = "delete from card_details cd where card_id in (select id from cards c where bucket_id in (select id from buckets b where created_by in (select id from users u where company_id in (select id from companies c where c.name like '%Automation%'))))";
	public static final String DELETE_CARDS = "delete from cards c where bucket_id in (select id from buckets b where created_by in (select id from users u where company_id in (select id from companies c where c.name like '%Automation%')))";
	public static final String DELETE_BUCKET = "delete from buckets b where created_by in (select id from users u where company_id in (select id from companies c where c.name like '%Automation%'))";
	public static final String DELETE_NOTIFICATION = "delete from notifications n where user_id in (select id from users where company_id in (select id from companies c where c.name like '%Automation%'))";
	public static final String DELETE_SCHEDULE = "DELETE FROM schedules s WHERE user_id in (SELECT id FROM users WHERE company_id in (SELECT id FROM companies c WHERE c.name like '%Automation%'))";
	public static final String DELETE_OUTLET = "DELETE FROM outlets o WHERE company_id in (SELECT id FROM companies c WHERE c.name LIKE '%Automation%')";
	public static final String DELETE_CUSTOMER = "DELETE FROM customers c WHERE company_id in (SELECT id FROM companies c WHERE c.name LIKE '%Automation%')";
	public static final String DELETE_PRODUCT = "DELETE FROM products p WHERE company_id in (SELECT id FROM companies c WHERE c.name LIKE '%Automation%')";
	public static final String DELETE_LEADLABEL = "DELETE FROM card_labels cl WHERE created_by in (SELECT id FROM users u WHERE u.company_id = (SELECT id FROM companies c WHERE name LIKE '%Automation%'))";
	public static final String DELETE_TASKCATEGORY = "DELETE FROM schedule_tasks st WHERE company_id in (SELECT id FROM companies c WHERE c.name = 'PT Automation Test Technology')";
	public static final String DELETE_CATEGORY_PRODUCT = "DELETE FROM product_categories pc  WHERE company_id IN (SELECT id FROM companies WHERE name = 'PT Automation Test Technology')";
	public static final String DELETE_ROLES_HAS_PERMISION = "DELETE FROM role_has_permissions rhp WHERE role_id IN (SELECT id FROM roles r WHERE company_id = (SELECT id FROM companies c WHERE name LIKE '%Automation%'))";
    public static final String DELETE_ROLES = "DELETE FROM roles WHERE company_id IN (SELECT id FROM companies WHERE name = 'PT Automation Test Technology')";
    public static final String DELETE_USERS = "DELETE FROM users WHERE company_id IN (SELECT id FROM companies WHERE name = 'PT Automation Test Technology')";
    public static final String DELETE_COMPANY = "DELETE FROM companies WHERE name = 'PT Automation Test Technology'";
    
    public static final String[] ALL_QUERIES = {
    		DELETE_EXPENSESATTACHMENT, DELETE_EXPENSES,
    		DELETE_TRANSACTIONDETAIL, DELETE_TRANSACTIONS, DELETE_TRACKING_ATTENDACE, DELETE_TRACKING, DELETE_ATTENDACE,
    		DELETE_CARD_COMMENT, DELETE_CARD_DETAIL, DELETE_CARDS, 
    		DELETE_NOTIFICATION, DELETE_SCHEDULE,
    		DELETE_BUCKET, DELETE_OUTLET, DELETE_CUSTOMER,
    		DELETE_PRODUCT, DELETE_LEADLABEL, DELETE_TASKCATEGORY, DELETE_CATEGORY_PRODUCT,
    		DELETE_ROLES_HAS_PERMISION, DELETE_ROLES,
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