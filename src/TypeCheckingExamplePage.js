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
	
	var code_url = "out/typechecks/" + testno + ".code";
	
	var stdout_url = "out/typechecks/" + testno + ".stdout";
	
	var code = $.get(code_url).responseText;
	
	var stdout = $.get(stdout_url).responseText;
	
	$("#construct-page-example-code").html(code);
	$("#construct-page-example-stdout").html(stdout);
}

function main()
{
	var params = getURLParameters();
	
	test = params["test-number"];
	
	document.title = "Example Type Check: " + test;
	//emitSummary();;
	emitExample();
	
	$(function() { $( "#tabs" ).tabs(); });	
}