var ERROR_CODE_JAVADOC = "javadoc/autumn/lang/compiler/errors/ErrorCode.html";

var test = null;

function emitSummary()
{
	var summary = page["description"];
	
	$("#construct-page-summary-text").append(summary);
}

function emitExample()
{
	$("#construct-page-example-code").empty();
	$("#construct-page-example-stdout").empty();
	
	var testno = "T" + zfill("" + test, 4);
	
	var code_url = "https://github.com/Mackenzie-High/autumn/tree/master/test/typechecks/" + testno;
	
	var stdout_url = "https://github.com/Mackenzie-High/autumn/tree/master/test/typechecks/stdout/" + testno;
	
	var code = $.get(code_url).responseText;
	
	var stdout = "";
	
	$("#construct-page-example-code").html(code);
	$("#construct-page-example-stdout").html(stdout);
}

function main()
{
	var params = getURLParameters();
	
	test = params["test-number"];
	
	document.title = "Example Type Check: " + test;
	emitSummary();;
	emitExample();
	
	$(function() { $( "#tabs" ).tabs(); });	
}