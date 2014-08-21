
# Get a new build number and update the build count.
read counter < "distro-resources/build-counter"
declare -i build=$counter+1
rm -f "distro-resources/build-counter"
echo $build > "distro-resources/build-counter"



# Move the Javadoc to the specification folder. 
rm -f "/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/javadoc/"
mv "dist/javadoc/" "/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/"




# Add the license to the distro.
cp "distro-resources/LICENSE" "dist/LICENSE"



# Remove the auto-generated read-me. 
rm -f "dist/README.TXT"

# Add the actual read-me to the distro. 
cp "distro-resources/README.pdf" "dist/README.pdf"



# Add a timestamp to the distro.
mkdir -p "dist/notes"
date > "dist/notes/timestamp"



# Embed the build number, which is used as the software version.
rm -f "src/high/mackenzie/autumn/resources/version.txt"
echo "$build" >> "src/high/mackenzie/autumn/resources/version.txt"



# Add the "Hello World" example.
cp "distro-resources/example-hello.leaf" "dist/notes/example-hello.leaf"



# Keep a copy of the release's source code for later reference in case needed.
zip -r "backups/autumn-dist-$build-src.zip" "src" "test" "parser"



# Create the distro's zip file. 
zip -r "releases/autumn-dist-$build.zip" "dist"



# Move the distro to my home folder, so I can upload it to GDrive.
cp "releases/autumn-dist-$build.zip" "/home/mackenzie/autumn-latest.zip"

