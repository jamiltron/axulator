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

(defn d6 []
  (repeatedly (fn [] (inc (rand-int 6)))))

(defn- max-die-roll [mode unit]
  (unit (mode tables)))

(defn hits [mode num unit die]
  (let [max-roll (max-die-roll mode unit)]
    (count (filter (fn [x] (<= x max-roll)) (take num die)))))

(defn force-hits [mode force die]
  (reduce + 0 (map (fn [u] (hits mode (first u) (second u) die)) force)))
