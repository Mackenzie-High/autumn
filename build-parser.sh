# ~/bin/bash

cd "parser"

#########################################################################################
# Create the AST node  structs.
#########################################################################################

rm -r "/media/disk/Code/NetBeansProjects/AutumnCompiler/src/autumn/lang/compiler/ast/nodes/" 

bash "/media/disk/bin/generate-struct" "autumn.structs"


#########################################################################################
# Create AstBuilder.java
#########################################################################################

unzip -o "Grammar.snow" 

rm "Grammar.txt"
rm "Input.txt"

cd ".."

mv "parser/Parser.java" "src/high/mackenzie/autumn/lang/compiler/parser/Parser.java"
mv "parser/Visitor.java" "src/high/mackenzie/autumn/lang/compiler/parser/AbstractVisitor.java"

bash "/media/disk/bin/generate-ast" "parser/AstVisitor.ast"

#########################################################################################

echo "Finished!"





