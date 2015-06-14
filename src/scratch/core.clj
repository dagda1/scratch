(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn ascii [char]
  (int (.charAt (str char) 0)))

(defn process [text]
  (let [parts (split-at (int (Math/floor (/ (count text) 2))) text)
        left (first parts)
        right (if (> (count (last parts)) (count (first parts)))
                (rest (last parts))
                (last parts))]
    (reduce (fn [acc i]
              (let [a (ascii (nth left i))
                    b (ascii (nth (reverse right) i))]
                (if (> a b)
                  (+ acc (- a b))
                  (+ acc (- b a))))
              ) 0 (range (count left)))))

(defn print-result [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))

; (slurp "/Users/paulcowan/Downloads/input10.txt")
(let [input (slurp "/Users/paulcowan/Downloads/input10.txt")
      inputs (str/split-lines input)
      length (read-string (first inputs))
      texts (rest inputs)]
  (time (print-result (map process texts))))
