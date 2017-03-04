# Manual

STEP 1: Install the Jenkins Go Plugin (https://wiki.jenkins-ci.org/display/JENKINS/Go+Plugin).

STEP 2: Install the preferred Go version:

 1. Go to "Manage Jenkins"
 2. Go to "Global Tool Configuration"
 3. Scroll to "Go"
 4. Add a new Go revision (probably installed from golang.net) and give it a name, e.g. "go_1_8_0". Make sure to use a directory safe name (see step 3 & 4).

STEP 3: Insert the following snipped at the top of your Jenkinsfile.

    def goWorkspace(go_tool_name, name, body) {
        def goroot = tool name: "${go_tool_name}", type: 'go'
        withEnv([
            "GOROOT=${goroot}",
            "PATH+GO=${goroot}/bin",
            "PATH+WSBIN=${WORKSPACE}/${go_tool_name}/bin",
            "GOPATH=${WORKSPACE}/${go_tool_name}"
        ]) {
            dir("${go_tool_name}/src/${name}") {
                body()
            }
        }
    }



STEP 4: Build your actual pipeline

    node {
        goWorkspace("go_1_8_0", "your/project") {
            // current directory is now $GOPATH/go_1_8_0/src/your/project
            stage("Checkout") { ... }
        }
    }

I'll update this once I figured out how to turn this into a library. Feel free to pull request.
