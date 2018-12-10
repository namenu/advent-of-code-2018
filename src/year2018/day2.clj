(ns year2018.day2)

(defn fingerprint [id]
  (let [occurs (into #{} (vals (frequencies id)))]
    [(if (occurs 2) 1 0)
     (if (occurs 3) 1 0)]))

(defn part1 [input]
  (reduce * (reduce #(map + %1 %2) (map fingerprint input))))


(defn diff [x y]
  (->> (map #(- (int %1) (int %2)) x y)
       (remove zero?)
       count))

(defn common-letters [x y]
  (apply str (map #(if (= %1 %2) %1 nil) x y)))

(defn part2 [input]
  (let [[x y] (first
                (for [i input
                      j input
                      :when (= (diff i j) 1)]
                  [i j]))]
    (common-letters x y)))


;; tests
(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-day2
  (let [input ["abcdef"
               "bababc"
               "abbcde"
               "abcccd"
               "aabcdd"
               "abcdee"
               "ababab"]]
    (is (= 12 (part1 input))))

  (let [input ["abcde"
               "fghij"
               "klmno"
               "pqrst"
               "fguij"
               "axcye"
               "wvxyz"]]
    (is (= "fgij" (part2 input)))))

(run-tests)