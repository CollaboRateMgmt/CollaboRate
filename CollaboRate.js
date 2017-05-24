/*
Project website: https://github.com/CollaboRateMgmt/CollaboRate	
Copyright Sohrab Saran (sohrabsaran@gmail.com) and additional contributors listed at https://github.com/CollaboRateMgmt/CollaboRate/wiki/Contributors
License type: MIT	
*/

//shortcut for document.getElementById or for ($("#"+eleid)[0]))
function el(eleid){return document.getElementById(eleid)}

function leftOf(s,delim)
{
    var i = s.indexOf(delim)
    if(i==-1){throw "delim '"+delim+"' not in '"+s+"'!!!"}
    return s.substring(0,i)
}

function rightOf(s,delim)
{
    var i = s.indexOf(delim)
    if(i==-1){throw "delim '"+delim+"' not in '"+s+"'!!!"}
    return s.substring(i+delim.length)
}