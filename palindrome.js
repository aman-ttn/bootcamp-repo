(
function(){
'use strict';
var st = window.prompt('Enter String.');
var l=st.length;
let i=0;
function palindrome(strn) {
	while(i<Math.floor(l/2)){
		if(strn[i]!==strn[l-1-i]){
		return false;
		}
	    return true;
	      }
	 
	}
var result=palindrome(st);
window.alert('The string is palindrome:'+result);
}
()
);
