(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (map read-string (str/split str #" "))))

(defn trade [acc bought wrappers]
  (let [traded (int (Math/floor (/ bought wrappers)))
        remainder (if (= 0 traded)
                    0
                    (- bought (* traded wrappers)))
        result (+ traded remainder)]
    (if (< result wrappers)
      (+ acc traded)
      (trade (+ acc traded) result wrappers))))

(defn process [[cash amount wrappers]]
  (let [bought (int (Math/floor (/ cash amount)))]
    (trade bought bought wrappers)))

(defn print-result [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))

(let [input "3\n10 2 5\n12 4 4\n6 2 2"
      lines (str/split-lines input)
      length (read-string (first lines))
      inputs (map numberify (rest lines))]
  (print-result (map process inputs)))
