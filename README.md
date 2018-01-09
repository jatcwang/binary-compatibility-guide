# Binary Compatibility Code Examples & Explanation

Complementary material to docs.scala.org's [Binary Compatibility Guide](https://docs.scala-lang.org/tutorials/binary-compatibility-for-library-authors.html).

The goal of this repo is to help library authors reduce the amount of binary incompatible releases they have to make. Here you'll find:

* The **Do**s and **Don't**s when writing your Scala library
* Examples of binary and source incompatible changes

More obvious incompatible changes such as removing a class/method won't be documented explicitly.

# How to run the examples

Ensure you have SBT installed and run the script

```
./publishlibs
```

It will publish all libraries locally for both Scala version 2.11 and 2.12.

After that, 

```
cd app
sbt +run
```

to execute the application under both 2.11 and 2.12 (since certain examples only apply in older scala versions)

## Understanding the examples

This repository sets contains 4 scala codebases simulating a real world relationship between different libraries.

First we have `App`, our application which depends on library `A` and `B`.

Both library `A` and `B` depends on another library `common`. However, library `A` depends on an older version of `common` (`common_old`), and thus during execution the older
version `common_old` depended on by `A` is evicted.

Here's a visualization of the dependency graph:

![Dependency Graph](https://raw.githubusercontent.com/jatcwang/binary-compatibility-guide/master/dependency_graph.png)

# Maintaing Compatibility - What to do and what not to do

## DO: Mark methods as package private when making breaking API changes

Marking methods as package private only breaks source compatibility, which means you can
break APIs safely without breaking binary compatibility.

Before:

```scala
package com.example.mypackage

class MyClass {
  def method(str: String)
}
```

After:
```scala
class MyClass {
  private[mypackage] def method(str: String)
}
```

## DONT: Adding parameters with default values to methods

**Scala version: all**  
**Incompatibility: binary**

Although it may not cause compilation errors, modifying the parameter list of a method will change its signature, resulting in `NoSuchMethodError` for code
compiled against an older version

## AVOID: Using Case Classes

**Scala version: all**  
**Incompatibility: Binary + Source**

Scala automatically generates a lot of methods for case classes (e.g. `equals`, `hashCode`). The signature of some generated methods like `unapply` depends
on the case class constructor parameters, and thus adding or removing parameters from the case class's primary constructor will break binary compatibility.

```scala
// Old
case class MyClass(first: String, second: Int)

// Old, generated interface in class file
class MyClass {
  // ... constructor and other methods

  def unapply(obj: MyClass): Option[(String, Int)]
}

// New
case class MyClass(first: String, second: Int, third: String = "default!")

// New, generated interface in class file
class MyClass {
  // ... constructor and other methods

  def unapply(obj: MyClass): Option[(String, Int, String)] // different method signature!
}
```

We recommend **NOT** to use case classes unless you

* are confident that the case class's fields will remain constant as libraries evolve
* really need methods such as `unapply` being generated for you

If you still want all the benefits of a [Contraband](https://github.com/sbt/contraband) is another alternative for generate your classes and is designed
to better facilitate library evolution without binary breakages. (It is used by SBT)

## DO: Annotate method return types explicitly

**Scala version: all**  
**Incompatibility: Binary + Source**

When the return type of a method is inferred by the compiler, modifying the content body sometimes changes the inferred return type and thus breaks
binary compatibility (different method signature). Therefore, we recommend annotating the return type of all methods explicitly.

An example from the Scala standard library:

In Scala 2.11 and previous versions, `Option#toRight`'s return type is inferred to be `Product with Serializable with Either[X, Int]`, however we were not able to correct it to `Either[X, Int]`
by explicit type annotation because it would break binary compatibility. The type annotation was added only in the intentionally binary incompatible 2.12 release.

## DONT: Adding methods with default implementation to traits (2.11 or before)

**Scala Version: 2.11 or before**  
**Incompatibility: Binary**

Before Java 8 (which 2.12 targets), interface methods cannot have default implementations.
In order to support default methods for traits, Scala 2.11 and before
automatically overrides the method for classes that extend the trait. However if no recompilation
of the library is performed with the updated trait (with the new defult method), no override method is generated
and an `AbstractMethodError` will be thrown when the default method is called.

See [this StackOverflow question](https://stackoverflow.com/questions/18366817/is-adding-a-trait-method-with-implementation-breaking-backward-compatibility)
for a more detailed explanation.

## DONT: Inlining from libraries

From Scala [2.12 release notes](http://www.scala-lang.org/news/2.12.0/)

> If you are building a library to publish on Maven Central, you should not inline code from dependencies. 
Users of your library might have different versions of those dependencies on the classpath, which breaks binary compatibility.

This means not using `-opt:l:inline-from` (or the deprecated option `-opt:l:classpath`) to inline from libraries.

