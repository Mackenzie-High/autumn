

function emitAdvancedIndex(json)
{
	var index = json;
	
	var length = index.length;

	for(var i = 0; i < length; i++)
	{
		var entry = index[i];
		
		var signature = entry[0];
		
		var page = "FunctionPage.html?function=" + signature;
		
		var link = '<a href="FILE"> SIGNATURE </a>'
			.replace("FILE", file)
			.replace("SIGNATURE", signature);

		$("#index").append("<li>" + link + "</li>");
	}
}

function main()
{
	var path = "out/index-of-functions.json";
	
	$.getJSON(path, emitAdvancedIndex);
}