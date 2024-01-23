# Triggered Testing

[![license](https://img.shields.io/github/license/interlok-testing/testing_triggered.svg)](https://github.com/interlok-testing/testing_triggered/blob/develop/LICENSE)
[![Actions Status](https://github.com/interlok-testing/testing_triggered/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/interlok-testing/testing_triggered/actions/workflows/gradle-build.yml)

Project tests interlok-triggered features

## What it does

This project contains a single Interlok instance that allows you to trigger the start-up of the single channel via JMX.

We have a single channel, which upon Interlok startup is set to not start by default.
When this channel does start it will generate a one time message which will be produced to the local filesystem; of course this message could be used to trigger any other sort of process, such as sending a message to a queue/topic which in turn triggers behaviour in another instance of Interlok.

To trigger the start-up of this process we will need to start the channel which can be done in two ways;
 - Using the UI
 - Using JConsole

Shown below is an example of starting the channel using JConsole.

![jconsole diagram](/jconsole.png "jconsole diagram")

![triggered diagram](/triggered.png "triggered diagram")
 
## Getting started

* `./gradlew clean build`
* `(cd ./build/distribution && java -jar lib/interlok-boot.jar)`

