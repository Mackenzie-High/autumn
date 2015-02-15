#!/bin/bash

###############################################################################
# Copyright 2015 Michael Mackenzie High
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
#
#
###############################################################################

# Author: Mackenzie High
# Date: Feb-14-2015

# This is the Google Drive ID of the latest release of Autumn. 
ID="0B2am-qoFTOsTTzRXT1pfZFRjYVU"



echo "Step 1: Download the latest version of Autumn."

# We need some place to store the download. 
mkdir -p "/tmp/autumn/"

# Download the latest version of Autumn. 
curl -o "/tmp/autumn/autumn-latest" "https://drive.google.com/uc?export=download&id=$ID"

# Extract the contents of the downloaded ZIP file. 
unzip "/tmp/autumn/autumn-latest.zip"



echo "Step 2: Install Autumn"

cp -rf "/tmp/autumn/dist/autumn.jar" "/usr/local/lib/autumn/autumn.jar"
