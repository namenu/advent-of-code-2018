(ns aoc.year2018.day20
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [aoc.graph :refer [bfs]]))

(defn move [pos dir]
  (let [moves {\N [1 0]
               \S [-1 0]
               \W [0 -1]
               \E [0 1]}]
    (mapv + pos (moves dir))))

(defn follow [facility from route]
  (let [[f e] (reduce (fn [[facility from] dir]
                        [(update facility from (fnil conj #{}) dir)
                         (move from dir)])
                      [facility from]
                      route)]
    [f #{e}]))

(declare traverse)

(defn branch [facility from routes]
  (reduce (fn [[facility ends] route]
            (let [[f e] (traverse [facility [from]] route)]
              [f (into ends e)]))
          [facility #{}]
          routes))

(defn traverse [[facility endpoints] routes]
  (letfn [(traverse-1 [[facility endpoints] route]
            (reduce (fn [[facility ends] from]
                      (let [[f e] (if (coll? route)
                                    (branch facility from route)
                                    (follow facility from route))]
                        [f (into ends e)]))
                    [facility #{}]
                    endpoints))]
    (reduce traverse-1 [facility endpoints] routes)))

(defn regex->edn
  "Convert input string into edn data.

   route  -> vector
   branch -> list
   path   -> string"
  [regex]
  (-> regex
      (str/replace "^" "[\"")
      (str/replace "$" "\"]")
      (str/replace "(" "\"([\"")
      (str/replace ")" "\"])\"")
      (str/replace "|" "\"][\"")
      (str/replace "\"\"" "")
      (read-string)))

(defn neighboring [facility]
  (fn [pos]
    (map #(move pos %) (facility pos))))

(def regex "^ENWWW(NEEE|SSE(EE|N))$")
(def regex "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$")
(def regex "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$")
(def regex "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$")
(def regex (-> "year2018/day20.in" io/resource slurp str/trim))

(def facility
  (first (traverse [{} [[0 0]]] (regex->edn regex))))

; part 1
(->> (bfs [0 0] (neighboring facility))
     (apply max-key val)
     (second))

; part 2
(->> (bfs [0 0] (neighboring facility))
     (filter #(>= (val %) 1000))
     (count))
