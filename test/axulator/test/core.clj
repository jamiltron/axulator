(ns axulator.test.core
  (:use [axulator.core])
  (:use [clojure.test]))

(deftest attack-tests
  (testing "infantry" (is (= 1 (:infantry attack))))
  (testing "tank" (is (= 3 (:tank attack))))
  (testing "fighter" (is (= 3 (:fighter attack))))
  (testing "bomber" (is (= 4 (:bomber attack))))
  (testing "submarine" (is (= 2 (:submarine attack))))
  (testing "transport" (is (= 0 (:transport attack))))
  (testing "carrier" (is (= 1 (:carrier attack))))
  (testing "aa-gun" (is (= 0 (:aa-gun attack)))))

(deftest defend-tests
  (testing "infantry" (is (= 2 (:infantry defend))))
  (testing "tank" (is (= 2 (:tank defend))))
  (testing "fighter" (is (= 4 (:fighter defend))))
  (testing "bomber" (is (= 1 (:bomber defend))))
  (testing "submarine" (is (= 2 (:submarine defend))))
  (testing "transport" (is (= 1 (:transport defend))))
  (testing "carrier" (is (= 3 (:carrier defend))))
  (testing "aa-gun" (is (= 1 (:aa-gun defend)))))

