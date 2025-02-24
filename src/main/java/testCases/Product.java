package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.admin.ProductPage;
import utilization.Utilize;

public class Product extends BaseConfig{
	ProductPage ProductPage;
	String FilePath;
	
	public Product() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		ProductPage = new ProductPage();
	}
	
	@DataProvider(name = "dataAddProduct")
	public Object[][] getAddProduct() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\Product.xlsx","addProduct");
	    return data;
	}
	
	@DataProvider(name = "dataEditProduct")
	public Object[][] getEditProduct() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\Product.xlsx","editProduct");
		return data;
	}
	
	@Test(dataProvider="dataAddProduct")
	public void testAddProduct(String productName, String productCategory, String productAdvantage, String productDetail, String productImage, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("setting/product");
        ProductPage.addProduct(productName, productCategory, productAdvantage, productDetail, productImage, expectedResult);
	}
	
	@Test(dataProvider="dataEditProduct")
	public void testEditProduct(String productName, String productCategory, String productAdvantage, String productDetail, String productImage, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("setting/product");
		ProductPage.editProduct(productName, productCategory, productAdvantage, productDetail, productImage, expectedResult);
	}
}