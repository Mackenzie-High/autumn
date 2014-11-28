
var ERROR_CODE_JAVADOC = "javadoc/autumn/lang/compiler/errors/ErrorCode.html";



/**
 * This function randomly generates a boolean value. 
 * 
 * @returns {Boolean} a random boolean.
 */
function randomBoolean()
{
	return Math.random() >= 0.5;
}

function randomIndex(maximum)
{
	return maximum > 1 ? Math.abs(Math.round(Math.random() * maximum - 1)) : 0;
}

function replaceAll(string, original, replacement)
{
	while(string.indexOf(original) >= 0)
	{
		string = string.replace(original, replacement);
	}
	
	return string;
}

function getURLParameters() 
{
	// The parameters section of a URL starts at the first '?' character. 
	// Parameters are separated by '&' characters.
	// Each parameter is a key-value pair.
	// Example: www.example.com?name=Erin&gender=female
	
	var map = { };
	
	var url = document.URL;
	
	var parts = url.split("?");
	
	if(parts.length < 2)
	{
		return map; // empty
	}
	
	pairs = parts[1].split("&");
	
	for(var i = 0; i < pairs.length; i++)
	{
		parts = pairs[i].split("=");
		
		key = parts[0];
		value = parts.length > 1 ? parts[1] : null;
		
		map[key] = ("" + decodeURIComponent("" + value)).trim();
	}
	
	return map;
}

function ast_link(klass)
{
	var href = 'javadoc/' + replaceAll(klass, ".", "/") + ".html";

	var link = '<a href="HREF"> ' + klass + '</a>';
	
	link = link.replace("HREF", href);
	
	return link;
}

function errorcode_link(key)
{
	var link = '<a href="HREF">' + key + '</a>';
	
	link = link.replace("HREF", ERROR_CODE_JAVADOC + '#' + key);
	
	return link;
}