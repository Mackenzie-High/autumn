

function main()
{
	var params = getURLParameters();
	
	var name = params["page"];
	
	document.title = name;
	
	var text = spec()[name];
	
	text = decodeURIComponent(text);
	
	// text = replaceAll(text, "%22", '"');
	
	
	$("#inner-body").append(text);;
}