# Difference Calculator

A lightweight console tool that analyzes and displays the differences between two files in different output formats (stylish, plain, json).

### Running the tool:

If the distribution is not built yet, run:

```bash
make -C app install-dist
```

Then execute:

```bash
make -C app run-dist ARGS="-f json file1.json file2.json"
```

### Hexlet tests and linter status:
[![Actions Status](https://github.com/mtvru/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/mtvru/java-project-71/actions)

[![SonarQube](https://github.com/mtvru/java-project-71/actions/workflows/build.yml/badge.svg)](https://github.com/mtvru/java-project-71/actions/workflows/build.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=mtvru_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=mtvru_java-project-71)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=mtvru_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=mtvru_java-project-71)

### Short video demo of the app:

**JSON files difference:**
[View demo on Asciinema](https://asciinema.org/a/G6h4yccob2LKEuyALsX8Qnr28)

**YAML files difference:**
[View demo on Asciinema](https://asciinema.org/a/WqQ1iCFMcvGqoDrnUnfwTferq)

**JSON files difference with nested nodes:**
[View demo on Asciinema](https://asciinema.org/a/WPiIcqdHN9luIPUS1kOwKyFim)

**JSON files difference with plain formatter:**
[View demo on Asciinema](https://asciinema.org/a/5pNkl5WvUXF1FuKySdJM0BRzX)

**JSON files difference with json formatter:**
[View demo on Asciinema](https://asciinema.org/a/tqWzjiEXD3NvPTKahfVfD7vPy)
