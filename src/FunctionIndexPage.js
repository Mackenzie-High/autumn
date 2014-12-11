

function emitAdvancedIndex(json)
{
	var index = json;
	
	var length = index.length;

	for(var i = 0; i < length; i++)
	{
		var entry = index[i];

		$("#type-checking-examples-table").append("<li>" + entry + "</li>");
	}
}

function main()
{
	var path = "out/index-of-functions.json";
	
	$.getJSON(path, emitAdvancedIndex);
}