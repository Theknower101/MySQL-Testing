import java.sql.Connection;
import java.sql.ResultSet;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import com.mysql.cj.xdevapi.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class MyTestCases {
	Connection con;
	java.sql.Statement stmt;
	ResultSet rs;
	ResultSet rs2;
	WebDriver driver=new ChromeDriver();
	String url="https://magento.softwaretestingboard.com/customer/account/create/";
	@BeforeTest
	public void SetUp() throws SQLException{
	con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/classicmodels","root","Aa@1234567890");	
	}
	@Test (priority=1)
public void addData() throws SQLException {
	String query="insert into customers (customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,city,country)"+"values(77000,'ABC','Ahmad','Mohammad','0791314605','Arajan','Amman','Jordan')";
	stmt=con.createStatement();
int insertRow=stmt.executeUpdate(query);
if(insertRow>0) {
	System.out.println("Data has been added");
}
else {
	System.out.println("Something went wrong");
}
}
	@Test(priority=2)
	public void updateData() throws SQLException {
		String query="update customers set customerName='automationCourse' where customerNumber='77000'";
		stmt=con.createStatement();
		int insertRow=stmt.executeUpdate(query);
		if(insertRow>0) {
			System.out.println("Has been Updated");
		}
		else {
			System.out.println("Something went wrong");
		}
		
	}
	@Test(priority=3)
	public void getData() throws SQLException{
		stmt=con.createStatement();
		rs=stmt.executeQuery("select * from customers where customerNumber=77000");

		String customerName=null;
		String customerLastName=null;
		while(rs.next()) {
			customerName=rs.getNString("customerName");
			customerLastName=rs.getNString("contactLastName");

		}
		driver.get(url);
		WebElement firstName=driver.findElement(By.id("firstname"));
		firstName.sendKeys(customerName);
		WebElement lastName=driver.findElement(By.id("lastname"));
		lastName.sendKeys(customerLastName);
	}
@Test(priority=4)
public void deleteData() throws SQLException {
	stmt=con.createStatement();
	String query="delete from customers where customerNumber=77000";
	int deletedRow=stmt.executeUpdate(query);
	
	if(deletedRow>0) {
		System.out.println("Has been Deleted");
	}
	else {
		System.out.println("SomeThing went wrong");
	}
	
}
}

