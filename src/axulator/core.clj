(ns axulator.core)

(def attack {:infantry 1,
             :armor 3,
             :fighter 3,
             :bomber 4,
             :submarine 2,
             :transport 0,
             :carrier 1,
             :antiaircraft 0,
             :battleship 4})

(def defend {:infantry 2,
             :armor 2,
             :fighter 4,
             :bomber 1,
             :submarine 2,
             :transport 1,
             :carrier 3,
             :antiaircraft 1,
             :battleship 4})

(def cost {:infantry 3,
           :armor 5,
           :fighter 12,
           :bomber 15,
           :submarine 8,
           :transport 8,
           :carrier 18,
           :antiaircraft 5,
           :battleship 24})

(def tables {:attack attack, :defend defend, :cost cost})

(defn- max-die-roll [mode unit]
  (unit (mode tables)))

(defn hits [mode num unit die]
  (let [max-roll (max-die-roll mode unit)]
    (count (filter (fn [x] (<= x max-roll)) (take num die)))))

(defn force-hits [mode force die]
  (reduce + 0 (map (fn [u] (hits mode (first u) (second u) die)) force)))

(defn reduce-unit [unit]
  (let [num (first unit)
        unit-type (second unit)]
    (if (> num 1)
      (vector (- num 1) unit-type)
      nil)))

(defn reduce-force-by-one [force]
  (cond (empty? force) []
        (= (first (first force)) 1) (vec (rest force))
        :else (let [new-unit (reduce-unit (first force))]
                   (cond (not (empty? (rest force)))
                         (cons new-unit (rest force))
                         :else
                         (vector new-unit)))))

(defn reduce-force [force n]
  (nth (iterate reduce-force-by-one force) n))

(defn battle-winner [battle-linup attacker-die defender-die]
  (let [attacker (:attacker battle-linup)
        defender (:defender battle-linup)
        attacker-hits (force-hits :attack attacker (attacker-die))
        defender-hits (force-hits :defend defender (defender-die))]
    (cond (empty? attacker) :defender
          (empty? defender) :attacker
          :else (battle-winner {:attacker (reduce-force attacker defender-hits)
                                :defender (reduce-force defender attacker-hits)}
                               attacker-die
                               defender-die))))

(defn wins [side battle-lineup die trials]
  (count (filter (fn [r] (= r side))
                 (repeatedly trials
                             (fn [] (battle-winner battle-lineup die die))))))

(defn outcomes [battle-lineup die trials]
  (let [attacker-wins (wins :attacker battle-lineup die trials)
        defender-wins (- trials attacker-wins)]
    (hash-map :attacker (float (/ attacker-wins trials))
              :defender (float (/ defender-wins trials)))))

(defn d6 []
  (repeatedly (fn [] (inc (rand-int 6)))))
