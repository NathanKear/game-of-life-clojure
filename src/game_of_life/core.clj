(ns game-of-life.core
  (:gen-class))

(def test-map [[1 0 1] [1 1 1] [1 0 0]])
(def cell-width 10)
(def grid-columns 20)
(def grid-rows 20)
(def neighbour-offsets
  (list [1 1] [1 0] [1 -1] [0 1] [0 -1] [-1 1] [-1 0] [-1 -1]))

(defn point-to-screen-rect [row column]
  [(* row cell-width) (* column cell-width) cell-width cell-width])

(defn add-offset [[row column]]
    (fn [[y-offset x-offset]] 
      [(+ row y-offset) (+ column x-offset)]))

(defn is-alive? [cell-map]
  (fn [[row column]]
    (= 1 
      (get-in cell-map [row column]))))

(defn count-cell-neighbours [cell-map [row column]]
  (count 
    (filter true? 
      (map 
        (is-alive? cell-map) 
          (map (add-offset [row column]) neighbour-offsets)))))

(defn in? [sequence item]
  (if (empty? sequence)
    false
    (reduce #(or %1 %2) (map #(= %1 item) sequence))))

(defn increment-cell-state [current-state living-neighbours]
    (if (= 1 current-state)
      (if (in? [2 3] living-neighbours) 1 0)
      (if (in? [3] living-neighbours) 1 0)))

(defn increment-cell-map [cell-map]
  (map-indexed 
    (fn [row-index cell-row] 
      (map-indexed 
        (fn [column-index cell-state]
          (let [cell-neighbours (count-cell-neighbours cell-map [row-index column-index])] 
            (increment-cell-state cell-state cell-neighbours))) 
      cell-row)) 
  cell-map))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))