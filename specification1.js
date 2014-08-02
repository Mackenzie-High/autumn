
function spec()
{
	return { 
		"if-statement" : 
		{
			"summary" : "This is a summary.",
			"syntax" : 
			[
			 	[0, "if (condition)"],
			    [0, "{"],
			    [1, "println"],
			    [0, "}"],
			],
			
			"details" : 
			[
			 	[0, "A"],
			 	[1, "B"],
			 	[2, "C"],
			 	[3, "D"],
			 	[4, "E"],
			 	[0, "Z"],
			],
			    
			"static-checks" :
			[
			 	["NO_SUCH_FIELD", "A field could not be resolved."],
			 	["NO_SUCH_METHOD", "A method could not be resolved."],
			],
			
			"examples" :
			[
			 	["leaf", "stdout"],
			 	["leaf", "stdout"],
			]
		}
	};
}