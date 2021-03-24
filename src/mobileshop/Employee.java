/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileshop;

/**
 *
 * @author USER
 */
public class Employee {
    private String  employeeID ;
    private String   employeeName;
    private String  employeePassword ;

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public Employee(String employeeID, String employeeName, String employeePassword) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
    }

}
