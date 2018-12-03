(ns day1
  (:require [clojure.java.io :as io]))

(def input (->> "day1.in" io/resource io/reader line-seq))

(defn part1 [input]
  (reduce + (map read-string input)))

(defn part2 [input]
  (loop [freq (cycle (map read-string input))
         sum  0
         seen #{}]
    (let [f (+ sum (first freq))]
      (if (seen f)
        f
        (recur (next freq) f (conj seen f))))))