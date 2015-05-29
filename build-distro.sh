
# Get a new build number and update the build count.
read counter < "distro-resources/build-counter"
declare -i build=$counter+1
rm -f "distro-resources/build-counter"
echo $build > "distro-resources/build-counter"



# Move the Javadoc to the specification folder. 
rm -rf "/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/javadoc/"
cp -rf "dist/javadoc/" "/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/"


# Add the notes folder to the distro.
mkdir -p "dist/notes"

# Add the license to the distro.
cp "distro-resources/LICENSE" "dist/LICENSE"

# Add the Autumn bash script to notes part of the distro. 
cp "distro-resources/autumn" "dist/notes/autumn"

# Add the installer to the distro.
cp "distro-resources/install-autumn-on-linux.sh" "dist/install-autumn-on-linux.sh"

# Remove the auto-generated read-me. 
rm -f "dist/README.TXT"

# Add the actual read-me to the distro. 
cp "distro-resources/README.pdf" "dist/README.pdf"



# Remove any Javadoc. 
rm -f "dist/javadoc"



# Add a timestamp to the distro.
date > "dist/notes/timestamp"



# Embed the build number, which is used as the software version.
rm -f "src/high/mackenzie/autumn/resources/version.txt"
echo "$build" >> "src/high/mackenzie/autumn/resources/version.txt"



# Keep a copy of the release's source code for later reference in case needed.
zip -r "backups/autumn-dist-$build-src.zip" "src" "test" "parser"



# Temporaily rename the "dist" folder.
mv "dist" "autumn-latest"



# Create the distro's zip file. 
zip -r "releases/autumn-dist-$build.zip" "autumn-latest"



# Move the distro to my home folder, so I can upload it to GDrive.
cp "releases/autumn-dist-$build.zip" "autumn-latest.zip"



# Restore the name of the "dist" folder. 
mv "autumn-latest" "dist"









