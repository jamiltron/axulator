(ns axulator.core)

(def attack {:infantry 1, :armor 3, :fighter 3, :bomber 4, :submarine 2, :transport 0, :carrier 1, :antiaircraft 0, :battleship 4})

(def defend {:infantry 2, :armor 2, :fighter 4, :bomber 1, :submarine 2, :transport 1, :carrier 3, :antiaircraft 1, :battleship 4})

(def cost {:infantry 3, :armor 5, :fighter 12, :bomber 15, :submarine 8, :transport 8, :carrier 18, :antiaircraft 5, :battleship 24})

(def d6
  (repeatedly (fn [] (inc (rand-int 6)))))

(defn attacker-hits [num unit die]
  (count (filter (fn [x] (>= (unit attack) x)) (take num die))))

(defn defender-hits [num unit die]
  (count (filter (fn [x] (>= (unit defend) x)) (take num die))))




