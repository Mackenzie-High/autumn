var ERROR_CODE_JAVADOC = "javadoc/autumn/lang/compiler/errors/ErrorCode.html";

var test = null;

function emitSummary()
{
	var summary = page["description"];
	
	$("#construct-page-summary-text").append(summary);
}

function formatCode(code)
{
	code = replaceAll(code, " ", "&nbsp;");
	code = replaceAll(code, "\n", "<br>");
	
	return code;
}

function emitExample()
{
	$("#construct-page-example-code").empty();
	$("#construct-page-example-stdout").empty();
	
	var testno = "T" + zfill("" + test, 4);
	
	var code_url = "out/typechecks/" + testno + ".code";
	
	var stdout_url = "out/typechecks/" + testno + ".stdout";
	
	var code = formatCode(syncGet(code_url));
	
	var stdout = formatCode(syncGet(stdout_url));
	
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