# Triggered Testing

[![license](https://img.shields.io/github/license/interlok-testing/testing_triggered.svg)](https://github.com/interlok-testing/testing_triggered/blob/develop/LICENSE)
[![Actions Status](https://github.com/interlok-testing/testing_triggered/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/interlok-testing/testing_triggered/actions/workflows/gradle-build.yml)

Project tests interlok-triggered features

## What it does

This project contains a simple Interlok instance that allows you to trigger the start-up of the single workflow via JMX.

We have a single channel with a single workflow, which upon Interlok startup is set to not start by default.

When the trigger is received the workflow will start, the channel waits for the workflow to do its thing, and then stops it afterwards.

When this workflow does start it will generate a one time message which will be produced to the local filesystem; of course this message could be used to trigger any other sort of process, such as sending a message to a queue/topic which in turn triggers behaviour in another instance of Interlok.

To trigger the start-up of this process we will need to start the channel which can be done using JConsole.

Shown below is an example of triggering the workflow start-up using JConsole.

![jconsole diagram](/jconsole.png "jconsole diagram")

![triggered diagram](/triggered.png "triggered diagram")
 
## Getting started

* `./gradlew clean build`
* `(cd ./build/distribution && java -jar lib/interlok-boot.jar)`
