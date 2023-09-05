

function load_page(json)
{
	text = decodeURIComponent(json);
	
	$("#inner-body").append(text);;
}

function main()
{
	var params = getURLParameters();
	
	name = params["page"];
	
	path = "out/" + name + ".html";
	
	document.title = name;
	
	$.get(path, load_page);
}