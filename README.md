# speclj-twmn

[![Build Status](https://secure.travis-ci.org/defhacks/speclj-twmn.png)](http://travis-ci.org/defhacks/speclj-twmn)

speclj-twmn is a plugin for [speclj](http://speclj.com/) that shows success and failure messages with [twmn](http://github.com/sboli/twmn).

## Installation

If you use [leiningen](https://github.com/technomancy/leiningen), add the following to your project.clj under the :dev profile:

    :dependencies [[speclj-twmn "0.0.1"]]

The original [speclj-growl](http://github.com/pgr0ss/speclj-growl) used internal resources for icons,
but twmnc needs file paths, so I cheaped out and just hardcoded some default gnome icons I liked.
Patches welcome :)

## Usage

Add `-f twmn` to lein spec to show output using twmn. For example, this will start autotest with both terminal and twmn output:

    lein spec -a -f twmn

This is short for:

    lein spec -r vigilant -f documentation -f twmn

## License

This is just some minor hacks to [speclj-growl](http://github.com/pgr0ss/speclj-growl).

Copyright (C) 2011 Paul Gross

Distributed under the The MIT License.
