/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.swing.JOptionPane;

/**
 *
 * @author Rampukar
 */
public class tables {
    public static void main(String[] args) {
        try{
//            String userTable = "create table user(id int primary key,fname varchar(200),lname varchar(200),uname varchar(200),password varchar(20),dob DATE,contactNumber varchar(10))";
//            String adminDetails = "insert into user(name,email,mobileNumber,address,password,securityQuestion,answer,status) values('Admin','admin@gmail.com','1234567890','Nepal','admin','What primary school did you attend?','ABC','true')";
//            String courseTable = "create table course(id int Auto_Increment primary key,cname varchar(200),cost double)";
            String Fee_details = "create table fee_details(receipt_no int(11) not null default 0,student_name varchar(50),roll_no varchar(10), payment_mode varchar(50), cheque_no varchar(50), bank_name varchar(50), dd_no varchar(50), courses varchar(100), total_amount float, date DATE,amount float, discount float,subtotal float ,vat float, total_in_words varchar(200), remark varchar(500), year1 int(11), year2 int(12), primary key (`receipt_no`))";
//            String productTable = "create table product(id int AUTO_INCREMENT primary key,name varchar(200),category varchar(200),price varchar(200))";
//            String billTable = "create table bill(id int primary key,tablenumber varchar(3),name varchar(200),mobileNumber numeric(10,0),email varchar(30),date varchar(50),total varchar(200),createdBy varchar(200))";
//            DbOperations.setDataorDelete(userTable, "User Table Created Successfully!");
            //DbOperations.setDataorDelete(adminDetails, "Admin Details Added Successfully!");
//            DbOperations.setDataorDelete(courseTable, "Course Table Created Successfully!");
            DbOperations.setDataorDelete(Fee_details, "Fee_details Created Successfully!");
//            DbOperations.setDataorDelete(productTable, "Product Table Created Successfully!");
//            DbOperations.setDataorDelete(billTable, "Bill Table Created Successfully!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
