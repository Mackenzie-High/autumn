function emitAdvancedIndex(json)
{
	var index = json;
	
	var length = index.length;
	
	for(var i = 0; i < length; i++)
	{
		var construct = "Hello";
		
		var error = "Moo";
		
		var link = "Boo";
		
		var description = "Zoo";
		
		var row = "<tr> <td>" + construct + "</td> <td>" + error + "</td> <td>" + link + "</td> <td>" + description + "</td> </tr>";

		$("#type-checking-examples-table").append(row);
	}
}

function main()
{
	var path = "out/index.json";
	
	$.getJSON(path, emitAdvancedIndex);
}