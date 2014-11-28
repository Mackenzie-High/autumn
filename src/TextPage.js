

function load_page(json)
{
	text = decodeURIComponent(json);
	
	$("#inner-body").append(text);;
}

function main()
{
	var params = getURLParameters();
	
	name = params["page"];
	
	path = "out/" + name + ".json";
	
	document.title = name;
	
	$.getJSON(path, load_page);
}