(ns axulator.test.core
  (:use [axulator.core])
  (:use [clojure.test]))

(deftest attack-tests
  (testing "infantry" (is (= 1 (:infantry attack))))
  (testing "armor" (is (= 3 (:armor attack))))
  (testing "fighter" (is (= 3 (:fighter attack))))
  (testing "bomber" (is (= 4 (:bomber attack))))
  (testing "submarine" (is (= 2 (:submarine attack))))
  (testing "transport" (is (= 0 (:transport attack))))
  (testing "carrier" (is (= 1 (:carrier attack))))
  (testing "antiaircraft" (is (= 0 (:antiaircraft attack))))
  (testing "battleship" (is (= 4 (:battleship attack)))))

(deftest defend-tests
  (testing "infantry" (is (= 2 (:infantry defend))))
  (testing "armor" (is (= 2 (:armor defend))))
  (testing "fighter" (is (= 4 (:fighter defend))))
  (testing "bomber" (is (= 1 (:bomber defend))))
  (testing "submarine" (is (= 2 (:submarine defend))))
  (testing "transport" (is (= 1 (:transport defend))))
  (testing "carrier" (is (= 3 (:carrier defend))))
  (testing "antiaircraft" (is (= 1 (:antiaircraft defend))))
  (testing "battleship" (is (= 4 (:battleship defend)))))

(deftest cost-tests
  (testing "infantry" (is (= 3 (:infantry cost))))
  (testing "armor" (is (= 5 (:armor cost))))
  (testing "fighter" (is (= 12 (:fighter cost))))
  (testing "bomber" (is (= 15 (:bomber cost))))
  (testing "submarine" (is (= 8 (:submarine cost))))
  (testing "transport" (is (= 8 (:transport cost))))
  (testing "carrier" (is (= 18 (:carrier cost))))
  (testing "antiaircraft" (is (= 5 (:antiaircraft cost))))
  (testing "battleship" (is (= 24 (:battleship cost)))))
