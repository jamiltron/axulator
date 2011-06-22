(ns axulator.test.core
  (:use [axulator.core])
  (:use [clojure.test]))

(deftest attack-tests
  (is (= 1 (:infantry attack)))
  (is (= 3 (:armor attack)))
  (is (= 3 (:fighter attack)))
  (is (= 4 (:bomber attack)))
  (is (= 2 (:submarine attack)))
  (is (= 0 (:transport attack)))
  (is (= 1 (:carrier attack)))
  (is (= 0 (:antiaircraft attack)))
  (is (= 4 (:battleship attack))))

(deftest defend-tests
  (is (= 2 (:infantry defend)))
  (is (= 2 (:armor defend)))
  (is (= 4 (:fighter defend)))
  (is (= 1 (:bomber defend)))
  (is (= 2 (:submarine defend)))
  (is (= 1 (:transport defend)))
  (is (= 3 (:carrier defend)))
  (is (= 1 (:antiaircraft defend)))
  (is (= 4 (:battleship defend))))

(deftest cost-tests
  (is (= 3 (:infantry cost)))
  (is (= 5 (:armor cost)))
  (is (= 12 (:fighter cost)))
  (is (= 15 (:bomber cost)))
  (is (= 8 (:submarine cost)))
  (is (= 8 (:transport cost)))
  (is (= 18 (:carrier cost)))
  (is (= 5 (:antiaircraft cost)))
  (is (= 24 (:battleship cost))))

(def all (range 1 6))

(deftest attacking
  (is (= 1 (attacker-hits 1 :infantry all)))
  (is (= 1 (attacker-hits 6 :infantry all)))
  (is (= 3 (attacker-hits 6 :armor all)))
  (is (= 4 (attacker-hits 6 :bomber all)))
  (is (= 2 (attacker-hits 6 :submarine all)))
  (is (= 0 (attacker-hits 6 :transport all)))
  (is (= 1 (attacker-hits 6 :carrier all)))
  (is (= 0 (attacker-hits 6 :antiaircraft all)))
  (is (= 4 (attacker-hits 6 :battleship all))))

(deftest defending
  (is (= 1 (defender-hits 1 :infantry all)))
  (is (= 2 (defender-hits 6 :infantry all)))
  (is (= 2 (defender-hits 6 :armor all)))
  (is (= 4 (defender-hits 6 :fighter all)))
  (is (= 1 (defender-hits 6 :bomber all)))
  (is (= 2 (defender-hits 6 :submarine all)))
  (is (= 1 (defender-hits 6 :transport all)))
  (is (= 3 (defender-hits 6 :carrier all)))
  (is (= 1 (defender-hits 6 :antiaircraft all)))
  (is (= 4 (defender-hits 6 :battleship all))))
