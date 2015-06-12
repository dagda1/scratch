(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (vec (map read-string (str/split str #" ")))))

(defn process [coll]
  )

(defn print-result [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))

(let [input "3\n11 2 4\n4 5 6\n10 8 -12\n"
      inputs (str/split-lines input)
      rows (vec (map #(numberify %) (rest inputs)))
      ]
  (prn (process rows)))
