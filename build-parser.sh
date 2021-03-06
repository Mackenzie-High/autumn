# ~/bin/bash

# Remember to use the Snowflake GUI to "Generate Parser" before running this script, if changes were made to the grammar. 

#########################################################################################
# Create the AST node  structs.
#########################################################################################

# Remove the node files, but leave the package-info.java file.
find "src/autumn/lang/compiler/ast/nodes/" -type f ! -name "package-info.java" -exec rm {} +

cd "parser"

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





