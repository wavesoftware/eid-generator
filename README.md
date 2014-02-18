Exception ID (EID) Generator
============================

[![Build Status](https://travis-ci.org/wavesoftware/eid-generator.png)](https://travis-ci.org/wavesoftware/eid-generator)

A plugin for Netbeans 7.4

Usage
-----

Generates a unique Exception ID, that can be used in Java source code.

In order to use this plugin type `Ctrl+Space` inside empty String literal. This will generate new unique identifier as a Code Completion. That generated EID can be used, for example, to identify your exceptions.

Example
-------

This is technical runtime exception, a posible bug. EID Generator plugin can be used to quickly fill unique bug id.

```java
try {
    shuldWorkIfNoBugsExists();
} catch (IOException ex) {
    throw new EidRuntimeException("20140218:161429", "Something wrong with HDD, permissions?", ex);
}
```

To invoke EID code completion:
```java
throw new EidRuntimeException("<Ctrl+Space>"
```