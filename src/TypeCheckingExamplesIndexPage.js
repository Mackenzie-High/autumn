

function emitAdvancedIndex(json)
{
	var index = json;
	
	var length = index.length;
	
	var header = "<tr> <td> <b>Construct</b> </td> <td> <b>Error Code</b> </td> <td> <b>Link to Example</b> </td> </tr>";
1
	$("#type-checking-examples-table").append(row);
	
	for(var i = 0; i < length; i++)
	{
		var entry = index[i];
		
		var test = entry["test"];
		
		var url = "TypeCheckingExamplePage.html?test-number=" + test;
		
		var construct = ast_link(entry["construct"]).replace("autumn.lang.compiler.ast.nodes.", "");
		
		var error = errorcode_link(entry["error-code"]);
		
		var link = "<a href='URL'>".replace("URL", url) + "Link to Example Page</a>";
		
		var row = "<tr> <td>" + construct + "</td> <td>" + error + "</td> <td>" + link + "</td> </tr>";

		$("#type-checking-examples-table").append(row);
	}
}

function main()
{
	var path = "out/index-of-type-checking-examples.json";
	
	$.getJSON(path, emitAdvancedIndex);
}