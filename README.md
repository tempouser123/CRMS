# CRMS - Campus Resource Management System

A project for managing campus resources, including student and teacher information, built using Java.

## Description

This project is a **Campus Resource Management System (CRMS)** designed to streamline the administration of a university or college campus. It provides functionality for managing the different entities within a campus, such as students and faculty.

The system is built using an object-oriented approach in Java. A key design feature is the use of a base `Person` class, which is extended by the `Student` and `Teacher` classes to promote code reuse and maintain a clean structure for common attributes (like name, ID, contact info).



## Features

* **Student Management:** Add, view, update, and remove student records.
* **Teacher Management:** Add, view, update, and remove teacher records.

## Technology Stack

* **Core:** Java

## Getting Started

Follow these instructions to get a local copy up and running.

### Prerequisites

* Java Development Kit (JDK) [Specify version, e.g., 11 or 17+]

### Installation & Running

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/tempouser123/CRMS.git](https://github.com/tempouser123/CRMS.git)
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd CRMS
    ```
3.  **Compile the project:**
    *(This step depends on your setup. Here are some common examples.)*

    * If using only `javac`:
        ```bash
        # Navigate to the source directory
        cd src
        # Compile all .java files
        javac [path/to/your/main/file.java]
        ```
    * If using Maven:
        ```bash
        mvn clean install
        ```
    * If using Gradle:
        ```bash
        gradlew build
        ```

4.  **Run the application:**
    * If using only `java`:
        ```bash
        java [your.main.class.name]
        ```
    * If using Maven:
        ```bash
        mvn exec:java -Dexec.mainClass="[your.main.class.name]"
        ```
    * If using Gradle:
        ```bash
        gradlew run
        ```
