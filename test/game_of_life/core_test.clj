(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest point-to-screen-rect-tests 
  (testing "point-to-screen-rect."
    (testing "Natural integer coordinates."
      (is (= [0 0 10 10] (point-to-screen-rect 0 0)))
      (is (= [20 30 10 10] (point-to-screen-rect 2 3))))
    (testing "Negative integer coordinates."
      (is (= [-50 -30 10 10] (point-to-screen-rect -5 -3)))
      (is (= [20 -30 10 10] (point-to-screen-rect 2 -3))))))

; (deftest increment-cell-state-tests
;   (testing "increment-cell-state."
;     (testing "Alive cell." 
;       (testing "Dies."
;         (is (= 0 (increment-cell-state 1 0)))
;         (is (= 0 (increment-cell-state 1 1)))
;         (is (= 0 (increment-cell-state 1 4)))
;         (is (= 0 (increment-cell-state 1 5)))
;         (is (= 0 (increment-cell-state 1 6)))
;         (is (= 0 (increment-cell-state 1 7)))
;         (is (= 0 (increment-cell-state 1 8))))
;       (testing "Remains alive."
;         (is (= 1 (increment-cell-state 1 2)))
;         (is (= 1 (increment-cell-state 1 3)))))
;     (testing "Dead cell."
;       (testing "Remains dead."
;         (is (= 0 (increment-cell-state 1 0)))
;         (is (= 0 (increment-cell-state 1 1)))
;         (is (= 0 (increment-cell-state 1 2)))
;         (is (= 0 (increment-cell-state 1 4)))
;         (is (= 0 (increment-cell-state 1 5)))
;         (is (= 0 (increment-cell-state 1 6)))
;         (is (= 0 (increment-cell-state 1 7)))
;         (is (= 0 (increment-cell-state 1 8))))
;       (testing "Comes alive."
;         (is (= 1 (increment-cell-state 1 3)))))))

(deftest is-alive?-tests
  (let [test-grid [[1 0 1] [1 1 1] [0 0 1]]]
    (testing "is-alive?"
      (testing "Coordinate on grid." 
        (is (= true ((is-alive? test-grid) [0 0])))
        (is (= true ((is-alive? test-grid) [1 2])))
        (is (= false ((is-alive? test-grid) [2 1])))
        (is (= false ((is-alive? test-grid) [0 1])))))))

(deftest add-offset-tests
  (testing "add-offset."
      (is (= [4 6] ((add-offset [1 2]) [3 4])))
      (is (= [0 0] ((add-offset [-3 2]) [3 -2])))))

(deftest count-cell-neighbours-tests
  (testing "count-cell-neighbours."
    (is (= 0 (count-cell-neighbours [[0 0 0] [0 1 0] [0 0 0]] 1 1)))
    (is (= 4 (count-cell-neighbours [[1 0 0] [0 1 1] [1 1 0]] 1 1)))
    (is (= 8 (count-cell-neighbours [[1 1 1] [1 1 1] [1 1 1]] 1 1)))
    (is (= 8 (count-cell-neighbours [[1 1 1] [1 0 1] [1 1 1]] 1 1)))
    (is (= 2 (count-cell-neighbours [[1 1 1] [1 0 1] [1 1 1]] 0 0)))
    (is (= 2 (count-cell-neighbours [[1 1 1] [1 0 1] [1 1 1]] 2 2)))))