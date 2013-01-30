# net.loadbang.jython

This package is an implementation of Python for [MaxMSP][max], using
the Jython package under MXJ.

Jython currently implements a slightly old version of Python (2.5; the
C version is now at 2.6) so we use the name "jython" in the package to
differentiate it from native implementations.

(Although the version of Python implemented by Jython is not quite
up-to-date, Jython seems to be something of a standard as an embedded
scripting language.)

Unlike the C Python, Jython has full access to the standard Java libraries
and any third-party libraries in the classpath, and can interact with
the Java class heirarchy in various interesting ways. Jython also comes
with an extensive utility library written in Python itself.

For more information on Jython, see the [home page][jython].

The prebuilt JAR files are in the [distribution][dist], or the
library can be built from the enclosed sources using Maven. (For the
Maven build, clone and build [net.loadbang.scripting][scripting]
first, since our libraries are not yet in a central repository.)

See the README for [net.loadbang.lib][lib] for installation details.

## Jython 2.7

Following some prompting from [Nathanaël Lécaudé][nat],
I've done a quick test with an
alpha release of Jython 2.7 from [here][j27]. Everything seems to work fine.

2.7 isn't yet available from any Maven repositories, so it'll need to be
downloaded manually and installed using the installer above. There's no
standalone release, so Max's JAR search directories need to include both
`jython.jar` and `Lib` (which will be unpacked into `cachedir` on first
launch).

[j27]: http://sourceforge.net/projects/jython/files/jython-dev/2.7.0a2/
[nat]: http://studioimaginaire.com/en/author/nat/

## Issues

Some unit tests are currently failing, due perhaps to the thread-based
handling of some printing methods. Am investigating. The package seems
to be perfectly functional without the print redirection machinery.

We currently cannot differentiate Python's `None` value from the null value we
see for unbound variables, so `X = None` will have much the same effect
as `del X` when it comes to outputting `X`. (For what it's worth, the Groovy
implementation is similar.)

The package manager seems to print to `stderr` when it finds new JAR files,
so these appear as errors in the Max window. This is benign.

The thread reentrancy issue is fixed: every invocation into an interpreter
is done in its own (synchronised) thread.

## Documentation

The JavaDocs can be generated from Maven by

        mvn javadoc:javadoc

The documentation is written to `target/site/apidocs`.

## License

Distributed under the [GNU General Public License][gpl].

Copyright (C) 2012 Nick Rothwell.

[max]: http://cycling74.com/products/max/
[jython]: http://jython.org
[scripting]: https://github.com/cassiel/net.loadbang.scripting
[lib]: https://github.com/cassiel/net.loadbang.lib
[gpl]: http://www.gnu.org/copyleft/gpl.html
[dist]: http://cassiel.github.com/net.loadbang.jython/distribution.zip
