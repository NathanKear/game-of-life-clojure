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

(defn count-cell-neighbours [cell-map row column]
  (count 
    (filter true? 
      (map 
        (is-alive? cell-map) 
          (map (add-offset [row column]) neighbour-offsets)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))