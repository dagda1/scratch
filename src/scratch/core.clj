(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (map read-string (str/split str #" "))))

(defn process [[cash amount wrappers]]
  (let [bought (int (Math/floor (/ cash amount)))
        free (int (Math/floor (/ bought wrappers)))
        remaining (rem bought wrappers)
        total (int (Math/floor (+ bought free)))
        next (int (Math/floor (/ (+ free remaining) wrappers)))]

    (prn "==========")
    (prn (str "bought " bought))
    (prn (str "free " (rem bought wrappers)))
    (prn (str "total " total))
    (prn (str "wrappers " wrappers))
    (prn (str "next " next))
    (prn "==========")
    (+ next total)))

(defn print-result [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))

(let [input "1\n43203 60 5"
      lines (str/split-lines input)
      length (read-string (first lines))
      inputs (map numberify (rest lines))]
  (print-result (map process inputs)))
; "1\n43203 60 5" => 899
