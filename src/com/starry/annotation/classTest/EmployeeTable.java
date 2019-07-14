package com.starry.annotation.classTest;

//@ShardMapping("shardRef")
@ShardMapping(value="shardRef")
public class EmployeeTable {
	public String EmpID;
	public String EmpName;
	public String EmpRank;
}
