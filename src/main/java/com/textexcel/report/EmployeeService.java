package com.textexcel.report;

import java.util.Arrays;
import java.util.List;

public class EmployeeService {
    public List<Employee> getAllEmployees() {
        return Arrays.asList(
                new Employee("张三", "技术部", 28),
                new Employee("李四", "市场部", 32),
                new Employee("王五", "财务部", 25)
        );//理论上可以实现从数据库调出数据的功能，但是这里这个不是主要功能所以写死，做一个“模拟数据库”。
        //仅作调试目的使用
    }
}
