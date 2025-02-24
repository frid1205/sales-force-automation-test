package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.admin.CategoryProductPage;
import utilization.Utilize;

public class CategoryProduct extends BaseConfig{
	CategoryProductPage CategoryProductPage;
	String FilePath;
	
	public CategoryProduct() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		CategoryProductPage = new CategoryProductPage();
	}
	
	@DataProvider(name = "dataAddCategory")
	public Object[][] getAddClass() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\CategoryProduct.xlsx","addCategoryProduct");
	    return data;
	}
	
	@DataProvider(name = "dataEditCategory")
	public Object[][] getEditClass() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\CategoryProduct.xlsx","editCategoryProduct");
		return data;
	}
	
	@Test(dataProvider="dataAddCategory")
	public void testAddCategory(String categoryName, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("setting/category-product");
        CategoryProductPage.addCategoryProduct(categoryName, expectedResult);
	}
	
	@Test(dataProvider="dataEditCategory")
	public void testEditCategory(String categoryName, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("setting/category-product");
		CategoryProductPage.editCategoryProduct(categoryName, expectedResult);
	}
}