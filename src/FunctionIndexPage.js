

function emitAdvancedIndex(json)
{
	var index = json;
	
	var length = index.length;

	for(var i = 0; i < length; i++)
	{
		// Get the entry in from the index. 
		var entry = index[i];
		
		// Get the signature of the function.
		// Example: print (value : Object) : void
		var signature = entry[0];
		
		// This is the address of the page that describes the function. 
		var address = "http://www.mackenziehigh.me/autumn/FunctionPage.html?function=" + signature;
		
		// Create a link to the aforesaid page. 
		var link = '<a href="ADDRESS"> SIGNATURE </a>'
			.replace("ADDRESS", address)
			.replace("SIGNATURE", signature);

		// Add the link to the index being displayed in the browser. 
		$("#index").append("<li>" + link + "</li>");
	}
}

function main()
{
	var path = "out/index-of-functions.json";
	
	$.getJSON(path, emitAdvancedIndex);
}