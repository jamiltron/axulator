Axulator
========

Axulator is a simple  tool to evaluate battles for the game Axis & Allies.  It helps players of the game evaluate the odds of winning a particular battle between forces by simulating the battle and providing basic statistics on the outcome.  It can also help a player figure out what types of units to buy/build by estimating the changes of winning defensive or offensive battles for territory.

Usage
-----

First, an attacking or defending force is represented as a vector of vectors.  For a force consisting of three infantry and seven armor units, we would have:

    [[3 :infantry] [7 :armor]]

A battle lineup consists of a hash of forces, defining which is the attacker and which is the defender, e.g.:

    { :attacker [[5 :infantry] [10 :armor] [1 :fighter]}
      :defender [[20 :infantry]] }

To determine the odds of each side winning, we call the win-percentages function, also passing in the die to use (see below) and the number of trials to run, e.g.:

    (win-percentages { :attacker [[5 :infantry] [10 :armor]]
                       :defender [[20 :infantry] [2 :armor]] }
                     d6
                     1000)

This runs 1000 trials of the battle using a six-sided die (standard) and gives us a hash with the expected winning percentage of both the attacker and defender, e.g.:

    {:defender 0.936, :attacker 0.064}

Some Improvements
-----------------

I can't think of any.  Okay, just kidding.

* Make a nice DSL for building units and forces, not vectors and hashes
* Create constructors and accessor functions (see: SICP) for units
* Send me your improvements via a pull request, or open an issue

License
-------

Copyright (C) 2011 Kevin W. Beam

Distributed under the Eclipse Public License, the same as Clojure.
