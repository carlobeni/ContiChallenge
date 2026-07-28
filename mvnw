#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Maven Start Up Batch script
#
# Required ENV vars:
# ------------------
#   JAVA_HOME - location of a JDK home dir
#
# Optional ENV vars
# -----------------
#   MAVEN_OPTS - parameters passed to the Java VM when running Maven
#     e.g. to debug Maven itself, use
#       set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
#   MAVEN_SKIP_RC - flag to disable loading of mavenrc files
# ----------------------------------------------------------------------------

if [ -z "$MAVEN_SKIP_RC" ]; then
  if [ -f /etc/mavenrc ]; then
    . /etc/mavenrc
  fi
  if [ -f "$HOME/.mavenrc" ]; then
    . "$HOME/.mavenrc"
  fi
fi

# OS specific support. $var _must_ be set to either true or false.
cygwin=false
msys=false
darwin=false
mingw=false
case "`uname`" in
  CYGWIN* ) cygwin=true ;;
  MINGW* ) msys=true; mingw=true ;;
  Darwin* ) darwin=true ;;
esac

if [ -z "$JAVA_HOME" ]; then
  if [ -x "/usr/libexec/java_home" ]; then
    JAVA_HOME="$(/usr/libexec/java_home)"
  else
    JAVA_HOME="$(java -XshowSettings:properties -version 2>&1 > /dev/null | grep 'java.home' | awk '{print $3}')"
  fi
fi

if [ -z "$JAVA_HOME" ]; then
  echo "Error: JAVA_HOME is not set and no 'java' command could be found in your PATH." >&2
  echo "Please set the JAVA_HOME variable in your environment to match the" >&2
  echo "location of your Java installation." >&2
  exit 1
fi

if [ -z "$MAVEN_HOME" ]; then
  # find MAVEN_HOME from maven-wrapper.jar
  MAVEN_HOME="$(dirname "$0")/.mvn/wrapper"
fi

# For Cygwin, ensure paths are in UNIX format before anything is touched
if $cygwin ; then
  [ -n "$JAVA_HOME" ] && JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
  [ -n "$CLASSPATH" ] && CLASSPATH=`cygpath --path --unix "$CLASSPATH"`
fi

# For Migwn, ensure paths are in UNIX format before anything is touched
if $mingw ; then
  [ -n "$JAVA_HOME" ] && JAVA_HOME=`(cd "$JAVA_HOME"; pwd)`
fi

# For Darwin, use classes.jar for TOOLS_JAR
TOOLS_JAR="$JAVA_HOME/lib/tools.jar"
if $darwin; then
  TOOLS_JAR="/Classes/classes.jar"
fi

if [ -r "$MAVEN_HOME/maven-wrapper.jar" ]; then
  WRAPPER_JAR="$MAVEN_HOME/maven-wrapper.jar"
else
  echo "Error: Could not find maven-wrapper.jar in $MAVEN_HOME" >&2
  exit 1
fi

# For Cygwin, switch paths to Windows format before running java
if $cygwin ; then
  JAVA_HOME=`cygpath --path --windows "$JAVA_HOME"`
  WRAPPER_JAR=`cygpath --path --windows "$WRAPPER_JAR"`
  TOOLS_JAR=`cygpath --path --windows "$TOOLS_JAR"`
fi

exec "$JAVA_HOME/bin/java" \
  $MAVEN_OPTS \
  -classpath "$WRAPPER_JAR" \
  "-Dmaven.home=${MAVEN_HOME}" \
  "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECTBASEDIR}" \
  org.apache.maven.wrapper.MavenWrapperMain \
  "$@"
