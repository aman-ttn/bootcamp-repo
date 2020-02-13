'use strict';
employee();
function employee(){
var allEmployees=[];
var emp={

	emp1:{
	  name: "Taman",
	  age: 5,
	  salary: 2200,
	  dob: '12/12/1997'
		},
	emp2:{
	  name: "Aradhya",
	  salary: 900,
	  age: 12,
	  dob: '12/12/1997'
		},
	emp3:{
	  name: "Vinay",
	  salary: 500,
	  age: 23,
	  dob: '12/12/1997'
		},
	emp4:{
	  name: "Ankit",
	  salary: 7600,
	  age: 24,
	  dob: '12/12/1999'
		}

	}

	for(var key in emp){
		allEmployees.push(emp[key]);
		}
	
		console.log('Employee having Salary Greater than 5000:');
	for(var key in allEmployees){
		if(allEmployees[key].salary>5000){
		console.log(allEmployees[key].name);	
				}	
			}
	
		console.log('Employees having age between 0-10');
	for(var key in allEmployees){
		if(allEmployees[key].age<11){
			console.log(allEmployees[key].name);	
				}	
			}
	console.log('Employees having age between 11-20');
	for(var key in allEmployees){
		if(allEmployees[key].age>10 && allEmployees[key].age<21){
		console.log(allEmployees[key].name);	
				}	
			}
		console.log('Employees having age greater than 20');
	for(var key in allEmployees){
		if(allEmployees[key].age>20){
		console.log(allEmployees[key].name);	
				}	
			}
		console.log('Employees having salary leass than 1000 and age greater than 20');
		for(var key in allEmployees){
		if(allEmployees[key].salary<1000 && allEmployees[key].age>20){
		allEmployees[key].salary *=5;
		console.log("Name:"+allEmployees[key].name+allEmployees[key].name);
		console.log("Salary:"+allEmployees[key].salary);
			}	
			}
	}

	
