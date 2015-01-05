
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
	var summary = page["summary"];
	
	$("#function-page-summary-text").append(summary);
}

function emitSignature(name)
{
	$("#construct-page-syntax-content").append(name);
}

function emitFormals()
{
	var formals = page["formals"];
	
	var length = formals.length;
	
	for(var i = 0; i < length; i++)
	{
		var formal = "Parameter <i>" + formals[i][0] + "</i> " + formals[i][2];
		
		$("#function-page-formals-list").append('<li>' + formal + '</li>');
	}
}

function emitReturns()
{
	var about = page["returns"][1];
	
	$("#function-page-returns").append(about);
}

function emitThrows()
{
	var problems = page["throws"];
	
	var length = problems.length;
	
	for(var i = 0; i < length; i++)
	{
		var problem = problems[i][0] + ", " + problems[i][1];
		
		$("#function-page-throws-list").append('<li>' + problem + '</li>');
	}
	
	/**
	 * Remove the section, if it is empty. 
	 */
	if(problems == undefined)
	{
		$("#function-page-throws").remove();
	}
}

function emitDetails()
{
	var details = page["details"];
	
	var length = details.length;
	
	for(var i = 0; i < length; i++)
	{
		var indent = details[i][0];
		
		var detail = details[i][1];
		
		$("#function-page-details-list").append('<li class="indent-' + indent + '">' + detail + '</li>');
	}
	
	/**
	 * Remove the section, if it is empty. 
	 */
	if(details == undefined)
	{
		$("#function-page-details").remove();
	}
}

function emitAstClass()
{
	var klass = page["ast"];
	
	var link = ast_link(klass);
	
	$("#construct-page-ast").append(link);
}

function displayExample(index)
{
	$("#construct-page-example-code").empty();
	$("#construct-page-example-stdout").empty();
	
	$("#example-button-1").css('border-width', "0px");
	$("#example-button-2").css('border-width', "0px");
	$("#example-button-3").css('border-width', "0px");
	$("#example-button-4").css('border-width', "0px");
	$("#example-button-5").css('border-width', "0px");
	
	$("#example-button-1").css('color', "#666666");
	$("#example-button-2").css('color', "#666666");
	$("#example-button-3").css('color', "#666666");
	$("#example-button-4").css('color', "#666666");
	$("#example-button-5").css('color', "#666666");
	
	$("#example-button-" + index).css('border-color', "#FF0099");
	$("#example-button-" + index).css('border-width', "2px");
	
	var key = "example-" + index;

	if(key in page == false)
	{
		return;
	}
	
	var example = page[key];
	
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
	
	var signature = json["signature"]; 

	emitSummary();
	emitSignature(signature);
	emitFormals();
	emitReturns();
	emitThrows();
	emitDetails();
	
	displayExample(1);
	
	$(function() { $( "#tabs" ).tabs(); });
}

function main()
{
	var params = getURLParameters();
	
	name = params["function"];
	
	path = "out/functions/" + name + ".json";
	
	$.getJSON(path, load_page);
}