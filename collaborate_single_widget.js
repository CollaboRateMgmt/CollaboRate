/*
	This is part of the CollaboRate project at https://github.com/CollaboRateMgmt/CollaboRate
	Author: Crowdian Technologies Pvt. Ltd 2017-18.
	License type: MIT
*/

//callbackfn is a callback function that accepts a single parameter that is the 
//fetched string
function httpGet(theUrl,callbackfn)
{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			callbackfn(xmlhttp.responseText);
		}
	}
	xmlhttp.open("GET", theUrl, false );
	xmlhttp.send();    
}

function leftof(s,delim)
{
	var i = s.indexOf(delim)
	if(i==-1){throw "delim '"+delim+"' not in '"+s+"'!!!"}
	return s.substring(0,i)
}

function rightof(s,delim)
{
	var i = s.indexOf(delim)
	if(i==-1){throw "delim '"+delim+"' not in '"+s+"'!!!"}
	return s.substring(i+delim.length)
}

function onwidgetload(txt)
{			
	var s = rightof(txt,"<!--widget start"+"-"+"->")		
	s = leftof(s,"</body>")			
	document.write(s)
}


/**
	Usage: Put the following somewhere in your html 
	(replace <url_to_widget_page.html> with the appropriate url):
	
	<script>			
		loadwidget("<url_to_widget_page.html>")
	</script>
*/
function loadwidget(widgeturl)
{
	httpGet(widgeturl,onwidgetload)
}