# Depclean experiments

This is an open-science repository with the necessary data to replicate our large-scale analysis of dependency usages in the Maven Central ecosystem using [Depclean](https://github.com/castor-software/royal-debloat/depclean).

## Materials

The following sections describe the main materials included in this repository.

### Data Collector

The [data-collector](https://github.com/castor-software/depclean-experiments/tree/master/data-collector) tool mine the Maven Central repository to obtain usage information between artifacts and their dependencies. The analysis relies on fine-grained static bytecode analysis with [Depclean](https://github.com/castor-software/royal-debloat/depclean), and the usage of dedicated Maven plugins to collect data about the projects' structure and their dependencies.     

### Notebooks

The [nootebooks](https://github.com/castor-software/depclean-experiments/tree/master/notebooks) folder contains a set of R scripts supporting the analysis of the data collected with the [data-collector](https://github.com/castor-software/depclean-experiments/tree/master/data-collector) tool.

### Contributions

The [contributions](https://github.com/castor-software/depclean-experiments/tree/master/contributions) folder contains a list of bloated dependencies that have been found with the help of [Depclean](https://github.com/castor-software/royal-debloat/depclean) and that have been directly communicated to the developer teams through pull requests. It contains links to the original code repositories and the actions taken by the developers.

## License

Distributed under the MIT License. See [LICENSE](https://github.com/castor-software/depclean-experiments/blob/master/LICENSE) for more information.
