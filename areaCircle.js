(
function(){
'use strict';
var p = parseFloat(window.prompt('Enter the radius.'));
window.alert('The area of circle is:'+area(p));
function area(radius) {
	return (radius*radius*3.14);
} 

}
()
);
