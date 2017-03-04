#!/usr/bin/env groovy

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

return this;