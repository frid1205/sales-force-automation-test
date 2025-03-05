package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.TaskCategoryPage;
import utilization.Utilize;

public class TaskCategory extends BaseConfig{
	TaskCategoryPage TaskCategoryPage;
	String FilePath;
	
	public TaskCategory() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		TaskCategoryPage = new TaskCategoryPage();
	}
	
	@DataProvider(name = "dataAddTaskCategory")
	public Object[][] getAddClass() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\TaskCategory.xlsx","addTaskCategory");
	    return data;
	}
	
	@DataProvider(name = "dataEditTaskCategory")
	public Object[][] getEditClass() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\TaskCategory.xlsx","editTaskCategory");
		return data;
	}
	
	@Test(dataProvider="dataAddTaskCategory")
	public void testAddTaskCategory(String taskCategoryName, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("setting/task");
        TaskCategoryPage.addTaskCategoryProduct(taskCategoryName, expectedResult);
	}
	
	@Test(dataProvider="dataEditTaskCategory")
	public void testEditTaskCategory(String taskCategoryName, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("setting/task");
		TaskCategoryPage.editTaskCategoryProduct(taskCategoryName, expectedResult);
	}
}