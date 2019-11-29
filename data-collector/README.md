# Data collector

### What is this?

Given a list of coordinates (GAVs) of Maven artifacts, `depclean-experiments` allows to download their `.pom` and  `.jar` files from the Maven Central repository and resolve all their dependencies to a customized local repository in order to collect information about bloated dependencies, i.e. dependencies that are in the classpath of the project but are not used.

### How it works?

`depclean-experiments` relies on the `maven-dependency` set of tools and plugins to resolve dependencies and validate the project. It extends the `maven-dependency-analyzer` plugin in order to analyze dependency usages at the bytecode level. By doing this, it takes into account both direct and transitive dependencies, as well as projects that belong to multi-module project. 

# Getting Started

## Executing from a Docker container

### Prerequisites

- [Docker](https://www.docker.com/)

### Usage

Pull the docker image from DockeHub:

```bash
docker pull cesarsotovalero/jdbl-pom-exp
```
Then run the container:

```bash
docker run -d --name jdbl-pom -v [dir to local file]:/xperiments cesarsotovalero/jdbl-pom-exp
```

## Installing and building from source code

### Prerequisites

- [Java JDK 8](https://openjdk.java.net) or above
- [Apache Maven](https://maven.apache.org)

### Usage

In a terminal clone the repository and switch to the cloned folder:

```bash
git clone https://github.com/castor-software/depclean-experiments.git
cd depclean-experiments
```

Then run the following Maven command to build the application:

```bash
mvn clean package
```
Once the application is built, execute it locally using the following command:

```bash
java -jar depclean-experiments-0.1.0-SNAPSHOT-jar-with-dependencies.jar -help
```





