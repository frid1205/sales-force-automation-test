package testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import bases.BaseConfig;

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