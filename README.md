Exception ID (EID) Generator
============================

[![Build Status](https://travis-ci.org/wavesoftware/eid-generator.png)](https://travis-ci.org/wavesoftware/eid-generator)

A plugin for Netbeans 7.4

Generates a unique Exception ID to be use in Java source code.

To use this plugin type `Ctrl+Space` inside empty String literal - it will generate new unique identifier. It can be used to identify your technical exceptions.

Example:

```java
throw new EidRuntimeException("20140218:161429", ex);
```