package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.admin.CategoryProductPage;
import utilization.Utilize;

public class PreparationConfig extends BaseConfig{
	
	public PreparationConfig() {
		super();
	}
	
	@Test
	public void executeQuery() throws InterruptedException, IOException 
	{
		setupDatabaseConnection(getDbUrl(), getDbUser(), getDbPassword());
	}
}