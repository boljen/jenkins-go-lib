# Jenkins Go Workspace

## Setup

### Explicit

    @Library('github.com/boljen/jenkinsgoworkspace@master')
    def dummy

    node {
        gows.goWorkspace("go_1_8_0", "app") {
            // $GOPATH/src/my/project
            sh 'pwd'
        }
    }


### Implicit

STEP 1: Use the Go Plugin and create a new Go tool installation (lets asume you name it go_1_8_0)

STEP 2: add the repository to the Global Pipeline Library. Make sure to enable "load implicitly".

STEP 3:

    node {
        gows.goWorkspace("go_1_8_0", "my/project") {
            // $GOPATH/src/my/project
            sh 'pwd'
        } 
    }

## License

MIT