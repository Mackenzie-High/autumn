# Date: May 31, 2015
# This script is for use by Mackenzie High only.  
# 
# This script builds the specification, the parser, and the compiler. 
# This script uploads the generated compiler binary to GDrive.
# This script DOES NOT push anything to GitHub. 
#



# This is the path to the master branch on Mackenzie's computer. 
MAIN="/media/disk/Code/NetBeansProjects/autumn"

# This is the path to the gh-pages branch on Mackenzie's computer. 
PAGES="/media/disk/Code/EclipseProjects/AutumnSpecification/autumn"





###################################################################################################
# Step: Generate the Parser and And Abstract-Syntax-Tree related classes.
#       In short, this generates the following files:
#       . All of the files in the autumn.lang.compiler.ast.nodes package. 
#       . high.mackenzie.autumn.lang.compiler.parser.Parser
#       . high.mackenzie.autumn.lang.compiler.parser.AbstractVisitor
#
#       The parser is generated from the parser/Grammar.snow file on the master branch. 
#
#       The AbstractVisitor is generated from the parser/autumn.structs file on the master branch. 
#       This relies upon a program, kanown as the AstGenerator, which only Mackenzie has. 
#
#       The AST nodes are generated from the parser/autumn.structs file on the master branch.
#       This relies upon a program, kanown as the StructGenerator, which only Mackenzie has. 
#
###################################################################################################

echo "Step: Generate Parser"

cd "$MAIN/parser"
bash "$MAIN/build-parser.sh"



###################################################################################################
# Step: Make a preliminary build of the compiler. 
#       This ensures that a compiler binary is avilable when the specification is generated.
#       However, the Specification Generator is used to generate some parts of the compiler.
#       In other words, the Specification Generator and the Compiler are tightly coupled. 
#       As a result, it will be necessary to rebuild the compiler later herein. 
#
###################################################################################################

echo "Step: Build Compiler (Preliminary)"

cd "$MAIN"
ant



###################################################################################################
# Step. Build the examples that are used on the website. 
###################################################################################################

echo "Step: Build Examples"

bash "$PAGES/build-examples.sh"



###################################################################################################
# Step: Build the Autumn Language Specification using the Specification Builder.
#       In short, this build the Autumn Website located at "www.MackenzieHigh.me/autumn/".
###################################################################################################

echo "Step: Generate Specification"

cd "$PAGES/specification/"
ant



###################################################################################################
# Step: Build the compiler again, for the final time.
###################################################################################################

echo "Step: Build Compiler (Final)"

cd "$MAIN"
ant



###################################################################################################
# Step: Copy the JavaDoc to the gh-pages branch from the master branch. 
###################################################################################################

echo "Step: Copy the JavaDoc from branch 'master' to branch 'gh-pages'."

# We cannot continue, if the new JavaDoc was not generated.
# The new JavaDoc must be generated manually, before this script is run.
if ! [ -a "$MAIN/dist/javadoc" ] ; then
    echo "Fatal Error: The JavaDoc could not be generated."
    exit 1;
fi

# Replace the old javadoc with the new javadoc.
rm -rf "$PAGES/javadoc/"
cp -r "$MAIN/dist/javadoc" "$PAGES/javadoc"



###################################################################################################
# Step: Build the distributable zip file. 
###################################################################################################

echo "Step: Build Distro"

# Get a new build number and update the build count.
read counter < "$MAIN/distro-resources/build-counter"
declare -i build=$counter+1
rm -f "$MAIN/distro-resources/build-counter"
echo $build > "$MAIN/distro-resources/build-counter"


# This is the name of the latest release.
DISTRO="autumn-dist-$build.zip"


# Add the notes folder to the distro.
mkdir -p "$MAIN/dist/notes"

# Add the license to the distro.
cp "$MAIN/distro-resources/LICENSE" "$MAIN/dist/LICENSE"

# Add the Autumn bash script to notes part of the distro. 
cp "$MAIN/distro-resources/autumn" "$MAIN/dist/notes/autumn"

# Add the installer to the distro.
cp "$MAIN/distro-resources/install-autumn-on-linux.sh" "$MAIN/dist/install-autumn-on-linux.sh"

# Remove the auto-generated read-me. 
rm -f "$MAIN/dist/README.TXT"

# Add the actual read-me to the distro. 
cp "$MAIN/distro-resources/README.pdf" "$MAIN/dist/README.pdf"

# Add a timestamp to the distro.
date > "$MAIN/dist/notes/timestamp"

# Embed the build number, which is used as the software version.
rm -f "$MAIN/src/high/mackenzie/autumn/resources/version.txt"
echo "$build" >> "$MAIN/src/high/mackenzie/autumn/resources/version.txt"

# Keep a copy of the release's source code for later reference in case needed.
zip -qr "$MAIN/backups/autumn-dist-$build-src.zip" "$MAIN/src" "$MAIN/test" "$MAIN/parser"

# Temporaily rename the "dist" folder.
mv "$MAIN/dist" "$MAIN/autumn-latest"

# Create the distro's zip file. 
zip -qr "$MAIN/releases/$DISTRO" "$MAIN/autumn-latest"

# Move the distro to my home folder, so I can upload it to GDrive.
cp "$MAIN/releases/$DISTRO" "$MAIN/autumn-latest.zip"

# Restore the name of the "dist" folder. 
mv "$MAIN/autumn-latest" "$MAIN/dist"



###################################################################################################
# Step: Upload the distro to the GDrive
###################################################################################################

echo "Step: Upload Distro"

# This is the ID of the folder that contains the builds on the GDrive. 
PARENT="0B2am-qoFTOsTfjhCd3lNMGc1cXdrZEw5WDFGZFVBaEpDbkxGM08xMGZVZkhFTVBNaHFpN2c"

"$MAIN/gdrive-uploader-for-linux-amd64" upload --file "$MAIN/releases/$DISTRO" --parent "$PARENT"


















