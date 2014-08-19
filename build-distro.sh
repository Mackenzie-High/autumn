
# Get a new build number and update the build count.
read counter < "distro-resources/build-counter"
declare -i build=$counter+1
rm -f "distro-resources/build-counter"
echo $build > "distro-resources/build-counter"



# Add the license to the distro.
cp "distro-resources/LICENSE" "dist/LICENSE"



# Remove the auto-generated read-me. 
rm -f "dist/README.TXT"

# Add the actual read-me to the distro. 
cp "distro-resources/README.pdf" "dist/README.pdf"



# Add a timestamp to the distro.
mkdir -p "dist/notes"
date > "dist/notes/timestamp"



# Add the "Hello World" example.
cp "distro-resources/example-hello.leaf" "dist/notes/example-hello.leaf"



# Keep a copy of the release's source code for later reference in case needed.
zip -r "backups/autumn-dist-$build-src.zip" "src" "test" "parser"



# Create the distro's zip file. 
zip -r "releases/autumn-dist-$build.zip" "dist"
