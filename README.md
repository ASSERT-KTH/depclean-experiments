# DepClean experiments

This is an open-science repository with the necessary data and tooling to replicate our large-scale study of bloated dependencies in the Maven. The data collection is based on our tool: [DepClean](https://castor-software.github.io/depclean/).

## Materials

The following sections describe the main materials included in this repository.

### Data Collector

The [data-collector](https://github.com/castor-software/depclean-experiments/tree/master/data-collector) tool mines the Maven Central repository to obtain usage information about artifacts and their dependencies. The analysis relies on fine-grained static bytecode analysis with [DepClean](https://github.com/castor-software/royal-debloat/depclean). The tool also uses dedicated Maven plugins to collect data about the projects' architecture and their dependencies.     

### Notebooks

The [nootebooks](https://github.com/castor-software/depclean-experiments/tree/master/notebooks) folder contains a set of R scripts supporting the analysis of the data collected with the [data-collector](https://github.com/castor-software/depclean-experiments/tree/master/data-collector) tool.

### Contributions

The [contributions](https://github.com/castor-software/depclean-experiments/tree/master/contributions) folder contains a list of bloated dependencies in open-source projects that have been found with the help of [Depclean](https://github.com/castor-software/royal-debloat/depclean). These bloated dependencies have been directly communicated to the developer teams through pull requests on GitHub. It contains links to the original code repositories and the actions taken by the developers.

## License

Distributed under the MIT License. See [LICENSE](https://github.com/castor-software/depclean-experiments/blob/master/LICENSE) for more information.
