'use strict';
simpleInterest();
function simpleInterest() {
var p = parseFloat(window.prompt('Enter Principal.'));
var r = parseFloat(window.prompt('Enter Rate.'));
var t = parseFloat(window.prompt('Enter Time.'));
var interest=(p*r*t)/100;
 window.alert('The simple interest is:'+interest);
}


