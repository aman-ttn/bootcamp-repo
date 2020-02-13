copyShallow();
copyDeep();
'use strict';
function copyShallow(){
var obj1 = {
  firstName: "Vinay",
  lastName: "Parida",
  age: 22,
  id: 4001
};
var obj2=obj1;
console.log(obj2.firstName);
console.log(obj2.lastName);
console.log(obj2.age);
console.log(obj2.id);
}
function copyDeep(){
var obj3 = {
  firstName: "Vinay",
  lastName: "Parida",
  age: 22,
  id: 4001
}
	var obj4={};
	for(var key in obj3){
		obj4[key]=obj3[key];
	}
obj4.firstName="Ankit";
console.log(obj3.firstName);
console.log(obj4.lastName);
console.log(obj4.age);
console.log(obj4.id);
}
