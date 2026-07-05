name=README.md

# MapsAPI RestAssured Framework

API automation framework for Maps API endpoints using Java, Maven and TestNG. This repository contains the test suites, request payloads and response validation artifacts used to validate Maps API behaviors.

---

## Table of contents

- [What this is](#what-this-is)
- [Stack](#stack)
- [Quick start](#quick-start)
- [Project layout](#project-layout)
- [How to run tests](#how-to-run-tests)
- [Adding tests](#adding-tests)
- [Resources & artifacts](#resources--artifacts)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License & contact](#license--contact)

---

## What this is
A lightweight, Maven-based test automation framework that uses Java + TestNG and RestAssured-style testing to exercise Maps API endpoints. It centralizes request bodies, expected response examples/schemas, and executable test suites.

### Stack
- Language(s): Java (test code) + JSON payloads + HTML test reports
- Build / runner: Maven
- Test framework: TestNG (testng.xml present)
- Notable artifacts: request payloads and response validation JSONs under src/main/resources

---

## Quick start

1. Clone the repo:
   git clone https://github.com/SatyaDevCheekatla/MapsAPI_RestAssuredFramework.git
2. Provide any required API credentials as environment variables (example below).
3. Run the test suite with Maven:
   mvn clean test -DapiKey="$API_KEY"

Notes:
- You can run the TestNG suite from your IDE using the included `testng.xml`.
- If your CI requires a different invocation, run Maven the same way (ensure JAVA_HOME and Maven are available).

---

## Project layout

Top-level files and directories
- .github/                CI/workflow glue (if present)
- .gitignore
- .idea/                  IDE metadata
- pom.xml                 Maven project configuration
- testng.xml              TestNG suite definition
- README.md
- reports/                Generated test reports
- target/                 Maven build output (generated)
- src/                    Source and test artifacts

Annotated src/ tree
---
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/d8ac2b31-f48f-499e-91af-6311ad6f9eb0" />
