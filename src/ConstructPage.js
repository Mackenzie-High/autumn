
var ERROR_CODE_JAVADOC = "javadoc/autumn/lang/compiler/errors/ErrorCode.html";

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

function emitName(name)
{
	$("#construct-page-name").append('<h1>' + name + '</h1>');
}

function emitSummary()
{
	var summary = page["summary"];
	
	$("#construct-page-summary-text").append(summary);
}

function emitSyntax()
{
	var syntax = page["syntax"];
	
	var length = syntax.length;
	
	var text = "";
	
	for(var i = 0; i < length; i++)
	{
		var line = decodeURIComponent(syntax[i][1]);
		
		text = text + repeatString("&nbsp;", syntax[i][0] * 4) + line + "<br>";
	}
	
	$("#construct-page-syntax-content").append(text);
}

function emitDetails()
{
	var details = page["details"];
	
	var length = details.length;
	
	for(var i = 0; i < length; i++)
	{
		var indent = details[i][0];
		
		var detail = details[i][1];
		
		$("#construct-page-details").append('<li class="indent-' + indent + '">' + detail + '</li>');
	}
}

function emitStaticChecks()
{
	var checks = page["static-checks"];
	
	var length = checks.length;
	
	for(var i = 0; i < length; i++)
	{
		var key = checks[i][0];
		
		var link = '<a href="HREF">' + key + '</a>';
		
		link = link.replace("HREF", ERROR_CODE_JAVADOC + '#' + key);
		
		var value = checks[i][1];
		
		var html = '<li>' + link + ': <br> <p class="indent-0">' + value + '</p> </li>';
		
		$("#construct-page-checks-list").append(html);
	}
}

function emitAstClass()
{
	var klass = page["ast"];
	
	var href = 'javadoc/' + replaceAll(klass, ".", "/") + ".html";

	var link = '<a href="HREF"> ' + klass + '</a>';
	
	link = link.replace("HREF", href);
	
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



function main()
{
	var params = getURLParameters();
	
	var name = params["construct"];
	
	page = spec()[name];
	
	document.title = name;
	
	emitName(name);
	emitSummary();
	emitSyntax();
	emitAstClass();
	emitDetails();
	emitStaticChecks();
	
	displayExample(1);
	
	$(function() { $( "#tabs" ).tabs(); });
}










