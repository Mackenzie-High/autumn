

function emitBasicIndex()
{
	var s = spec();
	
	var keys = Object.keys(s);
	
	var length = keys.length;

	for(var i = 0; i < length; i++)
	{
		var key = keys[i];
		
		var href = "ConstructPage.html?construct=" + key;
		
		var link = '<a href="HREF">' + key + '</a>';
		
		link = link.replace("HREF", href);
		
		var li = '<li>' + link + '</li>';
		
		$("#index-page-list").append(li);
	}
}

function emitAdvancedIndex()
{
	var index = spec()["index"];
	
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
	// emitBasicIndex();
	
	emitAdvancedIndex();
}