
function emitAdvancedIndex(json)
{
	var length = index.length;
	
	for(var i = 0; i < length; i++)
	{
		var link = decodeURIComponent(index[i][1]);
		
		var klass = "indent-" + index[i][0];
		
		var li = '<li class=\"' + klass + '\">' + link + '</li>';
		
		$("#index-page-list").append(li);
	}
}

function main()
{
	var path = "out/index.json";
	
	$.getJSON(path, emitAdvancedIndex);
}