 
## Bugs:
+ Bug: The command-line is not handling quoted string correctly at all!!!
+ Bug: The version numbers are not being embedded in the distros correctly. 
+ Bug: Annotation Usages should verify that the annotation-type is an Autumn-style annotation. 
+ Bug: $private$ on exception definitions construct page.
+ Bug: addSuppressed inherited by throwable is only available in Java 7.
+ Bug: Should import directives at least require that the type exists?
+ Bug: improve literal syntax, should big-decimal, float, double have to have a fracitonal part?
+ Bug: As and Is are missing some of the boxing conversions in their documentation. 
+ Bug: inferred return-type, when the inferred type is the null-type
+ Bug: void return-type for lambdas?
+ Document Tuple and Struct implements Tuple and Struct interfaces.
+ Bug: What happens when no module directive is present?
+ Bug: Duplicate Duplicate_Annotation static checks. see Module Directive.
+ Bug: see "&$8614;" on Page Inequality Operation, et al.
+ Bug: Example program output should scroll. See throw-statement Example #4
+ Bug: Should as/is conversions facilitate boxing to Object directly?
+ Bug: Static Checking of Numeric Literals when loss of information would occur.
+ Bug: Can float or double numeric literals lose information?
+ Bug: Do variable names, label names, etc every conflict with predefined names?
+ Bug: How do arithmetic operators handle null operands? - Add Documentation
+ Bug ?: Should auto boxing be allowed directly to serializable?
+ Bug: Chained method calls may not have embedded location information.
+ Bug ?: Should the pretty printer print structs before tuples?
+ Bug ?: Should structs be listed before tuples? 
+ Bug: Say something about the big-decimal scaling issues in the documentation. 
+ Should the AST printer handle string-literals better?
+ Remember to check that newlines are handled correctly on Windows, etc.
+ Should Parsing Failed errors be reported using ErrorReport objects?
+ Should ConstructList use identity equals?
+ Should AST nodes use identity equals.
+ Improve ConstructList.
+ Bug: Execution Test T0234?
+ Add a generalized error handler.

## Things Needed to Finish the Compiler Itself:
+ Make sure that the predefined methods in records generate bridges, when needed.
+ Remove Tuple Covariance - Inhibits Type Safety - Already Documented
+ Finish Tuple implementation and testing.  Documentation is done.
+ Finish Struct implementation and testing.  Documentation is done.
+ Finish Design implementation and testing.  Documentation is done.
+ Finish Functions documentation of special cases. 
+ Finish Modules implementation and testing.  Documentation is done.
+ Finish tests for numeric datums, including lowercase type-specifiers for numeric datums.
+ Finish Annotation-Usages
+ Finish Once
+ Finish Functor Implementation. Documentation is done.
+ Finish Delegate
+ Finish Lambda
+ Finish Documentation of Components
+ Create scoping documentation.
+ Finish Type-System Documentation
+ Look for todos.
+ Double Check the grammar for bugs! - locals, etc?
+ Add More Specific Type Checks for Unary and Binary Operators
+ Add more tests, as needed. 
+ Add more type-checking tests, as needed. 
+ Refactor Code 
+ Add More Comments

## Incomplete Functions - Many already partially work:
+ async
+ decode json
+ encode json
+ escape
+ is annotation type
+ is assignable to
+ is design type 
+ is enum type
+ is exception type
+ is functor type
+ is module type
+ is record type
+ is struct type
+ is tuple type
+ is subtype of
+ new proxy
+ printf
+ printerrf
+ range
+ readln
+ recall
+ remember
+ set - some forms only
+ sync
+ unescape
+ unique - needs an example
+ zip

## Current Improvement Proposals:
+ Add Limited Optimizer
+ Add support for primitives in Foreach loops.
+ Add support for primitives in List Comprehensions.
+ Delegates can refer to functions that are more specific than the functor. 
+ Should the ModuleInfo class contain the import-directives of the module?
+ Should records be serializable?
