(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (map read-string (str/split str #" "))))

(defn process [curr prev next num]
  (cond
    (= curr num) (+ prev next)
    (< curr 2) (process (inc curr) prev next num)
    (= 2 curr) (process (inc curr) 0 1 num)
    :else (process (inc curr) next (+ prev next) num)))

(let [input "1 5"
      lines (str/split-lines input)
      lst (map read-string lines)]
  (process 0 0 0 (first lst)))
