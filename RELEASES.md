`-*- mode: markdown; mode: visual-line; mode: adaptive-wrap-prefix; -*-`

## 1.4.1, 2015-05-31:

* Dependency version bumps: `net.loadbang.scripting` now `1.1.2`, `jython-standalone` now `2.7.0`.

## 1.4.0, 2013-08-13:

* Added an MXJ attribute `monothreaded` to suppress the new thread per call - probably not safe when a thread calls through multiple `mxj` instances.

## 1.3.0, 2013-01-30:

* Dependency on Jython bumped to 2.5.3. Version dependencies on other net.loadbang libraries bumped as well.

## 1.2.1, 2011-12-11:

* Ported VERSION properties, needed for greeting message.

## 1.2.0, 2011-12-08:

* Project converted to Maven and moved to GitHub.

* Dependency on Jython bumped to 2.5.2 - now depending on the standalone version, so no Python libraries have to be installed.

## 1.2, 2009-04-05:

* Built against Jython 2.5b3. This required some hacking to the output machinery in order to get messages flushed to the Max window (since 2.5b3 buffers differently to 2.2.1). There's a 250msec sweep on stderr, so error messages may appear to be a little sluggish.

## (1.1) 2008-07-17:

* Update of net.loadbang.lib to v1.4 - not significant for Jython, but needed in recent releases of shado.

## 1.1, 2008-03-15:

* Embarrassing synchronisation bug causing thread deadlock when a Jython interpreter instance threw an uncaught exception: now fixed.

## 1.0, 2008-03-06:

* Fixed thread reentrancy problem.

## 0.9b2, 2008-03-03:

* Cleaned up Javadocs; implemented thread reentrancy test as a stop-gap.

## 0.9b1, 2008-02-29:

* Initial beta release.
