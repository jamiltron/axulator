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
  (is (= 1 (hits :attack 1 :infantry all)))
  (is (= 1 (hits :attack 6 :infantry all)))
  (is (= 3 (hits :attack 6 :armor all)))
  (is (= 3 (hits :attack 6 :fighter all)))
  (is (= 4 (hits :attack 6 :bomber all)))
  (is (= 2 (hits :attack 6 :submarine all)))
  (is (= 0 (hits :attack 6 :transport all)))
  (is (= 1 (hits :attack 6 :carrier all)))
  (is (= 0 (hits :attack 6 :antiaircraft all)))
  (is (= 4 (hits :attack 6 :battleship all))))

(deftest defending
  (is (= 1 (hits :defend 1 :infantry all)))
  (is (= 2 (hits :defend 6 :infantry all)))
  (is (= 2 (hits :defend 6 :armor all)))
  (is (= 4 (hits :defend 6 :fighter all)))
  (is (= 1 (hits :defend 6 :bomber all)))
  (is (= 2 (hits :defend 6 :submarine all)))
  (is (= 1 (hits :defend 6 :transport all)))
  (is (= 3 (hits :defend 6 :carrier all)))
  (is (= 1 (hits :defend 6 :antiaircraft all)))
  (is (= 4 (hits :defend 6 :battleship all))))

(deftest force-attacking
  (is (= 1 (force-hits :attack [[1 :infantry]] all)))
  (is (= 4 (force-hits :attack [[6 :infantry] [6 :armor]] all)))
  (is (= 7 (force-hits :attack [[6 :infantry] [6 :armor] [6 :fighter]] all))))

(deftest force-defending
  (is (= 1 (force-hits :defend [[1 :infantry]] all)))
  (is (= 2 (force-hits :defend [[6 :infantry]] all)))
  (is (= 4 (force-hits :defend [[6 :infantry] [6 :armor]] all)))
  (is (= 9 (force-hits :defend [[6 :submarine] [6 :carrier] [6 :battleship]] all))))

(deftest unit-reduction
  (is (= nil (reduce-unit [1 :infantry])))
  (is (= [1 :infantry] (reduce-unit [2 :infantry])))
  (is (= [2 :infantry] (reduce-unit [3 :infantry]))))

(deftest force-reduction
  (is (= [[1 :infantry]] (reduce-force [[2 :infantry]] 1)))
  (is (= [] (reduce-force [[2 :infantry]] 2)))
  (is (= [] (reduce-force [[2 :infantry]] 1000)))
  (is (= [[1 :infantry] [4 :fighter]] (reduce-force [[2 :infantry] [4 :fighter]] 1)))
  (is (= [[4 :fighter]] (reduce-force [[2 :infantry] [4 :fighter]] 2)))
  (is (= [[3 :fighter]] (reduce-force [[2 :infantry] [4 :fighter]] 3)))
  (is (= [[1 :fighter]] (reduce-force [[2 :infantry] [4 :fighter]] 5)))
  (is (= [] (reduce-force [[2 :infantry] [4 :fighter]] 6)))
  (is (= [[10000 :bomber]]
         (reduce-force [[5 :infantry] [5 :fighter] [10 :battleship] [10000 :bomber]] 20)))
  (is (= [] (reduce-force [[1000 :infantry] [1000 :bomber] [1000 :fighter]] 10000))))

(deftest battle
  (is (= :attacker (battle-winner {:attacker [[1 :armor]], :defender [[1 :infantry]]} [1] [6])))
  (is (= :defender (battle-winner {:attacker [[1 :armor]], :defender [[1 :infantry]]} [6] [1])))
  (is (= :defender (battle-winner {:attacker [[1 :armor]], :defender [[1 :infantry]]} [1] [1])))
  (is (= :attacker (battle-winner {:attacker [[1 :armor]], :defender [[5 :infantry]]}
                                  [3 3 3 3 3]
                                  [6 6 6 6 6])))
  (is (= :defender (battle-winner {:attacker [[5 :armor]], :defender [[1 :infantry]]}
                                  [6 6 6 6 6]
                                  [1 1 1 1 1])))
  (is (= :attacker (battle-winner {:attacker [[1 :armor]], :defender [[1 :infantry] [1 :armor]]}
                                  [1 1]
                                  [6 6])))
  (is (= :defender (battle-winner {:attacker [[1 :infantry] [1 :armor]] :defender [[1 :armor]]}
                                  [6 6]
                                  [1 1]))))
