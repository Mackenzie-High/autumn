var ERROR_CODE_JAVADOC = "javadoc/autumn/lang/compiler/errors/ErrorCode.html";

var name = null;

var page = null;

function repeatString(string, count)
{
	var result = "";
	
	var i = 0;
	
	while(i < count)
	{
		result = result + string;
		i = i + 1;
	}
	
	return result;
}

function emitSummary()
{
	var summary = page["description"];
	
	$("#construct-page-summary-text").append(summary);
}

function emitExample(index)
{
	$("#construct-page-example-code").empty();
	$("#construct-page-example-stdout").empty();
	
	var example = "Hello";
	
	return;
	
	var code = decodeURIComponent(example[0]);
	code = replaceAll(code, "\n", "<br>");
	code = replaceAll(code, " ", "&nbsp;");
	
	var stdout = decodeURIComponent(example[1]);
	stdout = replaceAll(stdout, "\n", "<br>");
	
	$("#construct-page-example-code").html(code);
	$("#construct-page-example-stdout").html(stdout);
}

function load_page(json)
{
	page = json;
	
	document.title = name;
	emitName(name);
	emitSummary();;
	emitExample();
	
	$(function() { $( "#tabs" ).tabs(); });
}

function main()
{
	var params = getURLParameters();
	
	name = params["construct"];
	
	path = "out/" + name + ".json";
	
	$.getJSON(path, load_page);
}