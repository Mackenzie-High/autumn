function emitAdvancedIndex(json)
{
	var index = json;
	
	var length = index.length;
	
	for(var i = 0; i < length; i++)
	{
		var entry = index[i];
		
		var test = entry["test"];
		
		var url = "TypeCheckingExamplePage.html";
		
		var construct = entry["construct"];
		
		var error = entry["error-code"];
		
		var link = "<a href='URL'>".replace("URL", url) + "Link to Example Page</a>";
		
		var description = "Zoo";
		
		var row = "<tr> <td>" + construct + "</td> <td>" + error + "</td> <td>" + link + "</td> <td>" + description + "</td> </tr>";

		$("#type-checking-examples-table").append(row);
	}
}

function main()
{
	var path = "out/index-of-type-checking-examples.json";
	
	$.getJSON(path, emitAdvancedIndex);
}