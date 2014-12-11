
var HEADER = "<tr> <td> Group </td> <td> Function </td> </tr>";

function emitAdvancedIndex(json)
{
	var index = json;
	
	var length = index.length;

	// Add the header to the table. 
	$("#index").append(HEADER);
	
	// Add the entries to the table. 
	for(var i = 0; i < length; i++)
	{
		// Get the entry in from the index. 
		var entry = index[i];
		
		// Get the signature of the function.
		// Example: print (value : Object) : void
		var signature = entry[0];
		
		// Obtain the name of the group that the function belongs to. 
		var group = entry[1];
		
		// This is the address of the page that describes the function. 
		var address = "http://www.mackenziehigh.me/autumn/FunctionPage.html?function=" + signature;
		
		// Create a link to the aforesaid page. 
		var link = '<a href="ADDRESS"> SIGNATURE </a>'
			.replace("ADDRESS", address)
			.replace("SIGNATURE", signature);

		// Create a row representing the function in the index table. 
		var row = HEADER
			.replace("Group", group)
			.replace("Function", link);
		
		// Add the link to the index being displayed in the browser. 
		$("#index").append(row);
	}
}

function main()
{
	var path = "out/index-of-functions.json";
	
	$.getJSON(path, emitAdvancedIndex);
}