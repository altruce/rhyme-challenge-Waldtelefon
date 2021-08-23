[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=5435561&assignment_repo_type=AssignmentRepo)

# Rhyme Challenge Template

<hr>

## Requirements

- Java
- Gradle

## Setup

Run `gradle dependencies` and `gradle run --args "TODO_ADD_INPUT_DATA_HERE"`

## Input Data

Words to replace are marked with `_`. The first word for the rhyme has to be detected.
Multiple rhymes are separated with two line breaks.

The non completed rhymes will be passed as a run argument. The following example can be used for local testing:
```
"Als ich aufsah\n war niemand mehr _.\n\nEs war, als hätt' der Himmel\n die Erde still geküsst,\nDass sie im _\nVon Ihm nun träumen _.\n\nDie Luft ging durch die Felder,\n Die Ähren wogten sacht,\n Es rauschten leis' die _\nSo sternklar war die _."
```

## Where to start

No resource file needs to be changed. Only the Main class needs to be modified and if necessary
additional classes can be created.
